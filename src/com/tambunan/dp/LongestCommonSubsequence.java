package com.tambunan.dp;

/**
 * Created by Welly on 8/6/2016.
 */
public class LongestCommonSubsequence {

    // Compute length of LCS for all subproblems.
    public String lcs(String x, String y) {
        int m = x.length(), n = y.length();
        int[][] opt = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (x.charAt(i) == y.charAt(j)) {
                    opt[i][j] = opt[i + 1][j + 1] + 1;
                } else {
                    opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
                }
            }
        }

        // Recover LCS itself.
        String lcs = "";
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (x.charAt(i) == y.charAt(j)) {
                lcs += x.charAt(i);
                i++;
                j++;
            } else if (opt[i + 1][j] >= opt[i][j + 1]) i++;
            else j++;
        }
        return lcs;
    }

    public static void main(String[] args) {
        String lcs = new LongestCommonSubsequence().lcs("GGCACCACG", "ACGGCGGATACG");
        System.out.println(lcs.length());
    }
}
