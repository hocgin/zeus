package in.hocg.zeus.ums.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.ums.biz.pojo.ro.ApiCompleteRo;
import in.hocg.zeus.ums.biz.pojo.ro.ApiPagingRo;
import in.hocg.zeus.ums.biz.pojo.ro.ApiSaveRo;
import in.hocg.zeus.ums.biz.pojo.vo.ApiComplexVo;
import in.hocg.zeus.ums.biz.service.ApiService;
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
 * [权限模块] 接口表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Api(tags = "ums::接口")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/api")
public class ApiController {
    private final ApiService service;

    @ApiOperation("分页查询 - 接口")
    @PostMapping("/_paging")
    public Result<IPage<ApiComplexVo>> paging(@Validated @RequestBody ApiPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("检索 - 接口")
    @UseLogger("检索 - 接口")
    @PostMapping("/_complete")
    public Result<List<ApiComplexVo>> complete(@Validated @RequestBody ApiCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @ApiOperation("新增 - 接口")
    @PostMapping
    public Result<Void> insertOne(@Validated({Insert.class}) @RequestBody ApiSaveRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(ro);
        return Result.success();
    }

    @ApiOperation("修改 - 接口")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@ApiParam(value = "接口", required = true) @PathVariable Long id,
                                  @Validated({Update.class}) @RequestBody ApiSaveRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }

    @ApiOperation("查询详情 - 接口")
    @GetMapping("/{id}")
    public Result<ApiComplexVo> getComplex(@ApiParam(value = "接口", required = true) @PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @ApiOperation("删除 - 接口")
    @DeleteMapping("/{id}")
    public Result<Void> deleteOne(@ApiParam(value = "接口", required = true) @PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }
}

