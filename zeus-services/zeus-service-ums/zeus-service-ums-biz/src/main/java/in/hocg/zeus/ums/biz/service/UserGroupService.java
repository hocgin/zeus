package in.hocg.zeus.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.entity.UserGroup;
import in.hocg.zeus.ums.biz.pojo.ro.AssignUserGroupRo;
import in.hocg.zeus.ums.biz.pojo.ro.SaveUserGroupRo;
import in.hocg.zeus.ums.biz.pojo.ro.UserGroupCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.UserGroupGrantAuthorityRo;
import in.hocg.zeus.ums.biz.pojo.ro.UserGroupPagingRo;
import in.hocg.zeus.ums.biz.pojo.vo.UserGroupComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 用户组表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface UserGroupService extends AbstractService<UserGroup> {

    List<UserGroup> listByUserId(Long userId);

    UserGroupComplexVo getComplex(Long userGroupId);

    void insertOne(SaveUserGroupRo ro);

    IPage<UserGroupComplexVo> paging(UserGroupPagingRo ro);

    void updateOne(Long userGroupId, SaveUserGroupRo ro);

    void deleteOne(Long userGroupId);

    void assignUserGroup(Long userGroupId, AssignUserGroupRo ro);

    List<UserGroupComplexVo> complete(UserGroupCompleteRo ro);

    void grantAuthority(Long userGroupId, UserGroupGrantAuthorityRo ro);

}
