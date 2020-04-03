package com.vista.base;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Wen TingTing by 2020/4/2
 */
public class ArraysVista {
    /**
     * asList方法
     */
    @Test
    public void test1() {
        String[] str = new String[]{"a", "b", "c"};
        List<String> list = Arrays.asList(str);
        System.out.println(Arrays.toString(str));//[a, b, c]
        list.set(1, "e");
        System.out.println(Arrays.toString(str));//[a, e, c]
        list.add("d");// 抛异常：UnsupportedOperationException

    }

    /**
     * asList方法
     * 引用数据类型和基本类型的数组区别
     */
    @Test
    public void test2() {
        /*编译不通过，因为Arrays.asList(T)必须为泛型对象，而基本数据类型不能为泛型
        int[] i = {1, 2, 3};
        List<int> iList = Arrays.asList(i);
        List<Integer> iList = Arrays.asList(i);// 编译失败

        */
        int[] i = {1, 2, 3};
        List iList = Arrays.asList(i);// 此时是将int[]看做一个对象进行了封装
        List<int[]> list = Arrays.asList(i);
        List iiList = Collections.singletonList(i);
        System.out.println(Arrays.toString(i));//[1, 2, 3]
        System.out.println(iList.size());//1

        Integer[] n = {1, 2, 3};
        List<Integer> nList = Arrays.asList(n);
        System.out.println(Arrays.toString(n));//[1, 2, 3]
        System.out.println(nList.size());//3

        String[] str = new String[]{"a", "b", "c"};
        List<String> strList = Arrays.asList(str);
        System.out.println(Arrays.toString(str));//[a, b, c]
        System.out.println(strList.size());//3
    }

    /**
     * equals方法
     */
    @Test
    public void test3() {
        String[] str1 = new String[]{"a", "b", "c"};
        String[] str2 = new String[]{"a", "b", "c"};
        System.out.println(Arrays.equals(str1, str2));//true

        String[][] strT1 = new String[][]{{"a", "b"}, {"c", "d"}};
        String[][] strT2 = new String[][]{{"a", "b"}, {"c", "d"}};
        System.out.println(Arrays.equals(strT1, strT2));// false
        System.out.println(Arrays.deepEquals(strT1, strT2));//true

        Student[] stu1 = new Student[]{new Student(10, "tom"), new Student(12, "jack")};
        Student[] stu2 = new Student[]{new Student(10, "tom"), new Student(12, "jack")};
        System.out.println(Arrays.equals(stu1, stu2));// Student未重写equals方法：false；重写equals方法：true
    }

    /**
     * toString方法
     */
    @Test
    public void test4() {
        String[] str1 = new String[]{"a", "b", "c"};
        System.out.println(Arrays.toString(str1));//[a, b, c]

        String[][] strT1 = new String[][]{{"a", "b"}, {"c", "d"}};
        System.out.println(strT1[0]);// [Ljava.lang.String;@593634ad
        System.out.println(Arrays.toString(strT1[0]));// [a, b]
        System.out.println(Arrays.toString(strT1));// [[Ljava.lang.String;@593634ad, [Ljava.lang.String;@20fa23c1]
        System.out.println(Arrays.deepToString(strT1));//[[a, b], [c, d]]

        Student[] stu = new Student[]{new Student(10, "tom"), new Student(12, "jack")};
        /**
         * Student未重写toString方法：
         */
        System.out.println(stu[0]);// com.vista.leetcode.list.Student@6aa8ceb6
        System.out.println(Arrays.toString(stu));// [com.vista.leetcode.list.Student@6aa8ceb6, com.vista.leetcode.list.Student@2530c12]
        System.out.println(Arrays.deepToString(stu));//[com.vista.leetcode.list.Student@6aa8ceb6, com.vista.leetcode.list.Student@2530c12]
        /**
         * Student重写toString方法：
         */
        System.out.println(stu[0]);// Student{age=10, name='tom'}
        System.out.println(Arrays.toString(stu));// [Student{age=10, name='tom'}, Student{age=12, name='jack'}]
        System.out.println(Arrays.deepToString(stu));//[Student{age=10, name='tom'}, Student{age=12, name='jack'}]

        Student[][] stuT1 = new Student[][]{{new Student(10, "tom")}, {new Student(12, "jack")}};

        System.out.println(stuT1[0]);// 地址值[Lcom.vista.base.Student;@2957fcb0
        System.out.println(Arrays.toString(stuT1));// 地址值[[Lcom.vista.base.Student;@2957fcb0, [Lcom.vista.base.Student;@1376c05c]
        System.out.println(Arrays.deepToString(stuT1));//元素值[[Student{age=10, name='tom'}], [Student{age=12, name='jack'}]]


    }

