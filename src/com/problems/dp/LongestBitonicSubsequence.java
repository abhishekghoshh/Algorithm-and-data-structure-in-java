package com.problems.dp;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
 * https://www.naukri.com/code360/problems/longest-bitonic-sequence_1062688
 *
 * Solution link :
 * https://www.youtube.com/watch?v=y4vN0WNdrlg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=47
 *
 * https://takeuforward.org/data-structure/longest-bitonic-subsequence-dp-46/
 */
public class LongestBitonicSubsequence {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // we can do it inside a single iteration
    private static void type3() {
        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
        int n = arr.length;


        // Arrays to store lengths of increasing and decreasing subsequences
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        for (int left = 0; left < n; left++) {
            // Calculate the lengths of increasing subsequences
            int maxLeft = 0;
            for (int prev = 0; prev < left; prev++)
                if (arr[prev] < arr[left])
                    maxLeft = Math.max(maxLeft, dp1[prev]);
            dp1[left] = maxLeft + 1;
            // Reverse the direction of nested loops and calculate the lengths of decreasing subsequences
            int right = n - 1 - left;
            int maxRight = 0;
            for (int prev = n - 1; prev > right; prev--)
                if (arr[prev] < arr[right])
                    maxRight = Math.max(maxRight, dp2[prev]);
            dp2[right] = maxRight + 1;
        }
        int max = 0;
        // Calculate the length of the longest bitonic subsequence
        // we will decrease by 1 as for dp1 and dp2 both
        for (int i = 0; i < n; i++)
            max = Math.max(max, dp1[i] + dp2[i] - 1);

        System.out.println(max);
    }

    // this is inspired from the longest increasing subsequence
    // what is a bitonic array, it is increasing first then decreasing
    // and decreasing means increasing from the right to left.
    // so if we can start 2 longest increasing subsequences, and then on each
    // index, if we take the sum, then that will be a bitonic array focusing that index at a center.
    // we will take the maximum to find out the longest among them
    private static void type2() {
        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
        int n = arr.length;

        // Arrays to store lengths of increasing and decreasing subsequences
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        // Calculate the lengths of increasing subsequences
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int prev = 0; prev < i; prev++)
                if (arr[prev] < arr[i])
                    max = Math.max(max, dp1[prev]);
            dp1[i] = max + 1;
        }
        // Reverse the direction of nested loops and calculate the lengths of decreasing subsequences
        for (int i = n - 1; i >= 0; i--) {
            int max = 0;
            for (int prev = n - 1; prev > i; prev--)
                if (arr[prev] < arr[i])
                    max = Math.max(max, dp2[prev]);
            dp2[i] = max + 1;
        }

        int max = 0;
        // Calculate the length of the longest bitonic subsequence
        // we will decrease by 1 as for dp1 and dp2 both
        for (int i = 0; i < n; i++)
            max = Math.max(max, dp1[i] + dp2[i] - 1);

        System.out.println(max);
    }

    private static void type1() {

    }
}
