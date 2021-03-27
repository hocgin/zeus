package in.hocg.zeus.ums.biz.pojo.ro;


import in.hocg.boot.validation.autoconfigure.core.annotation.EnumRange;
import in.hocg.boot.validation.autoconfigure.group.Insert;
import in.hocg.boot.validation.autoconfigure.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by hocgin on 2020/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "角色保存")
public class SaveRoleRo {
    @ApiModelProperty("角色编码")
    @NotNull(message = "角色编码不能为空", groups = {Insert.class})
    private String encoding;
    @NotNull(message = "角色名称不能为空", groups = {Insert.class})
    @ApiModelProperty("角色名称")
    @Size(max = 20, groups = {Insert.class, Update.class}, message = "角色名称过长")
    private String title;
    @ApiModelProperty("机构ID")
    private Long orgId;
    @ApiModelProperty("角色描述")
    @Size(max = 255, groups = {Insert.class, Update.class}, message = "角色描述过长")
    private String remark;
    @ApiModelProperty("权限列表")
    private List<Long> authorities;

    @ApiModelProperty("启用状态")
    private Boolean enabled;
    @ApiModelProperty("是否保留角色")
    private Boolean isPersist;
    @ApiModelProperty(value = "创建者", hidden = true)
    private Long userId;
}
