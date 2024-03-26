package org.example.annotation;

import java.lang.annotation.*;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Logs {
    /**
     * 使用功能的名称
     */
    String value() default "";

}
