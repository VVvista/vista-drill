package com.vista.drill.improve.动态规划;

/**
 * 练习1：找零钱问题
 * 假设有25分、20分、5分、1分的硬币，现要找给客户41分的零钱，如何办到硬币个数最少？
 * ① 暴力递归（自顶向下，出现了重叠子问题）
 * ② 记忆化搜索（自顶向下）
 * ③ 递推（自底向上）
 *
 * @author Wen TingTing by 2020/7/12
 */
public class Coins {
    public static void main(String[] args) {

    }

    /**
     * 暴力递归(自顶而下的调用，可能出现重叠子问题)
     * 找n分钱需要的最少硬币
     * 假设只有1，5，20，25中硬币
     *
     * @param n
     * @return
     */
    static int coins1(int n) {
        // 基本情况
        if (n < 1) return Integer.MAX_VALUE;
        if (n == 1 || n == 5 || n == 20 || n == 25) return 1;
        // 当前情况的推导
        // 求出四种取法的最小值
        int min1 = Math.min(coins1(n - 1), coins1(n - 5));
        int min2 = Math.min(coins1(n - 20), coins1(n - 25));
        // 返回值
        return Math.min(min1, min2) + 1;
    }

    /**
     * 记忆化搜索：去除重复子问题的计算
     *
     * @param n
     * @return
     */
    static int coins2(int n) {
        // 处理非法值
        if (n < 1) return -1;
        // 定义数组，存储子问题值
        int[] dp = new int[n + 1];

        // 将面值所在数组位置值特殊化
        int[] faces = new int[]{1, 5, 20, 25};
        for (int face : faces) {
            // 用不着的面值无需初始化，如：换取20元，无需初始化dp[25]=1，因为dp.length=21，否则数组下标越界
            if (face > n) break;
            dp[face] = 1;
        }
        // 计算n的值
        return computeCoins(n, dp);
    }

    static int computeCoins(int n, int[] dp) {
        // 基础情况
        if (n < 1) return Integer.MAX_VALUE;

        // 当前情况计算
        if (dp[n] == 0) {
            int min1 = Math.min(computeCoins(n - 1, dp), computeCoins(n - 5, dp));
            int min2 = Math.min(computeCoins(n - 20, dp), computeCoins(n - 25, dp));
            dp[n] = Math.min(min1, min2) + 1;
        }
        // 返回值
        return dp[n];
    }

    /**
     * 递推(自底而上)
     * 从1->n 一次计算每枚硬币的最优换取个数。
     *
     * @param n
     * @return
     */
    static int coins3(int n) {
        // 基础情况
        if (n < 0) return -1;
        if (n == 1 || n == 5 || n == 20 || n == 25) return 1;

        // 创建数组存储n的最优解
        int[] dp = new int[n + 1];
        // 从底向上依次计算最优情况，直到n结束
        for (int i = 1; i < dp.length; i++) {
            // 当前情况的计算
            int min = Integer.MAX_VALUE;
            if (n >= 1) min = Math.min(dp[n - 1], min);
            if (n >= 5) min = Math.min(dp[n - 5], min);
            if (n >= 20) min = Math.min(dp[n - 20], min);
            if (n >= 25) min = Math.min(dp[n - 25], min);
            dp[n] = min + 1;

        }
        // 返回值
        return dp[n];
    }

    /**
     * 升级：输出找零钱的具体方案（具体用了哪些面值的硬币）
     * 在递推的基础上，将每个n当前的最优面值存储在另一个数组中
     * 遍历输出数组中n对应的面值即可
     * <p>
     * 总结：需要两个数组：
     * 1.数组1：存储零钱n的最优换取个数
     * 2.数组2：存储零钱n当前的最优换取面值
     *
     * @param n
     * @return
     */
    static int coins4(int n) {
        // 基础情况
        if (n < 1) return -1;

        // 定义数组
        int[] dp = new int[n + 1];
        int[] faces = new int[n + 1];
        // 从1->n输出零钱n的最佳换取方案
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            // 将零钱n当前的最佳换取面值x存储在另一个数组中
            // 只有当n-1的换取个数<min时，才将当前值放入faces[n]位置
            if (n >= 1 && dp[n - 1] < min) {
                min = dp[n - 1];
                faces[i] = 1;
            }
            if (n >= 5 && dp[n - 5] < min) {
                min = dp[n - 5];
                faces[i] = 5;
            }
            if (n >= 20 && dp[n - 20] < min) {
                min = dp[n - 20];
                faces[i] = 20;
            }
            if (n >= 25 && dp[n - 25] < min) {
                min = dp[n - 25];
                faces[i] = 25;
            }
            dp[n] = min + 1;
            // 输出最佳的换取面值方案
            print(faces, i);
        }
        // 返回值
        return dp[n];
    }

    static void print(int[] faces, int n) {
        while (n > 0) {
            System.out.print(faces[n]);
            n -= faces[n];
        }
        System.out.println();
    }

    /**
     * 最终：通用实现
     * 递推
     *
     * @param n     零钱
     * @param faces 面值
     * @return
     */
    public static int coins(int n, int[] faces) {
        // 基础情况
        if (n < 1 || faces == null || faces.length == 0) return -1;
        // 递推方式 ：1->n 每枚硬币的最佳换取个数存储在数组中
        int[] dp = new int[n + 1];

        // 当前零钱的最佳换取计算
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int face : faces) {
                if (i >= face) {
                    min = Math.min(dp[n - face], min);
                }
            }
            dp[n] = min + 1;
        }
        // 返回值
        return dp[n];
    }
}
