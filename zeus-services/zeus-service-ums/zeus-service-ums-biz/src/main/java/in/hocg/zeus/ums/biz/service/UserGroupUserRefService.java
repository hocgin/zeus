package in.hocg.zeus.ums.biz.service;

import in.hocg.zeus.ums.biz.entity.UserGroup;
import in.hocg.zeus.ums.biz.entity.UserGroupUserRef;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 用户组x用户表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface UserGroupUserRefService extends AbstractService<UserGroupUserRef> {

    List<UserGroup> listByUserId(Long userId);

    boolean hasUserByUserGroupId(Long userGroupId);

    void assignUserGroup(Long userGroupId, List<Long> assignUser, List<Long> clearUser);
}
