package com.vista.drill.xiaochao.动态规划;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author Wen TingTing by 2021/1/31
 */
public class 最长回文子串 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        if (s.length() == 1) return s;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLen = 1;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i + 1 == 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                } else
                    dp[i][j] = false;
            }
        }
        return s.substring(start, start + maxLen);

    }
}
