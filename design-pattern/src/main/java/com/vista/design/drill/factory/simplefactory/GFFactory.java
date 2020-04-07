package com.vista.design.drill.factory.simplefactory;

import com.vista.design.drill.factory.*;

/**
 * @author WenTingTing by 2020/4/7
 */
public class GFFactory {
    public static BaseGF createGF(GFType type) {
        switch (type) {
            case HEI_BEI:
                return new HeBeiGF();
            case SI_CHUAN:
                return new SiChuanGF();
            case SHAN_DONG:
                return new ShanDongGF();
            default:
                return null;
        }
    }
}
