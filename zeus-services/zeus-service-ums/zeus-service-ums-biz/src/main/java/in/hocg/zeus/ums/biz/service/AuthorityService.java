package in.hocg.zeus.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.entity.Authority;
import in.hocg.zeus.ums.biz.pojo.ro.GetAuthorityUserPagingRo;
import in.hocg.zeus.ums.biz.pojo.ro.GrantRoleRo;
import in.hocg.zeus.ums.biz.pojo.ro.GrantUserGroupRo;
import in.hocg.zeus.ums.biz.pojo.ro.SaveAuthorityRo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityOrdinaryVo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import in.hocg.zeus.ums.biz.pojo.vo.UserRoleComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 权限表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface AuthorityService extends AbstractService<Authority> {

    AuthorityComplexVo getComplex(Long id);

    void insertOne(SaveAuthorityRo ro);

    void updateOne(Long id, SaveAuthorityRo ro);

    void deleteOne(Long id);

    List<AuthorityTreeNodeVo> listAuthorityTree(Boolean enabled);

    IPage<UserRoleComplexVo> pagingUserByAuthorityId(Long id, GetAuthorityUserPagingRo ro);

    String generateSql();

    boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri);

    void grantRole(Long authorityId, GrantRoleRo ro);

    void grantUserGroup(Long authorityId, GrantUserGroupRo ro);

    List<AuthorityTreeNodeVo> listByProjectIdAndUserId(Long projectId, Long userId);

    List<String> listAuthorityCodeByProjectIdAndUserId(Long projectId, Long userId);

    List<AuthorityOrdinaryVo> listOrdinaryByRoleId(Long roleId);

    List<AuthorityOrdinaryVo> listOrdinaryByUserGroupId(Long userGroupId);
}
