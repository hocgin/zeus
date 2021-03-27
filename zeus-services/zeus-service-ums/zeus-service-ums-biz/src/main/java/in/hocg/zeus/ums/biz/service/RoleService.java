package in.hocg.zeus.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.entity.Role;
import in.hocg.zeus.ums.biz.pojo.ro.AssignRoleRo;
import in.hocg.zeus.ums.biz.pojo.ro.GrantAuthorityRo;
import in.hocg.zeus.ums.biz.pojo.ro.RoleCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.RolePagingRo;
import in.hocg.zeus.ums.biz.pojo.ro.SaveRoleRo;
import in.hocg.zeus.ums.biz.pojo.vo.RoleComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.RoleOrdinaryVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 角色表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface RoleService extends AbstractService<Role> {

    RoleComplexVo getRole(Long roleId);

    void insertOne(SaveRoleRo ro);

    IPage<RoleComplexVo> paging(RolePagingRo ro);

    void updateOne(Long id, SaveRoleRo ro);

    void deleteOne(Long id);

    List<Role> listByUserId(Long userId);

    void assignRole(Long id, AssignRoleRo ro);

    List<RoleComplexVo> complete(RoleCompleteRo ro);

    void grantAuthority(Long roleId, GrantAuthorityRo ro);

    List<RoleOrdinaryVo> listOrdinaryByAuthorityId(Long authorityId);

    List<RoleOrdinaryVo> listOrdinaryByUserId(Long userId);
}
