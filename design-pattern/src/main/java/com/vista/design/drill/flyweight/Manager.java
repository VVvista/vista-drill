package com.vista.design.drill.flyweight;

/**
 * 具体享元类
 * 实现抽象享元类，实现公共方法的处理逻辑
 *
 * @author Wen TingTing by 2020/4/12
 */

public class Manager implements Employee {
    private int number;
    private String name;

    public Manager(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void report() {
        System.out.println(name);
    }



}
