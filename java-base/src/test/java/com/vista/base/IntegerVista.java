package com.vista.base;

import org.junit.Test;

/**
 * https://juejin.im/post/5c16041f518825566d236529#heading-2
 *
 * @author WenTingTing by 2020/3/31
 */
public class IntegerVista {
    /**
     * Integer.value用final修饰，不能重新赋值，只能重新赋值对象
     * -128~127封装类直接在缓存中获取，其他需要new Integer(i)
     */
    @Test
    public void test1() {
        // 实际编译：Integer i = new Integer(10);
        // i = Integer.valueOf(5);
        Integer i = new Integer(10);
        // 涉及自动装箱
        i = 5;
    }

    /**
     * Integer的构造器：
     * new Integer(int);
     * new Integer(String);
     */
    @Test
    public void test2() {
        Integer i = new Integer(10);
        Integer s = new Integer("100");
        System.out.println(s);
    }

    /**
     * Integer.valueOf方法：
     * Integer.valueOf(int);
     * Integer.valueOf(String);
     * Integer类初始化时将-128~127对象放在cache中，调用valueOf()或自动装箱时从缓存中获取，超出范围的new Integer(xx).
     */
    @Test
    public void test3() {

        Integer n1 = new Integer(10);
        Integer n2 = new Integer(10);
        Integer i1 = Integer.valueOf(10);
        Integer i2 = Integer.valueOf(10);
        Integer s1 = Integer.valueOf("200");
        Integer s2 = Integer.valueOf("200");
        Integer z1 = 10;// 自动装箱，底层调用Integer.valueOf(10)
        Integer z2 = 10;
        Integer x1 = 200;// 自动装箱，底层调用Integer.valueOf(200)
        Integer x2 = 200;
        System.out.println(n1 == n2);// false
        System.out.println(i1 == i2);// true
        System.out.println(s1 == s2);// false
        System.out.println(z1 == z2);// true
        System.out.println(x1 == x2);// false
    }

    /**
     * Integer.parseInt方法：
     * Integer.parseInt(String);
     */
    @Test
    public void test4() {
        int i = Integer.parseInt("200");
        System.out.println(i);// true
    }
}
