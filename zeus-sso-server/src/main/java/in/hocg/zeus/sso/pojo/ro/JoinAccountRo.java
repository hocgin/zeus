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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by hocgin on 2020/12/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "注册")
public class JoinAccountRo {
    @NotNull(groups = {EmailModeGroup.class, PhoneModeGroup.class, UsernameModeGroup.class}, message = "该注册方式暂不支持")
    @EnumRange(groups = {EmailModeGroup.class, PhoneModeGroup.class, UsernameModeGroup.class}, enumClass = Mode.class, message = "该注册方式暂不支持")
    @ApiModelProperty(value = "模式", required = true)
    private String mode = Mode.UsePhone.getCode();

    @Valid
    @NotNull(message = "邮件信息不能为空", groups = {EmailModeGroup.class})
    @ApiModelProperty(value = "仅邮件模式使用")
    private EmailMode emailMode;

    @Valid
    @NotNull(message = "手机信息不能为空", groups = {PhoneModeGroup.class})
    @ApiModelProperty(value = "仅手机号模式使用")
    private PhoneMode phoneMode;

    @Valid
    @NotNull(message = "账户信息不能为空", groups = {UsernameModeGroup.class})
    @ApiModelProperty(value = "仅账户模式使用")
    private UsernameMode usernameMode;

    @Data
    @ApiModel(description = "账户模式")
    public static class UsernameMode {
        @Pattern(groups = {UsernameModeGroup.class}, regexp = RegexpConstant.USERNAME, message = "账户仅支持6~12位的字母、数字或下划线")
        @NotBlank(groups = {UsernameModeGroup.class}, message = "账户不能为空")
        @ApiModelProperty(value = "指定用户名")
        private String username;
        @Pattern(groups = {UsernameModeGroup.class}, regexp = RegexpConstant.PASSWORD, message = "请输入6~32位的密码")
        @NotBlank(groups = {UsernameModeGroup.class}, message = "密码不能为空")
        @ApiModelProperty(value = "指定密码")
        private String password;
    }

    @Data
    @ApiModel(description = "邮件模式")
    public static class EmailMode {
        @Email(groups = {EmailModeGroup.class}, message = "请输入正确的邮件地址")
        @NotBlank(groups = {PhoneModeGroup.class}, message = "邮件地址不能为空")
        @ApiModelProperty(value = "邮件地址")
        private String email;
        @Pattern(groups = {UsernameModeGroup.class}, regexp = RegexpConstant.PASSWORD, message = "请输入6~32位的密码")
        @NotBlank(groups = {UsernameModeGroup.class}, message = "密码不能为空")
        @ApiModelProperty(value = "指定密码")
        private String password;
        @Pattern(groups = {EmailModeGroup.class}, regexp = RegexpConstant.VERIFY_CODE, message = "请输入6位验证码")
        @NotBlank(groups = {EmailModeGroup.class}, message = "验证码不能为空")
        @ApiModelProperty(value = "验证码")
        private String verifyCode;
    }

    @Data
    @ApiModel(description = "手机号模式")
    public static class PhoneMode {
        @Pattern(groups = {PhoneModeGroup.class}, regexp = RegexpConstant.PHONE, message = "请输入正确的手机号码")
        @NotBlank(groups = {PhoneModeGroup.class}, message = "手机号码不能为空")
        @ApiModelProperty(value = "手机号码")
        private String phone;
        @Pattern(groups = {PhoneModeGroup.class}, regexp = RegexpConstant.VERIFY_CODE, message = "请输入6位验证码")
        @NotBlank(groups = {PhoneModeGroup.class}, message = "验证码不能为空")
        @ApiModelProperty(value = "验证码")
        private String verifyCode;
    }

    public interface PhoneModeGroup {
    }

    public interface EmailModeGroup {
    }

    public interface UsernameModeGroup {
    }


    @Getter
    @RequiredArgsConstructor
    public enum Mode implements ICode {
        UseUsername("use_username"),
        UsePhone("use_phone"),
        UseEmail("use_email");
        private final String code;
    }
}
