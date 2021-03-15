package in.hocg.zeus.gateway.service;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface UserService {
    boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri);
}
