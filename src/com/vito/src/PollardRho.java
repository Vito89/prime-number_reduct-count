package com.vito.src;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.numberOfTrailingZeros;
import static java.lang.Math.abs;
import static java.lang.Math.min;

public class PollardRho {

    public static List<Integer> primeFactors(int number) {
        final List<Integer> factors = new ArrayList<>();
        var num = SmallPrimes.divideToFactorsAndReturnUpdNumb(number, factors);
        if (1 == num) {
            return factors;
        }

        if (SmallPrimes.passesMillerRabin(num)) {
            factors.add(num);
            return factors;
        }

        int divisor = rhoBrent(num);
        factors.add(divisor);
        factors.add(num / divisor);
        return factors;
    }

    static int rhoBrent(final int n) {
        final int xInit = 2;
        final int m = 25;
        int cst = SmallPrimes.LAST_PRIME;
        int y = xInit;
        int roundCounts = 1;
        do {
            int x = y;
            for (int idx = 0; idx < roundCounts; idx++) {
                final long y2 = ((long) y) * y;
                y = (int) ((y2 + cst) % n);
            }

            int k = 0;
            do {
                final int bound = min(m, roundCounts - k);
                int q = 1;
                for (int idx = -3; idx < bound; idx++) { // enter loop at least 3 times
                    final long yPowTwo = ((long) y) * y;
                    y = (int) ((yPowTwo + cst) % n);
                    final long divisor = abs(x - y);
                    if (0 == divisor) {
                        cst += SmallPrimes.LAST_PRIME;
                        k = -m;
                        y = xInit;
                        roundCounts = 1;
                        break;
                    }
                    final long prod = divisor * q;
                    q = (int) (prod % n);
                    if (0 == q) {
                        return commonDivisor(abs((int) divisor), n);
                    }
                }
                final int result = commonDivisor(abs(q), n);
                if (1 != result) {
                    return result;
                }
                k += m;
            } while (k < roundCounts);
            roundCounts = 2 * roundCounts;
        } while (true);
    }

    // only positive args
    static int commonDivisor(int a, int b) {
        if (a == 0) {
            return b;
        } else if (b == 0) {
            return a;
        }

        final int trlA = numberOfTrailingZeros(a);
        final int trlB = numberOfTrailingZeros(b);
        a >>= trlA;
        b >>= trlB;

        final int shift = min(trlA, trlB);
        while (a != b) {
            final int diff = a - b;
            b = min(a, b);
            a = abs(diff);
            a >>= numberOfTrailingZeros(a);
        }

        return a << shift;
    }
}
