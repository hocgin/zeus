package in.hocg.zeus.ums.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * [用户模块] 账号表
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ums_user")
public class User extends AbstractEntity<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("昵称;显示使用")
    @TableField("nickname")
    private String nickname;
    @ApiModelProperty("用户名;唯一,登录使用")
    @TableField("username")
    private String username;
    @ApiModelProperty("邮箱;唯一,登录使用")
    @TableField("email")
    private String email;
    @ApiModelProperty("手机号码;唯一,登录使用")
    @TableField("phone")
    private String phone;
    @ApiModelProperty("密码")
    @TableField("password")
    private String password;
    @ApiModelProperty("头像地址")
    @TableField("avatar_url")
    private String avatarUrl;
    @ApiModelProperty("性别(0:女, 1:男)")
    @TableField("gender")
    private Integer gender;
    @ApiModelProperty("过期状态")
    @TableField("expired")
    private Boolean expired;
    @ApiModelProperty("锁定状态")
    @TableField("locked")
    private Boolean locked;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;
    @ApiModelProperty("注册时使用的IP")
    @TableField("created_ip")
    private String createdIp;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    @TableField("creator")
    private Long creator;
    @ApiModelProperty("更新时间")
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    @TableField("last_updater")
    private Long lastUpdater;



}
