import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class BinomialHeap {
    static class BinomialTree {
        int order;
        int height;

        public BinomialTree(int order) {
            this.order = order;
            this.height = order;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        int n = in.nextInt();

        List<BinomialTree> trees = buildBinomialHeap(n);

        if (trees == null) {
            out.println(-1);
            out.flush();
        } else {
            for (BinomialTree tree : trees) {
                out.println(tree.height);
                out.flush();
            }
        }
    }

    public static List<BinomialTree> buildBinomialHeap(int n) {
        List<BinomialTree> trees = new ArrayList<>();

        while (n > 0) {
            int k = (int) (Math.log(n) / Math.log(2));
            int order = (int) Math.pow(2, k);

            if (order > n) {
                k--;
                order = (int) Math.pow(2, k);
            }

            BinomialTree tree = new BinomialTree(k);
            trees.add(tree);

            n -= order;
        }

        if (n < 0) {
            return null;
        }

        Collections.sort(trees, (t1, t2) -> Integer.compare(t1.order, t2.order));

        return trees;
    }
}