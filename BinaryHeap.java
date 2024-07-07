import java.io.*;

public class BinaryHeap {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
             BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());

            String[] numbersAsString = reader.readLine().split(" ");
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(numbersAsString[i]);
                if (i > 0 && numbers[i] < numbers[(int) Math.floor((i - 1) / 2.0)]) {
                    out.println("No");
                    out.flush();
                    return;
                }
            }
            out.println("Yes");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}