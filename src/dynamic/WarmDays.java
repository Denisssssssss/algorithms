package dynamic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class WarmDays {

    public static void main(String[] args) {

        //31
        //9 7 4 4 4 8 5 5 5 6 0 0 0 1 0 5 4 3 0 0 1 1 2 8 7 1 4 1 1 1 1
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] temperature = new int[n];
        int[] ans = new int[n];
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            temperature[i] = scanner.nextInt();
        }
        boolean flag = true;
        stack.push(temperature[0]);
        for (int i = 1; i < n; i++) {
            if (!stack.isEmpty()) {
                int temp = stack.peek();
                flag = temp < temperature[i];
                int difference = 1;
                while (flag) {
                    flag = temp < temperature[i];
                    if (flag) {
                        boolean check = ans[i - difference] > 0;
                        ans[i - difference] += difference;
                        if (check) {
                            ans[i - difference] -= difference;
                        } else {
                            stack.pop();
                        }
                        difference++;
                        if (!stack.isEmpty()) {
                            temp = stack.peek();
                        } else {
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) {
                stack.push(temperature[i]);
            }
        }
        System.out.println(Arrays.toString(temperature));
        System.out.println(Arrays.toString(ans));

        for (int i = 0; i < n; i++) {
            System.out.println("Day: ".concat(" ".repeat(String.valueOf(n).length() - String.valueOf(i + 1).length())) + (i + 1) + " temp: " + temperature[i] + " closest: " + ans[i]);
        }
    }
}
