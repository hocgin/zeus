package in.hocg.zeus.sso.service.impl;

import in.hocg.zeus.sso.pojo.ro.JoinAccountRo;
import in.hocg.zeus.sso.pojo.ro.LoginRo;
import in.hocg.zeus.sso.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccountServiceImpl implements AccountService {

    @Override
    public String join(JoinAccountRo ro) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String login(LoginRo ro) {
        throw new UnsupportedOperationException();
    }

}
