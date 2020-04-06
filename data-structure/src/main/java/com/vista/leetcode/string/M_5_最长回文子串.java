package com.vista.leetcode.string;

/**
 * @author Wen TingTing by 2020/4/5
 */
public class M_5_最长回文子串 {
    /**
     * 暴力解法
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        String res = "";
        int len = s.length();
        int num = 0;
        for (int i = 0; i < len ; i++) {
            for (int j = i; j <= len; j++) {
                String sub = s.substring(i, j);
                if (reverse(sub) && sub.length() > num) {
                    res = sub;
                    num = Math.max(num, sub.length());
                }
            }
        }
        return res;
    }

    public static Boolean reverse(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        longestPalindrome("a");
    }
}
