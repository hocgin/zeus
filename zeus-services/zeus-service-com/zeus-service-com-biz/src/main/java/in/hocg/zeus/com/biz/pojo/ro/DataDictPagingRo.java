package in.hocg.zeus.com.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/2/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataDictPagingRo extends PageRo {
    private String keyword;
}
