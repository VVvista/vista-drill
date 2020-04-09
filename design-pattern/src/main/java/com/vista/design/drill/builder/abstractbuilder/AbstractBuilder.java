package com.vista.design.drill.builder.abstractbuilder;

import com.vista.design.drill.builder.Product;

/**
 * @author WenTingTing by 2020/4/9
 */
public interface AbstractBuilder {

    void setId();

    void setName();

    void setDesc();

    Product build();

}
