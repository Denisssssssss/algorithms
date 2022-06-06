package dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;

public class Bag {

    public static void main(String[] args) {

        Random random = new Random();
        int space = random.nextInt(30) + 1;
        System.out.println("Bag space: " + space);
        int n = 10;
        int[] weights = new int[n];
        int[] prices = new int[n];
        int[][] matrix = new int[n + 1][space + 1];

        System.out.println("ITEM | PRICE | WEIGHT");
        for (int i = 0; i < n; i++) {
            weights[i] = random.nextInt(15) + 1;
            prices[i] = random.nextInt(100) + 1;
            System.out.println(i + 1 + ": " + prices[i] + " " + weights[i]);
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < space + 1; j++) {
                if (weights[i - 1] > j) {
                    matrix[i][j] = matrix[i - 1][j];
                    continue;
                }
                int freeSpace = j - weights[i - 1];
                matrix[i][j] = max(matrix[i - 1][j], prices[i - 1] + matrix[i - 1][freeSpace]);
            }
        }

        List<Integer> items = new ArrayList<>();
        getItems(n, space, matrix, weights, items);
        System.out.println("items: " + items);
        System.out.println("items space: " + items.stream().map(item -> weights[item - 1]).reduce(0, Integer::sum));
        System.out.println("items sum: " + items.stream().map(item -> prices[item - 1]).reduce(0, Integer::sum));
    }

    public static void getItems(int n, int space, int[][] matrix, int[] weights, List<Integer> items) {
        if (matrix[n][space] == 0) {
            return;
        }
        if (matrix[n - 1][space] == matrix[n][space]) {
            getItems(n - 1, space, matrix, weights, items);
        } else {
            getItems(n - 1, space - weights[n - 1], matrix, weights, items);
            items.add(n);
        }
    }
}
