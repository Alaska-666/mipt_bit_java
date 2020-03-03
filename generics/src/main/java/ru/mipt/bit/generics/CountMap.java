import java.util.Map;

public interface CountMap<T> {
    void add(T t);

    int getCount(T t);

    int remove(T t);

    int size();

    void addAll(CountMap<T> source);

    Map<T, Integer> toMap();

    void toMap(Map<T, Integer> destination);
}

