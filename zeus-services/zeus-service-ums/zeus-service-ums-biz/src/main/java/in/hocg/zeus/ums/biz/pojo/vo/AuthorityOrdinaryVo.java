package in.hocg.zeus.ums.biz.pojo.vo;

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
 * Created by hocgin on 2020/10/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@ApiModel("权限详情")
public class AuthorityOrdinaryVo {
    private Long id;
    @ApiModelProperty("父ID")
    private Long parentId;
    @ApiModelProperty("权限编码")
    private String encoding;
    private Long projectId;
    @ApiModelProperty("权限名称")
    private String title;
    @ApiModelProperty("权限描述")
    private String remark;
    @ApiModelProperty("开启状态")
    private Boolean enabled;

    @ApiModelProperty("创建者")
    private Long creator;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
}
