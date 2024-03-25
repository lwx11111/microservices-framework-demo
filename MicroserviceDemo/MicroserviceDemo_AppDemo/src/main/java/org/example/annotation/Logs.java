package org.example.annotation;

import java.lang.annotation.*;

/**
 * @Author 刘文轩
 * @Date 2024/3/25 11:42
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Logs {
    /**
     * 使用功能的名称
     */
    String value() default "";

}
