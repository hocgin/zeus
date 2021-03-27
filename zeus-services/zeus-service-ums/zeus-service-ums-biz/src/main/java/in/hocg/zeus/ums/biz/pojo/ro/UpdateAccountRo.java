package in.hocg.zeus.ums.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/1/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "用户信息 - 修改")
public class UpdateAccountRo {
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty("昵称;显示使用")
    private String nickname;
    @ApiModelProperty("用户名;唯一,登录使用")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("头像地址")
    private String avatarUrl;
    @ApiModelProperty("性别(0:女, 1:男)")
    private Integer gender;
    @ApiModelProperty(value = "更新人", hidden = true)
    private Long updaterId;
}
