import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SumProblem {
    public static int n, k;
    public static long[] a;
    public static long[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out, true); // Включаем автоматический сброс буфера

        n = Integer.parseInt(reader.readLine());
        k = (int) Math.floor(Math.sqrt(n));
        a = new long[n];
        b = new long[(int) Math.ceil((double) a.length / k)];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(tokenizer.nextToken());
        }

        for (int i = 0; i < n; i += k) {
            long bsum = 0;
            for (int j = i; j < Math.min(i + k, n); j++) {
                bsum += a[j];
            }
            b[i / k] = bsum;
        }

        int numberOfRequests = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfRequests; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String l = tokenizer.nextToken();
            if (l.equals("FindSum")) {
                int start = Integer.parseInt(tokenizer.nextToken());
                int end = Integer.parseInt(tokenizer.nextToken());
                findSum(start, end, writer);
            } else if (l.equals("Add")) {
                int index = Integer.parseInt(tokenizer.nextToken());
                int value = Integer.parseInt(tokenizer.nextToken());
                add(index, value);
            }
        }

        reader.close();
    }

    public static void add(int i, int x) {
        a[i] += x;
        b[i / k] += x;
    }

    public static void findSum(int l, int r, PrintWriter writer) {
        int jl = l / k;
        int jr = r / k;
        long sum = 0;

        if (jl == jr) { // same block
            for (int i = l; i < r; i++) {
                sum += a[i];
            }
        } else {
            for (int i = l; i < (jl + 1) * k; i++) {
                sum += a[i];
            }
            for (int i = jl + 1; i < jr; i++) {
                sum += b[i];
            }
            for (int i = jr * k; i < r; i++) {
                sum += a[i];
            }
        }

        writer.println(sum);
    }
}