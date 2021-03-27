package in.hocg.zeus.ums.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.pojo.ro.GetAuthorityUserPagingRo;
import in.hocg.zeus.ums.biz.pojo.ro.GrantRoleRo;
import in.hocg.zeus.ums.biz.pojo.ro.GrantUserGroupRo;
import in.hocg.zeus.ums.biz.pojo.ro.SaveAuthorityRo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import in.hocg.zeus.ums.biz.pojo.vo.UserRoleComplexVo;
import in.hocg.zeus.ums.biz.service.AuthorityService;
import in.hocg.zeus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;

import in.hocg.boot.validation.autoconfigure.group.Insert;
import in.hocg.boot.validation.autoconfigure.group.Update;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [权限模块] 权限表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Api(tags = "ums::权限")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/authority")
public class AuthorityController {
    private final AuthorityService service;

    @ApiOperation("权限详情 - 权限")
    @GetMapping("/{id}")
    @ResponseBody
    public Result<AuthorityComplexVo> getAuthority(@ApiParam(value = "权限", required = true) @PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @ApiOperation("新增权限 - 权限")
    @PostMapping
    @ResponseBody
    public Result<Void> insertOne(@Validated({Insert.class}) @RequestBody SaveAuthorityRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(ro);
        return Result.success();
    }

    @ApiOperation("修改权限 - 权限")
    @PutMapping("/{id}")
    @ResponseBody
    public Result<Void> updateOne(@ApiParam(value = "权限", required = true) @PathVariable Long id,
                                  @Validated({Update.class}) @RequestBody SaveAuthorityRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }

    @ApiOperation("删除权限 - 权限")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result<Void> deleteOne(@ApiParam(value = "权限", required = true) @PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }

    @ApiOperation("获取权限树 - 权限")
    @GetMapping("/tree")
    @ResponseBody
    public Result<List<AuthorityTreeNodeVo>> tree() {
        return Result.success(service.listAuthorityTree(true));
    }

    @ApiOperation("获取权限树(所有) - 权限")
    @GetMapping("/tree/all")
    @ResponseBody
    public Result<List<AuthorityTreeNodeVo>> allTree() {
        return Result.success(service.listAuthorityTree(null));
    }

    @ApiOperation("分页获取用户列表 - 权限")
    @PostMapping("/{id}/users")
    @ResponseBody
    public Result<IPage<UserRoleComplexVo>> listUserByAuthorityId(@ApiParam(value = "权限", required = true) @PathVariable Long id,
                                                                  @Validated @RequestBody GetAuthorityUserPagingRo ro) {
        return Result.success(service.pagingUserByAuthorityId(id, ro));
    }

    @UseLogger("给角色授权权限")
    @PostMapping("/{authorityId}/grant/role")
    public Result<Void> grantRole(@PathVariable Long authorityId,
                                  @Validated @RequestBody GrantRoleRo ro) {
        service.grantRole(authorityId, ro);
        return Result.success();
    }

    @UseLogger("给用户组授权权限")
    @PostMapping("/{authorityId}/grant/user-group")
    public Result<Void> grantUserGroup(@PathVariable Long authorityId,
                                       @Validated @RequestBody GrantUserGroupRo ro) {
        service.grantUserGroup(authorityId, ro);
        return Result.success();
    }

    @ApiOperation("导出权限sql - 权限")
    @GetMapping("/export")
    @ResponseBody
    public ResponseEntity<?> exportSql(@ApiParam(value = "文件名")
                                       @RequestParam(value = "filename", required = false, defaultValue = "all_authority_sql.sql") String filename) {
        String sql = service.generateSql();
        return ResponseEntity
            .ok()
            .headers(new HttpHeaders() {{
                setContentDisposition(ContentDisposition.builder("attachment").filename(filename).build());
                setCacheControl(CacheControl.noCache());
                setPragma("no-cache");
                setExpires(0L);
            }})
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(sql);
    }
}

