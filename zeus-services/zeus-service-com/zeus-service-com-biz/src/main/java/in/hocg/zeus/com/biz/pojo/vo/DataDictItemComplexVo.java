package in.hocg.zeus.com.biz.pojo.vo;

import in.hocg.zeus.chaos.api.ChaosNamedApi;
import in.hocg.zeus.chaos.api.NamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.boot.named.annotation.UseNamedService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/2/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
public class DataDictItemComplexVo {
    private Long id;
    private Long dictId;
    private String title;
    private String code;
    private String remark;
    private Integer priority;
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("最后更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("最后更新时间")
    private Long lastUpdater;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;
}
