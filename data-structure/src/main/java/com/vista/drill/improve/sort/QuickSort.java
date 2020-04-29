package com.vista.drill.improve.sort;

/**
 * 快速排序
 * 1.从序列中寻找一个轴点元素pivot:假设每次选取的都是0位置元素
 * 2.利用pivot将序列分割成2个子序列： 小于pivot pivot 大于pivot
 * 3.对子序列重复以上操作，知道不能再分割(子序列中只含有一个元素)
 * 本质：逐步将每个元素当作轴点元素,即确定每个轴点元素的位置
 *
 * @author Wen TingTing by 2020/4/29
 */
public class QuickSort extends Sort {
    public QuickSort(Integer[] array) {
        super(array);
    }

    @Override
    public void sort() {
        sort(0, array.length);
    }

    /**
     * 对序列进行分割，并确定轴点元素位置
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if(end-begin<2) return;// 当数组至少有2个元素时才进行快速排序
        // 获取轴点元素位置
        int mid = pivotIndex(begin, end);
        // 对[begin,index) [index,end) 再进行快速排序
        sort(begin, mid);
        sort(mid + 1, end);
    }

    /**
     * 对子序列进行分割，并将轴点元素放置在合适位置
     * 假设：轴点元素为0元素位置
     *
     * @param begin
     * @param end
     * @return
     */
    private int pivotIndex(int begin, int end) {
        // 备份轴点元素
        Integer pivot = array[begin];
        while (begin < end) {
            while (begin<end) {
                if (array[end] > pivot) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin<end) {
                if (array[begin] < pivot) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        array[begin] = pivot;
        return begin;
    }
}
