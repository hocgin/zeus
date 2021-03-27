package in.hocg.zeus.com.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class DataDictItemInsertRo {
    @ApiModelProperty("排序")
    private Integer priority;
    @NotNull(message = "启用状态")
    @ApiModelProperty("启用状态")
    private Boolean enabled;
    @NotNull
    @ApiModelProperty("字典标识")
    private String code;
    @ApiModelProperty("字典备注")
    private String remark;
    @NotNull
    @ApiModelProperty("字典项名称")
    private String title;
}
