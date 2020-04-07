package com.vista.java.base.compara;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author WenTingTing by 2020/4/7
 */
public class PersonCompartor implements Comparator<Person1> {
    @Override
    public int compare(Person1 o1, Person1 o2) {
        return o1.name.compareTo(o2.name);
    }

    public static void main(String[] a) {
        Person1[] people = new Person1[]{new Person1("xujian", 20), new Person1("xiewei", 10)};
        System.out.println("排序前");
        System.out.println(Arrays.toString(people));// [Person{name='xujian', age=20}, Person{name='xiewei', age=10}]
        System.out.println("排序后");
        // 方式1：自定义比较器
        Arrays.sort(people, new PersonCompartor());
        System.out.println(Arrays.toString(people));// [Person{name='xiewei', age=10}, Person{name='xujian', age=20}]
        // 方式2：自定义匿名比较器
        Arrays.sort(people, new Comparator<Person1>() {

            @Override
            public int compare(Person1 o1, Person1 o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        System.out.println(Arrays.toString(people));// [Person{name='xiewei', age=10}, Person{name='xujian', age=20}]
    }

}


class Person1 {
    String name;
    int age;

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
