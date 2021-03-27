package in.hocg.zeus.ums.biz.mapstruct;

import in.hocg.zeus.ums.biz.entity.User;
import in.hocg.zeus.ums.biz.entity.Authority;
import in.hocg.zeus.ums.biz.pojo.ro.SaveAuthorityRo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityOrdinaryVo;
import in.hocg.zeus.ums.biz.pojo.vo.UserRoleComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AuthorityMapping {

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "treePath", ignore = true)
    @Mapping(target = "id", ignore = true)
    Authority asAuthority(SaveAuthorityRo ro);

    @Mapping(target = "userId", source = "id")
    UserRoleComplexVo asUserRoleComplexVo(User account);

    AuthorityComplexVo asComplex(Authority entity);

    AuthorityOrdinaryVo asOrdinary(Authority entity);
}
