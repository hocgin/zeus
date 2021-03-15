package in.hocg.zeus.chaos.biz.manager.sms;

import in.hocg.boot.sms.autoconfigure.impl.aliyun.SmsTemplate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2020/12/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum SmsTpl implements SmsTemplate {
    Zhifou("知否社区", "SMS_204297021");
    private final String signName;
    private final String templateCode;
}
