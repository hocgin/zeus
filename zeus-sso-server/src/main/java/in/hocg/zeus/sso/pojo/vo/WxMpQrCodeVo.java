package in.hocg.zeus.sso.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class WxMpQrCodeVo {
    private String qrCodeUrl;
    private String idFlag;
}
