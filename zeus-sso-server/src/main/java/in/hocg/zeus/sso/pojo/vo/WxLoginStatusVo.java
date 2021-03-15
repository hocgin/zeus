package in.hocg.zeus.sso.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel
public class WxLoginStatusVo {
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("跳转地址")
    private String redirectUrl;
}
