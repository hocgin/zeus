package in.hocg.zeus.ums.biz.cache;

import in.hocg.zeus.ums.biz.constant.CacheConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by hocgin on 2020/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UmsCacheService implements TokenCacheService {
    private final StringRedisTemplate template;

    @Override
    public void setToken(String username, String token, long expireMillis) {
        final String tokenKey = CacheConstant.getTokenKey(username);
        ValueOperations<String, String> opsForValue = template.opsForValue();
        opsForValue.set(tokenKey, token, expireMillis, TimeUnit.MINUTES);
        log.debug("设置用户Token[Username: {}, Token: {}]", username, token);
    }

    @Override
    public String getToken(String username) {
        final String tokenKey = CacheConstant.getTokenKey(username);
        ValueOperations<String, String> opsForValue = template.opsForValue();
        return opsForValue.get(tokenKey);
    }
}
