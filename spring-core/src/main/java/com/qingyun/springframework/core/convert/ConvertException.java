package com.qingyun.springframework.core.convert;

/**
 * @description： 类型转换异常
 * @author: 張青云
 * @create: 2021-08-28 23:36
 **/
public class ConvertException extends RuntimeException {
    public ConvertException() {
    }

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
