package com.vista.drill.improve.sort;

/**
 * 选择排序（升序）
 * 选择排序时对冒泡排序的优化升级：
 * 1.在每一个内循环中找出元素中的最大值与末尾元素交换，此时末尾元素即为最大值
 * 2.循环以上操作，直至排序完成
 * <p>
 * 说明：
 * 1.与冒泡排序相同，每次循环都是将最大值放置末尾。
 * 冒泡排序采用两两比较交换的方式；
 * 选择排序选择比较查找的方式，用临时变量记录最大值的位置，最终仅交换最大值与末尾元素，仅进行一次交换，提升了效率
 * 故：选择排序的交换次数远远小于冒泡排序，平均性能优于冒泡排序。
 * 2.选择排序无需考虑冒泡排序的优化情况：因为其内部循环是寻找最大值，而冒泡是两两交换（考虑特殊情况，减少比较交换次数），所以冒泡的两种特殊情况不适用于选择排序
 * 3.最好、最坏、平均时间复杂度：O(n^2)，空间复杂度：O(1) (原地算法，增加临时变量)
 * 4.不稳定排序
 *
 * @author WenTingTing by 2020/4/27
 */
public class SelectionSort extends Sort {
    public SelectionSort(Integer[] array) {
        super(array);
    }

    /**
     * 选择排序
     */
    @Override
    public void sort() {
        for (int end = array.length - 1; end > 0; end++) {
            int maxIndex = 0; // 记录最大元素位置
            for (int i = 1; i <= end; i++) {
                if (array[i] >= array[maxIndex]) {
                    maxIndex = i;
                }
            }
            // 交换最大值与末尾元素的位置
            int tmp = array[end];
            array[end] = array[maxIndex];
            array[maxIndex] = tmp;
        }
    }
}
