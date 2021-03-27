package in.hocg.zeus.ums.biz.apiimpl;

import in.hocg.zeus.ums.api.SocialServiceApi;
import in.hocg.zeus.ums.api.pojo.ro.InsertSocialRo;
import in.hocg.zeus.ums.api.pojo.vo.UserDetailVo;
import in.hocg.zeus.ums.biz.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class SocialServiceApiImpl implements SocialServiceApi {
    private final SocialService service;

    @Override
    public UserDetailVo getAccountBySocialTypeAndSocialId(String socialType, String socialId) {
        return service.getUserBySocialTypeAndSocialId(socialType, socialId).orElse(null);
    }

    @Override
    public void insertOne(InsertSocialRo ro) {
        service.insertOne(ro);
    }
}
