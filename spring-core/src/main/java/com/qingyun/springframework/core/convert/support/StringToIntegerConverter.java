package com.qingyun.springframework.core.convert.support;

import com.qingyun.springframework.core.convert.converter.Converter;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-28 21:58
 **/
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }

}
