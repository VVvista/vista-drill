package com.vista.java.thinkinjava.c7.examples;

/**
 * 带参数的构造器
 * <p>
 * 子类所有的构造器中必须指明调用父类的构造器(首行语句)
 * 1.父类无参构造器：可以省略调用语句（默认调用无参构造器）
 * 2.有参构造器：使用super(xx)指明调用的父类构造器
 *
 * @author WenTingTing by 2020/5/8
 */
public class C7_2_3_Extends {
    public static void main(String[] args) {
        new Chess(1);
    }
}

class Game {
    Game(int i) {
        System.out.println("Game " + i);
    }
}

class BoardGame extends Game {
    /*   当父类没有无参构造器时，子类必须显式使用super调用父类的构造器，否则编译报错：
    there is no default constructor available in Game
         BoardGame(int i) {
            System.out.println("BoardGame " + i);
        }*/

    BoardGame(int i) {
        super(i);
        System.out.println("BoardGame " + i);
    }
}

class Chess extends BoardGame {
    Chess(int i) {
        super(i);
        System.out.println("Chess " + i);
    }
}