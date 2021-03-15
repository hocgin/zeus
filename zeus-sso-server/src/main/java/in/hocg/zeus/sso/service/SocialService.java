package in.hocg.zeus.sso.service;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SocialService {

    void onAuthenticationSuccess(String socialType, String socialId, String username);
}
