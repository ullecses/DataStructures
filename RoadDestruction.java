import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class RoadDestruction implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new RoadDestruction(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));


            StringTokenizer st = new StringTokenizer(reader.readLine());
            int cities = Integer.parseInt(st.nextToken());
            int connections = Integer.parseInt(st.nextToken());
            int destroingRoads = Integer.parseInt(st.nextToken());
            int[][] numRoads = new int[connections][2];
            Dsu dsu = new Dsu(cities);
            for (int i = 0; i < connections; i++) {
                st = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                numRoads[i][0] = x;
                numRoads[i][1] = y;
            }
            int[] destroy = new int[destroingRoads];
            for (int i = 0; i < destroingRoads; i++) {
                st = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(st.nextToken());
                destroy[i] = x;
            }
            int[] check = new int[connections];
            for (int i = 1; i <= connections; i++) {
                check[i - 1] = i;
            }
            for (int i = 0; i < destroingRoads; i++) {
                check[destroy[i] - 1] = 0;
            }
            for (int i = 0; i < connections; i++) {
                int x = check[i] - 1;
                if (x >= 0) {
                    dsu.union(numRoads[x][0], numRoads[x][1]);
                }
            }
            int[] answer = new int[destroingRoads];
            if (dsu.getComponentsCount(cities) != 1) answer[destroingRoads - 1] = 0;
            else answer[destroingRoads - 1] = 1;
            for (int i = destroingRoads - 1; i > 0; i--) {
                int x = dsu.union(numRoads[destroy[i] - 1][0], numRoads[destroy[i] - 1][1]);
                if (x != 1) answer[i - 1] = 0;
                else answer[i - 1] = 1;
            }
            for (int i = 0; i < destroingRoads; i++) {
                writer.write(answer[i] + "");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
class Dsu {
    int count;
    int[] parent;
    int[] size;
    int num;

    public Dsu(int num) {

        this.num = num;
        parent = new int[num + 1];
        size = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        count = num;
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public int union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) count--;
        if (size[x] < size[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            if (size[x] == size[y]) {
                size[x]++;
            }
        }
        return count;
    }

    public int getComponentsCount(int size) {
        int count = 0;
        for (int i = 1; i <= size; i++) {
            if (parent[i] == i)
                count++;
        }
        return count;
    }
}