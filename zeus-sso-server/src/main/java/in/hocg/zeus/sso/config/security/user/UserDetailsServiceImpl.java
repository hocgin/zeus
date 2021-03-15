package in.hocg.zeus.sso.config.security.user;

import in.hocg.zeus.chaos.api.SupportServiceApi;
import in.hocg.zeus.chaos.api.pojo.vo.UserDetailVo;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by hocgin on 2020/1/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private final SupportServiceApi supportServiceApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailVo user = supportServiceApi.getUsername(username)
            .orElseThrow(() -> ServiceException.wrap("账号或密码错误"));
        return new User(username, user.getPassword(), Collections.emptyList());
    }

}
