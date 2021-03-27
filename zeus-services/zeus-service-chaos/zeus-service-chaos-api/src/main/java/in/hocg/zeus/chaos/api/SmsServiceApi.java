package in.hocg.zeus.chaos.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = SmsServiceApi.CONTEXT_ID)
public interface SmsServiceApi {
    String CONTEXT_ID = "SmsServiceApi";

    /**
     * 发送验证码
     *
     * @param phone
     * @param smsCode
     * @return
     */
    @PostMapping(CONTEXT_ID + "/validVerifyCode")
    boolean validVerifyCode(@RequestParam("phone") String phone,
                            @RequestParam("smsCode") String smsCode);

    /**
     * 验证验证码
     *
     * @param phone
     */
    @PostMapping(CONTEXT_ID + "/sendVerifyCode")
    void sendVerifyCode(@RequestParam("phone") String phone);
}
