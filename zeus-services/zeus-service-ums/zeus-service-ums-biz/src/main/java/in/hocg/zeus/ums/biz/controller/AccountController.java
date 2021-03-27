package in.hocg.zeus.ums.biz.controller;


import in.hocg.zeus.ums.biz.entity.User;
import in.hocg.zeus.ums.biz.pojo.ro.UpdateAccountEmailRo;
import in.hocg.zeus.ums.biz.pojo.ro.UpdateAccountPhoneRo;
import in.hocg.zeus.ums.biz.pojo.ro.UpdateAccountRo;
import in.hocg.zeus.ums.biz.pojo.vo.AccountComplexVo;
import in.hocg.zeus.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import in.hocg.zeus.ums.biz.service.UserService;
import in.hocg.zeus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import in.hocg.boot.web.utils.web.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * [用户模块] 账号表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
@Api(tags = "ums::账号")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/account")
public class AccountController {
    private final UserService service;

    @UseLogger("获取用户头像")
    @ApiOperation("获取用户头像")
    @GetMapping("/{username}:avatar")
    @ResponseBody
    public ResponseEntity<?> getAvatarUrl(@ApiParam("用户名") @PathVariable String username) {
        return ResponseUtils.preview(service.getAccountByUsernameOrEmailOrPhone(username)
            .map(User::getAvatarUrl).orElse(null));
    }

    @UseLogger("获取当前用户信息")
    @ApiOperation("获取当前用户信息")
    @GetMapping
    @ResponseBody
    public Result<AccountComplexVo> getCurrentAccount() {
        Long userId = UserContextHolder.getUserIdThrow();
        return Result.success(service.getComplexById(userId));
    }

    @UseLogger("获取当前用户权限")
    @ApiOperation("获取当前用户权限")
    @GetMapping("/authority/tree")
    @ResponseBody
    public Result<List<AuthorityTreeNodeVo>> listTreeCurrentAuthority(@ApiParam("项目编号") @RequestParam(value = "project", required = false) String projectSn) {
        Long userId = UserContextHolder.getUserIdThrow();
        return Result.success(service.listTreeCurrentAuthority(projectSn, userId));
    }

    @UseLogger("获取当前用户权限编码")
    @ApiOperation("获取当前用户权限编码")
    @GetMapping("/authority")
    @ResponseBody
    public Result<List<String>> listCurrentAuthorityCode(@ApiParam("项目编号") @RequestParam(value = "project", required = false) String projectSn) {
        Long userId = UserContextHolder.getUserIdThrow();
        return Result.success(service.listCurrentAuthorityCode(projectSn, userId));
    }

    @UseLogger("账号信息 - 修改")
    @ApiOperation("账号信息 - 修改")
    @PostMapping
    @ResponseBody
    public Result<Long> updateAccount(@Validated @RequestBody UpdateAccountRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setId(userId);
        ro.setUpdaterId(userId);
        return Result.success(service.updateAccount(userId, ro));
    }

    @UseLogger("账号手机号 - 修改")
    @ApiOperation("账号手机号 - 修改")
    @PostMapping("/phone")
    @ResponseBody
    public Result<Long> updatePhone(@Validated @RequestBody UpdateAccountPhoneRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setId(userId);
        ro.setUpdaterId(userId);
        return Result.success(service.updatePhone(ro));
    }

    @UseLogger("账号邮箱 - 修改")
    @ApiOperation("账号邮箱 - 修改")
    @PostMapping("/email")
    @ResponseBody
    public Result<Long> updateEmail(@Validated @RequestBody UpdateAccountEmailRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setId(userId);
        ro.setUpdaterId(userId);
        return Result.success(service.updateEmail(ro));
    }


}

