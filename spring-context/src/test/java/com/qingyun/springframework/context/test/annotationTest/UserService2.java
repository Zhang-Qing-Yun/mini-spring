package com.qingyun.springframework.context.test.annotationTest;

import com.qingyun.springframework.beans.annotation.Autowired;
import com.qingyun.springframework.beans.annotation.Value;
import com.qingyun.springframework.beans.stereotype.Component;

import java.util.Random;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-24 23:52
 **/
@Component("userService")
public class UserService2 implements IUserService {

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，" + token;
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}