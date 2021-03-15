package in.hocg.zeus.sso.service;

import in.hocg.zeus.sso.pojo.ro.JoinRo;
import in.hocg.zeus.sso.pojo.ro.SendSmsCodeRo;
import in.hocg.zeus.sso.pojo.vo.WxLoginStatusVo;
import in.hocg.zeus.sso.pojo.vo.WxMpQrCodeVo;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SsoIndexService {

    void createAccount(JoinRo ro);

    void sendSmsCode(SendSmsCodeRo ro);

    WxMpQrCodeVo getWxQrCode(String appid);

    WxLoginStatusVo getWxLoginStatus(String idFlag, String redirectUrl);

}
