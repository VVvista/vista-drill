package com.vista.drill.raise.h高频题;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * @author WenTingTing by 2020/12/21
 */
public class e_螺旋矩阵 {
    /**
     * 思路：
     * 设置四个指针： top、bottom、left、right
     * - 先外圈循环遍历： 上 -> 右  -> 下  -> 左。修改指针值
     * - 再外圈循环遍历
     * - 直至全都遍历完成
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) return null;
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        // 设置指针
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        // 循环遍历
        while (top <= bottom && left <= right) {
            // 遍历输出 上 -> 右  -> 下  -> 左
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // 奇数行、偶数列时有问题，所以需要加该判断
            if (top > bottom || left > right) break;

            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;

    }
}
