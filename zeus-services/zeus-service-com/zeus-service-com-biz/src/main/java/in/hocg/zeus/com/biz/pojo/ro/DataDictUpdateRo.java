package in.hocg.zeus.com.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/3/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class DataDictUpdateRo {
    @ApiModelProperty("字典名称")
    private String title;
    @ApiModelProperty("字典备注")
    private String remark;
    @ApiModelProperty("字典码")
    private String code;
    @ApiModelProperty("启用状态")
    private Boolean enabled;

    @ApiModelProperty(hidden = true)
    private Long userId;
}
