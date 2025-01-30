package Project1;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static final String dirPath = "src/Project1";
    static final Path fpIn = Path.of(dirPath, "input.txt");
    static final Path fpOut = Path.of(dirPath, "output.txt");

    public static void main(String[] args) {
//        try {
//            populateInput();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (BufferedReader nbr = Files.newBufferedReader(fpIn)) {
            List<Integer> arr = new ArrayList<>();
            String[] tmpStringArr = nbr.readLine().split(" ");
            for (String s : tmpStringArr)
                arr.add(Integer.parseInt(s));
            arr.sort((a,b) -> a - b);
            Files.writeString(fpOut, "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            getCombinationsOfSix(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getCombinationsOfSix(List<Integer> arr) throws IOException {
        combine(arr, 0, new ArrayList<>());
    }

    private static void combine(List<Integer> arr, int start, List<Integer> combination) throws IOException {
        if (combination.size() == 6) {
            StringBuilder toWrite = new StringBuilder();
            boolean maxFourEvens = combination.stream().filter(i -> i % 2 == 0).count() <= 4;
            boolean maxFourOdds = combination.stream().filter(i -> i % 2 == 1).count() <= 4;
            boolean maxTwoConsecutives = maxTwoConsecutives(combination);
            boolean maxThreeWithSameLastDigit = maxThreeWithSameLastDigit(combination);
            boolean maxThreeWithSameSecondToLastDigit = maxThreeWithSameSecondToLastDigit(combination);

            if (maxFourEvens && maxFourOdds && maxTwoConsecutives && maxThreeWithSameLastDigit && maxThreeWithSameSecondToLastDigit) {
                combination.forEach(num -> toWrite.append(num).append(" "));
                toWrite.append("\n");
            }
            Files.writeString(fpOut, toWrite, StandardOpenOption.APPEND);
            return;
        }

        for (int i = start; i < arr.size(); i++) {
            combination.add(arr.get(i));
            combine(arr, i + 1, combination);
            combination.remove(combination.size() - 1);
        }
    }

    private static boolean maxTwoConsecutives(List<Integer> arr) {
        for (int i = 0; i < arr.size() - 2; i++) {
            if (arr.get(i) + 1 == arr.get(i + 1) && arr.get(i + 1) + 1 == arr.get(i + 2)) return false;
        }
        return true;
    }

    private static boolean maxThreeWithSameLastDigit(List<Integer> arr) {
        int[] tmpArr = new int[10];
        for (int i = 0; i < arr.size(); i++) {
            tmpArr[arr.get(i) % 10]++;
        }
        for (int n : tmpArr) {
            if (n > 3) return false;
        }
        return true;
    }

    private static boolean maxThreeWithSameSecondToLastDigit(List<Integer> arr) {
        int[] tmpArr = new int[10];
        for (int i = 0; i < arr.size(); i++) {
            tmpArr[(arr.get(i)/10) % 10]++;
        }
        for (int n : tmpArr) {
            if (n > 3) return false;
        }
        return true;
    }

    private static void populateInput() throws IOException {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random(System.currentTimeMillis());
        int numbersCount = rand.nextInt(7, 50);
        Files.writeString(fpIn, "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        for (int i = 0; i < numbersCount; i++) {
            sb.append(rand.nextInt(1, 50)).append(" ");
        }
            Files.writeString(fpIn, sb.toString().trim(), StandardOpenOption.APPEND);
    }
}
