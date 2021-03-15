package in.hocg.zeus.chaos.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2021/1/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "发送验证码")
public class SendEmailCodeRo {
    @Email(message = "邮箱号错误")
    @NotBlank(message = "邮箱号错误")
    @ApiModelProperty("邮箱号")
    private String email;
}
