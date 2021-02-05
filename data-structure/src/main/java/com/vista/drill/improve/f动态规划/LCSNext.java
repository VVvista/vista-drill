package com.vista.drill.improve.f动态规划;

/**
 * 4.最长公共子串长度-连续
 * 子串是连续的子序列
 * 求两个字符串的最长公共子串长度
 * 状态：dp(i,j) 以 str1[i – 1]、str2[j – 1] 结尾的最长公共子串长度
 * 初始状态：dp(i,0)=0, dp(o,j)=0
 * 状态转移方程：
 * 若 str1[i-1]=str2[j-1],dp(i,j)=dp(i-1,j-1)+1
 * 若 str1[i-1]!=str2[j-1],dp(i,j)=0
 *https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 * @author WenTingTing by 2020/9/22
 */
public class LCSNext {
    public static void main(String[] args) {
        String str1 = "ABCBA";
        String str2 = "BABCA";
        System.out.println(lcs1(str1, str2));
        System.out.println(lcs2(str1, str2));
    }

    /**
     * 方案1
     * 动态规划
     * 状态：dp(i,j) 以 str1[i – 1]、str2[j – 1] 结尾的最长公共子串长度
     * 初始状态：dp(i,0)=0, dp(o,j)=0
     * 状态转移方程：
     * 若 str1[i-1]=str2[j-1],dp(i,j)=dp(i-1,j-1)+1
     * 若 str1[i-1]!=str2[j-1],dp(i,j)=0
     */
    private static int lcs1(String str1, String str2) {
        if (str1 == null || str1.isEmpty()) return 0; // 检测非法数据
        if (str2 == null || str2.isEmpty()) return 0; // 检测非法数据
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int max = 0;
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * @FixMe 方案2:方案1优化
     * 滚动数组-一维数组
     * 中间状态dp[][]由int[str1.length + 1][str2.length + 1] 变为int[str2.length + 1]
     * 状态：dp(i,j) 以 str1[i – 1]、str2[j – 1] 结尾的最长公共子串长度
     */
    private static int lcs2(String str1, String str2) {
        if (str1 == null || str1.isEmpty()) return 0; // 检测非法数据
        if (str2 == null || str2.isEmpty()) return 0; // 检测非法数据
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int max = 0;
        int[] dp = new int[chars2.length + 1];
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = chars2.length; j >= 1; j--) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }

}
