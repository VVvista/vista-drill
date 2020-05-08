package com.vista.java.thinkinjava.c5.examples;

/**
 * 5.1 用构造器确保初始化
 * 规则：构造器名称与类名相同
 * <p>
 * 注:
 * 1.当执行new Rock() 时，将会在堆中为对象分配存储空间，并调用对应的构造函数，完成初始化。使得后续对象可以正常调用
 * 2.不接受任何参数额构造函数为默认构造函数(无参构造函数)
 *
 * @author WenTingTing by 2020/5/4
 */
public class C5_1_Constructor {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Rock();// Rock!Rock!Rock!Rock!Rock!Rock!Rock!Rock!Rock!Rock!
        }
    }
}

class Rock {
    Rock() {
        System.out.print("Rock!");
        new Tree();
    }
}