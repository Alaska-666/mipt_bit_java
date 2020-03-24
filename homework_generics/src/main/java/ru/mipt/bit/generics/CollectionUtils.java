package ru.mipt.bit.generics;

import java.util.*;

public class CollectionUtils {
    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static<T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static<T> int indexOf(List<? extends T> source, T t) {
        return source.indexOf(t);
    }

    public static<T> List<? extends T> limit(List<? extends T> source, int size) {
        return source.subList(0, size);
    }

    public static<T> void add(List<? super T> source, T t) {
        source.add(t);
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static<T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static<T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T t : c2) {
            if (c1.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public static<T extends Comparable<? super T>> List<? super T> range(List<? extends T> list, T min, T max) {
        List<T> newList = newArrayList();
        for (T t : list) {
            if (t.compareTo(min) >= 0 && t.compareTo(max) <= 0) {
                newList.add(t);
            }
        }
        Collections.sort(newList);
        return newList;
    }

    public static<T> List<? super T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> newList = newArrayList();
        for (T t : list) {
            if (comparator.compare(t, min) >= 0 && comparator.compare(t, max) <= 0) {
                newList.add(t);
            }
        }
        newList.sort(comparator);
        return newList;
    }
}
