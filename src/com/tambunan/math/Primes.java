package com.tambunan.math;

/**
 * Created by Welly on 7/29/2016.
 */
public class Primes {
    public boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        int m = (int) Math.sqrt(n);

        for (int i = 3; i <= m; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }

}
