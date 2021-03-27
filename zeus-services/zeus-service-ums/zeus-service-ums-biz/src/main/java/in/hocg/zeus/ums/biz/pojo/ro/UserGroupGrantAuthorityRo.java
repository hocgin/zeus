package in.hocg.zeus.ums.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/1/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class UserGroupGrantAuthorityRo {
    @Size(min = 1, message = "请先选择权限")
    private List<Long> authorities = Collections.emptyList();
}
