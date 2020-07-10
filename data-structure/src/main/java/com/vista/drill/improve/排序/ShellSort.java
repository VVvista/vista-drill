package com.vista.drill.improve.排序;

import java.util.ArrayList;
import java.util.List;

/**
 * 希尔排序
 * 将序列看作一个矩阵，分成m列，逐列进行排序
 * m从某个正数逐渐减为1，当m=1时，整个序列将完全有序。因此也被称为递减增量排序
 * 矩阵的列数取决于步长序列：不同的步长序列，执行效率也不同
 * 底层使用插入排序对每列进行排序，因此希尔排序也可看作插入排序的改进版
 * <p>
 * 注：希尔排序每次选取一定的步长进行插入排序，从而减少逆序对的数量，
 * 所以当步长为1时，序列中的逆序对数量已非常少，再进行全量插入排序，交换的次数已非常少
 * <p>
 * 时间复杂度：
 * 平均复杂度：取决于步长序列
 * 最坏复杂度：O(n^2)
 * 最好复杂度：O(n)(步长序列只有1，且序列完全有序)
 * 空间复杂度：O(1)
 * 不稳定排序
 *
 * @author WenTingTing by 2020/4/30
 */
public class ShellSort extends Sort {
    public ShellSort(Integer[] array) {
        super(array);
    }

    @Override
    public void sort() {
        // 步长序列
        List<Integer> list = shellStepSequence();
        // 分别对不同步长序列的子列进行排序
        for (int step : list) {

        }
    }

    /**
     * 针对step步长进行排序
     * 1.循环[0,step)位置元素index
     * 2.对 index + step*n 位置的元素进行插入排序(操作同插入排序)，注意 不要数组越界
     * 3.插入排序过程中，比较的最小元素位置不能小于index
     *
     * @param step
     */
    private void sort(int step) {
        // 循环每个基本步长子序列
        for (int i = 0; i < step; i++) {
            int begin = i;
            // 每一列元素的索引为：col、col+step、col+2*step、col+3*step
            // 将每次增加固定步长的元素进行插入排序
            while ((begin = begin + step) < array.length) {

                // 元素的插入排序
                // 备份待插入元素
                int element = array[begin];
                int cur = begin;
                // 一直与前step步长的元素进行比较，如果小于当前元素则交换
                while (cur > i && element < array[cur - step]) {// 后者比前者小，则交换位置，注意比较的最小元素不能小于i位置
                    array[cur] = array[cur - step];
                    cur -= step;
                }
                // 将元素插入合适位置
                array[cur] = element;
            }
        }
    }


    /**
     * 计算步长序列：
     * 希尔规定的步长序列：n/(2^k)
     * 步长序列元素从大到小(以1结尾)：[109,41,19,5,1]
     *
     * @return
     */
    private List<Integer> shellStepSequence() {
        ArrayList<Integer> list = new ArrayList<>();
        int step = array.length;// 兼顾数组长度为1的情况
        while ((step >>= 1) > 0) {
            list.add(step);
        }
        return list;
    }


}
