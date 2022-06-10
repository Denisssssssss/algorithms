package dynamic;

import java.util.Scanner;

public class XOR {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        int ans;

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        ans = array[0];
        for (int i = 1; i < n; i++) {
            ans = ans ^ array[i];
        }
        System.out.println(ans);
    }
}
