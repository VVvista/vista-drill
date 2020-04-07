package com.vista.design.drill.factory.factory.factory;

import com.vista.design.drill.factory.BaseGF;
import com.vista.design.drill.factory.SiChuanGF;

/**
 * @author WenTingTing by 2020/4/7
 */
public class SiChuanGFFactory implements IGFFactory {
    @Override
    public BaseGF createGF() {
        return new SiChuanGF();
    }
}
