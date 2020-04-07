package com.vista.design.drill.factory.abstractfactory.factory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.HeBeiGF;
import com.vista.design.drill.factory.abstractfactory.father.HeBeiGFFather;
import com.vista.design.drill.factory.abstractfactory.father.IGFFather;

/**
 * @author WenTingTing by 2020/4/7
 */
public class HeBeiGFFactory implements IGFFactory {
    @Override
    public BaseGF createGF() {
        return new HeBeiGF();
    }

    @Override
    public IGFFather createGFFather() {
        return new HeBeiGFFather();
    }
}
