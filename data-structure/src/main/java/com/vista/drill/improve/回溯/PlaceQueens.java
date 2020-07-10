package com.vista.drill.improve.回溯;

/**
 * 八皇后问题1
 * 8*8矩阵中摆放八个皇后，使其互不攻击：任意两个皇后都不能处于同一行、同一列、同一对角线上
 * <p>
 * 回溯法：
 * 行、列号从0开始
 * <p>
 * 从第0行开始，根据约束条件，依次将皇后摆放在每一行的合适位置
 * 1.若某一行皇后没有合适的摆放位置，则回溯上一行皇后挪动到另一合适位置；
 * 2.若上一行皇后不能再挪动，再回溯到上上一行，挪动皇后位置，知道回溯的皇后挪动新的位置再进行向下摆放
 * 3.直到所有皇后都摆放合适位置，再依次回溯，挪动皇后位置，直到所有情况都考虑完成
 * 即第0行皇后遍历的所有的摆放位置情况
 * <p>
 * 1.递归实现
 * 2.使用长度为n的数组存放每行皇后的摆放位置：数组的下标即皇后所在的行数，元素值即为皇后所在列数
 * <p>
 * 参考：https://blog.csdn.net/weixin_43734095/article/details/105567135
 *
 * @author WenTingTing by 2020/5/6
 */
public class PlaceQueens {
    // 存放每一行皇后的列号（在第几列），cols[row] = col 表示第col行第row列摆放了皇后
    int[] cols;

    // 一共有多少种合理的摆法
    int ways;

    /**
     * n皇后的所有摆放情况
     * <p>
     * 从第0行开始将皇后摆放到合适位置
     * 使用长度为n的数组存放每行皇后的摆放位置：
     * 数组的下标即皇后所在的行数，元素值即为皇后所在列数
     *
     * @param n
     */
    void placeQueens(int n) {
        if (n < 1) return;
        cols = new int[n]; // 初始化数组存放皇后的摆放位置
        place(0); // 从第0行开始摆放皇后
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 从row行开始摆放皇后的位置
     * 从0列开始，依次判断皇后在每一列的摆放位置是否合理：
     * 如果摆放位置合理，再继续下一行摆放皇后
     * 如果不合理，在挪动皇后位置再继续下一行摆放
     * <p>
     * 缺点：判断每个位置的合理性，都需要从头到尾遍历已摆放皇后的位置，并相比较
     *
     * @param row
     */
    private void place(int row) {
        if (row == cols.length) {// 若摆放到最后一行之后，则可判断全部合理摆放一遍，合理摆法+1
            ways++;
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {// 判断第row行第col列摆放位置是否合理
                cols[row] = col;// 若摆放合理，则记录皇后位置，继续下一行的皇后摆放
                place(row + 1);
            }
            // 如果摆放位置不合理，或摆放位置完成，则继续挪动皇后位置进行向下摆放
        }
    }

    /**
     * 判断第row行第col列摆放位置是否合理
     * 1.col不能与前面row-1个皇后的列相同
     * 2.col不能在前面row-1个皇后的对角线上
     * 无需判断行是否相同，因为程序已经设置成一行只摆放一个
     *
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 第col行第row列已经摆放了皇后
            if (cols[i] == col) return false;
            // 第i行的皇后跟第row行第col列格子处在同一斜线上
            // 对角线:y=x
            if (row - i == Math.abs(cols[i] - col)) return false;
        }
        return true;
    }

    /**
     * 打印合理摆放时皇后的位置情况
     */
    private void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) { // 摆放了皇后
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
