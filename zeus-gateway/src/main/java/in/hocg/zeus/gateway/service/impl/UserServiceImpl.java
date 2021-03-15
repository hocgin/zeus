package in.hocg.zeus.gateway.service.impl;

import in.hocg.zeus.gateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserServiceImpl implements UserService {

    @Override
    public boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri) {
        // 判定是否有权限
        return true;
    }
}
