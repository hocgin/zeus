package in.hocg.zeus.ums.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/8/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@Accessors(chain = true)
@ApiModel("角色列表查询")
public class RoleComplexVo extends RoleOrdinaryVo {
    @ApiModelProperty("角色使用的用户数量")
    private Integer useUserCount = 0;

    @ApiModelProperty("权限列表")
    private List<AuthorityOrdinaryVo> authorities = Collections.emptyList();

}
