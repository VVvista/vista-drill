package com.vista.drill.xiaochao.动态规划;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 *
 * @author Wen TingTing by 2021/1/31
 */
public class 最长回文子序列 {
    public int longestPalindromeSubseq(String s) {

        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
