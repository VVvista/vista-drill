package com.vista.drill.improve.d贪心;

import java.util.Arrays;

/**
 * 假设有 25 分、10 分、5 分、1 分的硬币，现要找给客户 41 分的零钱，如何办到硬币个数最少？
 * 贪心策略：每一次都优先选择面值最大的硬币
 * 注意：每种面值的张数不限
 * <p>
 * 贪心策略解决该问题的弊端：获得结果并不是全局最优解。
 * 因为没有测试所有可能解，容易过早做绝对能，所以没法打到最佳解
 * 贪心策略采取每一步的最优解，而忽略大局，走一步看一步
 * <p>
 * 参考：https://blog.csdn.net/weixin_43734095/article/details/105585224
 *
 * @author WenTingTing by 2020/7/6
 */
public class CoinChange {
    public static void main(String[] args) {
        //  coinChange1(new Integer[]{25, 10, 5, 1}, 41);
        //  coinChange2(new Integer[]{25, 10, 5, 1}, 41);
        //   coinChange3(new Integer[]{25, 10, 5, 1}, 41);
        coinChange4(new Integer[]{25, 10, 5, 1}, 41);
    }

    /**
     * 方式1：采取与海盗船重量相同的代码逻辑
     * 每次已累加钱数 是否仍 小于等于 总钱数
     *
     * @param faces 面值集合
     * @param money 总钱数
     */
    static void coinChange1(Integer[] faces, int money) {
        // 面值从小到小排序
        Arrays.sort(faces, (Integer i1, Integer i2) -> i2 - i1);
        int coins = 0;// 已累加钱数
        int count = 0;// 有效面值数
        for (int i = 0; i < faces.length && coins < money; i++) {
            int newCoins = coins + faces[i];
            if (newCoins <= money) {
                coins = newCoins;
                count++;
                System.out.println(faces[i]);
                i = -1;
            }
        }
        System.out.println("找了 " + count + " 张");//找了 4 张
    }

    /**
     * 方式2：优化方式1 比较逻辑
     * 剩余应找钱数 > 当前面值，当前面值即为有效。
     * 省略已累加钱数变量coins，使用总钱数money维护剩余钱数
     *
     * @param faces 面值集合
     * @param money 总钱数
     */
    static void coinChange2(Integer[] faces, int money) {
        // 面值从小到小排序
        Arrays.sort(faces, (Integer i1, Integer i2) -> i2 - i1);
        int count = 0;// 有效面值数
        for (int i = 0; i < faces.length && money > 0; i++) {
            if (money >= faces[i]) {
                money -= faces[i];
                count++;
                System.out.println(faces[i]);
                i = -1;
            }
        }
        System.out.println("找了 " + count + " 张");//找了 4 张
    }


    /**
     * 方式3：优化方式2 for循环代码优化
     * 使用while循环代替for循环，当前面值<剩余钱数，下一轮仍从当前面值开始即可，前面面值肯定>剩余钱数,无需再循环比较
     *
     * @param faces 面值集合
     * @param money 总钱数
     */
    static void coinChange3(Integer[] faces, int money) {
        // 面值从小到小排序
        Arrays.sort(faces, (Integer i1, Integer i2) -> i2 - i1);
        int count = 0;// 有效面值数
        int i = 0;
        while (i < faces.length) {
            if (money < faces[i]) {
                i++;
                continue;
            }
            money -= faces[i];
            count++;
            System.out.println(faces[i]);
            // i = 0;无需从数组[0]开始，当前面值>剩余面值时，下一轮寻找即可从当前面值之后寻找，数组[0]-当前面值肯定不满足条件，减少代码循环
        }
        System.out.println("找了 " + count + " 张");//找了 4 张
    }


    /**
     * 方式4：优化方式3 if判断代码优化
     * 使用while循环代替if判断，如说 当前面值<剩余钱数，下一轮仍从当前面值开始即可，直到当前面值不满足剩余钱数，在查找下一个面值比较
     * 逻辑：while循环比较当前面值与剩余钱数的大小，直到 前面面值>剩余钱数，向下查找下一个面值
     *
     * @param faces 面值集合
     * @param money 总钱数
     */
    static void coinChange4(Integer[] faces, int money) {
        // 面值从小到小排序
        Arrays.sort(faces, (Integer i1, Integer i2) -> i2 - i1);
        int count = 0;// 有效面值数
        int i = 0;
        while (i < faces.length) {
            while (money >= faces[i]) {
                money -= faces[i];
                count++;
                System.out.println(faces[i]);
            }
            i++;// 当前面值>剩余钱数，向下查找下一个面值
        }

        System.out.println("找了 " + count + " 张");//找了 4 张
    }

}
