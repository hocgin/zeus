package in.hocg.zeus.ums.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import in.hocg.zeus.ums.biz.entity.Role;
import in.hocg.zeus.ums.biz.entity.RoleUserRef;
import in.hocg.zeus.ums.biz.mapper.RoleUserRefMapper;
import in.hocg.zeus.ums.biz.service.RoleService;
import in.hocg.zeus.ums.biz.service.RoleUserRefService;
import in.hocg.zeus.ums.biz.service.UserService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * <p>
 * [权限模块] 角色x账号表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RoleUserRefServiceImpl extends AbstractServiceImpl<RoleUserRefMapper, RoleUserRef> implements RoleUserRefService {
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public boolean hasUserByRoleId(Long roleId) {
        return has(RoleUserRef::getRoleId, roleId, RoleUserRef::getId);
    }

    @Override
    public Integer countUseByRoleId(Long roleId) {
        return lambdaQuery().eq(RoleUserRef::getRoleId, roleId).count();
    }

    @Override
    public List<Role> listByUserId(Long userId) {
        return baseMapper.listByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRole(Long roleId, List<Long> assignUser, List<Long> clearUser) {

        // 如果需要新增
        if (CollectionUtil.isNotEmpty(assignUser)) {
            List<RoleUserRef> existUser = lambdaQuery().eq(RoleUserRef::getRoleId, roleId)
                .in(RoleUserRef::getUserId, assignUser).list();
            List<Long> existUserId = existUser.parallelStream().map(RoleUserRef::getUserId).collect(Collectors.toList());
            List<Long> waitAddUserId = LangUtils.removeIfExits(assignUser, existUserId, (id1, id2) -> id1.compareTo(id2) == 0);

            if (CollectionUtil.isNotEmpty(waitAddUserId)) {
                List<RoleUserRef> waitAdd = waitAddUserId.parallelStream()
                    .map(uid -> new RoleUserRef().setUserId(uid).setRoleId(roleId)).collect(Collectors.toList());
                waitAdd.forEach(this::validInsertOrUpdate);
            }
        }

        if (CollectionUtil.isNotEmpty(clearUser)) {
            lambdaUpdate().eq(RoleUserRef::getRoleId, roleId)
                .in(RoleUserRef::getUserId, clearUser).remove();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantRole(Long userId, List<Long> roles) {
        List<RoleUserRef> entities = roles.parallelStream()
            .map(roleId -> new RoleUserRef().setUserId(userId).setRoleId(roleId))
            .collect(Collectors.toList());
        List<RoleUserRef> allData = this.listRoleUserByUserId(userId);

        final BiFunction<RoleUserRef, RoleUserRef, Boolean> isSame =
            (t1, t2) -> LangUtils.equals(t1.getRoleId(), t2.getRoleId());
        final List<RoleUserRef> mixedList = LangUtils.getMixed(allData, entities, isSame);
        List<RoleUserRef> deleteList = LangUtils.removeIfExits(allData, mixedList, isSame);
        List<RoleUserRef> addList = LangUtils.removeIfExits(entities, mixedList, isSame);

        // 删除
        this.removeByIds(deleteList.parallelStream()
            .map(RoleUserRef::getId)
            .collect(Collectors.toList()));

        // 新增
        addList.forEach(this::validInsertOrUpdate);

        // 更新
        mixedList.forEach(this::validInsertOrUpdate);
    }

    private List<RoleUserRef> listRoleUserByUserId(Long userId) {
        return lambdaQuery().eq(RoleUserRef::getUserId, userId).list();
    }
}
