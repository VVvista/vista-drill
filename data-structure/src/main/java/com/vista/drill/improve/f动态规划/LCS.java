package com.vista.drill.improve.f动态规划;

/**
 * 3.最长公共子序列长度
 * 求两个序列的最长公共子序列长度
 * 状态：dp(i,j) 以 nums1前i个元素与nums2 前j个元素的最长公共子序列长度
 * 初始状态：dp(i,0)=0, dp(o,j)=0
 * 状态转移方程：
 * 若 nums1[i-1]=nums2[j-1],dp(i,j)=dp(i-1,j-1)+1
 * 若 nums1[i-1]!=nums2[j-1],dp(i,j)=max{dp(i,j-1),dp(i-1,j)}
 *
 * @author WenTingTing by 2020/9/22
 */
public class LCS {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 9, 10};
        int[] nums2 = {1, 4, 9, 10};
        System.out.println(lcs1(nums1, nums2));
        System.out.println(lcs2(nums1, nums2));
        System.out.println(lcs3(nums1, nums2));
    }

    /**
     * 方案1
     * 递归实现:出现重复调用
     *
     * @return dp(i, j) 以 nums1前i个元素与nums2 前j个元素的最长公共子序列长度
     */
    private static int lcs1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0; // 检测非法数据
        if (nums2 == null || nums2.length == 0) return 0; // 检测非法数据
        return lcs1(nums1, nums1.length, nums2, nums2.length);

    }

    /**
     * 状态转移方程：
     * 若 nums1[i]=nums2[j],dp(i,j)=dp(i-1,j-1)+1
     * 若 nums1[i]!=nums2[j],dp(i,j)=max{dp(i,j-1),dp(i-1,j)}
     */
    private static int lcs1(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) {
            return lcs1(nums1, i - 1, nums2, j - 1) + 1;
        } else {
            return Math.max(lcs1(nums1, i - 1, nums2, j),
                    lcs1(nums1, i, nums2, j - 1));
        }
    }


    /**
     * 方法2：
     * 非递归实现
     * 状态：dp(i,j) 以 nums1前i个元素与nums2 前j个元素的最长公共子序列长度
     *
     * @return
     */
    private static int lcs2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0; // 检测非法数据
        if (nums2 == null || nums2.length == 0) return 0; // 检测非法数据
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /**
     * 方法3：方案2优化
     * 滚动数组实现：
     * 中间状态dp[][]由int[nums1.length + 1][nums2.length + 1] 变为int[2][nums2.length + 1]
     *
     * @return
     */
    private static int lcs3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0; // 检测非法数据
        if (nums2 == null || nums2.length == 0) return 0; // 检测非法数据
        int[][] dp = new int[2][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j - 1] + 1;
                } else {
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]);
                }
            }
        }
        return dp[(nums1.length) % 2][nums2.length];
    }


}
