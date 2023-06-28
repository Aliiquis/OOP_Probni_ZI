package hr.fer.oop.pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class SimplePairHashSet implements Iterable<Pair> {
    private Map<Pair, Object> internalMap;

    public SimplePairHashSet() {
        this.internalMap = new HashMap<>();
    }

    public Map<Pair, Object> getInternalMap() {
        return internalMap;
    }

    public boolean add(Pair pair) {
        if (internalMap.get(pair) != null) return false;
        internalMap.put(pair, pair);
        return true;
    }

    public int size() {
        return internalMap.size();
    }

    public boolean contains(Object o) {
        if (!(o instanceof Pair)) return false;
        else return internalMap.containsKey((Pair) o);
    }

    public boolean remove(Object o) {
        if (!(o instanceof Pair)) return true;
        else return internalMap.remove((Pair) o) != null;
    }

    @Override
    public Iterator<Pair> iterator() {
        return internalMap.keySet().iterator();
    }
}
