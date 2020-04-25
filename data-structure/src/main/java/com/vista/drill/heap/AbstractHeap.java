package com.vista.drill.heap;

import java.util.Comparator;

/**
 * @author Wen TingTing by 2020/4/25
 */
public abstract class AbstractHeap<E> implements Heap<E> {
    protected int size;
    protected Comparator comparator;

    protected int DEFAULT_CAPACITY = 10;

    public AbstractHeap(Comparator comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
        this(null);
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
    public void clear() {
        size = 0;
    }

    @Override
    public void add(E element) {

    }

    @Override
    public E get() {
        return null;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E element) {
        return null;
    }
}
