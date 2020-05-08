package com.vista.java.thinkinjava.c7.examples;

/**
 * 7.2 继承语法
 * 子类构造器没有引用父类构造器时，java会自动在子类的构造器中插入对父类构造器的引用
 * 所以：调用子类的构造器时，先创建父类对象
 *
 * @author WenTingTing by 2020/5/8
 */
public class C7_2_2_Extends {
    public static void main(String[] args) {
        new Cartoon();//Art  Drawing  Cartoon
        new Cartoon(1);//Art  Drawing  Cartoon1



    }

}

class Art {
    Art() {
        System.out.println("Art ");
    }
}


class Drawing extends Art {
    Drawing() {
        System.out.println("Drawing ");
    }
}


class Cartoon extends Drawing {
    Cartoon() {
        System.out.println("Cartoon ");
    }

    Cartoon(int marker) {
        System.out.println("Cartoon" + marker);
    }
}

