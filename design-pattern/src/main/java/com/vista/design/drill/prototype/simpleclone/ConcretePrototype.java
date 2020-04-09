package com.vista.design.drill.prototype.simpleclone;


import lombok.*;

/**
 * @author WenTingTing by 2020/4/8
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConcretePrototype implements Prototype {
    private String name;
    private int age;

    @Override
    public ConcretePrototype clone() {
        ConcretePrototype clone = new ConcretePrototype();
        clone.name = this.name;
        clone.age = this.age;
        return  clone;
    }



}
