package in.hocg.zeus.usercontext.autoconfigure;

import in.hocg.zeus.usercontext.basic.HeaderConstants;
import in.hocg.zeus.usercontext.ifc.UserContextService;
import in.hocg.zeus.usercontext.ifc.vo.UserDetail;
import in.hocg.boot.web.SpringContext;
import in.hocg.boot.web.exception.UnAuthenticationException;
import in.hocg.boot.web.servlet.SpringServletContext;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

/**
 * Created by hocgin on 2020/8/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class UserContextHolder {
    private final ThreadLocal<Map<String, Object>> cache = ThreadLocal.withInitial(WeakHashMap::new);

    public Optional<String> getUsername() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.USERNAME));
    }

    public Optional<UserDetail> getUserDetail() {
        return getUsername().map(userContextService()::getUserDetail);
    }

    public Optional<Long> getUserId() {
        return getUserDetail().map(UserDetail::getId);
    }

    public Long getUserIdThrow() {
        return getUserId().orElseThrow(UnAuthenticationException::new);
    }

    public Optional<String> getSource() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.SOURCE));
    }

    public Optional<String> getVersion() {
        HttpServletRequest request = getRequest();
        return Optional.ofNullable(request.getHeader(HeaderConstants.VERSION));
    }

    private HttpServletRequest getRequest() {
        return SpringServletContext.getRequest().orElseThrow(IllegalArgumentException::new);
    }

    private UserContextService userContextService() {
        return SpringContext.getBean(UserContextService.class);
    }
}
