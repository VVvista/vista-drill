package com.vista.java.thinkinjava.c5.examples;

/**
 * 5.7 构造器初始化
 * 5.7.1 初始化顺序-类全局变量的初始化
 * 1.全局变量初始化：变量的定义顺序决定了初始化顺序；不管变量定义位置是在任意方法(包含构造器)之前或之后，都优先进行初始化
 * 2.方法(包含构造器)被调用
 * <p>
 * 为何要先进行全局变量的初始化：
 * 若变量定义或构造器中都没有进行初始化，那么在方法中调用 变量.method() 抛空指针异常
 * (引用变量的初始化值：null)
 * <p>
 * 注意：
 * 1.全局变量先在初始化时被赋予初始值，在构造器或方法中又被重新赋值
 * 2.全局变量只在类初始化时初始化一次
 *
 * @author WenTingTing by 2020/5/6
 */
public class C5_7_1_Initialization {
    public static void main(String[] args) {
        // 练习1
        new Counter();
        // 练习2
        House house = new House();// Window(1)  Window(2)  Window(3)  House()  Window(33)
        house.f();// f()
    }
}

/**
 * 重点：
 * 全局变量的初始化：变量初始化先后与定义顺序有关；变量初始化不受其与方法先后顺序影响
 */
class Window {

    Window(int market) {
        System.out.println("Window(" + market + ")");
    }
}

 class House {
    Window w1 = new Window(1);

    House() {
        System.out.print("House()");
        // 全局变量定义位置不一定与要在方法调用之前
        w3 = new Window(33);
    }

    Window w2 = new Window(2);

    void f() {
        System.out.print("f()");
    }

    Window w3 = new Window(3);
}


/**
 * 重点：全局变量先在方法调用前进行初始化，方法后再被重新赋值
 */
class Counter {
    /**
     * 调用构造函数前，先进行i的初始化:i=0;
     * 然后在构造函数中，再对i赋值:i=7
     */
    int i;

    Counter() {
        System.out.print("i 赋值前:" + i);// i 赋值前:0
        i = 7;
        System.out.print("i 赋值后:" + i);// i 赋值后:7
    }

}