package com.vista.design.drill.factory.abstractfactory.factory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.abstractfactory.father.IGFFather;

/**
 * @author WenTingTing by 2020/4/7
 */
public interface IGFFactory {
    BaseGF createGF();
    IGFFather createGFFather();
}
