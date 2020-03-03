import java.util.HashMap;
import java.util.Map;

public class MyCountMap<T> implements CountMap<T>{
    private Map<T, Integer> myMap = new HashMap<>();

    @Override
    public void add(T t) {
        if (myMap.containsKey(t)) {
            myMap.put(t, myMap.get(t) + 1);
        } else {
            myMap.put(t, 1);
        }
    }

    @Override
    public int getCount(T t) {
        return myMap.getOrDefault(t, 0);
    }

    @Override
    public int remove(T t) {
        int count = getCount(t);
        myMap.remove(t);
        return count;
    }

    @Override
    public int size() {
        return myMap.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        for (Map.Entry<T, Integer> entry: source.toMap().entrySet()) {
            if (myMap.containsKey(entry.getKey())) {
                myMap.put(entry.getKey(), myMap.get(entry.getKey()) + entry.getValue());
            } else {
                myMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return myMap;
    }

    @Override
    public void toMap(Map destination) {
        destination = myMap;
    }
}
