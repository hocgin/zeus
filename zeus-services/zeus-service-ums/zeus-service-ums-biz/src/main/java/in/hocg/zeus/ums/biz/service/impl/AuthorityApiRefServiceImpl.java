package in.hocg.zeus.ums.biz.service.impl;

import in.hocg.zeus.ums.biz.entity.AuthorityApiRef;
import in.hocg.zeus.ums.biz.mapper.AuthorityApiRefMapper;
import in.hocg.zeus.ums.biz.service.AuthorityApiRefService;
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
 * [权限模块] 权限x接口表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuthorityApiRefServiceImpl extends AbstractServiceImpl<AuthorityApiRefMapper, AuthorityApiRef> implements AuthorityApiRefService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantApis(Long authorityId, List<Long> apis) {
        List<AuthorityApiRef> entities = apis.parallelStream()
            .map(apiId -> new AuthorityApiRef().setApiId(apiId).setAuthorityId(authorityId))
            .collect(Collectors.toList());
        List<AuthorityApiRef> allData = this.listAuthorityApiByAuthorityId(authorityId);

        final BiFunction<AuthorityApiRef, AuthorityApiRef, Boolean> isSame =
            (t1, t2) -> LangUtils.equals(t1.getApiId(), t2.getApiId());
        final List<AuthorityApiRef> mixedList = LangUtils.getMixed(allData, entities, isSame);
        List<AuthorityApiRef> deleteList = LangUtils.removeIfExits(allData, mixedList, isSame);
        List<AuthorityApiRef> addList = LangUtils.removeIfExits(entities, mixedList, isSame);

        // 删除
        this.removeByIds(deleteList.parallelStream()
            .map(AuthorityApiRef::getId)
            .collect(Collectors.toList()));

        // 新增
        addList.forEach(this::validInsertOrUpdate);

        // 更新
        mixedList.forEach(this::validInsertOrUpdate);
    }

    private List<AuthorityApiRef> listAuthorityApiByAuthorityId(Long authorityId) {
        return lambdaQuery().eq(AuthorityApiRef::getAuthorityId, authorityId).list();
    }
}
