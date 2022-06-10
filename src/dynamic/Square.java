package dynamic;

import java.util.Scanner;

import static java.lang.Math.abs;

public class Square {

    /*
        5
        3 4
        5 11
        12 8
        9 5
        5 6
        ans: 30
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }

        int ans = abs(calculate(x, y) - calculate(y, x)) / 2;
        System.out.println(ans);
    }

    private static int calculate(int[] a, int[] b) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            ans += a[i] * b[(i + 1) % a.length];
        }
        return ans;
    }
}
