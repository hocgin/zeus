package in.hocg.zeus.ums.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.datastruct.tree.Tree;
import in.hocg.zeus.common.utils.ZeusUtils;
import in.hocg.zeus.ums.biz.entity.Api;
import in.hocg.zeus.ums.biz.entity.Authority;
import in.hocg.zeus.ums.biz.entity.User;
import in.hocg.zeus.ums.biz.helper.AuthorityHelper;
import in.hocg.zeus.ums.biz.helper.MyBatisPlusHelper;
import in.hocg.zeus.ums.biz.mapper.AuthorityMapper;
import in.hocg.zeus.ums.biz.mapstruct.AuthorityMapping;
import in.hocg.zeus.ums.biz.pojo.ro.GetAuthorityUserPagingRo;
import in.hocg.zeus.ums.biz.pojo.ro.GrantRoleRo;
import in.hocg.zeus.ums.biz.pojo.ro.GrantUserGroupRo;
import in.hocg.zeus.ums.biz.pojo.ro.SaveAuthorityRo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityOrdinaryVo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import in.hocg.zeus.ums.biz.pojo.vo.UserRoleComplexVo;
import in.hocg.zeus.ums.biz.service.ApiService;
import in.hocg.zeus.ums.biz.service.AuthorityApiRefService;
import in.hocg.zeus.ums.biz.service.AuthorityService;
import in.hocg.zeus.ums.biz.service.RoleAuthorityRefService;
import in.hocg.zeus.ums.biz.service.RoleService;
import in.hocg.zeus.ums.biz.service.UserGroupAuthorityRefService;
import in.hocg.zeus.ums.biz.service.UserGroupService;
import in.hocg.zeus.ums.biz.service.UserGroupUserRefService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * <p>
 * [权限模块] 权限表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuthorityServiceImpl extends TreeServiceImpl<AuthorityMapper, Authority>
    implements AuthorityService {
    private final AuthorityMapping mapping;
    private final ApiService apiService;
    private final RoleService roleService;
    private final AuthorityApiRefService authorityApiRefService;
    private final RoleAuthorityRefService roleAuthorityRefService;
    private final UserGroupService userGroupService;
    private final UserGroupUserRefService userGroupUserRefService;
    private final UserGroupAuthorityRefService userGroupAuthorityRefService;

    @Override
    public AuthorityComplexVo getComplex(Long id) {
        return convertComplex(getById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(SaveAuthorityRo ro) {
        Long createUser = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Authority entity = mapping.asAuthority(ro);
        entity.setCreator(createUser);
        entity.setCreatedAt(now);
        validInsert(entity);

        Long id = entity.getId();
        List<Long> apis = ro.getApis();
        if (Objects.nonNull(apis)) {
            authorityApiRefService.grantApis(id, apis);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, SaveAuthorityRo ro) {
        Long createUser = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Authority update = mapping.asAuthority(ro);
        update.setId(id);
        update.setLastUpdater(createUser);
        update.setLastUpdatedAt(now);
        validUpdateById(update);

        List<Long> apis = ro.getApis();
        if (Objects.nonNull(apis)) {
            authorityApiRefService.grantApis(id, apis);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        ValidUtils.isFalse(userGroupAuthorityRefService.hasUserGroupByAuthorityId(id), "该权限有用户组正在使用");
        ValidUtils.isFalse(roleAuthorityRefService.hasRoleByAuthorityId(id), "该权限有角色正在使用");
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AuthorityTreeNodeVo> listAuthorityTree(Boolean enabled) {
        List<Authority> data = this.listAuthorityByEnabled(enabled);
        return Tree.getChild(null, LangUtils.toList(data, this::convertTreeNode));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<UserRoleComplexVo> pagingUserByAuthorityId(Long authorityId, GetAuthorityUserPagingRo ro) {
        IPage<User> result = baseMapper.pagingUserByAuthorityId(authorityId, ro, ro.ofPage());
        return result.convert(mapping::asUserRoleComplexVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generateSql() {
        String deleteAll = "delete from ams_authority where 1 = 1;";
        String insertSql = this.list().parallelStream()
            .map(MyBatisPlusHelper::getInsertSql)
            .reduce((s, s2) -> s + s2).orElse("");
        String checkRoleSql = "delete from ams_role_authority_ref where authority_id not in (select aa.id from ams_authority aa);";
        String checkUserGroupSql = "delete from ams_user_group_authority_ref where authority_id not in (select aa.id from ams_authority aa);";
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(deleteAll);
        stringJoiner.add(insertSql);
        stringJoiner.add(checkRoleSql);
        stringJoiner.add(checkUserGroupSql);
        return stringJoiner.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isPassAuthorize(String username, String servicePrefix, String methodName, String uri) {
        List<Api> apis = apiService.listByUsername(username);
        return AuthorityHelper.isPassAuthority(servicePrefix, methodName, uri, apis);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantRole(Long authorityId, GrantRoleRo ro) {
        List<Long> roles = ro.getRoles();
        roles.forEach(roleId -> roleAuthorityRefService.grantAuthority(roleId, authorityId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantUserGroup(Long authorityId, GrantUserGroupRo ro) {
        List<Long> userGroups = ro.getUserGroup();
        userGroups.forEach(userGroupId -> userGroupAuthorityRefService.grantAuthority(userGroupId, authorityId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AuthorityTreeNodeVo> listByProjectIdAndUserId(Long projectId, Long userId) {
        List<Authority> authorities = listAuthoritiesByProjectIdAndUserId(projectId, userId);
        return Tree.getChild(null, LangUtils.toList(authorities, this::convertTreeNode));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> listAuthorityCodeByProjectIdAndUserId(Long projectId, Long userId) {
        List<Authority> authorities = listAuthoritiesByProjectIdAndUserId(projectId, userId);
        return LangUtils.toList(authorities, Authority::getEncoding);
    }

    @Override
    public List<AuthorityOrdinaryVo> listOrdinaryByRoleId(Long roleId) {
        return LangUtils.toList(this.listByRoleId(roleId), this::convertOrdinary);
    }

    @Override
    public List<AuthorityOrdinaryVo> listOrdinaryByUserGroupId(Long userGroupId) {
        return LangUtils.toList(this.listByUserGroupId(userGroupId), this::convertOrdinary);
    }

    private List<Authority> listByUserGroupId(Long userGroupId) {
        return baseMapper.listByUserGroupId(userGroupId);
    }

    private List<Authority> listByRoleId(Long roleId) {
        return baseMapper.listByRoleId(roleId);
    }

    private AuthorityComplexVo convertComplex(Authority entity) {
        AuthorityComplexVo result = mapping.asComplex(entity);
        if (Objects.nonNull(result)) {
            Long authorityId = entity.getId();
            result.setApis(apiService.listOrdinaryByAuthorityId(authorityId));
            result.setRoles(roleService.listOrdinaryByAuthorityId(authorityId));
        }
        return result;
    }

    private AuthorityOrdinaryVo convertOrdinary(Authority entity) {
        return mapping.asOrdinary(entity);
    }

    private List<Authority> listAuthoritiesByProjectIdAndUserId(Long projectId, Long userId) {
        List<Authority> authorities;
        if (ZeusUtils.isSuperAdmin(userId)) {
            authorities = this.listByProjectId(projectId, true);
        } else {
            authorities = baseMapper.listByProjectIdAndUserId(projectId, userId);
        }
        return authorities;
    }

    private List<Authority> listByProjectId(Long projectId, Boolean enabled) {
        return lambdaQuery().eq(Objects.nonNull(projectId), Authority::getProjectId, projectId)
            .eq(Objects.nonNull(enabled), TreeEntity::getEnabled, enabled)
            .orderByAsc(Authority::getPriority).list();
    }

    private AuthorityTreeNodeVo convertTreeNode(Authority entity) {
        AuthorityTreeNodeVo result = new AuthorityTreeNodeVo();
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        result.setParentId(entity.getParentId());
        result.setAuthorityCode(entity.getEncoding());
        return result;
    }

    private List<Authority> listAuthorityByEnabled(Boolean enabled) {
        return lambdaQuery().eq(Objects.nonNull(enabled), Authority::getEnabled, enabled).list();
    }
}
