package com.vista.drill.xiaochao.动态规划;

/**https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 * @author Wen TingTing by 2021/1/30
 */
public class 最长公共子串长度 {
    public int findLength(int[] A, int[] B) {
        if(A==null||A.length==0) return 0;
        if(B==null||B.length==0) return 0;

        int[][] dp=new int[A.length+1][B.length+1];
        int max=0;
        for (int i = 1; i < A.length+1; i++) {
            for (int j = 1; j < B.length + 1; j++) {
                if(A[i-1]==B[j-1]) dp[i][j]=dp[i-1][j-1]+1;
                max=Math.max(max,dp[i][j]);
            }
        }
        return max;
    }

    /**
     * 降维
     * @param A
     * @param B
     * @return
     */
    public int findLength1(int[] A, int[] B) {
        if(A==null||A.length==0) return 0;
        if(B==null||B.length==0) return 0;

        int[] dp=new int[B.length+1];
        int max=0;
        for (int i = 1; i < A.length+1; i++) {
            for (int j = B.length; j >=1; j--) {
                if(A[i-1]==B[j-1]) dp[j]=dp[j-1]+1;
                else dp[j]=0;
                max=Math.max(max,dp[j]);
            }
        }
        return max;
    }
}
