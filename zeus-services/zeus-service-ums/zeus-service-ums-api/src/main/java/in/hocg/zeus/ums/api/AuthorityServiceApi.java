package in.hocg.zeus.ums.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hocgin on 2021/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = AuthorityServiceApi.CONTEXT_ID)
public interface AuthorityServiceApi {
    String CONTEXT_ID = "AuthorityServiceApi";

    @PostMapping(CONTEXT_ID + "/isPassAuthorize")
    boolean isPassAuthorize(@RequestParam("username") String username, @RequestParam("servicePrefix") String servicePrefix,
                            @RequestParam("methodName") String methodName, @RequestParam("uri") String uri);
}
