package com.problems.slidingwindow;


import java.util.Arrays;
import java.util.Comparator;


/*
 * Problem link :
 * https://leetcode.com/problems/maximum-coins-from-k-consecutive-bags/
 *
 * Solution link:
 *
 * */

// Tags
public class MaximumCoinsFromKConsecutiveBags {
    public static void main(String[] args) {
        type1();
    }

    // we want consecutive sequence, so we will sort the array based on their starting time
    // now we will apply sliding window, but here we have to do it wisely, as some position might be empty
    // we have to use 0 for that positions
    private static void type1() {
    }

    public long maximumCoins(int[][] coins, int k) {
        int n = coins.length;
        Arrays.sort(coins, Comparator.comparingInt(p -> p[0]));
        long max = 0;
        long coin = 0;
        int i = 0;
        int start = coins[0][0];
        while (i < n) {
            int[] bags = coins[i++];

        }
        return max;
    }
}
