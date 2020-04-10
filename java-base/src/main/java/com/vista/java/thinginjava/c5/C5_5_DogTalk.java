package com.vista.java.thinginjava.c5;

/**
 * Create a class with a default constructor (one that takes no arguments) that
 * prints a message. Create an object of this class.
 *
 * @author WenTingTing by 2020/4/10
 */
public class C5_5_DogTalk {
    public static void main(String[] args) {
        final Dog dog = new Dog();
        char c = 'c';
        byte b = 0;
        short s = 0;

        // 类型匹配，优先选用精准形参列表方法
        dog.bark(c);// char
        dog.bark(b); //byte
        dog.bark(0); // int
        dog.bark((int) c); // int
        dog.bark((double) c); // double
        dog.bark(1); // int
        dog.bark(1F); // float
        dog.bark(1.0);// double

        // 自动类型提升匹配
        dog.eat(c);// int
        dog.eat(b);// int
        dog.eat(1);// int
        dog.eat(1.0);// double
        dog.eat(1f);// double

        // 强转匹配
        dog.sleep(c);//char
        dog.sleep((char) b); // 必须强转，否则编译报错; char
        dog.sleep((char) 1.0f);//char
    }
}

class Dog {
    void bark() {
        System.out.println("quiet");
    }

    void bark(char c) {
        System.out.println("char quiet");
    }

    void bark(byte b) {
        System.out.println("byte quiet");
    }

    void bark(short s) {
        System.out.println("short quiet");
    }

    void bark(int i) {
        System.out.println("int quiet");
    }

    void bark(long L) {
        System.out.println("long quiet");
    }

    void bark(float f) {
        System.out.println("float quiet");
    }

    void bark(double d) {
        System.out.println("double quiet");
    }

    void eat(int i) {
        System.out.println("int eat quiet");
    }

    void eat(double d) {
        System.out.println("double eat quiet");
    }


    void sleep(char d) {
        System.out.println("char sleep quiet");
    }
}
