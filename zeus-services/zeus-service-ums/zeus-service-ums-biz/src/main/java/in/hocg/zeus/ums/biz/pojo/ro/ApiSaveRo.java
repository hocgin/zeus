package in.hocg.zeus.ums.biz.pojo.ro;


import in.hocg.boot.validation.autoconfigure.group.Insert;
import in.hocg.boot.validation.autoconfigure.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by hocgin on 2020/8/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "接口保存")
public class ApiSaveRo {
    @ApiModelProperty("接口编码")
    @NotNull(message = "接口编码不能为空", groups = {Insert.class})
    private String encoding;
    @ApiModelProperty("接口名称")
    @Size(max = 20, groups = {Insert.class, Update.class}, message = "接口名称过长")
    @NotNull(message = "接口名称不能为空", groups = {Insert.class})
    private String title;
    @ApiModelProperty("接口描述")
    @Size(max = 255, groups = {Insert.class, Update.class}, message = "接口描述过长")
    private String remark;
    @ApiModelProperty("请求方式")
    @NotNull(message = "请求方式不能为空", groups = {Insert.class})
    private String requestMethod;
    @ApiModelProperty("服务接口前缀, 比如: /chaos")
    @NotNull(message = "服务接口前缀不能为空", groups = {Insert.class})
    private String servicePrefix;
    @ApiModelProperty("请求URI, 比如: /xx/{id}")
    @NotNull(message = "请求URI不能为空", groups = {Insert.class})
    private String requestUri;
    @ApiModelProperty("启用状态")
    private Boolean enabled;

    @ApiModelProperty("优先级, 越大优先级越低")
    private Integer priority;
    @ApiModelProperty("是否保留权限")
    private Boolean isPersist;
    @ApiModelProperty(value = "创建者", hidden = true)
    private Long userId;
}
