package in.hocg.zeus.chaos.biz.service.impl;

import in.hocg.zeus.usercontext.ifc.UserContextService;
import in.hocg.zeus.usercontext.ifc.vo.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/11/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserContextServiceImpl implements UserContextService {

    @Override
    public UserDetail getUserDetail(String username) {
        return new UserDetail().setUsername("hocgin").setId(1L);
    }
}
