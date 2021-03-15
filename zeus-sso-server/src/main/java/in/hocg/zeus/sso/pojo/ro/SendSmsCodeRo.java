package in.hocg.zeus.sso.pojo.ro;

import in.hocg.zeus.common.constant.RegexpConstant;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by hocgin on 2020/12/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class SendSmsCodeRo {
    @Pattern(regexp = RegexpConstant.PHONE,
        message = "请输入正确的手机号码")
    @NotBlank(message = "手机号码不能为空")
    String phone;
}
