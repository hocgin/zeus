package in.hocg.zeus.ums.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * [权限模块] 用户组x权限表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ams_user_group_authority_ref")
public class UserGroupAuthorityRef extends AbstractEntity<UserGroupAuthorityRef> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("用户组")
    @TableField("user_group_id")
    private Long userGroupId;
    @ApiModelProperty("权限")
    @TableField("authority_id")
    private Long authorityId;



}
