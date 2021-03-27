package in.hocg.zeus.ums.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.zeus.ums.biz.entity.User;
import in.hocg.zeus.ums.biz.pojo.ro.UserCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.UserPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [用户模块] 账号表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<User> paging(@Param("ro") UserPagingRo ro, @Param("ofPage") Page<Object> ofPage);

    IPage<User> complete(@Param("ro") UserCompleteRo ro, @Param("ofPage") Page ofPage);
}
