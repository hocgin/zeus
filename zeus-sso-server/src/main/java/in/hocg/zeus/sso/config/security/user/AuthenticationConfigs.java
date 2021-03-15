package in.hocg.zeus.sso.config.security.user;

import in.hocg.zeus.sso.config.security.PageConstants;
import in.hocg.zeus.sso.config.security.social.CustomAuthenticationSuccessHandler;
import in.hocg.zeus.sso.config.security.social.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Created by hocgin on 2020/1/8.
 * email: hocgin@gmail.com
 * 配置
 *
 * @author hocgin
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuthenticationConfigs {
    private final CustomOAuth2UserService oAuth2UserService;
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    public void configure(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        final AuthorizedSuccessHandle successHandler = new AuthorizedSuccessHandle(PageConstants.INDEX_PAGE);
        final AuthorizedFailureHandle failureHandle = new AuthorizedFailureHandle(PageConstants.LOGIN_PAGE);

        // ==== OAuth2.0 ====
        http.oauth2Client();
        http.oauth2Login().loginPage(PageConstants.LOGIN_PAGE)
            .userInfoEndpoint().userService(oAuth2UserService).and()
            .successHandler(authenticationSuccessHandler);

        // ==== Form 表单 ====
        {
            http.formLogin().loginPage(PageConstants.LOGIN_PAGE)
                .successHandler(successHandler)
                .failureHandler(failureHandle)
                .permitAll();
        }

        // ==== 社交登陆 ====
    }

}
