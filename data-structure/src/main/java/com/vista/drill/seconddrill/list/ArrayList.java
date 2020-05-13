package com.vista.drill.seconddrill.list;

/**
 * 01-动态数组
 *
 * @author WenTingTing by 2020/5/11
 */
public class ArrayList<E> implements List<E> {
    private int size;
    private E[] elements;
    private static final int CAPACITY_DEFAULT = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        size = 0;
        elements = (E[]) new Object[CAPACITY_DEFAULT];
    }

    public ArrayList(int length) {
        size = 0;
        int capacity = length > CAPACITY_DEFAULT ? length : CAPACITY_DEFAULT;
        elements = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        if (size == 0) return null;
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        // 校验index的有效性
        rangeCheck(index);
        // 校验是否需要扩容
        ensureCapacity(size + 1);
        // index 及其之后的元素后移
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        // 将element元素添加到index位置
        elements[index] = element;
        // size ++;
        size++;
    }


    private void ensureCapacity(int newSize) {
        int length = elements.length;
        if (newSize <= length) return;
        int newLength = length + length >> 1;
        newLength = newSize > newLength ? newSize : newLength;// 此处还应该处理容量是否越界的问题，但未处理
        E[] newElements = (E[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E element = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        if (size == 0) return;
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }
}
