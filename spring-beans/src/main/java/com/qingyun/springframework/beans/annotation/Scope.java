package com.qingyun.springframework.beans.annotation;

import java.lang.annotation.*;

/**
 * @description： Bean的类型：单例还是原型（多例）
 * @author: 張青云
 * @create: 2021-08-24 18:04
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
