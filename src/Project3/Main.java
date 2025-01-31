package Project3;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String dirPath = "src/Project3";
        Path fpIn = Path.of(dirPath, "input.txt");
        int[][] freqArray = new int[128][2];
        int ch;
        try (BufferedReader nbr = Files.newBufferedReader(fpIn)) {
            while ((ch = nbr.read()) != -1) {
                if (ch == 10 || ch == 13 || ch == 32) continue;
                if (freqArray[ch][1]++ == 0) {
                    freqArray[ch][0] = ch;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Sorted by char by default
        System.out.println("Sorted by character:");
        for (int[] arr : freqArray) {
            if (arr[0] != 0)
                System.out.printf("'%c':%2d\n", arr[0], arr[1]);
        }
        // Sort by frequency
        Stream<int[]> sorted = Arrays.stream(freqArray).sorted((a, b) -> a[1] - b[1]);
        System.out.println("Sorted by frequency:");
        sorted.forEach(a -> {
            if (a[0] != 0)
                System.out.printf("'%c':%2d\n", a[0], a[1]);
        });
    }
}
