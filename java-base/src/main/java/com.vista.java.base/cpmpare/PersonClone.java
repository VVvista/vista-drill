package com.vista.java.base.cpmpare;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1.未使用cloneable接口，重写clone或调用clone方法
 * 2.实现cloneable接口，未重写clone，直接调用clone方法
 * 3.对象拷贝（基本数据类型、引用数据类型、数组类型-基本/引用）
 * 4.数组拷贝
 *
 * @author WenTingTing by 2020/4/8
 */
public class PersonClone {

    /**
     * 1.未使用cloneable接口，重写clone或调用clone方法
     */
    @Test
    public void test1() throws CloneNotSupportedException {
/*
        // 普通类不包含拷贝的任意方法接口（即未实现cloneable接口，重写clone方法等），外部对象不能调用clone方法
        PersonC tom = new PersonC("tom", 19);
        tom.clone();// 调用失败
        */

/*
        // 普通类仅重写clone方法（未实现cloneable接口），外部对象调用clone方法，抛出异常：java.lang.CloneNotSupportedException
        PersonC tom = new PersonC("tom", 19);
        Object clone = tom.clone();// 调用成功，但运行时抛出异常：java.lang.CloneNotSupportedException

        */

  /*
        // 普通类仅实现cloneable接口（未重写clone方法），外部对象不能调用clone方法，抛出异常：java.lang.CloneNotSupportedException
        PersonC tom = new PersonC("tom", 19);
        Object clone = tom.clone();// 调用失败

        */

 /*
       // 普通类实现cloneable接口，重写clone方法，外部对象可以调用clone方法
        PersonC tom = new PersonC("tom", 19);
        Object clone = tom.clone();// 调用成功
        System.out.println(tom == clone);// false
        System.out.println(tom.equals(clone));// PersonC重写equals方法：true

      */


        // 修改拷贝前后的值，看是否有影响
        // PersonC仅包含基本数据类型、string
    /*   // 普通类实现cloneable接口，重写clone方法，外部对象可以调用clone方法
        PersonC tom = new PersonC("tom", 19);
        PersonC clone = (PersonC)tom.clone();// 调用成功
        clone.name="jack";
        System.out.println(tom.toString());// PersonC{name='tom', age=19}

        System.out.println(clone.toString());// PersonC{name='jack', age=19}*/

    }

    /**
     * PersonC包含引用数据类型
     *
     * @throws CloneNotSupportedException
     */
    @Test
    public void test2() throws CloneNotSupportedException {
/*        PersonC tom = new PersonC("tom", 19, new Person("tom", 19));
        PersonC clone = (PersonC) tom.clone();// 调用成功
        System.out.println(tom == clone);// false
        System.out.println(tom.equals(clone));// true

        System.out.println(tom.person.equals(clone.person));// true
        System.out.println(tom.toString());// PersonC{name='tom', age=19, Person=Person{name='tom', age=19}}
        System.out.println(clone.toString());// PersonC{name='tom', age=19, Person=Person{name='tom', age=19}}

        tom.person.name = "jack";
        System.out.println(tom.toString());// PersonC{name='tom', age=19, Person=Person{name='jack', age=19}}
        System.out.println(clone.toString());// PersonC{name='tom', age=19, Person=Person{name='jack', age=19}}*/
    }

    /**
     * PersonC包含list
     *
     * @throws CloneNotSupportedException
     */
    @Test
    public void test3() throws CloneNotSupportedException {
   /*     ArrayList<Person> list = new ArrayList<Person>();
        list.add(new Person("tom", 19));
        PersonC tom = new PersonC("tom", 19, list);

        PersonC clone = (PersonC) tom.clone();// 调用成功

        System.out.println(tom == clone);// false
        System.out.println(tom.equals(clone));// true

        System.out.println(tom.list == (clone.list));// 引用的地址值：true

        System.out.println(tom.list.equals(clone.list));// true
        System.out.println(tom.list.get(0) == clone.list.get(0));// true
        System.out.println(tom.list.get(0).equals(clone.list.get(0)));// true

        tom.list.add(new Person("jack", 20));

        System.out.println(tom.list.hashCode());// 211422965
        System.out.println(clone.list.hashCode());// 211422965

        System.out.println(tom.toString());//PersonC{name='tom', age=19, list=[Person{name='tom', age=19}, Person{name='jack', age=20}]}
        System.out.println(clone.toString());// PersonC{name='tom', age=19, list=[Person{name='tom', age=19}, Person{name='jack', age=20}]}
*/

    }

    /**
     * PersonC包含数组person[],浅拷贝
     *
     * @throws CloneNotSupportedException
     */
    @Test
    public void test4() throws CloneNotSupportedException {
        PersonC tom = new PersonC("tom", 19, new Person[]{new Person("tom", 19)});

        PersonC clone = (PersonC) tom.clone();// 调用成功

        System.out.println(tom == clone);// false
        System.out.println(tom.equals(clone));// true

        System.out.println(tom.list == (clone.list));// 引用的地址值：true

        System.out.println(tom.list.equals(clone.list));// true

        tom.list[0] = new Person("jack", 20);

        //tom.list=new Person[]{new Person("jack", 20)};

        System.out.println(tom.toString());// PersonC{name='tom', age=19, list=[Person{name='jack', age=20}]}
        System.out.println(clone.toString());// PersonC{name='tom', age=19, list=[Person{name='jack', age=20}]}
    }

