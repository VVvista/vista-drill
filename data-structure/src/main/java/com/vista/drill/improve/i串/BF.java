package com.vista.drill.improve.i串;

/**
 * 1.串模式匹配-BF算法
 * https://blog.csdn.net/weixin_43734095/article/details/105796090
 *
 * @author WenTingTing by 2020/9/27
 */
public class BF {
    public static void main(String[] args) {
        String text = "Hello World";
        String pattern = "do";
        System.out.println(text.indexOf(pattern)); // 7
        System.out.println(indexOf(text, pattern)); // 7
    }

    public static int indexOf(String text, String pattern) {
        if (text == null || pattern == null) return -1;
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        if (textChars.length == 0 || patternChars.length == 0 || textChars.length < patternChars.length)
            return -1;
        return indexOf(textChars, patternChars);
    }

    /**
     * BF 算法
     * 暴力解法的优化
     * 从主串的第一个字符开始一次与子串的字符进行匹配
     *
     * @return
     */
    public static int indexOf(char[] textChars, char[] patternChars) {
        int tlen = textChars.length;
        int plen = patternChars.length;

        int ti = 0;
        int pi = 0;
        while (pi < plen && ti - pi <= tlen - plen) {
            if (textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                pi = 0;
                ti = ti - pi + 1;
            }
        }
        if (pi == plen) return ti - pi;
        return -1;
    }

    /**
     * 暴力匹配
     * 从主串的第一个字符开始一次与子串的字符进行匹配
     *
     * @return
     */
    public static int indexOf1(char[] textChars, char[] patternChars) {
        for (int i = 0; i <= textChars.length - patternChars.length; i++) {
            int j = 0;
            for (; j < patternChars.length; j++) {
                if (textChars[i + j] != patternChars[j]) {
                    break;
                }
            }
            if (j == patternChars.length) return i;
        }
        return -1;
    }

}
