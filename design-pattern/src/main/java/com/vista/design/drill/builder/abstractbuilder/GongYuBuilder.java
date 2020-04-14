package com.vista.design.drill.builder.abstractbuilder;

import com.vista.design.drill.builder.Product;

/**
 * @author WenTingTing by 2020/4/9
 */
public class GongYuBuilder implements AbstractBuilder {
    private Product product = new Product();

    @Override
    public void setId() {
        product.setId(12);
    }

    @Override
    public void setName() {
        product.setName("椅子");
    }

    @Override
    public void setDesc() {
        product.setDesc("宜家-莫塔利索系列");
    }

    @Override
    public Product build() {
        return product;
    }
}



