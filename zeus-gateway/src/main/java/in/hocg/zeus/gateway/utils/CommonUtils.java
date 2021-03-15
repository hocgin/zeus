package in.hocg.zeus.gateway.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

/**
 * Created by hocgin on 2021/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class CommonUtils {

    public boolean isAjaxRequest(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String accept = headers.getFirst("Accept");
        String requestedWith = headers.getFirst("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWith) ||
            accept != null && accept.contains("application/json");

    }
}
