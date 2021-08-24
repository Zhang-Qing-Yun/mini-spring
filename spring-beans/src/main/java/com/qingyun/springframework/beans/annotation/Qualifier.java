package com.qingyun.springframework.beans.annotation;

import java.lang.annotation.*;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-24 23:42
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
