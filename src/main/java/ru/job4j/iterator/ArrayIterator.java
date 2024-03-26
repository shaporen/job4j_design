package ru.job4j.iterator;

import java.util.*;

public class ArrayIterator implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public ArrayIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
