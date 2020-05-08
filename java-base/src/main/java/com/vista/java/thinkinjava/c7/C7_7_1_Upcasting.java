package com.vista.java.thinkinjava.c7;

/**
 * 7.7 向上转型
 * 本质是多态
 *
 * @author WenTingTing by 2020/5/8
 */
public class C7_7_1_Upcasting {
    public static void main(String[] args) {
        Instrument wind = new Wind();
        Instrument.tune(wind);
    }

}

class Instrument {
    void play() {

    }

    static void tune(Instrument i) {
        i.play();
    }
}

class Wind extends Instrument {

}