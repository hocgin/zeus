package in.hocg.zeus.com.biz.service;

import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;
import in.hocg.zeus.com.api.pojo.ro.UploadFileRo;
import in.hocg.zeus.com.api.pojo.vo.FileVo;
import in.hocg.zeus.com.biz.entity.File;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * [基础模块] 文件引用表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
public interface FileService extends AbstractService<File> {

    String upload(MultipartFile file);

    void upload(UploadFileRo dto);

    String getAvatarUrl(Long id);

    List<FileVo> listByRefTypeAndRefId(@NotNull String refType, @NotNull Long refId);
}
