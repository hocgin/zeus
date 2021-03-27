package in.hocg.zeus.com.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.zeus.com.biz.pojo.ro.DataDictInsertRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictPagingRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictUpdateRo;
import in.hocg.zeus.com.biz.pojo.vo.DataDictComplexVo;
import in.hocg.zeus.com.biz.pojo.vo.DataDictOrdinaryVo;
import in.hocg.zeus.com.biz.service.DataDictService;
import in.hocg.zeus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.datastruct.KeyValue;
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
 * [基础模块] 数据字典表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Api(tags = "com::数据字典")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/data-dict")
public class DataDictController {
    private final DataDictService service;

    @ApiOperation("查询 - 数据字典项")
    @GetMapping("/code:{code}")
    public Result<List<KeyValue>> listKeyValueByCodeAndEnabledIsOn(@ApiParam(value = "数据字典类型", required = true) @PathVariable("code") String code) {
        return Result.success(service.listKeyValueByCodeAndEnabledIsOn(code));
    }

    @ApiOperation("查询 - 数据字典项(所有)")
    @GetMapping("/{code}/all")
    public Result<List<KeyValue>> listKeyValueByCode(@ApiParam(value = "数据字典类型", required = true) @PathVariable("code") String code) {
        return Result.success(service.listKeyValueByCode(code));
    }

    @UseLogger("查询 - 数据字典详情")
    @GetMapping("/{id:\\d+}")
    public Result<DataDictComplexVo> getComplex(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger("批量删除 - 数据字典")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return Result.success();
    }

    @UseLogger("新增 - 数据字典")
    @PostMapping
    public Result<Void> insertOne(@Validated @RequestBody DataDictInsertRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger("更新 - 数据字典")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@PathVariable Long id,
                                  @Validated @RequestBody DataDictUpdateRo ro) {
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger("分页查询 - 数据字典列表")
    @PostMapping({"/_paging"})
    public Result<IPage<DataDictOrdinaryVo>> paging(@Validated @RequestBody DataDictPagingRo ro) {
        return Result.success(service.paging(ro));
    }


}

