package in.hocg.zeus.gateway.filter.authorize;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ToString
@ConfigurationProperties(prefix = AuthorizeProperties.PREFIX)
public class AuthorizeProperties {
    public static final String PREFIX = "zeus.gateway.authorize";
    /**
     * 忽略的IP列表
     */
    private List<String> ignoreIps = Collections.emptyList();

    /**
     * 忽略的URI列表
     */
    private List<String> ignoreUrls = Collections.emptyList();
}
