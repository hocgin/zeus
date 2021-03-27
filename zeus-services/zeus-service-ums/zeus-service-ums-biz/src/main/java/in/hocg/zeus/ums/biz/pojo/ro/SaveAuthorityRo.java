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
@ApiModel(description = "权限保存")
public class SaveAuthorityRo {
    @ApiModelProperty("父级ID, 顶级为 NULL")
    private Long parentId;
    @NotNull(message = "权限编码不能为空", groups = {Insert.class})
    @ApiModelProperty(value = "权限编码", required = true)
    private String encoding;
    @NotNull(message = "关联项目不能为空", groups = {Insert.class})
    @ApiModelProperty(value = "关联项目", required = true)
    private Long projectId;
    @Size(max = 20, groups = {Insert.class, Update.class}, message = "权限名称过长")
    @NotNull(message = "权限名称不能为空", groups = {Insert.class})
    @ApiModelProperty(value = "权限名称", required = true)
    private String title;
    @Size(max = 255, groups = {Insert.class, Update.class}, message = "权限描述过长")
    @NotNull(message = "权限描述不能为空")
    @ApiModelProperty("权限描述")
    private String remark;
    @ApiModelProperty("启用状态")
    private Boolean enabled;
    @ApiModelProperty("接口列表")
    private List<Long> apis;

    @ApiModelProperty("是否保留权限")
    private Boolean isPersist;
    @ApiModelProperty("优先级, 越大优先级越低")
    private Integer priority;
    @ApiModelProperty(value = "请求人", hidden = true)
    private Long userId;

}
