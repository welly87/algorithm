package com.tambunan;

import java.util.ArrayList;

/**
 * Created by Welly on 7/29/2016.
 */
public class Subset {

    // Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums to the given target?
    public boolean groupSum(int start, int[] nums, int target) {

        if (start >= nums.length) return (target == 0);

        return groupSum(start+1, nums, target - nums[start]) ||
                groupSum(start+1, nums, target);
    }

    public int howMany(int start, int[] nums, int target) {
        if (target == 0) return 1;

        if (target < 0) return 0;

        if (start >= nums.length) return 0;

        return howMany(start+1, nums, target - nums[start]) +
                howMany(start+1, nums, target);
    }

    static int[] set = {1, 2, 3, 4, 5};

    public static void main(String[] args) {
        int n=set.length;

        for (int i=0; i < (1<<n); i++) {
            ArrayList<Integer> subset=new ArrayList<Integer>();

            for (int j=0; j < n; j++) {
                if ((i & (1<<j)) > 0)
                    subset.add(set[j]); // process
            }

            // perform an action over the subset, here just print it
            System.out.print("Subset "+i+":");
            for (int k=0; k<subset.size(); k++)
                System.out.print(" "+subset.get(k));
            System.out.println();
        }
    }
}
