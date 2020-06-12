package vip.bblog.cunadmin.modules.file.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年06月12日 17:34
 * @desc UploadInfo
 */
@Data
public class UploadInfo implements Serializable {

    private static final long serialVersionUID = -8472025821311862106L;

    @NotBlank(message = "保存路径为空")
    private String prefix;

    @NotNull(message = "文件不能为空")
    private MultipartFile file;

}
