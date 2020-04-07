package com.vista.design.drill.factory.factory.factory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.ShanDongGF;

/**
 * @author WenTingTing by 2020/4/7
 */
public class ShanDongGFFactory implements IGFFactory {

    @Override
    public BaseGF createGF() {
        return new ShanDongGF();
    }
}
