package in.hocg.zeus.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/8/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "角色分页查询")
public class RolePagingRo extends PageRo {
    @ApiModelProperty(value = "关键字")
    private String keyword;
}
