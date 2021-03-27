package in.hocg.zeus.ums.api.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "创建账号")
public class CreateAccountRo {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮件地址")
    private String email;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("注册IP")
    private String createdIp;
    @ApiModelProperty("社交账号")
    private List<SocialItem> socials = Collections.emptyList();

    @Data
    @Accessors(chain = true)
    @ApiModel(description = "关联社交账号")
    public static class SocialItem {
        @ApiModelProperty("社交类型")
        private String socialType;
        @ApiModelProperty("社交账号id")
        private String socialId;
    }
}
