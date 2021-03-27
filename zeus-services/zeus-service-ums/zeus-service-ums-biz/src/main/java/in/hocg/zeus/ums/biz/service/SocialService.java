package in.hocg.zeus.ums.biz.service;

import in.hocg.zeus.ums.api.pojo.ro.InsertSocialRo;
import in.hocg.zeus.ums.api.pojo.vo.UserDetailVo;
import in.hocg.zeus.ums.biz.entity.Social;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [用户模块] 绑定社交账号表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-30
 */
public interface SocialService extends AbstractService<Social> {

    Optional<UserDetailVo> getUserBySocialTypeAndSocialId(String socialType, String socialId);

    void insertOne(InsertSocialRo ro);

    List<Social> listSocialByUserId(Long entityId);
}
