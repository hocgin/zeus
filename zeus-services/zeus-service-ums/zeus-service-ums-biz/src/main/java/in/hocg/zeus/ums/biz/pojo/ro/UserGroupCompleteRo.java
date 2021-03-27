package in.hocg.zeus.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.CompleteRo;
import lombok.Data;

import javax.annotation.security.DenyAll;

/**
 * Created by hocgin on 2021/1/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UserGroupCompleteRo extends CompleteRo {
    private String keyword;
}
