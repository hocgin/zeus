package in.hocg.zeus.ums.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * [权限模块] 权限表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ams_authority")
public class Authority extends TreeEntity<Authority> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("权限授权码")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("描述")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("项目ID")
    @TableField("project_id")
    private Long projectId;
    @ApiModelProperty("优先级, 越大优先级越低")
    @TableField("priority")
    private Integer priority;
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
