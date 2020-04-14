package com.vista.design.drill.facade.simplefacade;

import com.vista.design.drill.facade.ModuleA;
import com.vista.design.drill.facade.ModuleB;
import com.vista.design.drill.facade.ModuleC;

/**
 * 客户端直接创建并调用子模块方法
 * 缺点：客户端需要知道子模块的构造，并自创建子模块对象和调用方法
 * 如果调用子模块对象或方法过多，使得客户端代码过于复杂
 *
 * @author WenTingTing by 2020/4/14
 */
public class Main {
    public static void main(String[] args) {
        ModuleA moduleA = new ModuleA();
        ModuleB moduleB = new ModuleB();
        ModuleC moduleC = new ModuleC();

        moduleA.testA();
        moduleB.testB();
        moduleC.testC();

    }
}
