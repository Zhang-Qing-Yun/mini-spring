package com.qingyun.springframework.beans.stereotype;

import java.lang.annotation.*;

/**
 * @description： 标注该注解的类会被当作Bean来加载
 * @author: 張青云
 * @create: 2021-08-24 18:13
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
