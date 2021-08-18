package com.qingyun.springframework.beans;

/**
 * @description： 有关bean的异常
 * @author: 張青云
 * @create: 2021-08-18 16:45
 **/
public class BeansException extends RuntimeException{
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
