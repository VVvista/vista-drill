package com.vista.drill.raise.d串;

/**礼物的最大价值
 * 动态规划
 *
 * https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
 * @author Wen TingTing by 2020/11/15
 */
public class f_礼物的最大价值 {
    public int maxValue(int[][] grid) {
        int row=grid.length;
        int col=grid[0].length;
        int[][] dp=new int[row][col];
        // 计算第0行dp
        dp[0][0]=grid[0][0];
        for (int i = 1; i < col; i++) {
            dp[0][i]=dp[0][i -1]+grid[0][i];
        }
        // 计算第0列
        for (int i = 1; i < row; i++) {
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }
        for (int i = 1; i < row; i++) { // row
            for (int j = 1; j < col; j++) { // col
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1] )+ grid[i][j];
                }
            }
        return dp[row-1][col-1];
    }
}
