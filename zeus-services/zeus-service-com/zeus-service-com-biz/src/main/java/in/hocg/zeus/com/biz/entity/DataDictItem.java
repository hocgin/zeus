package in.hocg.zeus.com.biz.entity;

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
 * [基础模块] 数据字典项表
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_data_dict_item")
public class DataDictItem extends AbstractEntity<DataDictItem> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("com_data_dict ID")
    @TableField("dict_id")
    private Long dictId;
    @ApiModelProperty("字典项名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("字典标识")
    @TableField("code")
    private String code;
    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("排序, 从大到小降序")
    @TableField("priority")
    private Integer priority;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;

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
