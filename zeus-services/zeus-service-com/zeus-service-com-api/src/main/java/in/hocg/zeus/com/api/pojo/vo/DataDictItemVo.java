package in.hocg.zeus.com.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class DataDictItemVo {
    @ApiModelProperty("字典项名称")
    private String title;
    @ApiModelProperty("字典标识")
    private String code;
}
