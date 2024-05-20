package ru.job4j.map;

public interface SimpleMap<K, V> extends Iterable<K> {
    boolean put(K key, V value);

    boolean remove(K key);

    V get(K key);
}
