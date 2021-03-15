package in.hocg.zeus.gateway.filter.authentication;

import in.hocg.zeus.common.utils.JwtUtils;
import in.hocg.boot.sso.client.autoconfigure.core.BearerTokenAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
public class AuthenticationTokenFilter implements BearerTokenAuthentication {

    @Override
    public Authentication authentication(String token) {
        String username;
        try {
            username = JwtUtils.decode(token);
        } catch (Exception e) {
            log.warn("JWT解码token=[{}]失败: ", token, e);
            return new UsernamePasswordAuthenticationToken("", null, Collections.emptyList());
        }
        return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
    }
}
