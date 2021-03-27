package in.hocg.zeus.ums.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/10/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@ApiModel("权限详情")
@EqualsAndHashCode(callSuper = true)
public class AuthorityComplexVo extends AuthorityOrdinaryVo {
    @ApiModelProperty("权限列表")
    private List<RoleOrdinaryVo> roles = Collections.emptyList();
    @ApiModelProperty("接口列表")
    private List<ApiOrdinaryVo> apis = Collections.emptyList();
}
