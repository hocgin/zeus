package in.hocg.zeus.sso.mapstruct;

import in.hocg.zeus.sso.pojo.ro.JoinRo;
import in.hocg.zeus.ums.api.pojo.ro.CreateAccountRo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AccountMapping {
    @Mapping(target = "socials", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "createdIp", ignore = true)
    CreateAccountRo asCreateAccountRo(JoinRo ro);
}
