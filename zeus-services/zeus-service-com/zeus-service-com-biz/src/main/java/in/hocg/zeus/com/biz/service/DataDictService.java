package in.hocg.zeus.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.com.api.pojo.vo.DataDictItemVo;
import in.hocg.zeus.com.biz.entity.DataDict;
import in.hocg.zeus.com.biz.pojo.ro.DataDictInsertRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictPagingRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictUpdateRo;
import in.hocg.zeus.com.biz.pojo.vo.DataDictComplexVo;
import in.hocg.zeus.com.biz.pojo.vo.DataDictOrdinaryVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;
import in.hocg.boot.web.datastruct.KeyValue;

import java.util.List;

/**
 * <p>
 * [基础模块] 数据字典表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
public interface DataDictService extends AbstractService<DataDict> {

    List<KeyValue> listDataDictItemDtoByDictIdAndCode(String typeCode, List<String> itemCodes);

    List<DataDictItemVo> listDataDictItemVoDtoByDictIdAndCode(String typeCode, List<String> itemCodes);

    /**
     * 查询启用的数据字典项
     *
     * @param typeCode 数据字典类型
     * @return []
     */
    List<KeyValue> listKeyValueByCodeAndEnabledIsOn(String typeCode);

    List<KeyValue> listKeyValueByCode(String code);

    DataDictComplexVo getComplex(Long id);

    void delete(Long id);

    void insertOne(DataDictInsertRo ro);

    void updateOne(Long id, DataDictUpdateRo ro);

    IPage<DataDictOrdinaryVo> paging(DataDictPagingRo ro);
}
