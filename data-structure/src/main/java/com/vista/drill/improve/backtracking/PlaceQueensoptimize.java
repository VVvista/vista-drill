package com.vista.drill.improve.backtracking;

/**
 * 八皇后问题：优化
 * 八皇后问题的常规实现中，检验每行位置的合理性的时候，需要遍历前n-1个皇后位置，并相比较判断位置的合理性
 * <p>
 * 优化：
 * 1.新建数组：存放每列是否已摆放皇后:boolean[] cols;
 * 2.新建数组：存放每一对角先是否已摆放皇后(左上角->右下角):boolean[] leftTop;
 * 3.新建数组：存放每一对角先是否已摆放皇后(右上角->左下角)：boolean[] rightTop;
 * 此时不能判断每行皇后的摆放位置，只能判断某列否已摆放皇后
 * 利用该方法进行摆放位置合理性检查时 时间复杂度： O(1)
 * <p>
 * 注：
 * 根据行、列求对角线索引（左上、右上情况不同）
 * 左上角->右下角的对角线索引：row-col+7
 * 右上角->左下角的对角线索引：row+col
 * <p>
 * 参考：https://blog.csdn.net/weixin_43734095/article/details/105567135
 *
 * @author WenTingTing by 2020/5/6
 */
public class PlaceQueensoptimize {
    boolean[] cols;// 标记着某一列是否有皇后了
    boolean[] leftTop;// 标记着某一对角线是否有皇后了(左上角->右下角)
    boolean[] rightTop;// 标记着某一对角线是否有皇后了(右上角->左下角)
    int ways;// 一共有多少种合理的摆法

    /**
     * 摆放n皇后
     */
    public void placeQueens(int n) {
        if (n < 1) return;
        // 初始化
        cols = new boolean[n];// 总共有n列
        leftTop = new boolean[(n << 1) - 1]; // 2n-1 条对角线
        leftTop = new boolean[(n << 1) - 1];

        place(0); // 从第0行开始摆放皇后
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第 row 行开始摆放皇后
     */
    private void place(int row) {
        if (row == cols.length) {// 如果已经放到第n行,说明找到了一种n皇后的摆法
            ways++;
            return;
        }

        for (int col = 0; col < cols.length; col++) {
            if (cols[col]) continue;// 第col列已经有皇后, 继续下一轮

            int leftIndex = row - col + cols.length - 1;
            if (leftTop[leftIndex]) continue;// 判断对角线是否有皇后

            int rightIndex = row + col;
            if (rightTop[rightIndex]) continue;// 判断对角线是否有皇后

            // 若摆放位置合理，则添加摆放位置
            cols[col] = leftTop[leftIndex] = rightTop[rightIndex] = true;
            place(row + 1);// 这一行摆了皇后,继续下一行

            // 进行到此行，说明该行该列的皇后向下的摆放情况已经遍历完成，则挪动改行皇后位置，再挪动之前，需要将该行该列皇后位置上的约束条件解除掉
            cols[col] = leftTop[leftIndex] = rightTop[rightIndex] = false;

        }
    }
}
