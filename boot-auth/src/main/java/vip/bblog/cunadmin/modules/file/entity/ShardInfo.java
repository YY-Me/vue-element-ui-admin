package vip.bblog.cunadmin.modules.file.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年06月11日 15:45
 * @desc ShardInfo 文件分片信息
 */
@Data
public class ShardInfo implements Serializable {

    private static final long serialVersionUID = -381981147380441961L;

    @NotBlank(message = "上传id不能为空")
    private String uploadId;

    @NotNull(message = "总分片不能为空")
    private Integer totalShard;

    @NotNull(message = "当前分片不能为空")
    private Integer shard;

    @NotNull(message = "分片数据不能为空")
    private MultipartFile data;

}
