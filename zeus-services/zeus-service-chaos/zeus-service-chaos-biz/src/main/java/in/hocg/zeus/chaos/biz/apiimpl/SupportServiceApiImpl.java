package in.hocg.zeus.chaos.biz.apiimpl;

import in.hocg.zeus.chaos.api.SupportServiceApi;
import in.hocg.zeus.chaos.api.pojo.vo.UserDetailVo;
import in.hocg.zeus.ums.api.UserServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
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
    private final UserServiceApi accountServiceApi;

    @Override
    public Optional<UserDetailVo> getUsername(String username) {
        in.hocg.zeus.ums.api.pojo.vo.UserDetailVo userDetail = accountServiceApi.getUserDetailVoByUsername(username);
        if (Objects.isNull(userDetail)) {
            return Optional.empty();
        }
        return Optional.ofNullable(new UserDetailVo()
            .setPassword(userDetail.getPassword())
            .setUsername(userDetail.getUsername())
            .setId(userDetail.getId()));
    }
}
