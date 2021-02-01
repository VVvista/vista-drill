package com.vista.drill.xiaochao.动态规划;

/**https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 * @author Wen TingTing by 2021/1/31
 */
public class 让字符串成为回文串的最少插入次数 {
    public int minInsertions(String s) {
        if (s == null || s.length() == 0) return -1;
        if (s.length() == 1) return 0;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1])+1;
                }
            }
        }
        return dp[0][s.length() - 1];

    }
}
