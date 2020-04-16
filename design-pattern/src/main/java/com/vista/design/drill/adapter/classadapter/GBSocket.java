package com.vista.design.drill.adapter.classadapter;

import com.vista.design.drill.adapter.objectadapter.GBSocketInterface;

/**
 * 具体适配者
 * 实现适配者接口，实现方法的处理逻辑
 *
 * @author WenTingTing by 2020/4/16
 */
public class GBSocket implements GBSocketInterface {
    @Override
    public void powerWithThreeFlat() {
        System.out.println("使用三项扁头插孔供电");
    }
}
