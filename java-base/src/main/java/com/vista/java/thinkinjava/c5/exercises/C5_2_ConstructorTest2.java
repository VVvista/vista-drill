package com.vista.java.thinkinjava.c5.exercises;

/**
 * Create a class with a String field that is initialized at the point of
 * definition, and another one that is initialized by the constructor. What is
 * the difference between the two approaches.
 *
 * @author WenTingTing by 2020/4/10
 */
public class C5_2_ConstructorTest2 {
    public static void main(String[] args) {
         Tester2 tester2 = new Tester2("jack");
        System.out.println(tester2.s1);// null
        System.out.println(tester2.s2);// abc
        System.out.println(tester2.s3);// jack
        C5_1_ConstructorTest test = new C5_1_ConstructorTest();
    }
}

class Tester2 {
    String s1;
    String s2="abc";
    String s3;

    public Tester2(String s3) {
        this.s3 = s3;
    }
}