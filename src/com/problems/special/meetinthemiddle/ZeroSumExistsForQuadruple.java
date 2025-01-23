package com.problems.special.meetinthemiddle;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem link :
 *
 * Solution link :
 *
 *
 */
public class ZeroSumExistsForQuadruple {

    /**
     * Question:
     * Find if there is any quadruple exists in an array whose sum is zero
     * you can use same number more than once
     * n ⇐ 1000
     * total sum will not cross integer max value
     */
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // meet in the middle
    // same as previous but little optimize
    private static void type3() {
        int[] nums = {1, 2, -1, 3, 5};
        boolean ans = zeroSumExists3(nums);
        System.out.println(ans);
    }

    // it is also meet in the middle approach,
    // but here we are cleverly doing the both operations in the same loop
    private static boolean zeroSumExists3(int[] nums) {
        int n = nums.length;
        Set<Integer> bucket = new HashSet<>();
        // compute the pair sum and add it to the set and check for the reminder
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums[i] + nums[j];
                // add the sum
                bucket.add(sum);
                int rem = -sum;
                // check the reminder
                if (bucket.contains(rem)) return true;
            }
        }
        return false;
    }

    // brute force
    // meet in the middle technique
    // if we can split the task into two, then our work will be straightforward.
    // we have to compute the sum of nums[i]+nums[j]+nums[k]+nums[l] == 0
    // where i,j,k,l can be same index or different
    private static void type2() {
        int[] nums = {1, 2, -1, 3, 5};
        boolean ans = zeroSumExists2(nums);
        System.out.println(ans);
    }

    // we will use split our task.
    // first we will compute all the pair sums and it to the set,
    // and in the 2nd time we will again compute the pairs.
    // we will check if the reminder is present in the set or not
    private static boolean zeroSumExists2(int[] nums) {
        int n = nums.length;
        Set<Integer> bucket = new HashSet<>();
        // compute the pair sum and add it to the set
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                bucket.add(nums[i] + nums[j]);

        // compute the pair sum and check the reminder is present in the set or not
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums[i] + nums[j];
                int rem = -sum;
                if (bucket.contains(rem)) return true;
            }
        }
        return false;
    }

    // brute force
    private static void type1() {
        int[] nums = {1, 2, -1, 3, 5};
        boolean ans = zeroSumExists1(nums);
        System.out.println(ans);
    }

    // we will use a nested loop for checking all the possible sum and then check if it is 0 or not
    private static boolean zeroSumExists1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    for (int l = 0; l < n; l++)
                        if (nums[i] + nums[j] + nums[j] + nums[l] == 0)
                            return true;
        return false;
    }
}
