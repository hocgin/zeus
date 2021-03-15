package in.hocg.zeus.chaos.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "发送验证码")
public class SendSmsCodeRo {
    @NotBlank(message = "手机号码错误")
    @ApiModelProperty("手机号码")
    private String phone;
}
