package vip.bblog.cunadmin.annotation.param;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2019年10月10日 15:44
 * @desc JsonString 自定义校验规则：必须是json对象或数组的字符串
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {JsonStringValidator.class}
)
@Repeatable(JsonString.List.class)
public @interface JsonString {

    String message() default "illegal json string";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        JsonString[] value();
    }
}