    /**
     * PersonC重写clone方法，实现深拷贝
     * protected PersonC clone() throws CloneNotSupportedException {
     * PersonC clone = (PersonC) super.clone();
     * clone.list = Arrays.copyOf(this.list, this.list.length);
     * return clone;
     * }
     *
     * @throws CloneNotSupportedException
     */
    @Test
    public void test5() throws CloneNotSupportedException {
        PersonC tom = new PersonC("tom", 19, new Person[]{new Person("tom", 19)});

        PersonC clone = (PersonC) tom.clone();// 调用成功

        System.out.println(tom == clone);// false
        System.out.println(tom.equals(clone));// true

        System.out.println(tom.list == (clone.list));// 引用的地址值：false

        System.out.println(Arrays.equals(tom.list, (clone.list)));// true

        tom.list[0] = new Person("jack", 20);

        //tom.list=new Person[]{new Person("jack", 20)};

        System.out.println(tom.toString());// PersonC{name='tom', age=19, list=[Person{name='jack', age=20}]}
        System.out.println(clone.toString());// PersonC{name='tom', age=19, list=[Person{name='tom', age=19}]}
    }

    /**
     * PersonC重写clone方法，实现深拷贝
     * protected PersonC clone() throws CloneNotSupportedException {
     * PersonC clone = (PersonC) super.clone();
     * clone.person =this.person.clone();
     * return clone;
     * }
     *
     * @throws CloneNotSupportedException
     */
    @Test
    public void test6() throws CloneNotSupportedException {
        PersonC tom = new PersonC("tom", 19, new Person[]{new Person("tom", 19)});
        tom.person = new Person("tom", 19);
        PersonC clone = (PersonC) tom.clone();// 调用成功

        System.out.println(tom == clone);// false
        System.out.println(tom.person == (clone.person));// 引用的地址值：false
        tom.person.name = "jack";

        //tom.list=new Person[]{new Person("jack", 20)};

        System.out.println(tom.toString());// PersonC{name='tom', age=19, list=[Person{name='jack', age=20}]}
        System.out.println(clone.toString());// PersonC{name='tom', age=19, list=[Person{name='tom', age=19}]}

    }
    @Test
    public void test7() throws CloneNotSupportedException {
        Person[] toms = {new Person("tom", 19)};
        Person[] clone = toms.clone();
        System.out.println(toms == clone);// 实现数组拷贝，拷贝后地址值不同：false
        System.out.println(toms[0] == clone[0]);// 数组元素是浅拷贝，拷贝后元素地址值相同：true
    }

    @Test
    public void test8() throws CloneNotSupportedException {

        PersonC tom = new PersonC("tom", 19, new Person[]{new Person("tom", 19)});
        tom.person = new Person("tom", 19);
        PersonC clone = (PersonC) tom.clone();// 调用成功

        System.out.println(tom == clone);// false
        System.out.println(tom.person == (clone.person));// person属性浅拷贝：false
        System.out.println(tom.toString());// PersonC{name='tom', age=19, person=Person{name='tom', age=19}, list=[Person{name='tom', age=19}]}

        tom.person.name = "jack";

        //tom.list=new Person[]{new Person("jack", 20)};

        System.out.println(tom.toString());// PersonC{name='tom', age=19, person=Person{name='jack', age=19}, list=[Person{name='tom', age=19}]}
        System.out.println(clone.toString());// PersonC{name='tom', age=19, person=Person{name='jack', age=19}, list=[Person{name='tom', age=19}]}

    }

}

class PersonC implements Comparable<PersonC>, Cloneable {
    String name;
    int age;
    Person person;
    //   List<Person> list;
    Person[] list;

    public PersonC(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public PersonC(String name, int age, Person[] list) {
        this.name = name;
        this.age = age;
        this.list = list;
    }

    @Override
    public int compareTo(PersonC o) {
        return this.age - o.age;
    }

    @Override
    protected PersonC clone() throws CloneNotSupportedException {
     /*   PersonC clone = (PersonC) super.clone();
        clone.list = Arrays.copyOf(this.list, this.list.length);
        return clone;*/
        PersonC clone = (PersonC) super.clone();
        clone.person = this.person.clone();
        return clone;
   //  return (PersonC) super.clone();
    }


    @Override
    public String toString() {
        return "PersonC{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", person=" + person +
                ", list=" + Arrays.toString(list) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonC personC = (PersonC) o;

        if (age != personC.age) return false;
        if (name != null ? !name.equals(personC.name) : personC.name != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(list, personC.list);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + Arrays.hashCode(list);
        return result;
    }
}
