package com.vista.design.drill.builder.simplebuilder;

import com.vista.design.drill.builder.Product;

/**
 * @author WenTingTing by 2020/4/9
 */
public class ConcreteLinkedBuilder {
    private Product product = new Product();

    public ConcreteLinkedBuilder setId(int id) {
        product.setId(id);
        return this;
    }

    public ConcreteLinkedBuilder setName(String name) {
        product.setName(name);
        return this;
    }

    public ConcreteLinkedBuilder setDesc(String desc) {
        product.setDesc(desc);
        return this;
    }

    public Product build() {
        return this.product;
    }
}
