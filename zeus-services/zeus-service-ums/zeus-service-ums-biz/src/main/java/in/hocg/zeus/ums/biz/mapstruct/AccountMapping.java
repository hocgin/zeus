package in.hocg.zeus.ums.biz.mapstruct;

import in.hocg.zeus.ums.api.pojo.vo.AccountVo;
import in.hocg.zeus.ums.api.pojo.vo.UserDetailVo;
import in.hocg.zeus.ums.biz.entity.User;
import in.hocg.zeus.ums.biz.pojo.ro.UpdateAccountRo;
import in.hocg.zeus.ums.biz.pojo.vo.AccountComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AccountMapping {

    UserDetailVo asUserDetailVo(User account);

    @Mapping(target = "avatarUrl", source = "avatarUrl")
    AccountVo asAccountVo(User entity);

    @Mapping(target = "avatar", source = "avatarUrl")
    @Mapping(target = "social", ignore = true)
    @Mapping(target = "genderName", ignore = true)
    AccountComplexVo asComplex(User entity);

    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "expired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdIp", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User asAccount(UpdateAccountRo ro);
}
