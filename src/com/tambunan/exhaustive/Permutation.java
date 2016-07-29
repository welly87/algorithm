package com.tambunan.exhaustive;

/**
 * Created by Welly on 7/29/2016.
 */
public class Permutation {

    static int[] arr = {1, 2, 3, 4, 5};
    static int[] perm = new int[arr.length];
    static boolean[] used = new boolean[arr.length];

    public static void permute(int depth) {
        if (depth == arr.length) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(perm[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (used[i]) continue;

                perm[depth] = arr[i];
                used[i] = true;
                permute(depth + 1);
                used[i] = false;
            }
        }
    }

    public static boolean nextPermutation(int[] a) {
        int N = a.length;
        int i = N - 2;
        for (; i >= 0; i--) if (a[i] < a[i + 1]) break;
        if (i < 0) return false;

        for (int j = N - 1; j >= i; j--) {
            if (a[j] > a[i]) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                break;
            }
        }
        for (int j = i + 1; j < (N + i + 1) / 2; j++)        //reverse from a[i+1] to a[N-1]
        {
            int temp = a[j];
            a[j] = a[N + i - j];
            a[N + i - j] = temp;
        }
        return true;
    }

    public static void main(String[] args) {
//        permute(0);

        do {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
        while (nextPermutation(arr));
    }
}
