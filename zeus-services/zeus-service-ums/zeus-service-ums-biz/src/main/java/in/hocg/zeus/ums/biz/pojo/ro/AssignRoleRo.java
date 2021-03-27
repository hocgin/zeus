package in.hocg.zeus.ums.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel("分配角色")
public class AssignRoleRo {
    @ApiModelProperty("新分配角色的用户")
    @Size(max = 100, message = "请选择用户ID")
    private List<Long> assignUser = Collections.emptyList();

    @ApiModelProperty("移除角色的用户")
    @Size(max = 100, message = "请选择用户ID")
    private List<Long> clearUser = Collections.emptyList();
}
