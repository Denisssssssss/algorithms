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

        System.out.println("Начинаю бросать\n");
        // двоичным поиском находим промежуток, где предположительно разбиваются шары
        while (k > 2) {
            // проверяем, разбился или нет
            System.out.println("Бросаю с " + (firstFloor + (lastFloor - firstFloor) / 2) + " этажа");
            if (lastFloor / 2 >= criticalHeight) {
                System.out.println("Разбился");
                lastFloor = lastFloor / 2 - 1;
            } else {
                System.out.println("Не разбился");
                if ((lastFloor - firstFloor) / 2 + firstFloor < criticalHeight) {
                    firstFloor += (lastFloor - firstFloor) / 2;
                } else {
                    lastFloor -= (lastFloor - firstFloor) / 2;
                }
            }
            System.out.println("Теперь бросаю в промежутке [" + firstFloor + ", " + lastFloor + "]\n");
            throwsAmount++;
            k--;
        }

        // этот промежуток разделим на несколько отрезков, с концов которых бросаем шары
        System.out.println("Шар разбивается в этом промежутке: [" + firstFloor + ", " + lastFloor + "]\n");
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
        System.out.println("Минимум бросков: " + (throwsAmount + minimum) + "\n");

        System.out.println("Теперь бросаю в промежутке " +
                "[" + firstFloor + ", " + (firstFloor + section - 1) + "]\n");

        for (int i = firstFloor + section - 1; i <= lastFloor; i += section) {
            System.out.println("Бросаю с " + i + " этажа");
            throwsAmount++;
            if (i >= criticalHeight) {
                System.out.println("Разбился");
                lastFloor = i;
                break;
            }
            System.out.println("Не разбился");
            section--;
            firstFloor = i + 1;
            System.out.println("Теперь бросаю в промежутке " + "[" + firstFloor + ", " + (firstFloor + section - 1) + "]\n");
        }

        System.out.println("Шар разбивается в этом промежутке: [" + firstFloor + ", " + lastFloor + "]\n");

        for (int i = firstFloor; i <= lastFloor; i++) {
            System.out.println("Бросаю с " + i + " этажа");
            if (i == criticalHeight && minimum != 0) {
                System.out.println("\nОтвет: " + i + " этаж");
                return;
            }
            if (minimum == 0) {
                System.out.println("Исчерпан лимит бросков");
                return;
            }
            minimum--;
        }
    }
}
