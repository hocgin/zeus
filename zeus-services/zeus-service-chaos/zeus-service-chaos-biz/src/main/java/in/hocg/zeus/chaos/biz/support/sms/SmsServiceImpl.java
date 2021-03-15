package in.hocg.zeus.chaos.biz.support.sms;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import in.hocg.zeus.chaos.biz.manager.SmsManager;
import in.hocg.zeus.chaos.biz.pojo.ro.SendSmsCodeRo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SmsServiceImpl implements SmsService {
    private final SmsManager smsManager;

    @Override
    public void sendSmsCode(SendSmsCodeRo qo) {
        final String phone = qo.getPhone();
        Assert.notBlank(phone, "手机号不能为空");
        smsManager.sendVerifyCode(phone, RandomUtil.randomNumbers(4));
    }

    @Override
    public boolean validSmsCode(String phone, String smsCode) {
        return smsManager.validVerifyCode(phone, smsCode);
    }
}
