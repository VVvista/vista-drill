package com.vista.java.thinkinjava.c7.example;

/**
 * Create two classes, AA and BB, with default constructors (empty argument
 * lists) that announce themselves. Inherit AA new class called CC from AA, and
 * create AA member of class BB inside CC. Do not create AA constructor for CC. Create
 * an object of class CC and observe the resluts.
 *
 * @author WenTingTing by 2020/5/8
 */
public class C7_5_Extends {
}

class AA {
    AA() {
        System.out.println("AA()");
    }
}

class BB extends AA {
    BB() {
        System.out.println("BB()");
    }
}

class CC extends AA {
    BB BB = new BB(); // will then construct another AA and then AA BB

    public static void main(String[] args) {
        CC CC = new CC(); // will construct an AA first
    }
}