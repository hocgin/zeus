package in.hocg.zeus.ums.api.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum SocialType {
    WxMp("wxmp", "微信公众号"),
    WxMa("wxma", "微信小程序");
    private final Serializable code;
    private final String name;
}
