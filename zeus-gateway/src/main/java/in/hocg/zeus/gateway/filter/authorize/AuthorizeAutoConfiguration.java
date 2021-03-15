package in.hocg.zeus.gateway.filter.authorize;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Configuration
@ConditionalOnProperty(prefix = AuthorizeProperties.PREFIX, name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(AuthorizeProperties.class)
public class AuthorizeAutoConfiguration {
}
