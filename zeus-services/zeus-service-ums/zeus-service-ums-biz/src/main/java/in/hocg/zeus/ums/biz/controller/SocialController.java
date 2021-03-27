package in.hocg.zeus.ums.biz.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [用户模块] 绑定社交账号表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since2020-11-30
 */
@Api(tags = "ums::社交账号")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/social")
public class SocialController {

}

