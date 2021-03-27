package in.hocg.zeus.ums.biz.apiimpl;

import in.hocg.zeus.ums.api.AuthorityServiceApi;
import in.hocg.zeus.ums.biz.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class AuthorityServiceApiImpl implements AuthorityServiceApi {
    private final AuthorityService service;

    @Override
    public boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri) {
        return service.isPassAuthorize(username, servicePrefix, methodName, uri);
    }
}
