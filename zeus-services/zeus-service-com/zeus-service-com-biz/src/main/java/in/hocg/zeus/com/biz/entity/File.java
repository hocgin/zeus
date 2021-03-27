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
 * [基础模块] 文件引用表
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_file")
public class File extends AbstractEntity<File> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("文件名")
    @TableField("filename")
    private String filename;
    @ApiModelProperty("链接地址")
    @TableField("file_url")
    private String fileUrl;
    @ApiModelProperty("业务ID")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("业务类型")
    @TableField("ref_type")
    private String refType;
    @ApiModelProperty("排序,默认:1000")
    @TableField("priority")
    private Integer priority;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    @TableField("creator")
    private Long creator;

}
