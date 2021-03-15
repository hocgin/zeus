package in.hocg.zeus.chaos.biz.service;


import in.hocg.zeus.chaos.biz.pojo.ro.SendEmailCodeRo;
import in.hocg.zeus.chaos.biz.pojo.ro.SendSmsCodeRo;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ChaosService {

    void sendSmsCode(SendSmsCodeRo ro);

    void sendEmailCode(SendEmailCodeRo ro);

    String encrypt(String data);
}
