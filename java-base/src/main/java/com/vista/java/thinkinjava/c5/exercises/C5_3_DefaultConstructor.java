package com.vista.java.thinkinjava.c5.exercises;

/**
 * Create a class with a default constructor (one that takes no arguments) that
 * prints a message. Create an object of this class.
 *
 * @author WenTingTing by 2020/4/10
 */
public class C5_3_DefaultConstructor {
    public static void main(String[] args) {
        final Tester3 tester3 = new Tester3();// 输出hello tester3
    }
}

class Tester3 {
    public Tester3() {
        System.out.print("hello tester3");

    }
}