package vip.bblog.cunadmin.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import vip.bblog.cunadmin.common.enums.BizExceptionEnum;

import java.io.Serializable;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年05月15日 10:45
 * @desc BizException 业务异常处理
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class BizException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8073588683551379959L;

    private BizExceptionEnum bizExceptionEnum = BizExceptionEnum.BIZ_EXCEPTION;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
        log.warn(message);
    }

    public BizException(BizExceptionEnum bizExceptionEnum) {
        super(bizExceptionEnum.getMessage());
        this.bizExceptionEnum = bizExceptionEnum;
        log.warn(bizExceptionEnum.getMessage());
    }
}
