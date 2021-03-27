package in.hocg.zeus.ums.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.entity.Api;
import in.hocg.zeus.ums.biz.pojo.ro.ApiCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.ApiPagingRo;
import in.hocg.zeus.ums.biz.pojo.ro.ApiSaveRo;
import in.hocg.zeus.ums.biz.pojo.vo.ApiComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.ApiOrdinaryVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 接口表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface ApiService extends AbstractService<Api> {

    void insertOne(ApiSaveRo ro);

    void updateOne(Long id, ApiSaveRo ro);

    void deleteOne(Long id);

    List<Api> listByUsername(String username);

    IPage<ApiComplexVo> paging(ApiPagingRo ro);

    List<ApiComplexVo> complete(ApiCompleteRo ro);

    ApiComplexVo getComplex(Long id);

    List<ApiOrdinaryVo> listOrdinaryByAuthorityId(Long authorityId);
}
