package in.hocg.zeus.ums.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by hocgin on 2020/12/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class AccountVo {
    private Long id;
    private String nickname;
    private String username;
    private String avatarUrl;
}
