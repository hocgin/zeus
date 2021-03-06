package in.hocg.zeus.chaos.biz.manager;

import com.alibaba.fastjson.JSON;
import in.hocg.zeus.chaos.biz.constant.RedisConstants;
import in.hocg.boot.utils.LangUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by hocgin on 2020/4/5.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RedisManager {
    private final StringRedisTemplate template;

    /**
     * 设置重置密码 Token
     *
     * @param email
     * @param token
     * @param expiredMinutes
     */
    public void setResetPasswordToken(@NonNull String email, @NonNull String token, long expiredMinutes) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        opsForValue.set(email, token, expiredMinutes, TimeUnit.MINUTES);
        log.debug("验证码设置[邮箱: {}, Token: {}]", email, token);
    }

    /**
     * 验证重置密码 Token
     *
     * @param email
     * @param validToken
     * @return
     */
    public boolean validResetPasswordToken(@NonNull String email, @NonNull String validToken) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        final String token = opsForValue.get(email);
        if (LangUtils.equals(token, validToken)) {
            template.delete(email);
            return true;
        }
        return false;
    }

    private String deserialize(Object object) {
        return JSON.toJSONString(object);
    }

    private <T> Optional<T> serialize(String text, Class<T> clazz) {
        if (Strings.isBlank(text)) {
            return Optional.empty();
        }
        return Optional.of(JSON.parseObject(text, clazz));
    }

    public void setUserId(String sessionId, Long userId) {
        final ValueOperations<String, String> opsForValue = template.opsForValue();
        opsForValue.set(sessionId, String.valueOf(userId), 5, TimeUnit.MINUTES);
    }

    public Optional<Long> getAndCleanUserId(String sessionId) {
        final ValueOperations<String, String> opsForValue = template.opsForValue();
        final String userId = opsForValue.get(sessionId);
        if (Strings.isNotBlank(userId)) {
            return Optional.of(Long.parseLong(userId));
        }
        return Optional.empty();
    }

    /**
     * 是否存在未过期的验证码
     *
     * @param phone
     * @return
     */
    public Boolean exitsVerifyCodeByPhone(@NonNull String phone) {
        final String smsKey = RedisConstants.getSmsKey(phone);
        return template.hasKey(smsKey);
    }

    /**
     * 验证短信验证码
     *
     * @param phone
     * @param smsCode
     * @return
     */
    public boolean validVerifyCodeByPhone(@NonNull String phone, @NonNull String smsCode) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        final String smsKey = RedisConstants.getSmsKey(phone);
        if (LangUtils.equals(opsForValue.get(smsKey), smsCode)) {
            template.delete(smsKey);
            return true;
        }
        return false;
    }

    /**
     * 设置短信验证码
     *
     * @param phone
     * @param smsCode
     */
    public void setVerifyCodeByPhone(@NonNull String phone, @NonNull String smsCode) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        final String smsKey = RedisConstants.getSmsKey(phone);
        opsForValue.set(smsKey, smsCode, 1, TimeUnit.MINUTES);
        log.debug("验证码设置[手机号码: {}, Token: {}]", phone, smsCode);
    }

    public boolean exitsVerifyCodeByEmail(String email) {
        return template.hasKey(RedisConstants.getEmailKey(email));
    }

    public boolean validVerifyCodeByEmail(String email, String verifyCode) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        final String key = RedisConstants.getEmailKey(email);
        if (LangUtils.equals(opsForValue.get(key), verifyCode)) {
            template.delete(key);
            return true;
        }
        return false;
    }

    public void setVerifyCodeByEmail(String email, String code) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        final String key = RedisConstants.getEmailKey(email);
        opsForValue.set(key, code, 1, TimeUnit.MINUTES);
        log.debug("验证码设置[邮箱号: {}, Token: {}]", email, code);
    }
}
