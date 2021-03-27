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
 * [权限模块] 接口表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ams_api")
public class Api extends AbstractEntity<Api> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("接口编码")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("接口名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("接口描述")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("请求方式: 比如: GET,POST 或者 *")
    @TableField("request_method")
    private String requestMethod;
    @ApiModelProperty("服务接口前缀, 比如: /chaos 或者 *")
    @TableField("service_prefix")
    private String servicePrefix;
    @ApiModelProperty("请求URI, 比如: /xx/{id}  或者 /**")
    @TableField("request_uri")
    private String requestUri;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;
    @ApiModelProperty("优先级, 越大优先级越低")
    @TableField("priority")
    private Integer priority;
    @ApiModelProperty("是否保留权限")
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
