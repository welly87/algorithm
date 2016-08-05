package com.tambunan.dp;

class Max1DRangeSumContig {
    public int solve(int[] A) {
        int running_sum = 0, ans = 0;
        for (int i = 0; i < A.length; i++)                                       // O(n)
            if (running_sum + A[i] >= 0) {  // the overall running sum is still +ve
                running_sum += A[i];
                ans = Math.max(ans, running_sum);     // keep the largest RSQ overall
            } else        // the overall running sum is -ve, we greedily restart here
                running_sum = 0;      // because starting from 0 is better for future
        // iterations than starting from -ve running sum
        // System.out.printf("Max 1D Range Sum = %d\n", ans);         // should be 9

        return ans;
    }
}

class Max1DRangeSumNonContig {
    public int solve(int[] A) {

        int maxSofarNonCon = A[0];

        for (int j = 1; j < A.length; j++) {
            int include = Math.max(maxSofarNonCon, maxSofarNonCon + A[j]);
            maxSofarNonCon = Math.max(include, A[j]);
        }

        return maxSofarNonCon;
    }
}

class ch3_04_Max1DRangeSum {

    public static void main(String[] args) {
        int n = 9, A[] = {4, -5, 4, -3, 4, 4, -4, 4, -5};   // a sample array A
        System.out.println(new Max1DRangeSumContig().solve(A));

        System.out.println(new Max1DRangeSumNonContig().solve(A));
    }
}
