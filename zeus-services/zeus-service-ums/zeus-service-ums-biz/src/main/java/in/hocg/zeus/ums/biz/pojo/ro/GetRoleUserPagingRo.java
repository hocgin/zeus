package in.hocg.zeus.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/8/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetRoleUserPagingRo extends PageRo {
    @ApiModelProperty("关键字")
    private String keyword;
    @ApiModelProperty(value = "角色", hidden = true)
    private Long roleId;
}
