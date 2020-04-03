package com.vista.base;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wen TingTing by 2020/4/2
 */
public class StringVista {
    @Test
    public void test() {
        String s = "abcd";
        String s1 = "ab";
        String s2 = s1 + "cd";
        String s3 = "ab" + "cd";

        System.out.println(s == s2);//false
        System.out.println(s.equals(s2));//true
        System.out.println(s == s3);//true

        final String a1 = "ab";
        String a2 = a1 + "cd";
        System.out.println(s == a2);//true

        String string = "hollis";
        String string2 = (new StringBuilder(String.valueOf(string))).append("chuang").toString();

    }

    @Test
    public void test11() {
        char[] c = {'a', 'b', 'c', 'd'};
        String cStr = new String(c);

        System.out.println(c);//abcd
        System.out.println(cStr);//abcd

        c[0] = '1';
        System.out.println(c);//1bcd
        System.out.println(cStr);//abcd
    }

    @Test
    public void test1() {
        String s = "abcd";
        System.out.println(Arrays.toString(s.getBytes()));//[97, 98, 99, 100]

        char[] c = {'a', 'b', 'c', 'd'};
        s.getChars(0, 1, c, 2);
        System.out.println(c);//abad

        /*
        注意：println默认调用打印者的toString方法，
        所以直接打印数组（string[ ]）输出的为地址值，使用Arrays.toString才能正确输出数组元素
        但对于char[]，println直接打印元素值，而不是地址值
         */
        String[] str = {"aa", "bb", "cc", "dd"};
        System.out.println(str);//[Ljava.lang.String;@593634ad
        System.out.println(Arrays.toString(str));//[aa, bb, cc, dd]
        System.out.println(c);//abad
        System.out.println(s.toCharArray());//abcd

    }

    @Test
    public void test2() {
        String[] str = {"aa", "bb", "cc", "dd"};
        System.out.println(Arrays.toString(str));//[aa, bb, cc, dd]

        char[] c = {'a', 'b', 'c', 'd'};
        System.out.println(Arrays.toString(c));//[a, b, c, d]
        System.out.println(String.valueOf(c));//abcd

        int[] i = {1, 2, 3, 4};
        String iStr = String.valueOf(i);
        System.out.println(iStr);//将i看做一个object整体传入new String：[I@593634ad
    }

    @Test
    public void test3() {
        char[] c = {'a', 'b', 'c', 'd', 'a'};
        int[] i = {1, 2, 3, 4};
        String s = "abcda";
        String cStr = String.valueOf(c);

        System.out.println(s.length());//
        System.out.println(s.isEmpty());//
        System.out.println(s.hashCode());//
        System.out.println(s.equals(cStr));//true
        System.out.println(s == cStr);//false

        System.out.println(s.replaceAll("a", "1"));//1bcd1
        System.out.println(s.replace("a", "1"));//1bcd1
        System.out.println(s.replace('a', '3'));//3bcd3
        System.out.println(s.replaceFirst("a", "2"));//2bcda
        //  System.out.println(s.regionMatches("a","2"));//2bcda

    }

    @Test
    public void test4() {
        String s = "abcda";
        String s1 = new String(new char[]{'A', 'B', 'c', 'd', 'a'});
        String s2 = new String(new char[]{'a', 'b', 'c', 'D', 'A'});

        System.out.println(s1.toLowerCase());//abcda
        System.out.println(s2.toUpperCase());//ABCDA
        System.out.println(s1.equals(s2));//false
        System.out.println(s1.equalsIgnoreCase(s2));//true

        System.out.println(s.compareTo(s1));//1bcd1
        System.out.println(s.compareToIgnoreCase(s1));//1bcd1
        System.out.println(s.replace('a', '3'));//3bcd3
        System.out.println(s.replaceFirst("a", "2"));//2bcda
    }

    @Test
    public void test5() {
        String s = "dabcda";
        String ss = "adabcda";

        System.out.println(s.startsWith("da"));//true
        System.out.println(s.startsWith("cd", 3));//true
        System.out.println(s.endsWith("da"));//true
        System.out.println(s.contains("ab"));//true

        System.out.println(s.charAt(1));//a
        System.out.println(s.indexOf(1));//-1
        System.out.println(s.indexOf("a"));//1
        System.out.println(s.indexOf("a", 2));//5
        System.out.println(s.lastIndexOf("a"));//5
        System.out.println(s.lastIndexOf("a", 2));//从指定索引开始反向搜索：1
        System.out.println(ss.lastIndexOf("a", 3));//从指定索引开始反向搜索：2


        String s1 = "a,b,c,d,a";
        System.out.println(s.concat(s1));//dabcdaa,b,c,d,a
        System.out.println(s1.substring(2));//b,c,d,a
        System.out.println(s1.substring(2, 5));//b,c
        System.out.println(Arrays.toString(s1.split(",")));//[a, b, c, d, a]
        System.out.println(Arrays.toString(s1.split(",", 2)));//[a, b,c,d,a]
        System.out.println(s1.split(",", 2).length);//[a, b,c,d,a]

        String s2 = " ab cda ";
        String s3 = "ab cda ";
        System.out.println(s2.trim());//ab cda
        System.out.println(s3.trim());//ab cda

    }


    @Test
    public void test6() {
        String s = new String("wentingting");
        String intern = s.intern();
        String s2 = "wentingting";
        System.out.println(intern == s2);// true

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4); // false
    }

    @Test
    public void test7() {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);// false

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);// false
    }

    @Test
    public void test8() {
        String s1 = new StringBuilder("0").append("1").toString();
        System.out.println(s1.intern() == s1);  //返回true
        String s2 = new StringBuilder("0").toString();
        System.out.println(s2.intern() == s2);  //返回false
    }

    @Test
    public void test9() {
        String str1 = new StringBuilder("测试").append("01").toString();
        String str2 = new StringBuilder("测试").append("01").toString();
        System.out.println(str1.intern() == str1);//返回true
        System.out.println(str1.intern() == str2);//返回false
        System.out.println(str1.intern() == str2.intern());//返回true
        System.out.println(str1 == str2);//返回false
    }

    @Test
    public void test10() {
    }
}
