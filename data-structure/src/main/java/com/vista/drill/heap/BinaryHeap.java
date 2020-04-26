package com.vista.drill.heap;

import java.util.Comparator;
import java.util.Objects;

/**
 * 二叉堆：最大堆
 * 逻辑结构为完全二叉树，也称为完全二叉堆
 * 1.性质：每个节点值都 >= 子节点值
 * 2.基于二叉堆的性质，底层使用 数组 实现
 * 3.元素必须具有可比较性，且不能为null
 * 4.数组索引从 index=0 开始，非根节点 i 的父节点为：(i-1)/2(向下取整) ；i 的左右子节点：2i+1 、2i+2 (注意与数组有效个数的比较)
 * 需求：获取最大值、删除最大值、添加
 * 基于二叉堆的性质(完全二叉堆，节点与子节点的索引下标呈线性关系)底层使用数组实现，但是其他堆不一定也是用数组实现
 * <p>
 * 获取最大值：获取根节点，index=0
 * 删除最大值：删除根节点，index=0，与其他树的删除相同，用最后一个叶子结点的值替换根节点的值，删除叶子节点，对新根节点进行下虑操作
 * 添加：尾端添加叶子结点，index=size，然后对新节点进行上滤操作
 * 批量建堆：自上而下的上滤、自下而上的下滤（原理类同于添加、删除操作），但后者效率>前者效率
 * <p>
 * 二叉堆-最小堆实现：
 * 在最大堆的基础上，创建最大堆对象时，重新定义comparator(比较原则与最大堆相反)
 * <p>
 * 注意：
 * 因为数组下标从0开始，默认堆根节点的下标 i=0：
 * 1.i=0，根节点
 * 2.i>0，左右子节点下标：2i+1、2i+2 ，父节点下标：(i-1)/2(向下取整)。(注意与size、0的关系)
 *
 * @author Wen TingTing by 2020/4/25
 */
public class BinaryHeap<E> extends AbstractHeap<E> {
    private E[] elements;

    public BinaryHeap(Comparator comparator) {
        super(comparator);
        this.elements = (E[]) new Objects[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        super();
        this.elements = (E[]) new Objects[DEFAULT_CAPACITY];
    }

    /**
     *
     */
    public BinaryHeap(E[] elements, Comparator comparator) {
        super(comparator);
        if (elements.length == 0 || elements == null) {
            this.elements = (E[]) new Objects[DEFAULT_CAPACITY];
            return;
        }
        this.size = elements.length;
        int length = Math.max(size, DEFAULT_CAPACITY);
        this.elements = (E[]) new Objects[length];
        for (int i = 0; i < size; i++) {
            this.elements[i] = elements[i];
        }
        heapifyUp();
    }

    /**
     * 批量建堆:自上而下的上滤
     */
    private void heapifyUp() {
        for (int i = 0; i < size; i++) {
            siftUp(i);
        }
    }

    /**
     * 批量建堆:自下而上的下滤
     */
    private void heapifyDown() {
        for (int i = (size >> 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 清空堆
     * 1.将堆大小置为0
     * 2.将数组元素置为null
     */
    @Override
    public void clear() {
        super.clear();
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
    }

    /**
     * 添加元素
     * <p>
     * 1.首先将新元素添加至堆末尾（即数组index=size）
     * 2.再进行上滤操作：若元素>父节点，交换两者位置，否则退出
     * <p>
     * 说明：
     * 因为元素添加至堆尾时，有可能元素比父节点大，导致堆失衡，
     * 所以必须调整平衡，使堆再次恢复平衡，此处采用上滤操作：若元素>父节点，交换两者位置，直至平衡
     *
     * @param element
     */
    @Override
    public void add(E element) {
        // 判断element是否为null
        elementNotNullCheck(element); //非空判断
        int lastIndex = size;
        // 扩容
        ensureCapacity(++size);
        // 将元素添加至堆尾
        elements[lastIndex] = element;
        // 上滤，调整平衡
        siftUp(lastIndex);
    }

    /**
     * 扩容操作
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int length = elements.length;
        if (capacity <= length) return;
        //容量扩为原来的1.5倍
        length = length + length << 1;
        if (capacity > length) length = capacity;

        // 将原数组元素赋值到新数组
        E[] newElements = (E[]) new Object[length];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        this.elements = newElements;

    }

    /**
     * 判断元素是否为null
     *
     * @param element
     */
    public void elementNotNullCheck(E element) {
        if (element == null)
            throw new IllegalArgumentException("element must be not null");
    }

    /**
     * 上滤操作
     * index位置元素与父节点比较
     * 1.若元素>父节点，则交换两者位置；
     * 2.若没有父节点，则退出
     * 3.一直向上循环比较，直至元素<父节点或父节点为null位置
     * <p>
     * 默认堆根节点的下标 i=0，与数组下标一致：
     * 1.i=0，根节点
     * 2.i>0，父节点下标：(i-1)/2(向下取整)。(注意与0的关系)
     *
     * @param index 索引
     */
    private void siftUp(int index) {

        E element = elements[index];
        int parentIndex;
        E parent;
        while (index > 0) {
            parentIndex = (index - 1) >> 1;
            parent = elements[parentIndex];
            // 如果元素>父元素，交换位置
            if (compareTo(element, parent) <= 0) break;
            elements[index] = parent;
            index = parentIndex;
        }
        ;
        elements[index] = element;
    }

    /**
     * 比较两个元素的大小
     * （E必须具有可比较性，且不能为null）
     */
    private int compareTo(E e1, E e2) {
        if (comparator != null) return comparator.compare(e1, e2);
        return ((Comparable) e1).compareTo(e2);
    }

    /**
     * 获取最大值
     *
     * @return
     */
    @Override
    public E get() {
        if (size == 0) return null;
        return elements[0];
    }

    /**
     * 删除最大值
     * 1.用堆尾元素替换根元素
     * 2.删除堆尾元素
     * 3.对根节点进行下滤操作
     *
     * @return
     */
    @Override
    public E remove() {
        if (size == 0) return null;
        // 堆尾元素替换根元素
        E element = elements[0];
        // 删除堆尾元素
        elements[0] = elements[--size];
        elements[size] = null;
        // 下滤操作
        siftDown(0);
        return element;
    }

    /**
     * 下滤操作：
     * 1.比较元素与子节点的大小
     * 2.如果元素<max(子节点),则交换元素与max(子节点)位置
     * 3.循环以上操作，直至所有子节点都小于父节点
     * <p>
     * 注意：只有非叶子节点才需要下滤判断，叶子节点无需再进行下滤判断，
     * 而非叶子节点的个数=最后一个非叶子节点索引+1(下标从0开始)=最后一个叶子结点的父节点=(n/2)(向下取整)
     *
     * @param index
     */
    public void siftDown(int index) {
        if (size == 1) return;
        //最后一个非叶子节点的下标
        int num = size >> 1;
        int childIndex;
        E child;
        E element = elements[index];
        while (index <= num) {
            childIndex = index << 1 + 1;
            child = elements[childIndex];
            if (childIndex + 1 < size && compareTo(elements[childIndex + 1], child) > 0) {
                child = elements[childIndex = childIndex + 1];
            }
            if (compareTo(element, child) >= 0) break;
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }

    /**
     * 删除堆顶元素，添加新元素E
     * 1.直接将新元素E替换堆顶元素
     * 2.对根节点进行下滤操作
     *
     * @param element
     * @return
     */
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        if (size == 0) {
            size++;
        }
        E oldE = elements[0];
        elements[0] = element;
        siftDown(0);
        return oldE;
    }
}
