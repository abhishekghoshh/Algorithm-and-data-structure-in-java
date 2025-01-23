package com.problems.string;

import java.util.Arrays;

/*
 * Problem link:
 * https://www.naukri.com/code360/problems/count-palindromic-subsequences_1062696
 *
 * Solution link:
 * Yogesh & Shailesh (CodeLibrary) :
 * https://www.youtube.com/watch?v=vlbA8oUxSV0
 *
 *
 */
public class CountPalindromicSubsequences {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // TODO solve this
    // optimized approach
    // using dynamic programming
    private static void type2() {
        String s = "abcd";
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        int answer = countPalindrome(0, n - 1, arr, dp);
        System.out.println(answer);
    }

    private static int countPalindrome(int i, int j, char[] arr, int[][] dp) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (dp[i][j] != -1) return dp[i][j];
        if (arr[i] == arr[j])
            return dp[i][j] = countPalindrome(i + 1, j, arr, dp) + countPalindrome(i, j - 1, arr, dp);
        return
                dp[i][j] = countPalindrome(i + 1, j, arr, dp)
                        + countPalindrome(i, j - 1, arr, dp) -
                        countPalindrome(i + 1, j - 1, arr, dp);
    }

    // brute force approach
    private static void type1() {
    }
}
