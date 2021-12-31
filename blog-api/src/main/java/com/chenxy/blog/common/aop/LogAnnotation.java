package com.chenxy.blog.common.aop;

import java.lang.annotation.*;

/**
 * 日志注解
 */
//TYPE表示可以放在类上 METHOD表示可以放在方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";
    String operation() default "";
}
