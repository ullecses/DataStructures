import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Huffman {
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter("output.txt");
        int n = in.nextInt();
        int[] arr1 = new int[n + 1];
        long[] arr2 = new long[n];

        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt();
        }
        arr2[0] = arr1[0] + arr1[1];
        long sum = arr2[0];
        int i = 2, j = 0, sizeArr2 = 1;
        long newEl = 0;
        while (true) {
            if (arr1[i] != 0 && arr2[j] != 0) {
                if (arr1[i] <= arr2[j]) {
                    newEl = arr1[i];
                    i++;
                    if (arr1[i] != 0 && arr2[j] != 0) {
                        if (arr1[i] > arr2[j]) {
                            newEl+= arr2[j];
                            j++;
                        } else {
                            newEl+= arr1[i];
                            i++;
                        }
                    } else if (arr1[i] != 0) {
                        newEl+= arr1[i];
                        i++;
                    } else {
                        newEl+= arr2[j];
                        j++;
                    }
                } else {
                    newEl = arr2[j];
                    j++;
                    if (arr1[i] != 0 && arr2[j] != 0) {
                        if (arr1[i] > arr2[j]) {
                            newEl+= arr2[j];
                            j++;
                        } else {
                            newEl+= arr1[i];
                            i++;
                        }
                    } else if (arr1[i] != 0) {
                        newEl+= arr1[i];
                        i++;
                    } else {
                        newEl+= arr2[j];
                        j++;
                    }
                }
            } else if (arr2[j] != 0 && arr2[j + 1] != 0) {
                newEl = arr2[j] + arr2[j + 1];
                j+=2;
            } else if (arr2[j] != 0 && arr2[j + 1] == 0) {
                break;
            }
            arr2[sizeArr2] = newEl;
            sum += newEl;
            sizeArr2++;
        }
        out.write(Long.toString(sum));
        out.flush();
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
        long nextLong() {
            return Long.parseLong(next());
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