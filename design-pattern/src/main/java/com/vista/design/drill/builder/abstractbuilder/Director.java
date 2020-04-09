package com.vista.design.drill.builder.abstractbuilder;

/**
 * @author WenTingTing by 2020/4/9
 */
public class Director {

    public void makeProduct(AbstractBuilder builder) {
        builder.setId();
        builder.setName();
        builder.setDesc();
    }

    public void makeSimpleProduct(AbstractBuilder builder) {
        builder.setId();
        builder.setName();
    }
}
