package in.hocg.zeus.ums.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.zeus.ums.biz.entity.Api;
import in.hocg.zeus.ums.biz.pojo.ro.ApiCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.ApiPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [权限模块] 接口表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Mapper
public interface ApiMapper extends BaseMapper<Api> {

    List<Api> listByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<Api> listByUserGroupIds(@Param("userGroupIds") List<Long> userGroupIds);

    IPage<Api> paging(@Param("ro") ApiPagingRo ro, @Param("page") Page page);

    IPage<Api> complete(@Param("ro") ApiCompleteRo ro, @Param("page") Page ofPage);

    List<Api> listByAuthorityId(@Param("authorityId") Long authorityId);
}
