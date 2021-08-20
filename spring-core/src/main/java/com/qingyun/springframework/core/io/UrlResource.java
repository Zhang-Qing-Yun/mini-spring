package com.qingyun.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @description： 资源存放在网络上
 * @author: 張青云
 * @create: 2021-08-20 10:15
 **/
public class UrlResource implements Resource{

    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url,"URL must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException ex){
            throw ex;
        } finally {
            if (con instanceof HttpURLConnection){
                ((HttpURLConnection) con).disconnect();
            }
        }
    }

}
