package com.qingyun.springframework.context.test.type;

import com.qingyun.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-28 21:58
 **/
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter DATE_TIME_FORMATTER;

    public StringToLocalDateConverter(String pattern) {
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source, DATE_TIME_FORMATTER);
    }

}
