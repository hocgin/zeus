package in.hocg.zeus.ums.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.entity.Role;
import in.hocg.zeus.ums.biz.mapper.RoleMapper;
import in.hocg.zeus.ums.biz.mapstruct.RoleMapping;
import in.hocg.zeus.ums.biz.pojo.ro.AssignRoleRo;
import in.hocg.zeus.ums.biz.pojo.ro.GrantAuthorityRo;
import in.hocg.zeus.ums.biz.pojo.ro.RoleCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.RolePagingRo;
import in.hocg.zeus.ums.biz.pojo.ro.SaveRoleRo;
import in.hocg.zeus.ums.biz.pojo.vo.RoleComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.RoleOrdinaryVo;
import in.hocg.zeus.ums.biz.service.AuthorityService;
import in.hocg.zeus.ums.biz.service.RoleAuthorityRefService;
import in.hocg.zeus.ums.biz.service.RoleService;
import in.hocg.zeus.ums.biz.service.RoleUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * [权限模块] 角色表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RoleServiceImpl extends AbstractServiceImpl<RoleMapper, Role>
    implements RoleService {
    private final RoleMapping mapping;
    private final RoleAuthorityRefService roleAuthorityRefService;
    private final AuthorityService authorityService;
    private final RoleUserRefService roleUserRefService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleComplexVo getRole(Long roleId) {
        return this.convertComplex(getById(roleId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(SaveRoleRo ro) {
        Long createUser = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Role entity = mapping.asRole(ro);

        entity.setCreator(createUser);
        entity.setCreatedAt(now);
        validInsert(entity);

        Long id = entity.getId();
        List<Long> authorities = ro.getAuthorities();
        if (Objects.nonNull(authorities)) {
            roleAuthorityRefService.grantAuthorities(id, authorities);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<RoleComplexVo> paging(RolePagingRo ro) {
        IPage<Role> result = baseMapper.paging(ro, ro.ofPage());
        return result.convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, SaveRoleRo ro) {
        Long createUser = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Role update = mapping.asRole(ro);
        update.setId(id);
        update.setLastUpdater(createUser);
        update.setLastUpdatedAt(now);
        validUpdateById(update);

        List<Long> authorities = ro.getAuthorities();
        if (Objects.nonNull(authorities)) {
            roleAuthorityRefService.grantAuthorities(id, authorities);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        ValidUtils.isFalse(roleUserRefService.hasUserByRoleId(id), "该角色有用户正在使用");
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> listByUserId(Long userId) {
        return roleUserRefService.listByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRole(Long id, AssignRoleRo ro) {
        Role role = this.getById(id);
        ValidUtils.notNull(role, "角色不存在");
        roleUserRefService.assignRole(id, ro.getAssignUser(), ro.getClearUser());
    }

    @Override
    public List<RoleOrdinaryVo> listOrdinaryByAuthorityId(Long authorityId) {
        return LangUtils.toList(this.listByAuthorityId(authorityId), this::convertOrdinary);
    }

    @Override
    public List<RoleOrdinaryVo> listOrdinaryByUserId(Long userId) {
        return LangUtils.toList(this.listByUserId(userId), this::convertOrdinary);
    }

    private List<Role> listByAuthorityId(Long authorityId) {
        return baseMapper.listByAuthorityId(authorityId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RoleComplexVo> complete(RoleCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage()).convert(this::convertComplex).getRecords();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantAuthority(Long roleId, GrantAuthorityRo ro) {
        final List<Long> authorities = ro.getAuthorities();
        roleAuthorityRefService.grantAuthorities(roleId, authorities);
    }

    private RoleOrdinaryVo convertOrdinary(Role entity) {
        return mapping.asOrdinary(entity);
    }

    private RoleComplexVo convertComplex(Role entity) {
        Long roleId = entity.getId();
        return mapping.asComplex(entity)
            .setAuthorities(authorityService.listOrdinaryByRoleId(roleId))
            .setUseUserCount(roleUserRefService.countUseByRoleId(roleId));
    }
}
