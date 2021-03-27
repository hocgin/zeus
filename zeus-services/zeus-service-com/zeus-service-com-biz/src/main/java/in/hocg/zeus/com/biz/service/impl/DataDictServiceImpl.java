package in.hocg.zeus.com.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.com.api.pojo.vo.DataDictItemVo;
import in.hocg.zeus.com.biz.entity.DataDict;
import in.hocg.zeus.com.biz.entity.DataDictItem;
import in.hocg.zeus.com.biz.mapper.DataDictMapper;
import in.hocg.zeus.com.biz.mapstruct.DataDictItemMapping;
import in.hocg.zeus.com.biz.mapstruct.DataDictMapping;
import in.hocg.zeus.com.biz.pojo.ro.DataDictInsertRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictItemInsertRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictPagingRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictUpdateRo;
import in.hocg.zeus.com.biz.pojo.vo.DataDictComplexVo;
import in.hocg.zeus.com.biz.pojo.vo.DataDictOrdinaryVo;
import in.hocg.zeus.com.biz.service.DataDictItemService;
import in.hocg.zeus.com.biz.service.DataDictService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.datastruct.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * [基础模块] 数据字典表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DataDictServiceImpl extends AbstractServiceImpl<DataDictMapper, DataDict>
    implements DataDictService {
    private final DataDictItemService dataDictItemService;
    private final DataDictMapping mapping;
    private final DataDictItemMapping dataDictItemMapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<KeyValue> listDataDictItemDtoByDictIdAndCode(String typeCode, List<String> itemCodes) {
        return this.listDataDictItemByDictIdAndCode(typeCode, itemCodes).stream()
            .map(this::convertKeyValue)
            .collect(Collectors.toList());
    }

    @Override
    public List<DataDictItemVo> listDataDictItemVoDtoByDictIdAndCode(String typeCode, List<String> itemCodes) {
        return LangUtils.toList(listDataDictItemByDictIdAndCode(typeCode, itemCodes), dataDictItemMapping::asDataDictItemVo);
    }

    public List<DataDictItem> listDataDictItemByDictIdAndCode(String typeCode, List<String> itemCodes) {
        return baseMapper.listDataDictItemByDictIdAndCode(typeCode, itemCodes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<KeyValue> listKeyValueByCodeAndEnabledIsOn(String typeCode) {
        return this.listKeyValueByCodeAndEnabled(typeCode, Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<KeyValue> listKeyValueByCode(String typeCode) {
        return this.listKeyValueByCodeAndEnabled(typeCode, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataDictComplexVo getComplex(Long id) {
        return convertComplex(getById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        dataDictItemService.deleteByDataDictId(id);
        removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOne(DataDictInsertRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();
        List<DataDictItemInsertRo> items = ro.getItems();

        DataDict entity = mapping.asDataDict(ro);
        entity.setCreatedAt(now);
        entity.setCreator(userId);
        validInsert(entity);
        Long dataDictId = entity.getId();
        for (DataDictItemInsertRo item : items) {
            dataDictItemService.insertOne(dataDictId, userId, item);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOne(Long id, DataDictUpdateRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        DataDict entity = mapping.asDataDict(ro);
        entity.setLastUpdatedAt(now);
        entity.setLastUpdater(userId);
        validUpdateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<DataDictOrdinaryVo> paging(DataDictPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(this::convertOrdinary);
    }

    private DataDictOrdinaryVo convertOrdinary(DataDict entity) {
        return mapping.asOrdinary(entity);
    }

    private DataDictComplexVo convertComplex(DataDict entity) {
        DataDictComplexVo result = mapping.asComplex(entity);
        result.setItems(dataDictItemService.listOrdinaryByDataDictId(entity.getId()));
        return result;
    }

    private List<KeyValue> listKeyValueByCodeAndEnabled(String typeCode, Boolean enabled) {
        return listDataDictItemByCodeAndEnabled(typeCode, enabled)
            .stream().map(this::convertKeyValue)
            .collect(Collectors.toList());
    }

    private KeyValue convertKeyValue(DataDictItem item) {
        return new KeyValue()
            .setValue(item.getCode())
            .setKey(item.getTitle());
    }

    private List<DataDictItem> listDataDictItemByCodeAndEnabled(String typeCode, Boolean enabled) {
        return baseMapper.listDataDictItemByCodeAndEnabled(typeCode, enabled);
    }

}
