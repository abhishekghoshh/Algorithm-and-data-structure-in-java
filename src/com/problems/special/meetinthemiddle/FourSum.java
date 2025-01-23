package com.problems.special.meetinthemiddle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Problem link :
 * https://cses.fi/problemset/task/1642
 * Solution link :
 *
 *
 */
public class FourSum {
    public static void main(String[] args) {
        type1();
    }

    // we will use meet in the middle approach
    // rather thinking about quadruples we will think about pairs
    // i is the pivot here
    // when we are on i we are first checking if complement sums for (i,i+1) to (i,n-1) are present in the map or not
    // we surely know that map will not hold pair which includes i as of now,
    // because we are adding pairs later, first we are checking the complements
    private static void type1() {
        int[] nums = {3, 2, 5, 8, 1, 7, 3};
        int n = nums.length;
        int target = 16;
        Map<Integer, List<int[]>> pairSums = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // from i to n we are checking the sum and so the pairs will in this range,
            // and we know as of now we have only created pairs of 0 to i-1
            // so the pairs will be distinct
            for (int j = i + 1; j < n; j++) {
                int complement = target - nums[i] - nums[j];
                if (pairSums.containsKey(complement)) {
                    for (int[] pair : pairSums.get(complement)) {
                        System.out.println("Indices found: " + pair[0] + ", " + pair[1] + ", " + i + ", " + j);
                    }
                }
            }
            // now we will compute this operation
            // we will compute all the pairs for 0 to i, including i
            for (int j = 0; j < i; j++) {
                int sum = nums[i] + nums[j];
                pairSums.putIfAbsent(sum, new ArrayList<>());
                pairSums.get(sum).add(new int[]{i, j});
            }
        }
    }
}