    /**
     * hashCode方法
     */
    @Test
    public void test5() {
        int[] i = {1, 2, 3};
        System.out.println(Arrays.hashCode(i));//30817

        Integer[] n = {1, 2, 3};
        System.out.println(Arrays.hashCode(n));//30817

        String[] str = new String[]{"a", "b", "c"};
        System.out.println(Arrays.hashCode(str));//126145
    }

    /**
     * sort方法
     */
    @Test
    public void test6() {
        int[] i = {1, 3, 8, 5, 2, 4, 6, 7};
        Arrays.sort(i);
        System.out.println(Arrays.toString(i));//[1, 2, 3, 4, 5, 6, 7, 8]

        Integer[] n = {1, 3, 8, 5, 2, 4, 6, 7};
        Arrays.sort(n);
        System.out.println(Arrays.toString(n));//[1, 2, 3, 4, 5, 6, 7, 8]

        String[] str = new String[]{"a", "f", "c", "d"};
        Arrays.sort(str);
        System.out.println(Arrays.toString(str));//[a, c, d, f]

        int[] iRange = {1, 3, 8, 5, 2, 4, 6, 7};
        Arrays.sort(iRange, 1, 5);//[1,5)
        System.out.println(Arrays.toString(iRange));//[1, 2, 3, 5, 8, 4, 6, 7]


        Student[] stu = new Student[]{new Student(10, "tom"), new Student(12, "jack")};
        Arrays.sort(stu);//[1,5)
        System.out.println(Arrays.toString(stu));//[1, 2, 3, 5, 8, 4, 6, 7]
    }

    /**
     * binarySearch方法
     */
    @Test
    public void test7() {

        int[] i = {1, 3, 8, 5, 2, 4, 6, 7};
        System.out.println(Arrays.binarySearch(i, 2));// -2
        System.out.println(Arrays.binarySearch(i, 1, 5, 7));//-3
        System.out.println(Arrays.binarySearch(i, 1, 5, 8));//2

        Arrays.sort(i);
        System.out.println(Arrays.toString(i));//[1, 2, 3, 4, 5, 6, 7, 8]
        System.out.println(Arrays.binarySearch(i, 2));//1
        System.out.println(Arrays.binarySearch(i, 1, 5, 4));//3
        System.out.println(Arrays.binarySearch(i, 1, 5, 7));//-6

    }

    /**
     * fill方法
     */
    @Test
    public void test8() {

        int[] i = {1, 3, 8, 5, 2, 4, 6, 7};
        Arrays.fill(i, 2);
        System.out.println(Arrays.toString(i));//[2, 2, 2, 2, 2, 2, 2, 2]

        int[] n = {1, 3, 8, 5, 2, 4, 6, 7};
        Arrays.fill(n, 1, 5, 10);
        System.out.println(Arrays.toString(n));//[1, 10, 10, 10, 10, 4, 6, 7]
    }

    /**
     * copyOf方法
     */
    @Test
    public void test9() {

        int[] i = {1, 3, 8, 5, 2, 4, 6, 7};
        int[] iCopy = Arrays.copyOf(i, i.length);
        System.out.println(Arrays.toString(iCopy));//[1, 3, 8, 5, 2, 4, 6, 7]

        int[] iRange = Arrays.copyOfRange(i, 1, 5);
        System.out.println(Arrays.toString(iRange));//[3, 8, 5, 2]
    }

}


class Student implements Comparable<Student> {
    int age;
    String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        return name != null ? name.equals(student.name) : student.name == null;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }
}
