package com.vista.drill.improve.a排序;

/**
 * 快速排序
 * 1.从序列中寻找一个轴点元素pivot:假设每次选取的都是0位置元素
 * 2.利用pivot将序列分割成2个子序列： 小于pivot pivot 大于pivot
 * 3.对子序列重复以上操作，知道不能再分割(子序列中只含有一个元素)
 * 本质：逐步将每个元素当作轴点元素,即确定每个轴点元素的位置
 * <p>
 * 时间复杂度：
 * 在轴点左右元素比较均匀的情况下，此时也是最好的情况：O(nlogn)
 * 若轴点左右元素极度不均匀的情况，最坏情况：O(n^2)
 * 空间复杂度：O(logn)
 * 不稳定排序
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
     * 递归实现
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;// 当数组至少有2个元素时才进行快速排序
        // 获取轴点元素位置
        int mid = pivotIndex(begin, end);
        // 对[begin,index) [index,end) 再进行快速排序
        sort(begin, mid);
        sort(mid + 1, end);
    }

    /**
     * 依次循环比较每个元素，将元素分成2个部分，确定轴点元素放置在合适位置
     * 1.选取数组范围内的首元素为轴点元素，备份轴点元素element
     * 2.分别循环移动 begin 和 end指针
     * 先从end开始：
     * 1.若：array[end] > element,end--;尾指针前移一位
     * 2.若：array[end] < element,array[begin]=array[end],begin++;将end位置元素移动begin位置，并begin后移一位
     * 3.若：array[begin] < element,begin++;头指针前移一位
     * 4.若：array[begin] > element,array[end]=array[begin],end--;将begin位置元素移动到end位置，并end前移一位
     * 重复以上步骤，直至begin=end结束，此时begin位置即为轴点元素待插入位置，最后将轴点插入该位置
     * 注：若元素与轴点元素相同，仍然是将元素移动另一方，不管是begin方还是end方；此处是为了避免快速排序出现最坏情况，因此快速排序为不稳定排序
     * （若序列中所有元素都与轴点元素相等时，也能将序列分割成2个均匀的子序列）
     * <p>
     * 最坏的情况：每次选取的轴点元素都为最大最小值，使得数组范围内的元素都统一左移或右移一位；
     * 优化：随机选取数组中的元素作为轴点元素
     * 当随机选取一个轴点元素时，仍将轴点元素与首元素交换，此时轴点元素就在首位置，之后的操作跟常规操作一样
     *
     * @param begin
     * @param end
     * @return
     */
    private int pivotIndex(int begin, int end) {
/*   随机选取一个轴点元素
        // 为了降低最坏情况(O(n^2)的时间复杂度)的出现概率，随机选择一个元素跟begin位置进行交换
        swap(begin, begin + (int) (Math.random() * (end - begin)));
        */

        // 备份轴点元素
        Integer pivot = array[begin];
        end--;// end指向最后一个元素
        while (begin < end) {
            while (begin < end) {
                if (array[end] > pivot) {// 右边元素 > 轴点元素
                    end--;// 只位移，不进行元素交换
                } else {// 右边元素 <= 轴点元素
                    array[begin++] = array[end];
                    break; //执行完一次操作后，需要掉头，所以执行一个break
                }
            }
            while (begin < end) {
                if (array[begin] < pivot) {// 左边元素 < 轴点元素
                    begin++;
                } else {// 左边元素 >= 轴点元素
                    array[end--] = array[begin];
                    break;// 通过两个break，能实现两个while交替执行。
                }
            }
        }
        array[begin] = pivot;// 将轴点元素放入最终的位置
        return begin;// 返回轴点元素的位置
    }
}
