package in.hocg.zeus.ums.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel
public class AssignUserGroupRo {
    @ApiModelProperty("新分配角色的用户")
    @Size(max = 100, message = "请选择用户ID")
    private List<Long> assignUser = Collections.emptyList();

    @ApiModelProperty("移除角色的用户")
    @Size(max = 100, message = "请选择用户ID")
    private List<Long> clearUser = Collections.emptyList();
}
