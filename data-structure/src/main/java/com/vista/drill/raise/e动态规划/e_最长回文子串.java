package com.vista.drill.raise.e动态规划;

/**
 * 5.最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author WenTingTing by 2020/11/23
 */
public class e_最长回文子串 {
    /**
     * 方法1： 动态规划
     * dp(i)(j): s[i,j]是否为回文子串，仅true ，false。j>=i
     * 状态转移方程：
     * - 1.若s[i,j]长度<=2, dp(i)(j)= s[i]==s[j]
     * - 2.若s[i,j]长度>2, dp(i)(j)= s[i]==s[j]&& dp(i+1)(j-1)
     * 注意： dp(i)(j)是上三角矩阵，计算从左到右，从下到上
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;
        int length = s.length();
        char[] chars = s.toCharArray();

        // 记录最大的回文子串长度
        int maxLen = 0;
        // 回文子串的开始位置
        int begin = 0;
        boolean[][] dp = new boolean[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                // 子串的有效长度
                int len = j - i + 1;
                // 判断是否为回文子串
                dp[i][j] = len <= 2 ? chars[i] == chars[j] : ((chars[i] == chars[j]) && dp[i + 1][j - 1]);
                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 方法2：扩展中心法
     * - 以字符i为中心向两边扩展，判断子串是否为回文子串
     * - 以间隙为中心向两边扩展，判断子串是否为回文子串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;
        int length = s.length();
        char[] chars = s.toCharArray();

        // 记录最大的回文子串长度,
        int maxLen = 1;
        // 回文子串的开始位置
        int begin = 0;

        for (int i = 1; i < length - 1; i++) {
            // 注意下角标越界
            for (int j = 1; j <= Math.min(i, length - 1 - i); j++) {
                if (chars[i - j] != chars[i + j]) {
                    break;
                }
                if ((2 * j + 1) > maxLen) {
                    maxLen = 2 * j + 1;
                    begin = i - j;
                }
            }
        }

        for (double i = 0.5; i < length - 0.5; i++) {
            for (double j = 0.5; j <=  Math.min(Math.ceil(i),Math.ceil(length-0.5-i)); j++) {
                if (chars[(int) (i - j)] != chars[(int) (i + j)]) {
                    break;
                }
                if ((2 * j + 1) > maxLen) {
                    maxLen = (int) (2 * j + 1);
                    begin = (int) (i - j);
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args){
       // System.out.println(longestPalindrome2("babad"));
        System.out.println(longestPalindrome2("bb"));

    }
}
