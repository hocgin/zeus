package in.hocg.zeus.ums.biz.pojo.ro;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/2/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class GrantUserGroupRo {
    @Size(min = 1, message = "请先选择角色")
    private List<Long> userGroup = Collections.emptyList();
}
