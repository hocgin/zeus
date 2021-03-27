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
 * [权限模块] 用户组表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ams_user_group")
public class UserGroup extends AbstractEntity<UserGroup> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("编码")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("描述")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;
    @ApiModelProperty("是否保留")
    @TableField("is_persist")
    private Boolean isPersist;
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
