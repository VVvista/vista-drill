package com.vista.design.drill.prototype.deepclone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @author WenTingTing by 2020/4/8
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConcretePrototype implements Cloneable,Serializable {

    private String name;
    private int age;
    /**
     * person 实现Cloneable接口，实现clone()方法
     */
    private Person person;

    /**
     * 硬编码实现深拷贝，如果引用属性过多或嵌套太深，需要一直处理实现深拷贝
     */
    @Override
    public ConcretePrototype clone() {
        try {
            ConcretePrototype clone = (ConcretePrototype) super.clone();
            // 如果person仍存在引用属性，需要在person.clone()中实现深拷贝逻辑
            clone.person = clone.person.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 深克隆--将对象序列化到内存中在反序列化拿出来，得到一个新的对象
     */
    public ConcretePrototype deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            ConcretePrototype clone = (ConcretePrototype) ois.readObject();
            ois.close();
            return clone;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Person implements Cloneable,Serializable  {
    private String name;
    private int age;

    @Override
    public Person clone() {
        try {
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}