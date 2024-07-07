import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class BlackBox {
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        try (BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
             BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] mN = reader.readLine().split(" ");
            int mAdd = Integer.parseInt(mN[0]);
            int nGet = Integer.parseInt(mN[1]);
            CustomPriorityQueue maxPriorityQueue = new CustomPriorityQueue(nGet);
            CustomPriorityQueue minPriorityQueue = new CustomPriorityQueue(mAdd);
            int[] numbers1 = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] numbers2 = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int sizeOfQueues = 0;
            int x;
            for (int i = 1; i <= nGet; i++) {
                if (!maxPriorityQueue.isEmpty()) minPriorityQueue.add(maxPriorityQueue.poll());
                x = numbers2[i - 1];
                int added = x - sizeOfQueues;
                for (int z = 0; z < added; z++) {
                    if (!minPriorityQueue.isEmpty() && minPriorityQueue.peek() > numbers1[sizeOfQueues]) {
                        maxPriorityQueue.add(minPriorityQueue.poll());
                        minPriorityQueue.add(numbers1[sizeOfQueues]);
                    } else {
                        maxPriorityQueue.add(numbers1[sizeOfQueues]);
                    }
                    sizeOfQueues++;
                }
                int res = maxPriorityQueue.peek();
                str.append(res).append(" ");
            }
            out.write(str.toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class CustomPriorityQueue {
        private int[] heap;
        private int size;
        private Comparator<Integer> comparator;

        public CustomPriorityQueue(int capacity) {
            heap = new int[capacity];
            size = 0;
            comparator = Comparator.naturalOrder();
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(int element) {
            if (size == heap.length) {
                resizeHeap();
            }
            heap[size] = element;
            heapifyUp(size);
            size++;
        }

        public int poll() {
            if (isEmpty()) {
                throw new IllegalStateException("Priority queue is empty");
            }
            int root = heap[0];
            heap[0] = heap[size - 1];
            size--;
            heapifyDown(0);
            return root;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Priority queue is empty");
            }
            return heap[0];
        }

        private void heapifyUp(int index) {
            int parentIndex = (index - 1) / 2;
            while (index > 0 && comparator.compare(heap[index], heap[parentIndex]) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
                parentIndex = (index - 1) / 2;
            }
        }

        private void heapifyDown(int index) {
            int largestIndex = index;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            if (leftChildIndex < size && comparator.compare(heap[leftChildIndex], heap[largestIndex]) > 0) {
                largestIndex = leftChildIndex;
            }

            if (rightChildIndex < size && comparator.compare(heap[rightChildIndex], heap[largestIndex]) > 0) {
                largestIndex = rightChildIndex;
            }

            if (largestIndex != index) {
                swap(index, largestIndex);
                heapifyDown(largestIndex);
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void resizeHeap() {
            int[] newHeap = new int[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }
    }
}