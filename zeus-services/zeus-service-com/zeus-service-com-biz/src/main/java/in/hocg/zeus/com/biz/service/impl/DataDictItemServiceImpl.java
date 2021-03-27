package in.hocg.zeus.com.biz.service.impl;

import in.hocg.zeus.com.biz.entity.DataDictItem;
import in.hocg.zeus.com.biz.mapper.DataDictItemMapper;
import in.hocg.zeus.com.biz.mapstruct.DataDictItemMapping;
import in.hocg.zeus.com.biz.pojo.ro.DataDictItemBatchInsertRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictItemInsertRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictItemUpdateRo;
import in.hocg.zeus.com.biz.pojo.vo.DataDictItemComplexVo;
import in.hocg.zeus.com.biz.pojo.vo.DataDictItemOrdinaryVo;
import in.hocg.zeus.com.biz.service.DataDictItemService;
import in.hocg.zeus.com.biz.service.DataDictService;
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
 * [基础模块] 数据字典项表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DataDictItemServiceImpl extends AbstractServiceImpl<DataDictItemMapper, DataDictItem>
    implements DataDictItemService {
    private final DataDictItemMapping mapping;
    private final DataDictService dataDictService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DataDictItemOrdinaryVo> listOrdinaryByDataDictId(Long id) {
        return LangUtils.toList(listByDataDictId(id), this::convertOrdinary);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByDataDictId(Long dictId) {
        lambdaUpdate().eq(DataDictItem::getDictId, dictId).remove();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(Long dictId, Long userId, DataDictItemInsertRo ro) {
        LocalDateTime now = LocalDateTime.now();

        DataDictItem entity = mapping.asDataDictItem(ro);
        entity.setDictId(dictId);
        entity.setCreator(userId);
        entity.setCreatedAt(now);
        validEntity(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(Long dictId, DataDictItemBatchInsertRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();

        List<DataDictItemInsertRo> items = ro.getItems();
        for (DataDictItemInsertRo item : items) {
            DataDictItem entity = mapping.asDataDictItem(item);
            entity.setDictId(dictId);
            entity.setCreator(userId);
            entity.setCreatedAt(now);
            validInsert(entity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataDictItemComplexVo getComplex(Long id) {
        return convertComplex(getById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, DataDictItemUpdateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = ro.getUserId();
        DataDictItem entity = mapping.asDataDictItem(ro);

        entity.setId(id);
        entity.setLastUpdater(userId);
        entity.setLastUpdatedAt(now);
        validUpdateById(entity);
    }

    private DataDictItemComplexVo convertComplex(DataDictItem entity) {
        return mapping.asComplex(entity);
    }

    @Override
    public void deleteOne(Long id) {
        removeById(id);
    }

    private DataDictItemOrdinaryVo convertOrdinary(DataDictItem entity) {
        return mapping.asOrdinary(entity);
    }

    private List<DataDictItem> listByDataDictId(Long dataDictId) {
        return lambdaQuery().eq(DataDictItem::getDictId, dataDictId)
            .orderByDesc(DataDictItem::getPriority).list();
    }

    private boolean hasDictIdAndCodeIgnoreId(Long dictId, String code, Long ignoreId) {
        return lambdaQuery().eq(Objects.nonNull(dictId), DataDictItem::getDictId, dictId)
            .eq(DataDictItem::getCode, code)
            .notIn(Objects.nonNull(ignoreId), DataDictItem::getId, ignoreId)
            .count() > 0;
    }

    @Override
    public void validEntity(DataDictItem entity) {
        final String code = entity.getCode();
        Long dictId = entity.getDictId();
        final Long id = entity.getId();

        // 检查数据字典码
        if (Objects.nonNull(code)) {
            if (Objects.nonNull(id) && Objects.isNull(dictId)) {
                dictId = getById(id).getDictId();
            }
            ValidUtils.isFalse(hasDictIdAndCodeIgnoreId(dictId, code, id), "数据字典码已经存在");
        }
    }
}
