package com.qingyun.springframework.beansTest;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-18 22:54
 **/
public class UserService {
    private String uId;

    private UserDao userDao;

    public UserService() {
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }
}
