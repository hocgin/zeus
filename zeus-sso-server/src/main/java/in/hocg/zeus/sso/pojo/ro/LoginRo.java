package in.hocg.zeus.sso.pojo.ro;

import in.hocg.zeus.common.constant.RegexpConstant;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.boot.validation.autoconfigure.core.annotation.EnumRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by hocgin on 2020/12/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "登录")
public class LoginRo {
    @NotNull(message = "该登录方式暂不支持", groups = {PasswordModeGroup.class, SmsModeGroup.class})
    @EnumRange(message = "该登录方式暂不支持", groups = {PasswordModeGroup.class, SmsModeGroup.class}, enumClass = Mode.class)
    @ApiModelProperty(value = "模式", required = true)
    private String mode = Mode.UsePassword.getCode();

    @Valid
    @NotNull(message = "手机信息不能为空", groups = {SmsModeGroup.class})
    @ApiModelProperty(value = "仅短信模式使用")
    private SmsMode smsMode;

    @Valid
    @NotNull(message = "账号信息不能为空", groups = {PasswordModeGroup.class})
    @ApiModelProperty(value = "仅密码模式使用")
    private PasswordMode passwordMode;


    @Data
    @ApiModel(description = "密码模式")
    public static class PasswordMode {
        @NotBlank(message = "账户不能为空", groups = {PasswordModeGroup.class})
        @ApiModelProperty("账号/手机号/邮箱")
        private String username;
        @NotBlank(message = "密码不能为空", groups = {PasswordModeGroup.class})
        @ApiModelProperty("密码")
        private String password;
    }

    @Data
    @ApiModel(description = "短信模式")
    public static class SmsMode {
        @Pattern(message = "请输入正确的手机号码", groups = {SmsModeGroup.class}, regexp = RegexpConstant.PHONE)
        @NotBlank(message = "手机号码不能为空", groups = {SmsModeGroup.class})
        @ApiModelProperty("手机号")
        private String phone;
        @Pattern(message = "请输入6位验证码", groups = {SmsModeGroup.class}, regexp = RegexpConstant.VERIFY_CODE)
        @NotBlank(message = "验证码不能为空", groups = {SmsModeGroup.class})
        @ApiModelProperty(value = "验证码")
        private String verifyCode;
    }

    public interface PasswordModeGroup {
    }

    public interface SmsModeGroup {
    }

    @Getter
    @RequiredArgsConstructor
    public enum Mode implements ICode {
        UsePassword("use_password"),
        UseSms("use_sms");
        private final String code;
    }
}
