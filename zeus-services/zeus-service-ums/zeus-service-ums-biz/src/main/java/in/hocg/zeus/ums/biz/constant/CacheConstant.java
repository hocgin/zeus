package in.hocg.zeus.ums.biz.constant;

import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class CacheConstant {
    public enum Prefix {
        Token
    }

    public String getTokenKey(String token) {
        return prefix(Prefix.Token, token);
    }

    private String prefix(Prefix prefix, String suffix) {
        return String.format("Ums::%s::%s", prefix.name(), suffix);
    }
}
