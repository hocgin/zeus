package in.hocg.zeus.chaos.biz.support.sms;


import in.hocg.zeus.chaos.biz.pojo.ro.SendSmsCodeRo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SmsService {

    void sendSmsCode(SendSmsCodeRo qo);

    boolean validSmsCode(String phone, String smsCode);
}
