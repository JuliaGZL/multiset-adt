import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class Tree {
    private Integer root;
    private ArrayList<Tree> subtrees;

    public Tree(Integer root, ArrayList<Tree> subtrees) {
        this.root = root;
        if (root == null) {
            this.subtrees = new ArrayList<Tree>();
        } else {
            this.subtrees = new ArrayList<Tree>();
            this.subtrees.addAll(subtrees);
        }
    }

    public Tree(Integer root) {
        this(root, new ArrayList<Tree>());
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public Integer length() {
        if (this.isEmpty()) {
            return 0;
        }
        else {
            Integer size = 1;
            for (Tree subtree : subtrees) {
                size += subtree.length();
            }
            return size;
        }
    }

    public Integer count(Integer item) {
        if (this.isEmpty()) {
            return 0;
        } else {
            Integer num = 0;
            if (root.equals(item)) { num += 1; }
            for (Tree subtree : subtrees) {
                num += subtree.count(item);
            }
            return num;
        }
    }

    public String toString() {
        return this.strIndented();
    }

    private String strIndented(Integer depth) {
        if (this.isEmpty()) {
            return "";
        } else {
            String s = (" ".repeat(depth)).concat(root.toString());
            for (Tree subtree : subtrees) {
                s = s.concat(subtree.strIndented(depth + 1));
            }
            return s;
        }
    }

    private String strIndented() {
        return strIndented(0);
    }

    public Double average() {
        if (this.isEmpty()) {
            return 0.0;
        } else {
            Integer[] temp = averageHelper();
            Integer total = temp[0];
            Integer count = temp[1];
            return (double) count / total;
        }
    }

    private Integer[] averageHelper() {
        if (this.isEmpty()) {
            return new Integer[]{0, 0};
        } else {
            Integer total = root;
            Integer count = 1;
            for (Tree subtree : subtrees) {
                Integer[] temp = subtree.averageHelper();
                total += temp[0];
                count += temp[1];
            }
            return new Integer[]{total, count};
        }
    }

    public boolean equals(Tree other) {
        if (this.isEmpty() && other.isEmpty()) {
            return true;
        } else if (this.isEmpty() || other.isEmpty()) {
            return false;
        } else {
            if (!this.root.equals(other.root)) {
                return false;
            }
            if (this.subtrees.size() != other.subtrees.size()) {
                return false;
            }
            return this.subtrees.equals(other.subtrees);
        }
    }

    public boolean contains(Integer item) {
        if (this.isEmpty()) {
            return false;
        }

        if (root.equals(item)) {
            return true;
        } else {
            for (Tree subtree : subtrees) {
                if (subtree.contains(item)) {
                    return true;
                }
            }
            return false;
        }
    }

    public ArrayList<Integer> leaves() {
        if (this.isEmpty()) {
            ArrayList<Integer> results = new ArrayList<Integer>();
            results.add(0);
            return results;
        } else if (subtrees.isEmpty()) {
            ArrayList<Integer> results = new ArrayList<Integer>();
            results.add(root);
            return results;
        } else {
            ArrayList<Integer> results = new ArrayList<Integer>();
            for (Tree subtree : subtrees) {
                results.addAll(subtree.leaves());
            }
            return results;
        }
    }

    public boolean deleteItem(Integer item) {
        if (this.isEmpty()) {
            return false;
        } else if (root.equals(item)) {
            this.deleteRoot();
            return true;
        } else {
            for (Tree subtree : subtrees) {
                boolean deleted = subtree.deleteItem(item);
                if (deleted && subtree.isEmpty()) {
                    subtrees.remove(subtree);
                    return true;
                } else if (deleted) {
                    return true;
                }
            }
            return false;
        }
    }

    private void deleteRoot() {
        if (subtrees.isEmpty()) {
            root = null;
        } else {
            Tree chosenSubtree = subtrees.removeLast();

            root = chosenSubtree.root;
            subtrees.addAll(chosenSubtree.subtrees);
        }
    }

    private Integer extractLeaf() {
        if (subtrees.isEmpty()) {
            Integer oldRoot = root;
            root = null;
            return oldRoot;
        } else {
            Integer leaf = subtrees.getFirst().extractLeaf();
            if (subtrees.getFirst().isEmpty()) {
                subtrees.removeFirst();
            }
            return leaf;
        }
    }

    public void insert(Integer item) {
        if (this.isEmpty()) {
            root = item;
        } else if (subtrees.isEmpty()) {
            subtrees = new ArrayList<Tree>() {{ add(new Tree(item, new ArrayList<Tree>())); }};
        } else {
            Random random = new Random();
            if (random.nextInt(3) + 1 == 3) {
                subtrees.add(new Tree(item, new ArrayList<Tree>()));
            } else {
                int subtreeIndex = random.nextInt(subtrees.size());
                subtrees.get(subtreeIndex).insert(item);
            }
        }
    }

    public boolean insertChild(Integer item, Integer parent) {
        if (this.isEmpty()) {
            return false;
        } else if (root.equals(parent)) {
            subtrees.add(new Tree(item, new ArrayList<Tree>()));
            return true;
        } else {
            for (Tree subtree : subtrees) {
                if (subtree.insertChild(item, parent)) {
                    return true;
                }
            }
            return false;
        }
    }
}
