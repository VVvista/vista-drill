package com.vista.drill.improve.贪心;

import java.util.Arrays;

/**
 * 最优装载(加勒比海盗)
 * 海盗船的载重量为W，每件古董的重量为Wi，海盗们该如何把尽量多数量的古董装上海盗船？
 * 例如W=30，Wi={3,45,4,10,7,14,2,11}
 * <p>
 * 贪心策略：每一次优先选择重量最小的古董
 *
 * @author WenTingTing by 2020/7/6
 */
public class Pirate {

    /**
     * 最优装载：
     * 1.先将古董重量按照从小到大排序
     * 2.依次循环排序好的古董重量数组
     * 3.依次累加古董重量，直到超过船的载重量即停止添加
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};// 古董重量
        Arrays.sort(weights);

        int capacity = 30;// 船的最大载重量
        int weight = 0;// 船实际的载重量
        int count = 0;// 装载的古董有效个数

        for (int i = 0; i < weights.length && weight < capacity; i++) {
            int newWeight = weight + weights[i];
            if (newWeight < capacity) {
                weight = newWeight;
                count++;
                System.out.println(weights[i]);
            }
        }
        System.out.println("装了 " + count + " 件古董，共" + weight + "重");//装了 5 件古董，共21重
    }
}
