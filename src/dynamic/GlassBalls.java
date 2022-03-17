package dynamic;

import java.util.Random;
import java.util.Scanner;

public class GlassBalls {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // этажи
        int k = in.nextInt(); // шары
        int criticalHeight = Math.abs(random.nextInt()) % n + 1; // высота, с которой начинают разбиваться
        int throwsAmount = 0;

        int firstFloor = 1;
        int lastFloor = n;

        // двоичным поиском находим промежуток, где предположительно разбиваются шары
        while (k > 2) {
            if (n / 2 >= criticalHeight) {
                lastFloor = n / 2 - 1;
            } else {
                firstFloor = n / 2 + 1;
            }
            throwsAmount++;
            k--;
        }

        // этот промежуток разделим на несколько отрезков, с концов которых бросаем шары
        int floors = lastFloor - firstFloor + 1;
        double d = 1 + 8 * floors;
        double x = (Math.sqrt(d) - 1) / 2;
        int section = (int) Math.floor(x); // размер отрезков
        if (section < x) {
            section++;
        }

        for (int i = firstFloor + section - 1; i <= lastFloor; i += section) {
            throwsAmount++;
            if (i >= criticalHeight) {
                lastFloor = i;
                break;
            }
            section--;
            firstFloor = i + 1;
        }

        for (int i = firstFloor; i <= lastFloor; i++) {
            throwsAmount++;
            if (i == criticalHeight) {
                System.out.println("Breaks from: " + i);
                System.out.println("Throws taken: " + throwsAmount);
                break;
            }
        }
        System.out.println("Error");
    }
}
