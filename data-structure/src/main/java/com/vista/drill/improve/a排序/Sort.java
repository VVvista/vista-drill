package com.vista.drill.improve.a排序;

/**
 * 排序公共接口
 *
 * @author WenTingTing by 2020/4/27
 */
public abstract class Sort {
    protected Integer[] array;

    public Sort(Integer[] array) {
        this.array = array;
    }

    public abstract void sort();
}
