package in.hocg.zeus.ums.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import in.hocg.zeus.ums.biz.entity.UserGroup;
import in.hocg.zeus.ums.biz.entity.UserGroupUserRef;
import in.hocg.zeus.ums.biz.mapper.UserGroupUserRefMapper;
import in.hocg.zeus.ums.biz.service.UserGroupUserRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * [权限模块] 用户组x用户表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserGroupUserRefServiceImpl extends AbstractServiceImpl<UserGroupUserRefMapper, UserGroupUserRef> implements UserGroupUserRefService {

    @Override
    public List<UserGroup> listByUserId(Long userId) {
        return baseMapper.listByUserId(userId);
    }

    @Override
    public boolean hasUserByUserGroupId(Long userGroupId) {
        return has(UserGroupUserRef::getUserGroupId, userGroupId, UserGroupUserRef::getId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignUserGroup(Long userGroupId, List<Long> assignUser, List<Long> clearUser) {
        // 如果需要新增
        if (CollectionUtil.isNotEmpty(assignUser)) {
            List<UserGroupUserRef> existUser = lambdaQuery().eq(UserGroupUserRef::getUserGroupId, userGroupId)
                .in(UserGroupUserRef::getUserId, assignUser).list();
            List<Long> existUserId = existUser.parallelStream().map(UserGroupUserRef::getUserId).collect(Collectors.toList());
            List<Long> waitAddUserId = LangUtils.removeIfExits(assignUser, existUserId, (id1, id2) -> id1.compareTo(id2) == 0);

            if (CollectionUtil.isNotEmpty(waitAddUserId)) {
                List<UserGroupUserRef> waitAdd = waitAddUserId.parallelStream()
                    .map(uid -> new UserGroupUserRef().setUserId(uid).setUserGroupId(userGroupId)).collect(Collectors.toList());
                waitAdd.forEach(this::validInsertOrUpdate);
            }
        }

        if (CollectionUtil.isNotEmpty(clearUser)) {
            lambdaUpdate().eq(UserGroupUserRef::getUserGroupId, userGroupId)
                .in(UserGroupUserRef::getUserId, clearUser).remove();
        }
    }
}
