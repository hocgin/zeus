package in.hocg.zeus.ums.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2021/1/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "用户信息 - 修改")
public class UpdateAccountEmailRo {
    @ApiModelProperty(hidden = true)
    private Long id;
    @Email(message = "邮箱号错误")
    @NotBlank(message = "邮箱号不能为空")
    @ApiModelProperty("邮箱号")
    private String email;
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String verifyCode;
    @ApiModelProperty(value = "更新人", hidden = true)
    private Long updaterId;
}
