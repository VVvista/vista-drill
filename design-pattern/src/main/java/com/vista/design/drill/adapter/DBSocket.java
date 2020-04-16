package com.vista.design.drill.adapter;

/**
 * 具体抽象类
 * 实现抽象类，实现方法的处理逻辑
 *
 * @author WenTingTing by 2020/4/16
 */
public class DBSocket implements DBSocketInterface {
    @Override
    public void powerWithTwoRound() {
        System.out.println("使用两项圆头的插孔供电");
    }
}
