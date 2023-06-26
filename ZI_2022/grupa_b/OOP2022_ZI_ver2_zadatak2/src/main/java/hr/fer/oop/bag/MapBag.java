package hr.fer.oop.bag;

import java.util.*;

public class MapBag<T> extends AbstractBag<T> {

    final Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T e) {
        if (e == null) throw new IllegalArgumentException();
        map.computeIfPresent(e, (k, v) -> v + 1);
        map.putIfAbsent(e, 1);
    }

    @Override
    public void remove(T e) {
        map.computeIfPresent(e, (k, v) -> v - 1);
        if (map.get(e) == 0) map.remove(e);
    }

    @Override
    public Iterator<T> iterator() {
        List<T> list = new LinkedList<>();
        for (Map.Entry<T, Integer> e : map.entrySet()) {
            for (int i = 0; i < e.getValue(); i++) {
                list.add(e.getKey());
            }
        }
        return list.iterator();
    }

    @Override
    public int frequency(Object o) {
        Integer value = map.get((T) o);
        return value == null ? 0 : value;
    }

    @Override
    public int size() {
        return map.values().stream().mapToInt(e -> e).sum();
    }
}
