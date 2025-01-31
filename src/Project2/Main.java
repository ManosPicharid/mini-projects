package Project2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] n = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] maxArr;
        int currSum = n[0];
        int maxSum = n[0];
        int currIndexStart = 0;
        int maxIndexStart = 0;
        int maxIndexEnd = 0;

        for (int i = 1; i < n.length; i++) {
            if (currSum < 0) {
                currSum = 0;
                currIndexStart = i;
            }

            currSum += n[i];

            if (currSum > maxSum) {
                maxSum = currSum;
                maxIndexStart = currIndexStart;
                maxIndexEnd = i;
            }
        }
        maxArr = Arrays.copyOfRange(n, maxIndexStart, maxIndexEnd + 1);
        for (int num : maxArr) System.out.print(num + " ");
        System.out.printf("\n%d", maxSum);
    }
}
