package com.vista.design.drill.prototype.shallowclone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WenTingTing by 2020/4/8
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConcretePrototype implements Cloneable {

    private String name;
    private int age;

    private Person person;

    @Override
    public ConcretePrototype clone() {
        try {
            return (ConcretePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Person {
    private String name;
    private int age;
}