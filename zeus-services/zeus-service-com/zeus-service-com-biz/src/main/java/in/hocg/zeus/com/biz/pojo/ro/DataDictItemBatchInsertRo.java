package in.hocg.zeus.com.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class DataDictItemBatchInsertRo {
    private List<DataDictItemInsertRo> items = Collections.emptyList();

    @ApiModelProperty(hidden = true)
    private Long userId;
}
