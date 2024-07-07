import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RoadConstruction {
    public static int[] size;
    public static int count;
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter("output.txt");
        int numOfCities = in.nextInt();
        int numOfRequests = in.nextInt();
        size = new int[numOfCities];

        Arrays.fill(size, -1);

        count = numOfCities;
        StringBuilder stringBuilder = new StringBuilder();
        int firstCity, secondCity;
        int i;
        for (i = 0; i < numOfRequests; i++) {
            firstCity = in.nextInt() - 1;
            secondCity = in.nextInt() - 1;
            union(firstCity, secondCity);
            stringBuilder.append(count + "\n");
        }
        out.println(stringBuilder);
        out.flush();
    }
    public static void union(int x, int y) {
        x = findSet(x);
        y = findSet(y);
        if (x != y) {
            count--;
            if (Math.abs(size[x]) < Math.abs(size[y])) {
                int temp = x;
                x = y;
                y = temp;
            }
            size[x] += size[y];
            size[y] = x;
        }
    }
    public static int findSet(int x) {
        if (size[x] < 0) {
            return x;
        }
        size[x] = findSet(size[x]);
        return size[x];
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() throws FileNotFoundException {
            br = new BufferedReader(new FileReader("input.txt"));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}