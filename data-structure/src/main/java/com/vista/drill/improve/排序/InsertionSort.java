package com.vista.drill.improve.排序;

/**
 * 插入排序：
 * 1.从序列的头部开始扫描每一个元素：每扫描一个元素将其插入头部合适的位置，使头部数据依然保持有序
 * 2.在过程中将插入的序列分为2部分：头部是已排好序，尾部是未排序的
 * <p>
 * 时间复杂度：
 * 常规的插入排序的时间复杂度与逆序对的数量成正比：逆序对的数量越多，时间复杂度越高
 * （逆序对的数量=交换次数）
 * 最好时间复杂度：O(n)（正序，无需进行交换，仅循环对比n次即可）
 * 最坏时间复杂度：O(n^2)(逆序，每个元素都插入到首位置)
 * 空间复杂度：O(1)
 * 稳定排序
 * 注：当数据量不是很大的时候，插入排序的效率是非常好的。
 *
 * @author Wen TingTing by 2020/4/29
 */
public class InsertionSort extends Sort {

    public InsertionSort(Integer[] array) {
        super(array);
    }

    /**
     * 常规的插入排序
     */
    @Override
    public void sort() {
        // 扫描每一个元素
        for (int i = 1; i < array.length; i++) {// 当数组只有一个元素时，没有排序的必要
            // 与头部元素依次进行比较，直至找到合适位置
            int index = i;
            int element = array[index];
            while (index > 0 && element < array[index - 1]) {
                array[index] = array[index - 1];
                index--;
            }
            // 插入到合适位置
            array[index] = element;
        }
    }
}

/**
 * 插入排序-二分搜索优化
 * 该方式是在插入排序的基础，对每个元素查找头部的待插入位置做的优化（即减少比较次数）
 * 本质仍然是从头到尾扫描每个元素，并将其插入到头部的合适位置。其时间复杂度未改变
 * <p>
 * 二分搜索：
 * 对[begin,end)区间内的元素，查找元素V(注：end-begin=有效元素个数)
 * 1.先对比mid=(end-begin)>>1
 * 2.若 v < mid，去[begin,mid)查找
 * 若 v > mid，去[mid+1,end)查找
 * 若 v = mid，返回
 * 3.循环以上操作直至查找结束，若未找到时，begin=end 即 [begin,end)
 * 若存在多个相同元素，返回元素不确定
 * <p>
 * 插入排序-二分查找优化
 * 1.在元素v的插入过程中，可以先二分搜索出合适的插入位置，然后再讲元素v插入（找到插入位置时仍是将后面元素后移）
 * 2.要求二分查找返回的插入位置：第一个大于V的元素位置。
 */
class BinarySearchInsertionSort extends Sort {

    public BinarySearchInsertionSort(Integer[] array) {
        super(array);
    }

    @Override
    public void sort() {

        // 循环每个元素
        for (int i = 1; i < array.length; i++) {
            Integer element = array[i];
            // 找到带插入位置
            int index = binarySearch(i);
            // 位置后所有元素后移
            for (int j = i; j > index; j--) {
                array[j] = array[j - 1];
            }
            // 元素插入到合适位置
            array[index] = element;
        }
    }

    /**
     * 查找区间内第一个大于array[end]的元素
     *
     * @param end
     * @return
     */
    private int binarySearch(int end) {
        int begin = 0;
        Integer element = array[end];
        while (begin < end) {
            int mid = (end + begin) >> 1;
            if (array[mid] > element) {
                end = mid;
            } else {
                begin = mid + 1;// 注：如果array[mid]=element,仍然是右边查找，因为查找的第一个大于的元素位置
            }
        }
        return begin;
    }


}