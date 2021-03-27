package in.hocg.zeus.sso.service.impl;

import in.hocg.boot.web.servlet.SpringServletContext;
import in.hocg.zeus.chaos.api.SmsServiceApi;
import in.hocg.zeus.sso.mapstruct.AccountMapping;
import in.hocg.zeus.sso.pojo.ro.JoinRo;
import in.hocg.zeus.sso.pojo.ro.SendSmsCodeRo;
import in.hocg.zeus.sso.pojo.vo.WxLoginStatusVo;
import in.hocg.zeus.sso.pojo.vo.WxMpQrCodeVo;
import in.hocg.zeus.sso.service.SocialService;
import in.hocg.zeus.sso.service.SsoIndexService;
import in.hocg.zeus.ums.api.UserServiceApi;
import in.hocg.zeus.ums.api.pojo.ro.CreateAccountRo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SsoIndexServiceImpl implements SsoIndexService {
    private final UserServiceApi userServiceApi;
    private final SmsServiceApi smsServiceApi;
    private final AccountMapping mapping;
    private final PasswordEncoder passwordEncoder;
    private final SocialService socialService;

    @Override
    public void createAccount(JoinRo ro) {
        ro.setPassword(passwordEncoder.encode(ro.getPassword()));
        CreateAccountRo createAccountRo = mapping.asCreateAccountRo(ro);
        createAccountRo.setCreatedIp(SpringServletContext.getClientIp().orElse(null));
        userServiceApi.createAccount(createAccountRo);
    }

    @Override
    public void sendSmsCode(SendSmsCodeRo ro) {
        smsServiceApi.sendVerifyCode(ro.getPhone());
    }

    @Override
    public WxMpQrCodeVo getWxQrCode(String appid) {
//        return wxApi.getQrCode(appid);
        throw new UnsupportedOperationException();
    }

    @Override
    public WxLoginStatusVo getWxLoginStatus(String idFlag, String redirectUrl) {
        throw new UnsupportedOperationException();
    }
}
