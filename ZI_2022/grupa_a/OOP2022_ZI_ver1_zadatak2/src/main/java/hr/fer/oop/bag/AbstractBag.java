package hr.fer.oop.bag;

public abstract class AbstractBag<T> implements Bag<T> {

    /**
     * Compares the specified object with this bag for equality. Returns
     * {@code true} if the given object is also a bag and the two bags contain
     * the same elements.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Bag<?> bag) {
            return this.toCollection().containsAll(bag.toCollection()) && this.size() == bag.size();
        }
        return false;
    }

    /**
     * Returns the hash code value for this bag. Two bags that contain the same
     * group of elements (irrespective of their order) should return the same
     * hash code.
     *
     * @return the hash code value for this bag
     */
    @Override
    public int hashCode() {
        return this.toCollection().stream().mapToInt(e -> e == null ? 1 : e.hashCode()).sum();
    }
}
