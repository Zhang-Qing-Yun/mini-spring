package com.qingyun.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description： 有着Bean定义的资源以及相关操作
 * @author: 張青云
 * @create: 2021-08-20 10:06
 **/
public interface Resource {
    /**
     * 从资源中获取输入流
     * @return 输入流
     * @throws IOException IO异常
     */
    InputStream getInputStream() throws IOException;
}
