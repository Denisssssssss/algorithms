package dynamic;

import java.math.BigInteger;
import java.util.Scanner;

public class LuckyTicketTask {

    public static void main(String[] args) {

        int n = 10;
        Scanner scanner = new Scanner(System.in);
        int ticketLength = scanner.nextInt();
        if (ticketLength % 2 == 1) {
            return;
        }
        BigInteger[] values = new BigInteger[n];
        for (int i = 0; i < 10; i++) {
            values[i] = BigInteger.ONE;
        }
        // skip length = 1 because already calculated
        for (int length = 2; length <= ticketLength / 2; length++) {
            values = recalculateArray(values);
        }
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i < values.length; i++) {
            ans = ans.add(values[i].multiply(values[i]));
        }
        System.out.println(ans);
    }

    private static BigInteger[] recalculateArray(BigInteger[] values) {
        // skip i = 0, because values[0] always equals 1
        BigInteger[] array = new BigInteger[values.length + 9];
        array[0] = BigInteger.ONE;
        for (int i = 1; i < array.length; i++) {
            array[i] = array[i - 1];
            if (i < values.length) {
               array[i] = array[i].add(values[i]);
            }
            if (i >= 10) {
                array[i] = array[i].subtract(values[i - 10]);
            }
        }
        return array;
    }
}
