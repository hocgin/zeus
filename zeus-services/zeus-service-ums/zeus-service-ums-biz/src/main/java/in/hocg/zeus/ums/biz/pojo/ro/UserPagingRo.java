package in.hocg.zeus.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import lombok.Data;

/**
 * Created by hocgin on 2021/1/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UserPagingRo extends PageRo {
    private String keyword;

}
