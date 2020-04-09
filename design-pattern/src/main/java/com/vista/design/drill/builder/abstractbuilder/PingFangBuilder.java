package com.vista.design.drill.builder.abstractbuilder;

import com.vista.design.drill.builder.Product;

/**
 * @author WenTingTing by 2020/4/9
 */
public class PingFangBuilder implements AbstractBuilder {
    private Product product = new Product();

    @Override
    public void setId() {
        product.setId(13);
    }

    @Override
    public void setName() {
        product.setName("桌子");
    }

    @Override
    public void setDesc() {
        product.setDesc("办公桌");
    }

    @Override
    public Product build() {
        return product;
    }
}
