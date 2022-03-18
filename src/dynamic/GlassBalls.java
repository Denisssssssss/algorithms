package dynamic;

import java.math.BigDecimal;
import java.math.MathContext;
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
            // проверяем, разбился или нет
            if (lastFloor / 2 >= criticalHeight) {
                lastFloor = lastFloor / 2 - 1;
            } else {
                if ((lastFloor - firstFloor) / 2 + firstFloor < criticalHeight) {
                    firstFloor += (lastFloor - firstFloor) / 2;
                } else {
                    lastFloor -= (lastFloor - firstFloor) / 2;
                }
            }
            throwsAmount++;
            k--;
        }

        // этот промежуток разделим на несколько отрезков, с концов которых бросаем шары
        int floors = lastFloor - firstFloor + 1;
        BigDecimal eight = new BigDecimal(8);
        BigDecimal d = BigDecimal.ONE.add(eight.multiply(BigDecimal.valueOf(floors)));
        double sqrt = d.sqrt(new MathContext(10)).doubleValue();
        double x = (sqrt - 1) / 2;
        int section = (int) Math.floor(x); // размер отрезков
        if (section < x) {
            section++;
        }
        int minimum = section;
        System.out.println("Minimum throws: " + (throwsAmount + minimum));

        for (int i = firstFloor + section - 1; i <= lastFloor; i += section) {
            throwsAmount++;
            if (i >= criticalHeight) {
                lastFloor = i;
                break;
            }
            section--;
            firstFloor = i + 1;
        }

        System.out.print("Answer is correct: ");
        for (int i = firstFloor; i <= lastFloor; i++) {
            if (i == criticalHeight && minimum != 0) {
                System.out.println(true);
                return;
            }
            if (minimum == 0) {
                System.out.println(false);
                return;
            }
            minimum--;
        }
    }
}
