package in.hocg.zeus.common.datadict.ums;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum Gender implements DataDictEnum {
    Man("male", "男"),
    Woman("female", "女");
    private final Serializable code;
    private final String name;

    public static final String KEY = "gender";
}
