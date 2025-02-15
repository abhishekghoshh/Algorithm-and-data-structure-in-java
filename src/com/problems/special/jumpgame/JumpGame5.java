package com.problems.special.jumpgame;

/*
 *
 * problem links :
 * https://leetcode.com/problems/jump-game-v/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=pQXbujZLTv0&list=PLJtzaiEpVo2yaP5v5bq0-QJgU0lO3TrEi&index=5
 *
 * https://github.com/Algorithms-Made-Easy/Leetcode-Challenge/blob/main/1340.%20Jump%20Game%20V
 * */
public class JumpGame5 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // same as the previous just one line difference
    // using dynamic programming
    private static void type2() {
        int[] arr = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
        int d = 2;
        int n = arr.length;
        int max = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int jump = jumpNext(i, arr, d, dp);
            max = Math.max(max, jump);
        }
        System.out.println(max);
    }

    private static int jumpNext(int start, int[] arr, int d, int[] dp) {
        // if the value is already calculated then we will return from the dp table
        if (dp[start] != 0) return dp[start];
        // taking max as 0 and setting height as the current index height
        int n = arr.length;
        int max = 0, height = arr[start];
        int left = Math.max(0, start - d);
        int right = Math.min(n - 1, start + d);
        // going in the left direction
        for (int i = start - 1; i >= left; i--) {
            // if the current height is greater than starting height then we will stop here
            if (height <= arr[i]) break;
            max = Math.max(max, jumpNext(i, arr, d, dp));
        }
        // going in the right direction
        for (int i = start + 1; i <= right; i++) {
            // if the current height is greater than starting height then we will stop here
            if (height <= arr[i]) break;
            max = Math.max(max, jumpNext(i, arr, d, dp));
        }
        // +1 for including itself
        return dp[start] = max + 1;
    }

    // brute force using recursion
    // the intuition very simple
    // from one index we have to go left and right with the range, and which will be lesser than the current height
    // if any cell height is greater than the current height then we will stop right there.
    // if we find any lesser height then from that index we will again start JumpNext recursively.
    // and after everything we will return the max jump
    private static void type1() {
        int[] arr = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
        int d = 2;
        int n = arr.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jumpNext(i, arr, d));
        }
        System.out.println(max);
    }

    private static int jumpNext(int start, int[] arr, int d) {
        int n = arr.length;
        // taking max as 0 and setting height as the current index height
        int max = 0, height = arr[start];
        int left = Math.max(0, start - d);
        int right = Math.min(n - 1, start + d);
        // going in the left direction
        for (int i = start - 1; i >= left; i--) {
            // if the current height is greater than starting height then we will stop here
            if (height <= arr[i]) break;
            max = Math.max(max, jumpNext(i, arr, d));
        }
        // going in the right direction
        for (int i = start + 1; i <= right; i++) {
            // if the current height is greater than starting height then we will stop here
            if (height <= arr[i]) break;
            max = Math.max(max, jumpNext(i, arr, d));
        }
        // +1 for including itself
        return max + 1;
    }
}
