package com.vista.drill.raise.a数组排序;

/**
 * 3.部分排序
 * 给定一个整数数组，找出索引m n，只要将索引区间[m,n]元素排序，整个数组便有序。
 * 思路：找逆序对(分别从左到右、从右到左遍历寻找逆序对的边界，即可确定范围）
 * 从左到右：记录最大值、当前小于最大值的最新位置
 * 从右到左：记录最小值、当前大于最小值的最新位置
 * 两次扫描记录的位置范围，就是需要排序的范围。
 * <p>
 * 0 <= len(array) <= 1000000
 * https://leetcode-cn.com/problems/sub-sort-lcci/
 *
 * @author WenTingTing by 2020/9/28
 */
public class c_部分排序 {

    /**
     * @param array
     * @return
     */
    public int[] subSort(int[] array) {
        if (array == null || array.length == 0) return new int[]{-1, -1};

        // 从左到右记录最大值、当前小于最大值的最新索引位置
        int max = array[0];
        int maxIndex = -1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= max) {
                max = array[i];
            } else {
                maxIndex = i;
            }
        }
        if (maxIndex == -1) return new int[]{-1, -1};

        // 从右到左最小值、当前大于最小值的最新索引位置
        int min = array[array.length - 1];
        int minIndex = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] <= min) {
                min = array[i];
            } else {
                minIndex = i;
            }
        }
        return new int[]{minIndex, maxIndex};
    }
}
