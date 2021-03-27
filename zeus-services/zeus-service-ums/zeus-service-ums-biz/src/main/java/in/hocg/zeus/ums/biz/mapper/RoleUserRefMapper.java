package in.hocg.zeus.ums.biz.mapper;

import in.hocg.zeus.ums.biz.entity.Role;
import in.hocg.zeus.ums.biz.entity.RoleUserRef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [权限模块] 角色x账号表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Mapper
public interface RoleUserRefMapper extends BaseMapper<RoleUserRef> {

    List<Role> listByUserId(@Param("userId") Long userId);
}
