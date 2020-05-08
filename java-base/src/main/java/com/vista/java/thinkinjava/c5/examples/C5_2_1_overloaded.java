package com.vista.java.thinkinjava.c5.examples;

/**
 * 5.2.1 区分重载方法
 * 问题：若方法名相同，如何区分调用的是哪个方法：
 * 每个重载的方法必须具有独一无二的参数列表(参数列表位置不同也可，但不好维护)
 * <p>
 * 注：当参数可以匹配多个重载方法时，选择参数匹配最优的方法
 *
 * @author WenTingTing by 2020/5/4
 */
public class C5_2_1_overloaded {

    public static void main(String[] args) {
        f("first", 11);// String:first,int:11
        f(99, "last");// int:99,String:last
        f(99, "second", "last");// int:99,String:second,String:last
new Rock();
    }

    /**
     * 方法重载之：参数列表不同
     *
     * @param s
     * @param t
     */
    static void f(String s, int t) {
        System.out.println("String:" + s + ",int:" + t);
    }

    static void f(int t, String s) {
        System.out.println("int:" + t + ",String:" + s);
    }

    static void f(int t, String s, String ss) {
        System.out.println("int:" + t + ",String:" + s + ",String:" + ss);
    }
}
