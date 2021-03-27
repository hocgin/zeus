package in.hocg.zeus.ums.biz.pojo.ro;

import in.hocg.boot.validation.autoconfigure.group.Insert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel
public class SaveUserGroupRo {
    @ApiModelProperty("名称")
    @NotNull(message = "名称不能为空", groups = {Insert.class})
    private String title;
    @NotNull(message = "编码不能为空", groups = {Insert.class})
    @ApiModelProperty("编码")
    private String encoding;
    @ApiModelProperty("描述")
    private String remark;
    @ApiModelProperty("启用状态")
    private Boolean enabled;
    @ApiModelProperty("是否保留")
    private Boolean isPersist;

    @ApiModelProperty(value = "创建者", hidden = true)
    private Long userId;
}
