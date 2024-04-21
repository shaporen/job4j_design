package ru.job4j.collection;

import java.util.*;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> current = head;
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("cannot be changed during iteration");
        }
        T deleted = head.item;
        Node<T> newHead = head.next;
        head.next = null;
        head.item = null;
        head = newHead;
        size--;
        modCount++;
        return deleted;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private ForwardLinked.Node<T> current = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException("cannot be changed during iteration");
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("no elements anymore");
                }
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private ForwardLinked.Node<T> next;

        Node(T element, ForwardLinked.Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
