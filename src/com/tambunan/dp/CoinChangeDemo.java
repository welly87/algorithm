package com.tambunan.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Welly on 8/6/2016.
 */
public class CoinChangeDemo {
    int[] coinValue;

    public int change(int value) {
        if (value == 0) return 0;
        if (value < 0) return 1000000;

        int min = 100000;

        for (int i = 0; i < coinValue.length; i++) {
            min = Math.min(min, change(value - coinValue[i]));
        }

        return 1 + min;
    }

    public int solve(int V, int[] coinValue) {
        this.coinValue = coinValue;

        return change(V);
    }

    public int minimumCoinBottomUp(int total, int coins[]) {
        int T[] = new int[total + 1];
        int R[] = new int[total + 1];
        T[0] = 0;
        for (int i = 1; i <= total; i++) {
            T[i] = Integer.MAX_VALUE - 1;
            R[i] = -1;
        }
        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i <= total; i++) {
                if (i >= coins[j]) {
                    if (T[i - coins[j]] + 1 < T[i]) {
                        T[i] = 1 + T[i - coins[j]];
                        R[i] = j;
                    }
                }
            }
        }
        printCoinCombination(R, coins);
        return T[total];
    }

    public void printCoinChangingSolution(int total, int coins[]) {
        List<Integer> result = new ArrayList<>();
        printActualSolution(result, total, coins, 0);
    }

    public int numberOfSolutionsOnSpace(int total, int arr[]) {

        int temp[] = new int[total + 1];

        temp[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j >= arr[i]) {
                    temp[j] += temp[j - arr[i]];
                }
            }
        }
        return temp[total];
    }

    private void printActualSolution(List<Integer> result, int total, int coins[], int pos) {
        if (total == 0) {
            for (int r : result) {
                System.out.print(r + " ");
            }
            System.out.print("\n");
        }
        for (int i = pos; i < coins.length; i++) {
            if (total >= coins[i]) {
                result.add(coins[i]);
                printActualSolution(result, total - coins[i], coins, i);
                result.remove(result.size() - 1);
            }
        }
    }

    private void printCoinCombination(int R[], int coins[]) {
        if (R[R.length - 1] == -1) {
            System.out.print("No solution is possible");
            return;
        }
        int start = R.length - 1;
        System.out.print("Coins used to form total ");
        while (start != 0) {
            int j = R[start];
            System.out.print(coins[j] + " ");
            start = start - coins[j];
        }
        System.out.print("\n");
    }


    public static void main(String[] args) {
        CoinChangeDemo c = new CoinChangeDemo();

        int sol = c.solve(7, new int[]{1, 3, 4, 5});
        System.out.println(sol);

        c.minimumCoinBottomUp(7, new int[]{1, 3, 4, 5});

        System.out.println(c.numberOfSolutionsOnSpace(7, new int[]{1, 3, 4, 5}));

        c.printCoinChangingSolution(7, new int[]{1, 3, 4, 5});
    }
}
