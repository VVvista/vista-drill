package com.vista.design.drill.adapter;

import com.vista.design.drill.adapter.DBSocketInterface;

/**
 * 客户端调用类
 * 添加抽象类的引用，对外提供公共访问方法，方法中调用抽象类对象的方法
 *
 * @author WenTingTing by 2020/4/16
 */
public class Hotel {
    /**
     * 旅馆中有一个德标的插口
     */

    private DBSocketInterface dbSocket;

    public void setDbSocket(DBSocketInterface dbSocket) {
        this.dbSocket = dbSocket;
    }

    /**
     * 旅馆中有一个充电的功能
     */
    public void charge() {
        dbSocket.powerWithTwoRound();
    }

}
