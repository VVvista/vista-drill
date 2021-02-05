package com.vista.drill.xiaochao.动态规划;

/**https://leetcode-cn.com/problems/coin-change-2/
 * @author Wen TingTing by 2021/1/30
 */
public class 兑零钱2 {
    public int change(int amount, int[] coins) {
        int[][] dp=new int[coins.length+1][amount+1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0]=1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <=amount; j++) {
                if(j-coins[i-1]>=0){
                    dp[i][j]= dp[i-1][j]+dp[i][j-coins[i-1]];
                }else{
                    dp[i][j]= dp[i-1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }

    /**
     * 降维
     * @param amount
     * @param coins
     * @return
     */
    public int change2(int amount, int[] coins) {
        int[] dp=new int[amount+1];
            dp[0]=1;

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <=amount; j++) {
                if(j-coins[i-1]>=0){
                    dp[j]= dp[j]+dp[j-coins[i-1]];
                }else{
                    dp[j]= dp[j];                }
            }
        }
        return dp[amount];
    }
}
