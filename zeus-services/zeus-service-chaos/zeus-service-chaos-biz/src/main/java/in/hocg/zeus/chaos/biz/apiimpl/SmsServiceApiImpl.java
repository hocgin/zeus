package in.hocg.zeus.chaos.biz.apiimpl;

import in.hocg.zeus.chaos.api.SmsServiceApi;
import in.hocg.zeus.chaos.biz.pojo.ro.SendSmsCodeRo;
import in.hocg.zeus.chaos.biz.support.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class SmsServiceApiImpl implements SmsServiceApi {
    private final SmsService service;

    @Override
    public boolean validVerifyCode(String phone, String smsCode) {
        return service.validSmsCode(phone, smsCode);
    }

    @Override
    public void sendVerifyCode(String phone) {
        service.sendSmsCode(new SendSmsCodeRo()
            .setPhone(phone));
    }
}
