package in.hocg.zeus.common.constant;

import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2020/12/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class RegexpConstant {
    public static final String USERNAME = "^\\w{6,12}$";
    public static final String PASSWORD = "^.{6,32}$";
    public static final String VERIFY_CODE = "^\\d{6}$";
    public static final String PHONE = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$";
}
