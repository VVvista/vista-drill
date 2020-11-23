package com.vista.drill.raise.e动态规划;

/**
 * 4.编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * - 插入一个字符
 * - 删除一个字符
 * - 替换一个字符
 * 0 <= word1.length, word2.length <= 500
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * @author WenTingTing by 2020/11/23
 */
public class d_编辑距离 {
    /**
     * 方法： 动态规划
     * dp(i)(j):s1的前i个字符转换成s2前j个字符的最小操作数
     * 初始化：dp(0)(0)=0; dp(i)(0)=i; dp(0)(j)=j
     * 状态转移方程： 4中情况
     * - 1.dp(i)(j)=dp(i)(j-1)+1 -> 在dp(i)(j-1)基础上添加元素j
     * - 2.dp(i)(j)=dp(i-1)(j)+1 -> 在dp(i-1)(j)基础上删除元素i
     * - 3.若s1(i)==s2(j),则 dp(i)(j)=dp(i-1)(j-1) -> 在dp(i)(j-1)基础上删除元素i
     * - 3.若s1(i)!=s2(j),则 dp(i)(j)=dp(i-1)(j-1)+1 -> 在dp(i-1)(j-1)基础上元素i替换为元素j
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int s1length = s1.length;
        int s2length = s2.length;

        int[][] dp = new int[s1length + 1][s2length + 1];
        dp[0][0] = 0;
        for (int i = 1; i < s1length + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < s2length + 1; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < s1length + 1; i++) {
            for (int j = 1; j < s2length + 1; j++) {
                dp[i][j] = s1[i-1] == s2[j-1] ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i][j]));
            }
        }
        return dp[s1length][s2length];
    }
}
