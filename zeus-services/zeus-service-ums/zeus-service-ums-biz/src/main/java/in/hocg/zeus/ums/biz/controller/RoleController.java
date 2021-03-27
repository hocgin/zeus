package in.hocg.zeus.ums.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.pojo.ro.AssignRoleRo;
import in.hocg.zeus.ums.biz.pojo.ro.GrantAuthorityRo;
import in.hocg.zeus.ums.biz.pojo.ro.RoleCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.RolePagingRo;
import in.hocg.zeus.ums.biz.pojo.ro.SaveRoleRo;
import in.hocg.zeus.ums.biz.pojo.vo.RoleComplexVo;
import in.hocg.zeus.ums.biz.service.RoleService;
import in.hocg.zeus.usercontext.autoconfigure.UserContextHolder;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.validation.autoconfigure.group.Insert;
import in.hocg.boot.validation.autoconfigure.group.Update;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [权限模块] 角色表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Api(tags = "ums::角色")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/role")
public class RoleController {
    private final RoleService service;

    @PostMapping("/_complete")
    @ApiOperation("检索 - 角色")
    @ApiOperationSupport(author = "hocgin")
    public Result<List<RoleComplexVo>> complete(@Validated @RequestBody RoleCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @ApiOperation("查询详情 - 角色")
    @GetMapping("/{id}")
    public Result<RoleComplexVo> get(@ApiParam(value = "角色", required = true) @PathVariable Long id) {
        return Result.success(service.getRole(id));
    }

    @ApiOperation("新增角色 - 角色")
    @PostMapping
    public Result<Long> insertOne(@Validated({Insert.class}) @RequestBody SaveRoleRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(ro);
        return Result.success();
    }

    @ApiOperation("分页查询角色 - 角色")
    @PostMapping("/_paging")
    public Result<IPage<RoleComplexVo>> paging(@Validated @RequestBody RolePagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("修改角色 - 角色")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@ApiParam(value = "角色", required = true) @PathVariable Long id,
                                           @Validated({Update.class}) @RequestBody SaveRoleRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }

    @ApiOperation("删除角色 - 角色")
    @DeleteMapping("/{id}")
    public Result<Void> deleteOne(@ApiParam(value = "角色", required = true) @PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }

    @ApiOperation("分配用户 - 角色")
    @PostMapping("/{id}/assign")
    public Result<Void> assignRole(@ApiParam(value = "角色", required = true) @PathVariable Long id,
                                   @Validated @RequestBody AssignRoleRo ro) {
        service.assignRole(id, ro);
        return Result.success();
    }

    @UseLogger("给角色授权权限")
    @PostMapping("/{roleId}/grant/authority")
    public Result<Void> grantAuthority(@PathVariable Long roleId,
                                       @Validated @RequestBody GrantAuthorityRo ro) {
        service.grantAuthority(roleId, ro);
        return Result.success();
    }
}

