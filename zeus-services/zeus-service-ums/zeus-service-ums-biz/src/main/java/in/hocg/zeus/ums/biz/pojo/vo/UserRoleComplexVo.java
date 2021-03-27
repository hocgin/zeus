package in.hocg.zeus.ums.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/8/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@Accessors(chain = true)
@ApiModel("用户信息")
public class UserRoleComplexVo {
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("用户ID")
    private Long userId;
}
