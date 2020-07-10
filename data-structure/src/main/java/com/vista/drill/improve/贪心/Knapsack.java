package com.vista.drill.improve.贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 0-1 背包问题
 * 有n件物品和一个最大承重为W的背包，每件物品的重量为Wi、价值Vi
 * a.在保证总重要不超过W的前提下，将哪几件物品装入背包，可以使背包的总价值最大？
 * 注意：每个物品只有1件，也就是每个物品只能选择0件或1件，因此成为0-1背包问题
 * <p>
 * 贪心策略，3种方案：
 * 价值导向：优先选择价值最高的物品放进背包
 * 重量导向：优先选择重量最低的物品放进背包
 * 价值密度主导：优先选择价值密度最高的物品放进背包(价值密度=价值/重量)
 * <p>
 * 贪心策略只是决定每一步都是最优解，不能保证全局最优解
 *
 * @author WenTingTing by 2020/7/6
 */
public class Knapsack {
    public static void main(String[] args) {
        select("价值主导", (Article a1, Article a2) -> {
            // 价值大的优先
            return a2.value - a1.value;
        });//价值主导：价值 165 ，共130重


        select("重量主导", (Article a1, Article a2) -> {
            // 重量小的优先
            return a2.weight - a1.weight;
        });//重量主导：价值 115 ，共150重


        select("价值密度主导", (Article a1, Article a2) -> {
            // 价值密度大的优先
            return Double.compare(a2.valueDensity, a1.valueDensity);
        });//价值密度主导：价值 170 ，共150重

    }

    /**
     * 以一个属性为主导实现贪心策略
     *
     * @param title 显示标题
     * @param cmp   比较器决定主导属性, [价值、重量、价值密度]
     */
    static void select(String title, Comparator<Article> cmp) {
        // 模拟题意的物品
        Article[] articles = new Article[]{
                new Article(35, 10), new Article(30, 40),
                new Article(60, 30), new Article(50, 50),
                new Article(40, 35), new Article(10, 40),
                new Article(25, 30)
        };
        Arrays.sort(articles, cmp);

        int capacity = 150;// 最大承受量
        int weight = 0;//当前承受量
        int value = 0;// 物品有效个数
        for (int i = 0; i < articles.length && weight < capacity; i++) {
            int newWeight = weight + articles[i].weight;
            if (newWeight <= capacity) {
                weight = newWeight;
                value += articles[i].value;
                System.out.println(articles[i]);
            }
        }

        System.out.println(title + "：价值 " + value + " ，共" + weight + "重");//装了 5 件古董，共21重

    }
}

/**
 * 物品类
 */
class Article {
    int weight; // 重量
    int value;  // 价值
    double valueDensity; // 价值密度

    public Article(int weight, int value) {
        this.weight = weight;
        this.value = value;
        valueDensity = value * 1.0 / weight;
    }

    @Override
    public String toString() {
        return "Article [weight=" + weight + ", value=" + value + ", ValueDensity=" + valueDensity + "]";
    }

}