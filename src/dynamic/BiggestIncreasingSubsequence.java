package dynamic;

import java.util.Arrays;
import java.util.Random;

public class BiggestIncreasingSubsequence {

    public static void main(String[] args) {

        Random random = new Random();
        int n = random.nextInt(50) + 20;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(30);
        }
        System.out.println("Source sequence: " + Arrays.toString(a));

        int max = 1;
        int index = 0;
        int k = 1;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] < a[i + 1]) {
                k++;
            } else {
                if (max < k) {
                    max = k;
                    index = i;
                }
                k = 1;
            }
        }

        System.out.println("Biggest increasing subsequence");
        for (int i = index - max + 1; i <= index; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
