package com.vista.design.drill.facade.regularfacade;

/**
 * 外观模式：
 * 1.创建子模块Module
 * 2.创建外观类，添加子模块的引用，并对外暴露子模块方法组合的调用方法接口
 * <p>
 * <p>
 * 特点：
 * 1.客户端通过外观类调用子模块系统，客户端对子模块是完全解耦的
 * 2.客户端无需创建和知道子模块构造和调用逻辑，直接访问外观类即可
 * 3.不同的子模块方法组合可以通过创建不同的外观类实现，客户端只需与外观类通信即可
 *
 * @author WenTingTing by 2020/4/14
 */
public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.test();
        facade.test1();
    }
}
