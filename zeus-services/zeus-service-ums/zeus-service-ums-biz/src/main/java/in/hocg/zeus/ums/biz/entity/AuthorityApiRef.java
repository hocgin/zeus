package in.hocg.zeus.ums.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [权限模块] 权限x接口表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ams_authority_api_ref")
public class AuthorityApiRef extends AbstractEntity<AuthorityApiRef> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("权限ID")
    @TableField("authority_id")
    private Long authorityId;
    @ApiModelProperty("接口ID")
    @TableField("api_id")
    private Long apiId;



}
