package com.vista.java.thinkinjava.c7.examples;

/**
 * 7.1组合语法
 * 组合：类中添加对象的引用。
 * 基本类型：直接定义
 * 非基本类型：对象的引用
 * 可以在全局变量、方法参数、局部变量中添加对象的引用
 * 此处仅考虑全局变量
 * <p>
 * 重点：
 * 1.print方法底层调用的类.toString()方法
 * 2."string"+对象：底层 "string"+ 对象.toString()进行字符串拼接
 * 3.对象转换为字符串：调用toString()方法，默认为对象地址值
 *
 * @author Wen TingTing by 2020/5/7
 */
public class C7_7_1_Composition {
    public static void main(String[] args) {
        SprinklerSystem system = new SprinklerSystem();//WaterSource()
        System.out.println(system);//value1=null value1=null value2=null value3=null value4=null i=0 f=0.0 source=Constructed
    }
}

class WaterSource {
    private String s;

    WaterSource() {
        System.out.println("WaterSource()");
        s = "Constructed";
    }

    public String toString() {
        return s;
    }
}

class SprinklerSystem {
    private String value1, value2, value3, value4;
    private WaterSource source = new WaterSource();
    private int i;
    private float f;

    public String toString() {
        return
                "value1=" + value1 + " " +
                        "value1=" + value1 + " " +
                        "value2=" + value2 + " " +
                        "value3=" + value3 + " " +
                        "value4=" + value4 + " " +
                        "i=" + i + " " +
                        "f=" + f + " " +
                        "source=" + source;
    }

}


