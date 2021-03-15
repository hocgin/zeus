package in.hocg.zeus.sso.service.impl;

import in.hocg.zeus.sso.service.SocialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SocialServiceImpl implements SocialService {

    @Override
    public void onAuthenticationSuccess(String socialType, String socialId, String username) {
        throw new UnsupportedOperationException();
//        SecurityContext.signIn(username);
    }

}
