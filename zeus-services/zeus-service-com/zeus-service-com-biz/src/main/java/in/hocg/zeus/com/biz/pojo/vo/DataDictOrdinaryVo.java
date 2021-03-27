package in.hocg.zeus.com.biz.pojo.vo;

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
public class DataDictOrdinaryVo {
    private Long id;
    @ApiModelProperty("字典名称")
    private String title;
    @ApiModelProperty("字典标识")
    private String code;
    @ApiModelProperty("备注")
    private String remark;
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
