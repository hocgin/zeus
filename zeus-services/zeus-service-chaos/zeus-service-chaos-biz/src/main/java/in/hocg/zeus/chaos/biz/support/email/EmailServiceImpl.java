package in.hocg.zeus.chaos.biz.support.email;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import in.hocg.zeus.chaos.biz.manager.EmailManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2021/1/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class EmailServiceImpl implements EmailService {
    private final EmailManager emailManager;

    @Override
    public boolean validVerifyCode(String email, String verifyCode) {
        return emailManager.validVerifyCode(email, verifyCode);
    }

    @Override
    public void sendVerifyCode(String email) {
        Assert.notBlank(email, "邮箱号不能为空");
        emailManager.sendVerifyCode(email, RandomUtil.randomNumbers(4));
    }
}
