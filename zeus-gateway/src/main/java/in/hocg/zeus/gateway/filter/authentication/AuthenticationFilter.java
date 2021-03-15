package in.hocg.zeus.gateway.filter.authentication;

import in.hocg.zeus.usercontext.basic.HeaderConstants;
import in.hocg.boot.sso.client.autoconfigure.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 * Created by hocgin on 2020/9/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return exchange.getPrincipal().map(Principal::getName).defaultIfEmpty("")
            .map(username -> {
                if (!isAnonymous(username)) {
                    exchange.getRequest().mutate().header(HeaderConstants.USERNAME, username).build();
                }
                return exchange;
            }).flatMap(chain::filter);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private boolean isAnonymous(String username) {
        return StringUtils.isEmpty(username) || TokenUtils.ANONYMOUS_AUTHENTICATION_TOKEN.getPrincipal().equals(username);
    }
}
