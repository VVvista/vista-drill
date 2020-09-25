package com.vista.drill.improve.f动态规划;

/**
 * 5.0-1背包（恰好装满）
 * 有n件物品和一个最大承重为w的背包，每件物品的重量是Wi、价值是Vi
 * 保证总量刚好是W的前提下，选择某些物品装进背包，背包的最大总价值是多少？
 * 注意：每件物品只有一件。
 * <p>
 * 解题：0-1背包问题的特殊化(仅修改状态、初始值即可)
 * 假设：values：价值数组 ； weights：重量数组
 * 编号为k的物品，价值是values[k],重量是weights[k]
 * 状态：dp(i,j)承重刚好为j、有前i件物品可选时的最大总价值。
 * 初始值：dp(i,0)=dp(0,j)=math.Min_value
 * 状态转移方程：
 * 若j<weights[i-1],则dp(i,j)=dp(i-1,j)
 * 若j>=weights[i-1],则dp(i,j)=max{ dp(i-1,j),dp(i-1,j-weights[i-1])+values[i-1])}
 *
 * @author WenTingTing by 2020/9/23
 */
public class Bag0_1_Full {
    public static void main(String[] args) {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int capacity = 10;
        System.out.println(maxValue2(values, weights, capacity));
    }

    /**
     * 方案1：
     * 滚动数组-一维数组
     * 中间状态dp[][]由 int[weights.length + 1][capacity + 1] 变为 int[capacity + 1]
     * *状态：dp(i,j)最大承重为j、有前i件物品可选时的最大总价值。
     * * 初始值：dp(i,0)=dp(0,j)=0
     * * 状态转移方程：
     * * 若j<weights[i-1],则dp(i,j)=dp(i-1,j)
     * * 若j>=weights[i-1],则dp(i,j)=max{ dp(i-1,j),dp(i-1,j-weights[i-1])+values[i-1])}
     *
     * @return
     */
    private static int maxValue2(int[] values, int[] weights, int capacity) {
        // 检测非法输入
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (weights.length != values.length) return 0;
        if (capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int j = 1; j <= capacity; j++) {
            dp[j] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= weights.length; i++) {
            for (int j = capacity; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[capacity] < 0 ? -1 : dp[capacity];
    }

}