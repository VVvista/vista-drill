package com.vista.drill.improve.b递归;

/**
 * 4.汉诺塔
 * 将a中的n个盘子挪动到c中：每次只挪动1个盘子，大盘子必须在小盘子下面
 * 当n=1时，直接将盘子从a挪动c；
 * 当n>1时，将n-1个盘子从a挪动到b；将编号n的盘子从a挪动到c；将n-1个盘子从b挪动到c
 * 由T(n)=T(n-1)+T(n-1)+O(1) =>时间复杂度：O(2^n)，空间复杂度：O(n)
 *
 * @author WenTingTing by 2020/5/5
 */
public class Hanoi {

    /**
     * 递归
     * 将n个盘子从p1挪动到p3
     * <p>
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(n)
     *
     * @param n  n个盘子
     * @param p1 开始位置
     * @param p2 中间位置
     * @param p3 最终位置
     */
    public void hanoi(int n, String p1, String p2, String p3) {
        hanoi(n - 1, p1, p3, p2);//  将 n – 1 个盘子从 p1 移动到 p2
        move(n, p1, p3);// 将编号为 n 的盘子从 p1 移动到 p3
        hanoi(n - 1, p2, p1, p3);// 将 n – 1 个盘子从 p2 移动到 p3
    }

    /**
     * 将n号盘子从from位置挪动到to位置
     *
     * @param n    n号盘子
     * @param from 开始位置
     * @param to   最终位置
     */
    private void move(int n, String from, String to) {
        System.out.println(n + "号盘子: " + from + "->" + to);
    }
}
