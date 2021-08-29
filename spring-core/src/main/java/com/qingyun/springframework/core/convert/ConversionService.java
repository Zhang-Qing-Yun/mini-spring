package com.qingyun.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * @description： 类型转换抽象接口
 * @author: 張青云
 * @create: 2021-08-28 21:28
 **/
public interface ConversionService {

    /** 当原类型可以被转换成目的类型时返回true */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /** 进行数据类型的转换*/
    <T> T convert(Object source, Class<T> targetType);

}
