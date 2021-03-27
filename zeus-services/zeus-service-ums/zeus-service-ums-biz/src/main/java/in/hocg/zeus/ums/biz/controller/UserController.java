package in.hocg.zeus.ums.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.pojo.ro.RoleGrantUserRo;
import in.hocg.zeus.ums.biz.pojo.ro.UserCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.UserPagingRo;
import in.hocg.zeus.ums.biz.pojo.vo.AccountComplexVo;
import in.hocg.zeus.ums.biz.service.UserService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [用户模块] 账号表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
@Api(tags = "ums::用户")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @ApiOperation("查询账号列表")
    @PostMapping({"/_paging"})
    @UseLogger("查询账号列表")
    public Result<IPage<AccountComplexVo>> paging(@Validated @RequestBody UserPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("查询列表 - 账号")
    @PostMapping("/_complete")
    @UseLogger("查询列表 - 账号")
    public Result<List<AccountComplexVo>> complete(@Validated @RequestBody UserCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @ApiOperation("给账号授权角色")
    @PostMapping("/{userId:\\d+}/grant/role")
    @UseLogger("给账号授权角色")
    public Result<Void> grantRole(@PathVariable Long userId,
                                  @Validated @RequestBody RoleGrantUserRo ro) {
        service.grantRole(userId, ro);
        return Result.success();
    }

    @ApiOperation("获取账号信息")
    @GetMapping("/{id:\\d+}")
    @UseLogger("获取账号信息")
    public Result<AccountComplexVo> getComplex(@PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }
}

