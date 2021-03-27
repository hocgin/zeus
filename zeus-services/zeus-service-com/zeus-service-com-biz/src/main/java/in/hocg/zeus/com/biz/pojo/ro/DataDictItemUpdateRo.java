package in.hocg.zeus.com.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class DataDictItemUpdateRo {
    private String title;
    private String code;
    private String remark;
    private Integer priority;
    private Boolean enabled;

    @ApiModelProperty(hidden = true)
    private Long userId;
}
