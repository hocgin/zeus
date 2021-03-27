package in.hocg.zeus.ums.biz.pojo.vo;

import in.hocg.boot.web.datastruct.tree.AbstractTreeNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/2/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AuthorityTreeNodeVo extends AbstractTreeNode<AuthorityTreeNodeVo> {
    @ApiModelProperty("code")
    private String authorityCode;
}
