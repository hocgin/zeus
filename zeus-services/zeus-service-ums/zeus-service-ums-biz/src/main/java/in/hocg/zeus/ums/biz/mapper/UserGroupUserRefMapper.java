package in.hocg.zeus.ums.biz.mapper;

import in.hocg.zeus.ums.biz.entity.UserGroup;
import in.hocg.zeus.ums.biz.entity.UserGroupUserRef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [权限模块] 用户组x用户表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Mapper
public interface UserGroupUserRefMapper extends BaseMapper<UserGroupUserRef> {

    List<UserGroup> listByUserId(@Param("userId") Long userId);
}
