package in.hocg.zeus.chaos.biz.service.impl;

import in.hocg.zeus.chaos.biz.pojo.ro.SendEmailCodeRo;
import in.hocg.zeus.chaos.biz.pojo.ro.SendSmsCodeRo;
import in.hocg.zeus.chaos.biz.service.ChaosService;
import in.hocg.zeus.chaos.biz.support.email.EmailService;
import in.hocg.zeus.chaos.biz.support.sms.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ChaosServiceImpl implements ChaosService {
    private final SmsService smsService;
    private final EmailService emailService;
    private final StringEncryptor stringEncryptor;

    @Override
    public void sendEmailCode(SendEmailCodeRo ro) {
        String email = ro.getEmail();
        emailService.sendVerifyCode(email);
    }

    @Override
    public void sendSmsCode(SendSmsCodeRo ro) {
        smsService.sendSmsCode(ro);
    }


    @Override
    public String encrypt(String data) {
        return stringEncryptor.encrypt(data);
    }
}
