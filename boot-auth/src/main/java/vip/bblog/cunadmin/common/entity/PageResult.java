package vip.bblog.cunadmin.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年05月15日 10:30
 * @desc PageResult
 */
@ApiModel(description = "分页响应信息")
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult<T> extends BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 3086977781477220031L;

    @ApiModelProperty(value = "总记录数")
    private long count;

    public PageResult() {
        super();
        this.count = 0;
    }

    public PageResult(T data, long count) {
        super(data);
        this.count = count;
    }

    public static <T> PageResult<T> success(T data, long count) {
        return new PageResult<T>(data, count);
    }
}
