package in.hocg.zeus.sso.config.security;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/11/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class AuthorizedSuccessResult {
    private String redirectUrl;

    public static AuthorizedSuccessResult create(String redirectUrl) {
        return new AuthorizedSuccessResult().setRedirectUrl(redirectUrl);
    }

    public String toJSON() {
        return JSONUtil.toJsonStr(this);
    }
}
