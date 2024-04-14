package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void grow() {
        int newLength = container.length > 0 ? container.length * 2 : 1;
        container = Arrays.copyOf(container, newLength);
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T changed = get(index);
        container[index] = newValue;
        return changed;
    }

    @Override
    public T remove(int index) {
        T removed = get(index);
        System.arraycopy(container, index + 1, container,
                index, container.length - index - 1);
        size--;
        modCount++;
        return removed;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException("can't");
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("can't");
                }
                return container[index++];
            }
        };
    }
}
