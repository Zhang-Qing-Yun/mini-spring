package com.qingyun.springframework.core.convert.converter;

/**
 * @description： 数据类型转换器
 * @author: 張青云
 * @create: 2021-08-28 21:02
 **/
public interface Converter<S, T>  {

    /** 数据类型转换，从S到T */
    T convert(S source);

}
