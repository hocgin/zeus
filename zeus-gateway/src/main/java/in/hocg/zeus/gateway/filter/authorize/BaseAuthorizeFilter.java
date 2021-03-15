package in.hocg.zeus.gateway.filter.authorize;

import in.hocg.boot.utils.LangUtils;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/8/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class BaseAuthorizeFilter implements GlobalFilter, Ordered {
    private final PathMatcher matcher = new AntPathMatcher();
    private final AuthorizeProperties properties;

    public BaseAuthorizeFilter(AuthorizeProperties properties) {
        this.properties = properties;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }

    /**
     * IP 白名单
     *
     * @param request
     * @return
     */
    protected boolean isPermitAllWithIp(ServerHttpRequest request) {
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        assert remoteAddress != null;
        String ipAddress = remoteAddress.getAddress().getHostAddress();
        return properties.getIgnoreIps().parallelStream()
            .anyMatch(ipAddress::matches);
    }

    /**
     * URI 白名单
     *
     * @param request
     * @return
     */
    protected boolean isPermitAllWithUri(ServerHttpRequest request) {
        String path = request.getPath().toString();
        List<String> ignoreUrls = LangUtils.getOrDefault(properties.getIgnoreUrls(), Collections.emptyList());
        return ignoreUrls.parallelStream()
            .anyMatch(pattern -> matcher.match(pattern, path));
    }

    /**
     * 是否通过权限认证
     *
     * @param request
     * @param username
     * @return
     */
    protected boolean isPassAuthorize(ServerHttpRequest request, String username) {
        return true;
    }
}
