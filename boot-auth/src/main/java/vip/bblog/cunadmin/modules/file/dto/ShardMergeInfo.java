package vip.bblog.cunadmin.modules.file.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年06月12日 10:53
 * @desc ShardMergeInfo
 */
@Data
public class ShardMergeInfo implements Serializable {

    private static final long serialVersionUID = -5305027756194025462L;

    @NotBlank(message = "上传id为空")
    private String uploadId;

    @NotBlank(message = "保存路径为空")
    private String prefix;

    @NotBlank(message = "文件名为空")
    private String fileName;
}
