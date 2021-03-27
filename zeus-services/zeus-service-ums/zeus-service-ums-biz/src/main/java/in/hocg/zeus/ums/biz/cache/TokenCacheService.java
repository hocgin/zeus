package in.hocg.zeus.ums.biz.cache;

/**
 * Created by hocgin on 2020/7/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface TokenCacheService {

    void setToken(String username, String token, long expireMillis);

    String getToken(String username);
}
