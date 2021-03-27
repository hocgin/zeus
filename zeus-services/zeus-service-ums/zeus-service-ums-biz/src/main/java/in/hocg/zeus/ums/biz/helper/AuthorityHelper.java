package in.hocg.zeus.ums.biz.helper;

import cn.hutool.core.util.StrUtil;
import in.hocg.zeus.ums.biz.entity.Api;
import lombok.experimental.UtilityClass;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class AuthorityHelper {
    private final PathMatcher matcher = new AntPathMatcher();

    public boolean isPassAuthority(String servicePrefixPath, String requestMethodStr, String requestUriPath, List<Api> apis) {
        return apis.parallelStream().anyMatch(api -> {
            String requestUri = api.getRequestUri();
            String requestMethod = api.getRequestMethod();
            String servicePrefix = api.getServicePrefix();

            boolean isPassRequestMethod = StrUtil.equals("*", requestMethod)
                || requestMethod.compareToIgnoreCase(requestMethodStr) >= 0;
            boolean isPassServicePrefix = StrUtil.equals("*", servicePrefix)
                || StrUtil.equals(servicePrefixPath, servicePrefix);
            boolean isPassRequestUri = matcher.match(requestUri, requestUriPath);
            return isPassRequestMethod
                && isPassServicePrefix
                && isPassRequestUri;
        });
    }
}
