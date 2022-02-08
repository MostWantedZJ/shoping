package com.highio.shoping.validator;


import com.highio.shoping.vo.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {MobileValidator.class}
)
public @interface IsMobile {

    boolean required() default true;

    String message() default "{手机格式错误}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
