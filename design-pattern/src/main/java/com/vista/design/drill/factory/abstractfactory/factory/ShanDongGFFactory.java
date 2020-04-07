package com.vista.design.drill.factory.abstractfactory.factory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.ShanDongGF;
import com.vista.design.drill.factory.abstractfactory.father.IGFFather;
import com.vista.design.drill.factory.abstractfactory.father.ShanDongGFFather;

/**
 * @author WenTingTing by 2020/4/7
 */
public class ShanDongGFFactory implements IGFFactory {

    @Override
    public BaseGF createGF() {
        return new ShanDongGF();
    }

    @Override
    public IGFFather createGFFather() {
        return new ShanDongGFFather();
    }
}
