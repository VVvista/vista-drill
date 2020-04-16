package com.vista.design.drill.adapter.classadapter;

import com.vista.design.drill.adapter.DBSocketInterface;

/**
 * 适配器
 * 继承适配者，实现抽象类，实现抽象类方法：底层调用适配者方法
 *
 * @author WenTingTing by 2020/4/16
 */
public class SocketAdapter extends GBSocket implements DBSocketInterface {

    @Override
    public void powerWithTwoRound() {
        super.powerWithThreeFlat();
    }

}
