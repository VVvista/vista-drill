package com.vista.drill.improve.a排序;

/**
 * 归并排序
 * 1.不断的将当前序列分割成2个子序列（divide）,直到不能在分割（即序列中仅剩一个元素）
 * 2.不断的将2个子序列进行合并，知道最后只剩1个有序序列(即最初序列)
 * 注：以上过程可以使用递归实现
 * 将2个子序列进行合并的时候，需要将前部数组元素进行备份，合并备份数组与后部数组进行比较插入原数组
 * <p>
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n/2+logn)=O(n)
 * 稳定排序
 *
 * @author Wen TingTing by 2020/4/29
 */
public class MergeSort extends Sort {
    private Integer[] backArr;

    public MergeSort(Integer[] array) {
        super(array);
    }

    /**
     * 归并排序
     */
    @Override
    public void sort() {
        backArr = new Integer[array.length >> 1];
        divide(0, array.length);
    }

    /**
     * 分割成2个子序列
     * 使用递归，不断的将序列分成两个子序列
     */
    private void divide(int begin, int end) {
        if (begin >= end) return;
        int mid = (begin + end) >> 1;
        // 分割成两个子序列
        divide(begin, mid);
        divide(mid , end);

        // 合并两个子序列
        merge(begin, mid, end);

    }

    /**
     * 合并2个子序列
     * 不断将[begin，mid) [mid,end)合并成1个有序序列
     * 1.先将序列[begin，mid)进行备份
     * 2.依次循环与[mid,end)中的元素，较小元素依次放入原数组中
     * 3.直至备份序列[begin，mid)没有元素为止。
     * (如果[begin，mid)先没数据，则[mid,end)剩余元素不用动；
     * 若[mid,end)先没数据，然后将[begin，mid)依次插入，直至无元素，循环结束)
     *
     * @param begin
     * @param mid
     * @param end
     */
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid + 1, re = end;
        int index = begin;
        // 前部序列进行备份
        for (int i = 0; i < le; i++) {
            backArr[i] = array[begin + i];
        }
        //序列合并
        while (li < le) {
            // 如果备份数组元素<后部元素，备份数组元素插入到数组中
            if (ri < re && backArr[li] <= array[ri]) {
                array[index++] = backArr[li++];
            } else {
                // 否则，后部数组元素入数组
                array[index++] = array[ri++];
            }
        }
    }
}
