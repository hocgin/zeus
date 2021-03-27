package in.hocg.zeus.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import lombok.Data;

/**
 * Created by hocgin on 2021/1/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ApiPagingRo extends PageRo {
    private String keyword;
}
