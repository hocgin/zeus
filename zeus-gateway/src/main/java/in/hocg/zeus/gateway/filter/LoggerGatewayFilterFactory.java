package in.hocg.zeus.gateway.filter;

import in.hocg.zeus.usercontext.basic.HeaderConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by hocgin on 2020/7/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
public class LoggerGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggerGatewayFilterFactory.Config> {
    private static final String MY_LOG_START_TIME = LoggerGatewayFilterFactory.class.getName() + "." + "startTime";

    public LoggerGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!config.getEnabled()) {
                return chain.filter(exchange);
            }
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String username = headers.getFirst(HeaderConstants.USERNAME);
            String source = headers.getFirst(HeaderConstants.SOURCE);
            String version = headers.getFirst(HeaderConstants.VERSION);

            exchange.getAttributes().put(MY_LOG_START_TIME, System.currentTimeMillis());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Long startTime = exchange.getAttribute(MY_LOG_START_TIME);
                if (null != startTime) {
                    ServerHttpRequest serverHttpRequest = exchange.getRequest();
                    String methodName = serverHttpRequest.getMethodValue();
                    String uri = serverHttpRequest.getURI().getRawPath();
                    MultiValueMap<String, String> queryParams = serverHttpRequest.getQueryParams();
                    String logStr = new StringJoiner(System.lineSeparator())
                        .add("")
                        .add("=====================================================")
                        .add(String.format("URI: %s %s", methodName, uri))
                        .add("RequestParams: " + queryParams)
                        .add("Username: " + username)
                        .add("Source: " + source)
                        .add("Version: " + version)
                        .add("TotalTime: " + (System.currentTimeMillis() - startTime) + "ms")
                        .add("=====================================================")
                        .toString();
                    log.info(logStr);
                }
            }));
        };
    }

    @Data
    public static class Config {
        private Boolean enabled = Boolean.TRUE;
    }
}
