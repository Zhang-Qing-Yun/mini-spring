package com.qingyun.springframework.context.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-19 00:13
 **/
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "青云先生");
        hashMap.put("10002", "張青云");
        hashMap.put("10003", "云鸽");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
