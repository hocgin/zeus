package in.hocg.zeus.sso.service.impl;

import in.hocg.zeus.sso.pojo.ro.JoinRo;
import in.hocg.zeus.sso.pojo.ro.SendSmsCodeRo;
import in.hocg.zeus.sso.pojo.vo.WxLoginStatusVo;
import in.hocg.zeus.sso.pojo.vo.WxMpQrCodeVo;
import in.hocg.zeus.sso.service.SsoIndexService;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createAccount(JoinRo ro) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendSmsCode(SendSmsCodeRo ro) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WxMpQrCodeVo getWxQrCode(String appid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WxLoginStatusVo getWxLoginStatus(String idFlag, String redirectUrl) {
        throw new UnsupportedOperationException();
    }
}
