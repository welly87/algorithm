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

class ch3_06_LIS {

    public static void main(String[] args) {
        final int MAX_N = 100000;

        int n = 11;
        int[] A = new int[]{-7, 10, 9, 2, 3, 8, 8, 1, 2, 3, 4};

        LongestIncreasingSubsequence a = new LongestIncreasingSubsequence();

        System.out.printf("Final LIS is of length %d: ", a.solve(A).length);
    }
}
