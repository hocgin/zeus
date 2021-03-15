package in.hocg.zeus.usercontext.ifc.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/9/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class UserDetail {
    private Long id;
    private String username;
}
