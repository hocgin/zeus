package in.hocg.zeus.com.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.zeus.com.biz.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * [基础模块] 文件引用表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

    List<File> listByRefTypeAndRefIdOrderBySortDescAndCreatedAtDesc(@Param("refType") Serializable code, @Param("refId") Long relId);
}
