package com.tambunan.dp;

/**
 * Created by Welly on 8/6/2016.
 */
class KnapsackCombos {
    int[] C;

    public int solve(int[] C, int m) {
        this.C = C;

        return combos(this.C.length, m);
    }

    public int combos(int i, int m) {
        if (m == 0) return 1; // empty knapsack, we arrived at target
        if (i == 0) return 0; // we exhaust all combination
        if (C[i] > m) return combos(i - 1, m); // don't take this item
        else return combos(i - 1, m) + combos(i - 1, m - C[i]);
    }
}

class Knapsack01BottomUp {
    int[][] picks = new int[10000][10000];


    public int solve(int[] weights, int[] values, int size) {

        int[][] matrix = new int[10000][10000];


        for (int i = 1; i <= weights.length; i++) {
            for (int j = 0; j <= size; j++) {
                if (weights[i - 1] <= j) {
                    matrix[i][j] = Math.max(matrix[i - 1][j], values[i - 1] + matrix[i - 1][j - weights[i - 1]]);

                    if (values[i - 1] + matrix[i - 1][j - weights[i - 1]] > matrix[i - 1][j])
                        picks[i][j] = 1;
                    else
                        picks[i][j] = -1;

                } else {
                    picks[i][j] = -1;
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        }

        printPicks(weights.length, size, weights);

        return matrix[weights.length][size];
    }

    void printPicks(int item, int size, int weights[]) {

        while (item > 0) {
            if (picks[item][size] == 1) {
                System.out.print((item - 1) + " ");
                item--;
                size -= weights[item];
            } else {
                item--;
            }
        }

        System.out.println();

        return;
    }
}

public class Knapsack01 {
    int n;
    int[] W;
    int[] V;
    int[][] memo;

    public int solve(int[] W, int[] V, int S) {
        this.W = W;
        this.V = V;
        this.n = W.length;
        memo = new int[10000][10000];

        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                memo[i][j] = -1;
            }
        }

        return val(0, S);
    }

    private int val(int id, int remW) {
        if (remW == 0) return 0;
        if (id == n) return 0;

        if (memo[id][remW] != -1) return memo[id][remW];

        if (W[id] > remW)
            return memo[id][remW] = val(id + 1, remW);
        else
            return memo[id][remW] = Math.max(val(id + 1, remW), V[id] + val(id + 1, remW - W[id]));
    }

    public static void main(String[] args) {
        Knapsack01 a = new Knapsack01();

        int[] W = new int[]{10, 4, 6, 12};
        int[] V = new int[]{100, 70, 50, 10};
        int S = 12;

        System.out.println(a.solve(W, V, S));

        Knapsack01BottomUp b = new Knapsack01BottomUp();
        System.out.println(b.solve(W, V, S));


    }
}
