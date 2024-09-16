import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MultiSet[] multiSets = {new TreeMultiSet()};
        for (MultiSet multiSet : multiSets) {
            for (int n : new int[]{500, 1000, 2000, 4000}) {
                profileMultiSet(multiSet, n);
            }
        }
    }

    public static void profileMultiSet(MultiSet myInput, Integer n) {
        ArrayList<Integer> itemsAdded = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(101);
            myInput.add(x);
            itemsAdded.add(x);
        }

        assert (myInput.size().equals(n));

        long start = System.nanoTime();

        for (Integer x : itemsAdded) {
            myInput.remove(x);
        }

        long end = System.nanoTime();

        if (!myInput.isEmpty()) {
            throw new AssertionError("Items were not successfully removed!");
        }

        System.out.printf("%5d %37s % .6f%n", n, myInput.getClass().getName(), (end - start) / 1e9);
    }
}
