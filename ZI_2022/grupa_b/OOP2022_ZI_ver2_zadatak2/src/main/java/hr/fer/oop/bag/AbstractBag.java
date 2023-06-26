package hr.fer.oop.bag;

import java.util.Iterator;

public abstract class AbstractBag<T> implements Bag<T> {

    /**
     * Compares the specified object with this bag for equality. Returns
     * {@code true} if the given object is also a beg and the two begs contain
     * the same elements.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractBag map) {
            Iterator<T> thisIterator = this.iterator();
            Iterator otherIterator = map.iterator();

            if (!this.iterator().hasNext()) {
                return false;
            }

            for (T i; thisIterator.hasNext(); ) {
                i = thisIterator.next();
                if (otherIterator.hasNext()) {
                    if (!i.equals(otherIterator.next())) {
                        return false;
                    }
                } else {
                    return false;
                }

            }
            if (thisIterator.hasNext() == otherIterator.hasNext()) {
                return true;
            } else {
                return false;
            }
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
        int hash = 0;
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            hash += iterator.next().hashCode();
        }
        return hash;
    }
}
