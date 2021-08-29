package com.qingyun.springframework.aop.test.threeCache;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-26 20:51
 **/
public class Husband {

    private Wife wife;

    public String queryWife(){
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

}
