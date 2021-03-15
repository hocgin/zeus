package in.hocg.zeus.chaos.api;

import in.hocg.boot.named.autoconfiguration.annotation.NamedService;
import in.hocg.boot.named.autoconfiguration.ifc.NamedArgs;
import in.hocg.boot.named.autoconfiguration.ifc.NamedHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME, contextId = ChaosNamedApi.CONTEXT_ID)
public interface ChaosNamedApi extends NamedService {
    String CONTEXT_ID = "ChaosNamedAPI";

    @NamedHandler(NamedType.DataDict)
    @PostMapping(CONTEXT_ID + "/loadByDataDict")
    Map<String, Object> loadByDataDict(@RequestBody NamedArgs args);
}
