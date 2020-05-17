package vip.bblog.cunadmin.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2020年05月15日 10:25
 * @desc BaseResult 响应对象
 */
@ApiModel(description = "响应信息")
@Data
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -4151233557950079315L;

    private static final int SUCCESS_CODE = 200;

    private static final String SUCCESS_MESSAGE = "ok";

    @ApiModelProperty(value = "响应码", name = "status", required = true, example = "" + SUCCESS_CODE)
    private int status;

    @ApiModelProperty(value = "响应消息", name = "message", required = true, example = SUCCESS_MESSAGE)
    private String message;

    @ApiModelProperty(value = "响应数据", name = "data")
    private T data;

    public BaseResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public BaseResult() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public BaseResult(int code, String msg) {
        this(code, msg, null);
    }

    public BaseResult(T data) {
        this(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> BaseResult<T> success() {
        return new BaseResult<>();
    }

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<>(data);
    }

    public static <T> BaseResult<T> build(int code, String msg) {
        return new BaseResult<>(code, msg, null);
    }


}
