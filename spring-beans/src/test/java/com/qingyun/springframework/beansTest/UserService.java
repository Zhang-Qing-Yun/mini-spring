package com.qingyun.springframework.beansTest;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-18 22:54
 **/
public class UserService {
    private String user;

    public UserService() {
    }

    public UserService(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
