package in.hocg.zeus.sso.controller;

import in.hocg.boot.web.result.Result;
import in.hocg.zeus.sso.pojo.ro.JoinAccountRo;
import in.hocg.zeus.sso.pojo.ro.LoginRo;
import in.hocg.zeus.sso.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

@Api(tags = "sso::账号")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AccountController {
    private final AccountService service;

    @ApiOperation("获取 Token")
    @PostMapping("/login/token")
    @ResponseBody
    public Result<String> login(@RequestBody LoginRo ro) {
        return Result.success(service.login(ro));
    }

    @ApiOperation("注册账号")
    @PostMapping("/join")
    @ResponseBody
    public Result<String> join(@RequestBody JoinAccountRo ro) {
        return Result.success(service.join(ro));
    }
}
