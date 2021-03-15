package in.hocg.zeus.usercontext.ifc;

import in.hocg.zeus.usercontext.ifc.vo.UserDetail;

/**
 * Created by hocgin on 2020/8/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface UserContextService {
    UserDetail getUserDetail(String username);
}
