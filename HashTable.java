import java.util.LinkedHashSet;
import java.io.*;

public class HashTable {
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
             BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            String[] inputLine = in.readLine().split(" ");
            int m = Integer.parseInt(inputLine[0]);
            int c = Integer.parseInt(inputLine[1]);
            int n = Integer.parseInt(inputLine[2]);

            LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
            for (int i = 0; i < n; i++) {
                hashSet.add(Integer.parseInt(in.readLine()));
            }
            Integer[] nums;
            nums = hashSet.toArray(new Integer[hashSet.size()]);
            int[] table = new int[m];
            for (int i = 0; i < m; i++) {
                table[i] = -1;
            }

            int index;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < m; j++) {
                    index = ((nums[i] % m) + c * j) % m;
                    if(table[index] == -1) {
                        table[index] = nums[i];
                        break;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                out.print(table[i] + " ");
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}