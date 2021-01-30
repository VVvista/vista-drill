package com.vista.drill.improve.b递归;

/**
 * 2.斐波那契数列
 * 1、1、2、3、5、8、13、21、34、……
 * <p>
 * 注:
 * 递归调用的空间复杂度 = 递归深度 * 每次调用所需的辅助空间
 * <p>
 * 优化：
 * 1.常规的递归调用：重复计算过度，时间复杂度过高
 * 2.优化1：使用数组存储元素值，避免重复计算，降低了时间复杂度
 * 3.循环法:创建数组存储元素值
 * 4.优化2：在3基础上，使用滚动数组存储前两个元素值，降低空间复杂度
 * 5.优化3：在4基础上，使用2个临时变量存储前两个元素值，仅是去除了数组
 * 6.公式法
 * 7.尾递归
 *
 * @author WenTingTing by 2020/5/5
 */
public class Fibonacci {

    /**
     * 递归法
     * 求出第n项斐波那契数
     * F(1)=1，F(2)=1，F(n)=F(n−1)+F(n−2)（n≥3）
     * <p>
     * T(n) = T(n − 1) + T(n − 2) + O(1) => T(n) = O(2^n)
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(n)
     * 递归调用的空间复杂度 = 递归深度 * 每次调用所需的辅助空间
     * <p>
     * 问题：
     * 此递归中会出现很多重复的计算：当fib(n-1)调用得出结果之后 再调用计算fib(n - 2)，所以导致至少n-2项数重复计算。
     * 因此需要优化
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 递归优化1
     * 解决重复计算：用数组存放计算过的结果，避免重复计算
     * 降低了时间复杂度：O(n)
     * <p>
     * 问题：
     * 需要额外申请数组空间
     *
     * @param n
     * @return
     */
    public int fib1(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];// 创建数组存放每项数值，数组下标与n对应，0位置不用
        array[2] = array[1] = 1;
        return fib0(array, n);// 获取数组下标n位置的元素
    }

    /**
     * 获取数组下标n位置的元素
     * 如果数组位置元素为0，则向下计算得出结果，并将结果放入数组中；
     * 如果数组位置元素不为0，直接获取返回
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private int fib0(int[] array, int n) {
        if (array[n] == 0) {
            array[n] = fib0(array, n - 1) + fib0(array, n - 2);
        }
        return array[n];
    }

    /**
     * 循环法
     * 使用数组存储每项元素值
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) (使用数组存储元素值)
     * <p>
     * 问题：
     * 需要额外申请数组空间
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];// 创建数组存放每项数值，数组下标与n对应，0位置不用
        array[2] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    /**
     * 优化：滚动数组
     * 使用滚动数组(长度为2)存储前两位的元素
     * 因为每次计算只需要用到数组中的前两位元素，当n较大时数组空间过大，所以使用滚动数组存储值
     * 时间复杂度：O(n)
     * 空间复杂度：O(1) (使用滚动数组存储元素值)
     * <p>
     * 注：
     * 降低了空间复杂度
     *
     * @param n
     * @return
     */
    public static int fib3(int n) {
        if (n <= 2) return 1;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i % 2] = array[0] + array[1];
            System.out.print(array[i % 2] + " ");
        }
        return array[n % 2];
    }

    /**
     * 优化：去除数组
     * 去除数组，通过定义两个临时变量存储前两个元素，无需创建数组
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * <p>
     * 注：仅是优化了数组
     *
     * @param n
     * @return
     */
    public static int fib4(int n) {
        if (n <= 2) return 1;
        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++) {
    /*        int tmp = second;
            second = first + second;
            first = tmp;*/
            second = first + second;
            first = second - first;
        }
        return second;
    }

    /**
     * 公式法
     * 时间复杂度、空间复杂度取决于 pow 函数（至少可以低至 O(logn) ）
     *
     * @param n
     * @return
     */
    public static int fib5(int n) {
        double c = Math.sqrt(5);
        return (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }

    /**
     * 尾递归
     *
     * @param n
     * @return
     */
    public static int fib6(int n) {
       // if (n <= 2) return 1;
        return fib60(n, 1, 1);
    }

    private static int fib60(int n, int first, int second) {
        if (n <= 1) return first;
        return fib60(n - 1, second, second + first);
    }

    public static void main(String[] args) {
        int num=14;
        fib3(num);
        System.out.println();
        System.out.print(fib6(num));
    }
}
