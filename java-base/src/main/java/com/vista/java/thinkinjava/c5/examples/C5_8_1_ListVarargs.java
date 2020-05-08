package com.vista.java.thinkinjava.c5.examples;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 5.8.1 可变参数列表
 * 可变参数列表中的参数类型：基本数据类型、应用数据类型、Object均可
 * 当方法参数列表为可变参数列表时，实际调用时：
 * 1.传递数组
 * 2.传递不定参数，编译器会将不定参数列表转换为数组
 * 3.参数个数为0:转换的数组长度为0
 * 若传递不定参数列表，编译器会将参数转化为数组传递给调用方法；若传递的为数组，则直接调用方法，编译器不进行转化
 * 方法内可以用for循环遍历参数中的每个元素；可以使用Arrays.asList(**)输出所有参数
 *
 * @author WenTingTing by 2020/5/7
 */
public class C5_8_1_ListVarargs {

    public static void main(String[] args) {

        Varargs varargs = new Varargs();
        varargs.f(1, 2, 3);// 参数自动装箱
        System.out.println();
        varargs.f(new Integer(1), new Integer(2));// 传递可变参数列表
        System.out.println();
        varargs.f(new Integer(1), 2);
        System.out.println();
        varargs.f();// 参数为空，length:0,arrays.list:[]
        System.out.println();
        varargs.f(new Integer[]{1, 2, 3});//传递数组： 1 2 3 length:3,arrays.list:[1, 2, 3]
    }

}

class Varargs {

    public void f(Integer... args) {
        for (Integer i : args) {
            System.out.print(i + " ");
        }
        System.out.println("length:" + args.length);
        System.out.println("arrays.list:" + Arrays.asList(args));
    }
}