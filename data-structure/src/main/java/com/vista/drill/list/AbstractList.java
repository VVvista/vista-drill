package com.vista.drill.list;


/**
 * @author WenTingTing by 2020/3/23
 */
public abstract class AbstractList<E> implements List<E> {
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 判断下标是否越界
   public void isIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new NullPointerException("index" + index + ",size:" + size);
        }
    }

    // 判断下标是否越界
    public  void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new NullPointerException("index" + index + ",size:" + size);
        }
    }
    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }
    @Override
    public void add(E element) {
        add(size, element);
    }

}
