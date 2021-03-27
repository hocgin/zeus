package in.hocg.zeus.com.biz.controller;


import in.hocg.zeus.com.biz.pojo.ro.DataDictItemBatchInsertRo;
import in.hocg.zeus.com.biz.pojo.ro.DataDictItemUpdateRo;
import in.hocg.zeus.com.biz.pojo.vo.DataDictItemComplexVo;
import in.hocg.zeus.com.biz.service.DataDictItemService;
import in.hocg.zeus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
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

/**
 * <p>
 * [基础模块] 数据字典项表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/data-dict-item")
public class DataDictItemController {
    private final DataDictItemService service;

    @UseLogger("删除数据字典项")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }

    @UseLogger("新增数据字典项")
    @PostMapping("/{dictId}")
    public Result<Void> insertOne(@PathVariable("dictId") Long dictId,
                                  @RequestBody DataDictItemBatchInsertRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(dictId, ro);
        return Result.success();
    }

    @UseLogger("查询数据字典详情")
    @GetMapping("/{id:\\d+}")
    public Result<DataDictItemComplexVo> getComplex(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger("更新数据字典项")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@PathVariable Long id,
                                  @Validated @RequestBody DataDictItemUpdateRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }
}

