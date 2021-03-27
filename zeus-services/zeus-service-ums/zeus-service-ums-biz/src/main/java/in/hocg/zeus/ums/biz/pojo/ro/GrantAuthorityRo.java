package in.hocg.zeus.ums.biz.pojo.ro;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/2/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class GrantAuthorityRo {
    @Size(min = 1, message = "请先选择权限")
    private List<Long> authorities = Collections.emptyList();
}
