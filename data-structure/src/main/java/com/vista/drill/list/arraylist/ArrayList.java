package com.vista.drill.list.arraylist;


import com.vista.drill.list.AbstractList;

/**
 * 自定义实现ArrayList方法-动态数组
 * https://juejin.im/post/5df740526fb9a0164423d976
 * 允许添加null元素
 *
 * 注意点：
 * 1.List底层使用数组存储元素。
 *   1.1List初始化时申请或默认给与长度空间，但List元素个数用size表示，每次添加一个元素后 size+=1。
 * 2.add():添加前检验是否要扩容，创建扩容后的数组并赋值元素，将新数组地址传递给elements
 * 3.clear():size=0，同时将数组元素设为null，元素对象等待JVM的回收（因为JVM回收时，检查到对象没有引用时就会对对象进行回收）
 * 4.remove():待删除位置后的元素迁移，同时无用元素位置设为null，等待元素无用时可以回收。
 * 5.null元素：list允许添加null元素，但是在contains或indexOf等方法中，需要对null元素特殊处理，因为调用null.方法名会抛异常：NULLPoniterExecption。
 * 6.泛型：不能创建泛型数组，必须创建Object数组，然后强转：(E[]) new Object[capacity]
 *
 * 问题：
 * 1. 对象数组为什么是存放的对象地址。
 *    1.基本数据类型的每个数据的内存空间大小是固定的（所占字节），所以在创建基本数据类型的数组时，申请的内存空间是确定的
 *    2.每个对象中存在多个属性，且每个属性值大小是不确定的，所以创建对象数组时每个对象的存储空间是不确定的，数组分配的内存空间可能不够存储一个对象，或存储一个对象过剩
 *    3.为了规避上述问题，对象数组存储对象地址，而每个对象地址大小是固定的，所以规范统一了对象数组每个元素的内存空间大小
 *    4.同时在对象无引用时便于Jvm回收
 *
 * 心得：
 * 1.因为List<E>为泛型类，所以在创建初期可以设定为特殊类型，如 int[]，然后再修改为泛型 E
 * 2.方法编写中，可以先写简单的实现，然后再添加异常或特殊处理
 *
 * @author WenTingTing by 2020/3/18
 */
public class ArrayList<E> extends AbstractList<E> {
    private E[] elements;

    // 默认数组长度
    private static final int CAPACITY_DEFAULT = 10;

    public ArrayList() {
        this(CAPACITY_DEFAULT);
    }

    public ArrayList(int capacity) {
        capacity = capacity > CAPACITY_DEFAULT ? capacity : CAPACITY_DEFAULT;
        this.elements = (E[]) new Object[capacity];
    }

    /**
     * 判断数组容量是否够用
     *
     * @param size 需要的数组长度
     */
    private void isCapacityInspection(int size) {
        // 判断数组容量是否够用
        int length = elements.length;
        if (size <= length) {
            return;
        }
        // 扩容为原来的1.5倍
        int newLength = length + length >> 1;
        if (size >= newLength) {
            newLength = size;
        }
        // 数组复制
        E[] newElement = (E[]) new Object[newLength];
        for (int i = 0; i < length; i++) {
            newElement[i] = elements[i];
        }
        // 数组赋值
        elements = newElement;
    }




    @Override
    public E get(int index) {
        // 判断下标是否越界
        isIndexValid(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        // 验证index是否越界
        isIndexValid(index);

        // 索引元素重置
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        // 验证index是否越界
        rangeCheckForAdd(index);

        // 判断容量是否满足
        isCapacityInspection(size + 1);

        // 原index及之后元素后移
        for (int i = size - 1; i > index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        // 验证index是否越界
        isIndexValid(index);

        final E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return old;
    }

    @Override
    public int indexOf(E element) {
        // 判断element是否是为null
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;

    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(",");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

}
