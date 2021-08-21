package com.qingyun.springframework.beans.factory;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-21 23:09
 **/
public interface DisposableBean {

    /**
     * 销毁Bean前调用的方法
     */
    void destroy() throws Exception;
}
