package com.vista.java.thinkinjava.c5.examples;

/**
 * 5.2方法重载
 * 前引：因为已规定构造函数必须与类名相同，如果要定义多个参数形式的构造器时，就必须用到方法重载。
 * 同时其他方法也可以使用方法重载
 *
 * @author WenTingTing by 2020/5/4
 */
public class C5_2_overloaded {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Tree tree = new Tree(i);
            tree.info();
            tree.info("overloaded method");
        }
        new Tree();
    }
}
class Tree {
    int height;

    /**
     * 构造函数的重载
     */
    public Tree() {
        System.out.println("planting a seedling!!");
        height = 0;
    }

    public Tree(int height) {
        this.height = height;
        System.out.println("creating new tree that is " + height + " feet tall");
    }

    /**
     * 方法重载
     */
    void info() {
        System.out.println("tree is " + height + " feet tall");
    }

    void info(String s) {
        System.out.println(s + ":tree is " + height + " feet tall");
    }
}