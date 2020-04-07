package com.vista.java.base.compara;

import java.util.Arrays;

/**
 * @author WenTingTing by 2020/4/7
 */
public class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] a) {
        Person[] people = new Person[]{new Person("xujian", 20), new Person("xiewei", 10)};
        System.out.println("排序前");
        System.out.println(Arrays.toString(people));// [Person{name='xujian', age=20}, Person{name='xiewei', age=10}]
        System.out.println("排序后");
        Arrays.sort(people);
        System.out.println(Arrays.toString(people)); // [Person{name='xiewei', age=10}, Person{name='xujian', age=20}]
    }
}
