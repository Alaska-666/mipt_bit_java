package ru.mipt.bit.generics;

import java.util.Map;

public interface CountMap<K> {
    void add(K key);

    int getCount(K key);

    int remove(K key);

    int size();

    void addAll(CountMap<? extends K> source);

    Map<K, Integer> toMap();

    void toMap(Map<K, Integer> destination);
}

