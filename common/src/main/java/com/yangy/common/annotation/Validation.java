package com.yangy.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validation {

    public boolean notNull() default true;

    public boolean isValidate() default true;

    public boolean isForm() default false;

}
