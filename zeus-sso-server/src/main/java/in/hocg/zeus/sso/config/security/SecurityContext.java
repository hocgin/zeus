package in.hocg.zeus.sso.config.security;

import in.hocg.boot.web.SpringContext;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by hocgin on 2020/1/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@UtilityClass
public class SecurityContext {

    /**
     * 登录
     *
     * @param username 用户ID
     */
    public void signIn(String username) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, null));
    }

    /**
     * 获取授权方式
     *
     * @return
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 当前登录的用户
     *
     * @return
     */
    public Optional<String> getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(auth) || auth instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        if (auth instanceof UsernamePasswordAuthenticationToken) {
            Object principal = auth.getPrincipal();
            String username = null;
            if (principal instanceof User) {
                User user = (User) principal;
                username = user.getUsername();
            } else if (principal instanceof String) {
                username = (String) principal;
            }

            if (Strings.isNotBlank(username)) {
                return Optional.of(username);
            }
        }

        try {
            return Optional.of(((String) auth.getPrincipal()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * 当前登录的用户
     *
     * @return
     */
    public Optional<User> getCurrentUser() {
        final Optional<String> usernameOptional = getCurrentUsername();
        if (usernameOptional.isPresent()) {
            final String username = usernameOptional.get();
            final UserDetailsService userDetailsService = SpringContext.getBean(UserDetailsService.class);
            return Optional.of(((User) userDetailsService.loadUserByUsername(username)));
        }
        return Optional.empty();
    }

    /**
     * 注销当前账号
     */
    public void signOut() {
        SecurityContextHolder.clearContext();
    }
}
