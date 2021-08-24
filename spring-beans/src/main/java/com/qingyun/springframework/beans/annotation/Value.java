package com.qingyun.springframework.beans.annotation;

import java.lang.annotation.*;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-24 23:41
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    /**
     * The actual value expression: e.g. "#{systemProperties.myProp}".
     */
    String value();

}
