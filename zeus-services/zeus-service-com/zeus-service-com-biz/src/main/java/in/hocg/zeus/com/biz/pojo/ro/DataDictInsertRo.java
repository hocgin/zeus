package in.hocg.zeus.com.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/3/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class DataDictInsertRo {
    @NotBlank(message = "字典名称")
    @ApiModelProperty("字典名称")
    private String title;
    @ApiModelProperty("字典备注")
    private String remark;
    @NotNull
    @ApiModelProperty("字典码")
    private String code;
    @NotNull(message = "启用状态")
    @ApiModelProperty("启用状态")
    private Boolean enabled;
    @Valid
    private List<DataDictItemInsertRo> items = Collections.emptyList();

    @ApiModelProperty(hidden = true)
    private Long userId;
}
