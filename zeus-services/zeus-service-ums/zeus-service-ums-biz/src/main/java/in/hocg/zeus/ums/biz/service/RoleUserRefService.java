package in.hocg.zeus.ums.biz.service;

import in.hocg.zeus.ums.biz.entity.Role;
import in.hocg.zeus.ums.biz.entity.RoleUserRef;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 角色x账号表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface RoleUserRefService extends AbstractService<RoleUserRef> {

    boolean hasUserByRoleId(Long roleId);

    Integer countUseByRoleId(Long id);

    List<Role> listByUserId(Long userId);

    void assignRole(Long roleId, List<Long> assignUser, List<Long> clearUser);

    void grantRole(Long userId, List<Long> roles);
}
