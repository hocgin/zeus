package in.hocg.zeus.ums.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/8/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@ApiModel("角色详情")
public class RoleAuthorityComplexVo {
    private Long id;
    @ApiModelProperty("角色编码")
    private String encoding;
    @ApiModelProperty("角色名称")
    private String title;
    @ApiModelProperty("角色描述")
    private String remark;
    @ApiModelProperty("开启状态")
    private Boolean enabled;
}
