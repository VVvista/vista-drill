package com.vista.design.drill.proxy.staticproxy;

/**
 * 代理类
 * 添加具体类的引用，实现抽象类，拥有与具体相同的接口和方法，对外暴露具体类能够访问的方法
 * 客户端访问具体对象的代理类
 *
 * @author WenTingTing by 2020/4/13
 */
public class StationProxy implements Subject {
    private Subject station;

    public StationProxy(Subject station) {
        this.station = station;
    }

    @Override
    public void sell() {
        station.sell();
    }
}
