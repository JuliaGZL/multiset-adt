public class TreeMultiSet extends MultiSet {

    private Tree tree;

    public TreeMultiSet() {
        this.tree = new Tree(null);
    }
    /**
     * Add the given item to this multiset.
     *
     * @param item the item to add
     */
    @Override
    void add(Integer item) {
        tree.insert(item);
    }

    @Override
    void remove(Integer item) {
        tree.deleteItem(item);
    }

    @Override
    boolean contains(Integer item) {
        return tree.contains(item);
    }

    @Override
    boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    Integer count(Integer item) {
        return tree.count(item);
    }

    @Override
    Integer size() {
        return tree.length();
    }
}
