package in.hocg.zeus.gateway.filter.authorize;

import in.hocg.zeus.common.utils.ZeusUtils;
import in.hocg.zeus.gateway.service.UserService;
import in.hocg.zeus.gateway.utils.ResultUtils;
import in.hocg.zeus.usercontext.basic.HeaderConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Created by hocgin on 2021/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
public class AuthorizeFilter extends BaseAuthorizeFilter {
    private final UserService userService;

    public AuthorizeFilter(AuthorizeProperties properties, UserService userService) {
        super(properties);
        this.userService = userService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();

        // 如果不需要认证
        if (isPermitAllWithIp(request) || isPermitAllWithUri(request)) {
            log.debug("无需认证, 访问URL=[{}]", uri);
            return chain.filter(exchange);
        }

        String username = request.getHeaders().getFirst(HeaderConstants.USERNAME);

        if (Strings.isBlank(username)) {
            log.warn("未登陆, 访问URL=[{}]", uri);
            return ResultUtils.notLogin(exchange);
        }

        if (!isPassAuthorize(request, username) && !ZeusUtils.isSuperAdmin(username)) {
            log.warn("username:[{}], 访问URL=[{}]", username, uri);
            return ResultUtils.accessDenied(exchange);
        }

        return chain.filter(exchange);
    }

    @Override
    protected boolean isPassAuthorize(ServerHttpRequest request, String username) {
        PathContainer pathContainer = request.getPath().pathWithinApplication();

        String servicePrefixPath = pathContainer.subPath(0, 2).toString();
        String requestUriPath = pathContainer.subPath(2).toString();
        String requestMethodName = request.getMethodValue();
        return userService.isPassAuthorize(username, servicePrefixPath, requestMethodName, requestUriPath);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 3;
    }
}
