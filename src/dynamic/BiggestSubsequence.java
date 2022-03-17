package dynamic;

import java.util.Scanner;

public class BiggestSubsequence {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String[] first = in.nextLine().split("");
        String[] second = in.nextLine().split("");
        int max = 0;
        int lastInFirst = 0;

        int[][] matches = new int[first.length][second.length];

        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < second.length; j++) {
                if (first[i].equals(second[j])) {
                    if (i > 0 && j > 0) {
                        matches[i][j] = matches[i - 1][j - 1];
                    }
                    matches[i][j]++;
                    if (matches[i][j] > max) {
                        max = matches[i][j];
                        lastInFirst = i;
                    }
                }
            }
        }

        if (max == 0) {
            System.out.println("No subsequence");
            return;
        }

        String ans = "";
        for (int i = 0; i < max; i++) {
            ans = first[lastInFirst - i] + ans;
        }

        System.out.println(ans);
    }
}
