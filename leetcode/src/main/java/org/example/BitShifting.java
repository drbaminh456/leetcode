package org.example;

public class BitShifting {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int res = 0;
        while (a >= b) {
            int multiple = 1;
            long temp = b;
            while (a > (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            res += multiple;
        }
        return dividend < 0 == divisor < 0 ? -res : res;
    }
}
