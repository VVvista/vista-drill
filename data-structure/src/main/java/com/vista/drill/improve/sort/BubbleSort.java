package com.vista.drill.improve.sort;

/**
 * 冒泡排序（升序）
 * 底层使用数组实现，并且使用原地排序，排序完成后原数组即有序。降序同原理，仅是比较规则不同
 * 操作：
 * 1.从头开始比较相邻位置的元素，如果前者>后者，交换两者位置，直至遍历比较至尾部，此时尾部位置即为最大值
 * 2.再依照上面的原则，从头到倒数第二个位置进行比较交换，遍历结束后，底数第二位即为次最大值
 * 3.循环以上操作，直至数组array[1]位置放置好元素。
 * <p>
 * <p>
 * 说明：
 * 1.优化1是元素全排序时提前退出外循环；优化2是元素尾部局部有序时缩短内部循环长度，包含优化1情况；
 * 2.两种优化仅是提升了特殊情况下的循环判断次数，降低了最好时间复杂度(O(n^2)->O(n)),但是没有本质改变冒泡排序的核心思想,即 平均/最坏 时间复杂度：O(n^2)未变；
 * 3.因此冒泡排序：平均时间复杂度：O(n^2)，最好时间复杂度 O(n)(循环一次判断元素完全有序即退出)，空间复杂度 O(1)(在原数组上进行交换操作，仅添加临时变量)
 * 4.稳定排序
 *
 * @author WenTingTing by 2020/4/27
 */
public class BubbleSort extends Sort {

    public BubbleSort(Integer[] array) {
        super(array);
    }

    /**
     * 最简单的排序方式
     */
    @Override
    public void sort() {
        for (int end = array.length - 1; end > 0; end--) {

            for (int i = 1; i <= end; i++) {
                // 如果前者比后者大，则交换两者位置
                if (array[i - 1] > array[i]) {
                    int tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                }
            }
        }
    }


    /**
     * 代码优化1
     * 问题：当初始数组或排序过程中(剩余数组元素)已有序，按照sort()方式仍会进行o(n^2)次比较，性能较低；
     * <p>
     * 优化：如果初始数组或未排序元素已完全有序，则可以提前终止冒泡排序。
     * 原理：当元素已有序时，便不会进行if中的操作，此时可以看做元素以有序，直接终止排序
     * 操作：添加临时变量sorted，在每一次外部排序时，若进行if中的操作，则认为数组仍不有序，否则认为数组有序未发生交换，则退出外部循环。
     * <p>
     * 说明：此种方式只有在初始数组有序或排序过程中数组有序，提前提出遍历可节省效率；
     * 乱序数组中并不能提高效率，甚至会降低效率，因为会进行sorted的创建 赋值 判断等操作。
     */
    public void sort2() {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;//是否进行过排序

            for (int i = 1; i <= end; i++) {
                // 如果前者比后者大，则交换两者位置
                if (array[i - 1] > array[i]) {
                    int tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                    sorted = false;// 此轮循环进行过排序,任务当前元素未有序
                }
            }
            if (!sorted) break;
        }
    }


    /**
     * 代码优化2
     * 问题：在优化2问题中，只有未排序元素全都有序才终止循环，如果仅是未排序元素的末尾几个元素已有序，则可以缩减未排序元素遍历的尾部index，从而缩减比较次数
     * <p>
     * 优化：如果未排序元素的末尾部分元素已有序，则可以缩短未排序元素遍历的尾部索引index。
     * 原理：记录排序时最后一次交换的位置index，index之后元素自然是有序 未发生交换，下一轮比较时，比较遍历截止到index即可。
     * 操作：添加临时变量，记录执行if中的操作时的交换位置index，下一次外部循环时，内部循环截止到index
     * <p>
     * 说明：此种方式是在优化1的基础上进行的进一步优化，在未排序元素末尾出现有序时提升效率；
     * 完全乱序数组中并不能提高效率，甚至会降低效率，因为会进行sorted的创建 赋值 判断等操作。
     */
    public void sort3() {
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 1;// sortedIndex的初始值在数组完全有序的时候有用

            for (int i = 1; i <= end; i++) {
                // 如果前者比后者大，则交换两者位置
                if (array[i - 1] > array[i]) {
                    int tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                    sortedIndex = i;
                }
            }
            end = sortedIndex;// 若sortedIndex=1，说明数组完全有序，退出外循环，即为优化1情况
        }
    }
}