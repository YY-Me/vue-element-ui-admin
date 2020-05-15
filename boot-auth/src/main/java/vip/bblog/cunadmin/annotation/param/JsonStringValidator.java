package vip.bblog.cunadmin.annotation.param;

import com.alibaba.fastjson.JSON;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2019年10月10日 15:54
 * @desc JsonStringValidator 校验字符串是json字符串
 */
public class JsonStringValidator implements ConstraintValidator<JsonString, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (null == value) {
            return true;
        }
        try {
            JSON.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
