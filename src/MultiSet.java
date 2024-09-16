/**
 * Abstract class representing a MultiSet ADT.
 */
public abstract class MultiSet {

    /**
     * Add the given item to this multiset.
     *
     * @param item the item to add
     */
    abstract void add(Integer item);

    abstract void remove(Integer item);

    abstract boolean contains(Integer item);

    abstract boolean isEmpty();

    abstract Integer count(Integer item);

    abstract Integer size();
}
