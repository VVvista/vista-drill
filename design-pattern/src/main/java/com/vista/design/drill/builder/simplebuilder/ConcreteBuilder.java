package com.vista.design.drill.builder.simplebuilder;

import com.vista.design.drill.builder.Product;

/**
 * @author WenTingTing by 2020/4/9
 */
public class ConcreteBuilder {
    private Product product=new Product();

    public void setId(int id) {
        product.setId(id);
    }

    public void setName(String name) {
        product.setName(name);
    }

    public void setDesc(String desc) {
        product.setDesc(desc);
    }
    public Product build() {
        return this.product;
    }

}
