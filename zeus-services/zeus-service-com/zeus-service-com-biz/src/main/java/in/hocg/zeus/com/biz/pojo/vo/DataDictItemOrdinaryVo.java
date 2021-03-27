package in.hocg.zeus.com.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
public class DataDictItemOrdinaryVo {
    private Long id;
    @ApiModelProperty("com_data_dict ID")
    private Long dictId;
    @ApiModelProperty("字典项名称")
    private String title;
    @ApiModelProperty("字典标识")
    private String code;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("排序, 从大到小降序")
    private Integer priority;
    @ApiModelProperty("启用状态")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
}
