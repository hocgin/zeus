package in.hocg.zeus.chaos.api;

import in.hocg.zeus.chaos.api.pojo.vo.UserDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Created by hocgin on 2021/3/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = SupportServiceApi.CONTEXT_ID)
public interface SupportServiceApi {
    String CONTEXT_ID = "SupportNamedAPI";

    @GetMapping(CONTEXT_ID + "/getUsername")
    Optional<UserDetailVo> getUsername(@RequestParam("username") String username);
}
