package in.hocg.zeus.chaos.biz.apiimpl;

import in.hocg.zeus.chaos.api.SupportServiceApi;
import in.hocg.zeus.chaos.api.pojo.vo.UserDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by hocgin on 2021/3/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class SupportServiceApiImpl implements SupportServiceApi {

    @Override
    public Optional<UserDetailVo> getUsername(String username) {
        throw new UnsupportedOperationException();
    }
}
