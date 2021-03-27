package in.hocg.zeus.ums.biz.mapstruct;

import in.hocg.zeus.ums.biz.entity.UserGroup;
import in.hocg.zeus.ums.biz.pojo.ro.SaveUserGroupRo;
import in.hocg.zeus.ums.biz.pojo.vo.UserGroupComplexVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface UserGroupMapping {
    UserGroupComplexVo asComplex(UserGroup entity);

    UserGroup asUserGroup(SaveUserGroupRo ro);
}
