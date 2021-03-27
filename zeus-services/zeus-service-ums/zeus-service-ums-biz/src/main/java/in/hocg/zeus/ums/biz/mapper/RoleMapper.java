package in.hocg.zeus.ums.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.zeus.ums.biz.entity.Role;
import in.hocg.zeus.ums.biz.pojo.ro.RoleCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.RolePagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [权限模块] 角色表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    IPage<Role> paging(@Param("ro") RolePagingRo ro, @Param("ofPage") Page<Object> ofPage);

    IPage<Role> complete(@Param("ro") RoleCompleteRo ro, @Param("page") Page page);

    List<Role> listByAuthorityId(@Param("authorityId") Long authorityId);
}
