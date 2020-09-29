package com.vista.drill.improve.g布隆过滤器;

/**
 * 1.布隆过滤器
 * 基础操作：添加、查询元素
 * 删除困难
 * <p>
 * 注：元素类型为泛型T
 * 1、设置二进位为1
 * 将第n位设置为1，使用 | 运算
 * 100000
 * | 	000100   ==  (1 << 2)
 * ------------------
 * 100100
 * 2、查询二进制位
 * 10101111
 * & 	00000100	== 	(1 << 2)
 * --------------
 * 00000100 != 0, 说明index位的二进制为1
 * <p>
 * https://blog.csdn.net/weixin_43734095/article/details/105766972
 *
 * @author WenTingTing by 2020/9/25
 */
public class BloomFilter<T> implements BloomFilterInter<T> {
    // 二进制向量的长度(一共有多少个二进制位)
    private int bitSize;
    // 哈希函数的个数
    private int hashSize;
    // 存储二进制位
    private long[] bits;

    /**
     * 布隆顾虑器构造器
     *
     * @param n 数据规模
     * @param p 误判率
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p >= 1 || p <= 0) {
            throw new IllegalArgumentException("wrong n or p");
        }
        // 根据公式求出对应的数据
        double ln2 = Math.log(2);
        // 求出二进制向量长度
        bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));
        // 求出hash函数个数
        hashSize = (int) (bitSize * ln2 / n);
        // 构造存储二进制位数组
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
        bits = new long[(int) Math.ceil(bitSize / Long.SIZE)];
    }

    /**
     * 设置index位置的二进制位为1
     *
     * @param index 二进制位
     * @return 是否设置成功
     */
    private boolean set(int index) {
        // 获取二进制数组位置
        int i = index / Long.SIZE;
        // 获取数组位置元素值
        long value = bits[index / Long.SIZE];
        // 获取二进制位位置
        int bitValue = 1 << (index % Long.SIZE);
        //设置index位置二进制位为1
        bits[i] = value | bitValue;
        return (value & bitValue) == 0;
    }

    /**
     * 添加元素
     *
     * @param value
     * @return true 添加成功，bit发生变化
     */
    @Override
    public boolean put(T value) {
        // 获取value的hash值
        // 利用value生成 2 个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >> 16;
        boolean result = false;
        for (int i = 1; i <= hashSize; i++) {
            // 分别计算hash函数对应的二进制位置
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 生成一个二进制的索引
            int index = combinedHash % bitSize;
            // 设置二进制位置为1
            // 只要有一个二进制位置发生变化，就代表添加成功
            if (set(index)) result = true;

        }
        return result;
    }

    /**
     * 判断元素是否存在
     * 比如要查看 10101111 的 第2位bit 为 1，应当 10101111 & 00000100，即 10101111 & (1 << 2)，
     * 只有指定位置的二进制的值为 0，返回值才会是 0，否则为 1；
     *
     * @param value
     * @return false 一定不存在，true 可能存在
     */
    @Override
    public boolean contains(T value) {
        int hash1 = value.hashCode();
        int hash2 = hash1 >> 16;
        // 遍历hash函数
        for (int i = 1; i <= hashSize; i++) {
            // 查询二进制数组位置
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0)
                combinedHash = ~combinedHash;

            // 查询二进制位位置
            int index = combinedHash % bitSize;
            // 获取二进制位元素，即判断value是否存在
            if (!get(index)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查看index位置的二进制的值
     *
     * @param index 二进制位 位置
     * @return true  result!=0：存在； false result=0 不存在
     */
    private boolean get(int index) {
        // 二进制数组位置
        int i = index / Long.SIZE;
        // 数组中 二进制位 位置
        int j = index % Long.SIZE;
        // 二进制数组元素值
        long value = bits[i];
        long result = value & (1 << j);
        return result != 0;
    }
}

interface BloomFilterInter<T> {

    /**
     * 添加元素
     *
     * @param value
     * @return true 添加成功，bit发生变化
     */
    boolean put(T value);

    /**
     * 查询元素是否存在
     *
     * @param value
     * @return false 一定不存在，true 可能存在
     */
    boolean contains(T value);
}
