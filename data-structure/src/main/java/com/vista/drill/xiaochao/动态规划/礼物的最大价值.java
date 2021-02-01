package com.vista.drill.xiaochao.动态规划;

/**
 * @author Wen TingTing by 2021/1/31
 */
public class 礼物的最大价值 {
    public int maxValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i-1][j-1];
            }
        }
        return dp[row][col];
    }
}
