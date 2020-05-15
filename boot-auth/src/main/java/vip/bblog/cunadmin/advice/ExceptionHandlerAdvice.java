package vip.bblog.cunadmin.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.common.exception.BizException;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2020年05月15日 10:33
 * @desc ExceptionHandlerAdvice 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler({BizException.class})
    @ResponseStatus(HttpStatus.OK)
    public BaseResult<String> bizException(BizException bizException) {
        return new BaseResult<>(bizException.getBizExceptionEnum().getCode(), bizException.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult<String> badRequestException(Exception exception) {
        return new BaseResult<>(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), "");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult<String> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        StringBuilder errors = new StringBuilder();
        allErrors.forEach(a -> errors.append(a.getDefaultMessage()).append(";"));
        return new BaseResult<>(HttpStatus.BAD_REQUEST.value(), errors.toString(), "");
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult<String> methodArgumentNotValidException(BindException exception) {
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        StringBuilder errors = new StringBuilder();
        allErrors.forEach(a -> errors.append(a.getDefaultMessage()).append(";"));
        return new BaseResult<>(HttpStatus.BAD_REQUEST.value(), errors.toString(), "");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public BaseResult<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new BaseResult<>(HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage(), "");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResult<String> accessDeniedException() {
        return new BaseResult<>(HttpStatus.FORBIDDEN.value(), "您无权限操作");
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult<String> exception(Throwable throwable) {
        log.error("服务器异常", throwable);
        return new BaseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), throwable.getMessage(), "");
    }

}
