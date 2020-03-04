import java.util.HashMap;
import java.util.Map;

public class MyCountMap<K> implements CountMap<K>{
    private HashMap<K, Integer> myMap = new HashMap<>();

    @Override
    public void add(K key) {
        if (myMap.containsKey(key)) {
            myMap.put(key, myMap.get(key) + 1);
        } else {
            myMap.put(key, 1);
        }
    }

    @Override
    public int getCount(K key) {
        return myMap.getOrDefault(key, 0);
    }

    @Override
    public int remove(K key) {
        int count = getCount(key);
        myMap.remove(key);
        return count;
    }

    @Override
    public int size() {
        return myMap.size();
    }

    @Override
    public void addAll(CountMap<? extends K> source) {
        for (Map.Entry<? extends K, Integer> entry: source.toMap().entrySet()) {
            if (myMap.containsKey(entry.getKey())) {
                myMap.put(entry.getKey(), myMap.get(entry.getKey()) + entry.getValue());
            } else {
                myMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public Map<K, Integer> toMap() {
        return (Map<K, Integer>) myMap.clone();
    }

    @Override
    public void toMap(Map<K, Integer> destination) {
        destination = (Map<K, Integer>) myMap.clone();
    }
}
