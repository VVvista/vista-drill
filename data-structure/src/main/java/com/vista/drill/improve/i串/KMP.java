package com.vista.drill.improve.i串;

/**
 * 2.Kmp算法
 * 利用部分匹配的结果而加快模式串的滑动速度，且主串指针不回溯
 * 主串仅遍历一遍，设置前缀表 next[],利用动态规划及next[] 求出状态转移方程
 * https://blog.csdn.net/weixin_43734095/article/details/105796090
 *
 * @author WenTingTing by 2020/9/28
 */
public class KMP {
    public static void main(String[] args) {
        String text = "Hello World";
        String pattern = "d";
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
     * Kmp算法
     * 利用部分匹配的结果而加快模式串的滑动速度，且主串指针不回溯
     * 0.定义两个指针
     * ti:主串遍历指针
     * pi：子串指针
     * 1.初始状态
     * ti=0
     * pi=0
     * 2.状态转移方程
     * 若t[ti]=p[pi]，则 ti++;pi++
     * 若t[ti]!=p[pi]，则 pi=next[pi]
     *
     * @return
     */
    public static int indexOf(char[] textChars, char[] patternChars) {
        // 前缀表
        int[] next = next(patternChars);
        int tlen = textChars.length;
        int plen = patternChars.length;

        int ti = 0, pi = 0;
        while (pi < plen && ti - pi <= tlen - plen) {
            if (pi < 0 || textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                pi = next[pi];
            }
        }
        return pi == plen ? ti - pi : -1;
    }

    /**
     * 求解子串的前缀表 next[] 数组
     * 0.设置2个指针：
     * k:当前子串最大相同前后缀长度
     * j：pattern串遍历指针
     * 1.初始状态：
     * k=-1; j=0;next[0]=-1
     * 2.状态转移方程：
     * 若p[k]=p[j],则next[j]=next[k]+1
     * 若p[k]!=p[j],则k=next[k],再比较p[k]与p[j]大小。
     *
     * @param patternChars 子串数组
     * @return 前缀表
     */
    private static int[] next(char[] patternChars) {
        int k = -1, j = 0;
        int[] next = new int[patternChars.length];
        next[0] = -1;
        while (j < patternChars.length - 1) {
            if (k < 0 || patternChars[k] == patternChars[j]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
