package in.hocg.zeus.ums.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.entity.Api;
import in.hocg.zeus.ums.biz.entity.Role;
import in.hocg.zeus.ums.biz.entity.User;
import in.hocg.zeus.ums.biz.entity.UserGroup;
import in.hocg.zeus.ums.biz.mapper.ApiMapper;
import in.hocg.zeus.ums.biz.mapstruct.ApiMapping;
import in.hocg.zeus.ums.biz.pojo.ro.ApiCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.ApiPagingRo;
import in.hocg.zeus.ums.biz.pojo.ro.ApiSaveRo;
import in.hocg.zeus.ums.biz.pojo.vo.ApiComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.ApiOrdinaryVo;
import in.hocg.zeus.ums.biz.service.ApiService;
import in.hocg.zeus.ums.biz.service.AuthorityApiRefService;
import in.hocg.zeus.ums.biz.service.RoleService;
import in.hocg.zeus.ums.biz.service.UserGroupService;
import in.hocg.zeus.ums.biz.service.UserService;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * [权限模块] 接口表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ApiServiceImpl extends AbstractServiceImpl<ApiMapper, Api>
    implements ApiService {
    private final ApiMapping mapping;
    private final UserService accountService;
    private final RoleService roleService;
    private final AuthorityApiRefService authorityApiRefService;
    private final UserGroupService userGroupService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(ApiSaveRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Api entity = mapping.asApi(ro);
        entity.setCreatedAt(now);
        entity.setCreator(userId);
        validInsert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, ApiSaveRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Api update = mapping.asApi(ro);
        update.setId(id);
        update.setLastUpdater(userId);
        update.setLastUpdatedAt(now);
        validUpdateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(Long id) {
        Api api = getById(id);
        ValidUtils.isFalse(api.getIsPersist(), "该接口为保留接口");
        removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Api> listByUsername(String username) {
        Optional<User> userOpt = accountService.getByUsername(username);
        if (!userOpt.isPresent()) {
            return Collections.emptyList();
        }
        Long userId = userOpt.get().getId();
        List<Role> roles = roleService.listByUserId(userId);
        List<UserGroup> userGroups = userGroupService.listByUserId(userId);

        List<Api> roleApis = baseMapper.listByRoleIds(LangUtils.toList(roles, Role::getId));
        List<Api> userGroupApis = baseMapper.listByUserGroupIds(LangUtils.toList(userGroups, UserGroup::getId));

        List<Api> result = Lists.newArrayList();
        result.addAll(roleApis);
        result.addAll(userGroupApis);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<ApiComplexVo> paging(ApiPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ApiComplexVo> complete(ApiCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage())
            .convert(this::convertComplex).getRecords();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiComplexVo getComplex(Long id) {
        return this.convertComplex(getById(id));
    }

    @Override
    public List<ApiOrdinaryVo> listOrdinaryByAuthorityId(Long authorityId) {
        return LangUtils.toList(listByAuthorityId(authorityId), this::convertOrdinary);
    }

    private List<Api> listByAuthorityId(Long authorityId) {
        return baseMapper.listByAuthorityId(authorityId);
    }

    private ApiComplexVo convertComplex(Api entity) {
        return mapping.asComplex(entity);
    }

    private ApiComplexVo convertOrdinary(Api entity) {
        return mapping.asComplex(entity);
    }

    private boolean hasApiTitle(String title, Long... ignoreId) {
        return has(Api::getTitle, title, Api::getId, ignoreId);
    }

    private boolean hasApiEncoding(String encoding, Long... ignoreId) {
        return has(Api::getEncoding, encoding, Api::getId, ignoreId);
    }

    @Override
    public void validEntity(Api entity) {
        Long id = entity.getId();
        String title = entity.getTitle();
        String encoding = entity.getEncoding();
        if (Objects.nonNull(title)) {
            ValidUtils.isFalse(hasApiTitle(title, id), "接口名称已存在");
        }

        if (Objects.nonNull(encoding)) {
            ValidUtils.isFalse(hasApiEncoding(encoding, id), "接口编码已存在");
        }
    }
}
