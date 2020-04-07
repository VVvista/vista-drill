package com.vista.design.drill.factory.factory.factory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.HeBeiGF;

/**
 * @author WenTingTing by 2020/4/7
 */
public class HeBeiGFFactory implements IGFFactory {
    @Override
    public BaseGF createGF() {
        return new HeBeiGF();
    }
}
