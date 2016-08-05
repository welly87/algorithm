package com.tambunan.dp;

import java.util.*;

class LongestIncreasingSubsequence {
    final int MAX_N = 100000;

    private int[] L_id = new int[MAX_N], P = new int[MAX_N];
    private Vector<Integer> L = new Vector<Integer>();
    private int lis = 0, lis_end = 0;
    int[] A = null;
    int[] lisPath = null;

    public int[] solve(int[] A) {
        this.A = A;
        int n = A.length;

        for (int i = 0; i < n; ++i) {
            int pos = Collections.binarySearch(L, A[i]);
            if (pos < 0) pos = -(pos + 1); // some adjustments are needed
            if (pos >= L.size()) L.add(A[i]);
            else L.set(pos, A[i]);
            L_id[pos] = i;
            P[i] = pos > 0 ? L_id[pos - 1] : -1;
            if (pos + 1 > lis) {
                lis = pos + 1;
                lis_end = i;
            }
        }

        this.lisPath = new int[lis];

        reconstruct_print();

        return lisPath;
    }

    public void reconstruct_print() {

        int x = lis_end;
        Stack<Integer> s = new Stack();
        for (; P[x] >= 0; x = P[x]) s.push(A[x]);

        int i = 0;
        this.lisPath[i++] = A[x];

        for (; !s.isEmpty(); s.pop()) {
            this.lisPath[i++] = s.peek();
        }
    }
}

class LongestIncreasingSub {
    int[] A = new int[]{-7, 10, 9, 2, 3, 8, 8, 1};
    int[] memo = new int[8];

    public LongestIncreasingSub() {
        for (int i = 0; i < memo.length; i++)
            memo[i] = -1;
    }

    public int longest() {
        int max = -1;
        for (int i = 0; i < memo.length; i++) {
            max = Math.max(LIS(i), max);
        }

        return max;
    }

    public int LIS(int i) {
        if (i == 0) return 1;

        if (memo[i] != -1) return memo[i];

        int max = 0;

        for (int j = 0; j < i; j++) {
            if (A[j] < A[i]) {
                max = Math.max(max, LIS(j) + 1);
            }
        }

        return memo[i] = max;
    }
}

class LongestDecreasingSub {
    int[] A = new int[]{-7, 10, 9, 2, 3, 8, 8, 1};
    int[] memo = new int[8];

    public LongestDecreasingSub() {
        for (int i = 0; i < memo.length; i++)
            memo[i] = -1;

        memo[0] = 1;
    }

    public int longest() {
        int max = -1;
        for (int i = 0; i < memo.length; i++) {
            max = Math.max(LDS(i), max);
        }

        return max;
    }

    public int LDS(int i) {
        if (i == 0) return 1;

        if (memo[i] != -1) return memo[i];

        int max = 1;

        for (int j = 0; j < i; j++) {
            if (A[j] > A[i]) {
                max = Math.max(max, LDS(j) + 1);
            }
        }

        return memo[i] = max;
    }
}

// next bottom up dp

class LongestIncreasingSubBottomUp {
    public int solve(int arr[]) {
        int T[] = new int[arr.length];
        int actualSolution[] = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            T[i] = 1;
            actualSolution[i] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (T[j] + 1 > T[i]) {
                        T[i] = T[j] + 1;
                        actualSolution[i] = j;
                    }
                }
            }
        }

        int maxIndex = 0;

        for (int i = 0; i < T.length; i++) {
            if (T[i] > T[maxIndex]) {
                maxIndex = i;
            }
        }

        int t = maxIndex;
        int newT = maxIndex;
        int[] solution = new int[T[maxIndex]];
        int idx = T[maxIndex];

        do {
            t = newT;
            solution[--idx] = arr[t];
            // System.out.print(arr[t] + " ");
            newT = actualSolution[t];
        } while (t != newT);
        //System.out.println();

        for (int sl : solution) {
            System.out.print(sl + " ");
        }
        System.out.println();

        return T[maxIndex];
    }
}

class ch3_06_LIS {


    public static void main(String[] args) {
        final int MAX_N = 100000;

        int n = 11;
        int[] A = new int[]{-7, 10, 9, 2, 3, 8, 8, 1, 2, 3, 4};


        LongestIncreasingSubBottomUp a = new LongestIncreasingSubBottomUp();

        System.out.println(a.solve(A));

//        LongestIncreasingSubsequence a = new LongestIncreasingSubsequence();
//
//        System.out.printf("Final LIS is of length %d: ", a.solve(A).length);

//        System.out.println(new LongestIncreasingSub().LIS(5));
//        System.out.println(new LongestDecreasingSub().longest());
//
//        System.out.println(new LongestDecreasingSub().LDS(1));
    }
}
