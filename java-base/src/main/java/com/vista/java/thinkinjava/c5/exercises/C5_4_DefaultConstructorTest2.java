package com.vista.java.thinkinjava.c5.exercises;

/**
 * Add an overloaded constructor to the previous exercise that takes a String argument and prints it along with your message
 *
 * @author WenTingTing by 2020/4/10
 */
public class C5_4_DefaultConstructorTest2 {
    public static void main(String[] args) {

        final Tester4 tester4 = new Tester4("hello tester4");// s:hello tester4
    }
}
class Tester4 {
    public Tester4(String s) {
        System.out.print("s:"+s);

    }
}