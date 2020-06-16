package vip.bblog.cunadmin.modules.file.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年06月11日 14:31
 * @desc File 文件
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FileInfo对象", description = "系统-文件信息")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = -1034843121397398866L;

    private String md5;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否是目录")
    private Boolean dir;

    @ApiModelProperty(value = "文件大小，单位：字节")
    private long size;

    @ApiModelProperty(value = "格式化后的大小")
    private String formattedSize;

    @ApiModelProperty(value = "文件后缀")
    private String suffix;

    @ApiModelProperty(value = "如：image/jpeg")
    private String contentType;

    @ApiModelProperty(value = "文件的path")
    private String path;

    @ApiModelProperty(value = "文件的url")
    private String url;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
