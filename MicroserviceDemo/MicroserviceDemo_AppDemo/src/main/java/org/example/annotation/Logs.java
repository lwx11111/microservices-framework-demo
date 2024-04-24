package org.example.annotation;

import java.lang.annotation.*;
<<<<<<< HEAD
=======

/**
 * @author ljc
 * @Date 2024/3/25 11:42
 */
>>>>>>> a7b8e0ef96f8c093533dbae6faeaa78311048a6b
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Logs {
    /**
     * 使用功能的名称
     */
    String value() default "";

}
