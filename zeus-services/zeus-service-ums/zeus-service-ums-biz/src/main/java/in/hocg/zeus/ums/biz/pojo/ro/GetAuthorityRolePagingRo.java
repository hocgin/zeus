package in.hocg.zeus.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/8/31
 * email: hocgin@gmail.com
 *
 * @author hocgi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetAuthorityRolePagingRo extends PageRo {
    @ApiModelProperty("关键词")
    private String keyword;
}
