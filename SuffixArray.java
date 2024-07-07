import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class SuffixArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int numOfTriplets , n = input.length();
        System.out.println(n);
        String str;
        if (n > 3) {
            if (n % 3 == 0) {
                str = input + "$$$";
                numOfTriplets = n / 3 + 1;
            } else if (n % 3 == 1) {
                str = input + "$$";
                numOfTriplets = (int) Math.ceil(n/3);
            } else {
                str = input + "$";
                numOfTriplets = (int) Math.ceil(n/3);
            }
            String[] arr3_1 = new String[numOfTriplets];
            String[] arr3_2 = new String[numOfTriplets];
            for (int i = 0; i < n; i++) {
                
            }
        }


        String[] array = {"0212", "a12", "$", "apple123", "banana456", "orange123"};
        radixSort(array);
        System.out.println(Arrays.toString(array));

    }



    public static void radixSort(String[] array) {
        // Находим максимальную длину строки для определения количества разрядов
        int maxLength = Arrays.stream(array).mapToInt(String::length).max().getAsInt();

        // Применяем сортировку для каждого разряда, начиная с младшего разряда
        for (int i = maxLength - 1; i >= 0; i--) {
            countingSortByDigit(array, i);
        }
    }

    private static void countingSortByDigit(String[] array, int digitIndex) {
        int n = array.length;
        String[] output = new String[n];
        int[] count = new int[128]; // Количество возможных символов (ASCII)

        // Инициализируем массив count
        Arrays.fill(count, 0);

        // Подсчитываем количество элементов с определенным разрядом
        for (int i = 0; i < n; i++) {
            String str = array[i];
            int digit;
            if (digitIndex < str.length()) {
                char ch = str.charAt(digitIndex);
                digit = ch;
            } else {
                digit = 0; // Дополняем строку нулевыми символами
            }
            count[digit]++;
        }

        // Преобразуем count, чтобы каждый элемент содержал сумму предыдущих элементов
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Строим выходной массив
        for (int i = n - 1; i >= 0; i--) {
            String str = array[i];
            int digit;
            if (digitIndex < str.length()) {
                char ch = str.charAt(digitIndex);
                digit = ch;
            } else {
                digit = 0;
            }
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        // Копируем отсортированный массив в исходный
        System.arraycopy(output, 0, array, 0, n);
    }
}
