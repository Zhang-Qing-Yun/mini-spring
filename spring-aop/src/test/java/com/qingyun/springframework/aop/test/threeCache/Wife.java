package com.qingyun.springframework.aop.test.threeCache;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-26 20:51
 **/
public class Wife {

    private Husband husband;
    private IMother mother; // 婆婆

    public String queryHusband() {
        return "Wife.husband、Mother.callMother：" + mother.callMother();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }

}
