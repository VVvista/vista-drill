package com.vista.java.thinkinjava.c7.examples;

/**
 * 7.2 继承语法
 * 继承：extends 关键字连接两个类的关系。
 * Object类是所有类的超父类
 *
 * 重点：
 * 子类继承父类所有的属性和方法；
 * 子类可以调用父类所有对外展示的属性和方法
 *
 * 疑点：
 * 子类是否可以重写 或调用父类的静态方法
 * @author Wen TingTing by 2020/5/7
 */
public class C7_7_2_1_Extends {
    public static void main(String[] args) {
        Detergent detergent = new Detergent();
        detergent.dilute();
        detergent.apply();
        detergent.scrub();
        detergent.foam();
        System.out.println(detergent);//Cleaner  dilute apply Detergent.scrub() scrub foam
        Cleaner.method();//Cleaner  dilute apply scrub
    }
}

class Cleaner{
    private String s="Cleaner ";
    public void append(String a){
        s+=a;
    }
    public void dilute(){
        append(" dilute");
    }
    public void apply(){
        append(" apply");
    }
    public void scrub(){
        append(" scrub");
    }

    @Override
    public String toString() {
        return s;
    }
    public static void method(){
        Cleaner cleaner = new Cleaner();
        cleaner.dilute();
        cleaner.apply();
        cleaner.scrub();
        System.out.println(cleaner);
    }
}

class Detergent extends Cleaner{
    @Override
    public void scrub() {
        append(" Detergent.scrub()");
        super.scrub();
    }
    public void foam(){
        append(" foam");
    }
}