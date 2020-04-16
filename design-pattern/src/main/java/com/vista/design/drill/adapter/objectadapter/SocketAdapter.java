package com.vista.design.drill.adapter.objectadapter;

import com.vista.design.drill.adapter.DBSocketInterface;

/**
 * 适配器
 * 实现抽象类，添加适配者引用，重写抽象类方法：底层调用适配者引用的方法
 *
 * @author WenTingTing by 2020/4/16
 */
public class SocketAdapter implements DBSocketInterface {
    private GBSocketInterface gbSocket;

    public SocketAdapter(GBSocketInterface gbSocket) {
        this.gbSocket = gbSocket;
    }

    @Override
    public void powerWithTwoRound() {
        gbSocket.powerWithThreeFlat();
    }
}
