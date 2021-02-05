package com.vista.drill.xiaochao.动态规划;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author Wen TingTing by 2021/1/31
 */
public class 无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int[] dp = new int[s.length()];
        int max = 1;
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            dp[i] =  dp[i - 1]+1;
            for (int j = i - dp[i - 1]; j < i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i] = i-j;
                }

            }
            if(dp[i]>max){
                max=dp[i];
            }
        }
        return max;
    }
}
