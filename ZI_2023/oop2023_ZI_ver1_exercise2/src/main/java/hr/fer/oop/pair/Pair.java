package hr.fer.oop.pair;

import java.util.Objects;

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first) + Objects.hash(second);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair<?, ?> pair) {
            return this.hashCode() == pair.hashCode();
        }
        return false;
    }
}
