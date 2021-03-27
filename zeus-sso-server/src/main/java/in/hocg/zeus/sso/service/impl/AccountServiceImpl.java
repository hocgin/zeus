package in.hocg.zeus.sso.service.impl;

import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.validation.autoconfigure.core.ValidatorUtils;
import in.hocg.boot.web.exception.ServiceException;
import in.hocg.boot.web.servlet.SpringServletContext;
import in.hocg.zeus.chaos.api.SmsServiceApi;
import in.hocg.zeus.sso.mapstruct.AccountMapping;
import in.hocg.zeus.sso.pojo.ro.JoinAccountRo;
import in.hocg.zeus.sso.pojo.ro.LoginRo;
import in.hocg.zeus.sso.service.AccountService;
import in.hocg.zeus.ums.api.UserServiceApi;
import in.hocg.zeus.ums.api.pojo.ro.CreateAccountRo;
import in.hocg.zeus.ums.api.pojo.vo.UserDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
public class AccountServiceImpl implements AccountService {
    private final AccountMapping mapping;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SmsServiceApi smsApi;
    private final UserServiceApi accountServiceApi;

    @Override
    public String join(JoinAccountRo ro) {
        JoinAccountRo.Mode mode = ICode.ofThrow(ro.getMode(), JoinAccountRo.Mode.class);
        switch (mode) {
            case UsePhone: {
                ValidatorUtils.validThrow(ro, JoinAccountRo.PhoneModeGroup.class);
                return this.joinUsePhone(ro.getPhoneMode());
            }
            case UseUsername: {
                ValidatorUtils.validThrow(ro, JoinAccountRo.UsernameModeGroup.class);
                return this.joinUseUsername(ro.getUsernameMode());
            }
            case UseEmail:
                ValidatorUtils.validThrow(ro, JoinAccountRo.EmailModeGroup.class);
                return this.joinUseEmail(ro.getEmailMode());
            default:
                throw ServiceException.wrap("该注册方式暂不支持");
        }
    }

    private String joinUseEmail(JoinAccountRo.EmailMode ro) {
        String email = ro.getEmail();
        String verifyCode = ro.getVerifyCode();
        String password = ro.getPassword();

        // todo: 后续添加校验邮件验证码 verifyCode
        CreateAccountRo newRo = new CreateAccountRo()
            .setPassword(passwordEncoder.encode(password))
            .setCreatedIp(SpringServletContext.getClientIp().orElse(null))
            .setEmail(email);
        UserDetailVo userDetailVo = accountServiceApi.createAccount(newRo);
        return accountServiceApi.getUserToken(userDetailVo.getUsername());
    }

    private String joinUseUsername(JoinAccountRo.UsernameMode ro) {
        String username = ro.getUsername();
        String password = ro.getPassword();

        CreateAccountRo newRo = new CreateAccountRo()
            .setPassword(passwordEncoder.encode(password))
            .setCreatedIp(SpringServletContext.getClientIp().orElse(null))
            .setUsername(username);
        UserDetailVo userDetailVo = accountServiceApi.createAccount(newRo);
        return accountServiceApi.getUserToken(userDetailVo.getUsername());
    }

    private String joinUsePhone(JoinAccountRo.PhoneMode ro) {
        String phone = ro.getPhone();
        String verifyCode = ro.getVerifyCode();

        if (!smsApi.validVerifyCode(phone, verifyCode)) {
            throw ServiceException.wrap("验证码错误");
        }
        CreateAccountRo newRo = new CreateAccountRo()
            .setCreatedIp(SpringServletContext.getClientIp().orElse(null))
            .setPhone(phone);
        UserDetailVo userDetailVo = accountServiceApi.createAccount(newRo);
        return accountServiceApi.getUserToken(userDetailVo.getUsername());
    }

    @Override
    public String login(LoginRo ro) {
        LoginRo.Mode mode = ICode.ofThrow(ro.getMode(), LoginRo.Mode.class);
        switch (mode) {
            case UseSms: {
                ValidatorUtils.validThrow(ro, LoginRo.SmsModeGroup.class);
                return this.loginUseSms(ro.getSmsMode());
            }
            case UsePassword: {
                ValidatorUtils.validThrow(ro, LoginRo.PasswordModeGroup.class);
                return this.loginUsePassword(ro.getPasswordMode());
            }
            default:
                throw ServiceException.wrap("该登录方式暂不支持");
        }
    }

    private String loginUseSms(LoginRo.SmsMode ro) {
        String phone = ro.getPhone();
        String verifyCode = ro.getVerifyCode();
        if (!smsApi.validVerifyCode(phone, verifyCode)) {
            throw ServiceException.wrap("验证码错误");
        }
        UserDetailVo userDetail = accountServiceApi.getUserByPhone(phone);
        ValidUtils.notNull(userDetail, "手机号码错误");
        return accountServiceApi.getUserToken(userDetail.getUsername());
    }

    private String loginUsePassword(LoginRo.PasswordMode ro) {
        String username = ro.getUsername();
        String password = ro.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw ServiceException.wrap("用户名或密码错误");
        }
        return accountServiceApi.getUserToken(username);
    }

}
