package com.vista.drill.improve.sort;

/**
 * 堆排序（升序）
 * 堆排序是对选择排序的优化，优化了内循环查找最大值的操作。
 * 堆排序利用二叉堆的性质(二叉堆底层使用数组实现，父节点与子节点索引有对应关系，最大值存储在根节点)
 * 1.利用原地建堆将原数组转换成最大堆，将根节点(array[0])与尾部元素交换；
 * 2.利用下滤操作再次将数组调整为最大堆，再将根节点与次尾部元素交换；
 * 3.循环以上操作，直至所有元素均有序。
 * <p>
 * 说明：
 * 1.原地建堆：采用效率更高的：自下而上的下滤(O(n))
 * 2.最好、最坏、平均时间复杂度：O(nlogn)，空间复杂度：O(1)
 * 3.不稳定排序
 *
 * @author WenTingTing by 2020/4/27
 */
public class HeapSort extends Sort {
    private int heapSize;

    public HeapSort(Integer[] array) {
        super(array);
    }

    /**
     * 堆排序:
     * 1.对原数组进行原地建堆
     * 2.交换堆顶与尾元素
     */
    @Override
    public void sort() {
        // 原地建堆
        heapSize = array.length;
        heapify();
   /*     for (int end = heapSize - 1; end > 0; end--) {

            // 将根节点移到尾部
            int tmp = array[0];
            array[0] = array[end];
            array[end] = tmp;
            // 堆的元素数量减1
            heapSize--;
            // 根节点下滤操作
            siftDown(0);
        }*/

        while (heapSize > 1) {// 当堆中只剩两个元素时即停止操作
            // 将根节点移到尾部
            int tmp = array[0];
            array[0] = array[--heapSize];// 堆的元素数量减1
            array[heapSize] = tmp;
            // 根节点下滤操作
            siftDown(0);
        }
    }

    /**
     * 原地建堆
     */
    private void heapify() {
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 下滤操作
     */
    private void siftDown(int index) {
        int element = array[index];
        int childIndex;
        while (index < heapSize) {
            childIndex = index >> 1 + 1;// 先获取左子节点的索引

            if (childIndex + 1 < heapSize && array[childIndex + 1] > array[childIndex]) {// 如果右子节点存在，并且值大于左子节点值，则更新childIndex
                childIndex = childIndex + 1;
            }
            // 父节点大于子节点，直接退出
            if (element >= array[childIndex]) break;
            // 比较交换子节点位置
            array[index] = array[childIndex];
            index = childIndex;
        }
        array[index] = element;
    }
}
