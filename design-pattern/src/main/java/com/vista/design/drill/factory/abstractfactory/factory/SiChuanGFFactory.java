package com.vista.design.drill.factory.abstractfactory.factory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.SiChuanGF;
import com.vista.design.drill.factory.abstractfactory.father.IGFFather;
import com.vista.design.drill.factory.abstractfactory.father.SiChuanGFFather;

/**
 * @author WenTingTing by 2020/4/7
 */
public class SiChuanGFFactory implements IGFFactory {
    @Override
    public BaseGF createGF() {
        return new SiChuanGF();
    }

    @Override
    public IGFFather createGFFather() {
        return new SiChuanGFFather();
    }
}
