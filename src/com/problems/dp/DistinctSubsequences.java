package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/distinct-subsequences/description/
 * https://neetcode.io/problems/count-subsequences
 * https://www.naukri.com/code360/problems/subsequence-counting_3755256
 *
 * Solution link :
 * https://www.youtube.com/watch?v=nVG7eTiD2bY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=33
 * https://www.youtube.com/watch?v=-RDzMJ33nx8
 *
 * https://takeuforward.org/data-structure/distinct-subsequences-dp-32/
 * https://neetcode.io/solutions/distinct-subsequences
 */
public class DistinctSubsequences {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
        // space optimized to 1D array
        type5();
    }

    // todo top-down approach or the tabulation form
    // we will use some space optimization here
    // we will use a single array for storing all dp values.
    // todo please check the 0/1 knapsack or unbounded knapsack if you are unable to understand single array concept
    //  we do not need the prev array, because of the recurrence
    private static void type5() {
        String s = "rabbbit";
        String t = "rabbit";
        int count = numDistinct5(s, t);
        System.out.println(count);
    }

    private static int numDistinct5(String s, String t) {
        char[] arr1 = s.toCharArray(), arr2 = t.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        int[] dp = new int[n2 + 1];
        dp[0] = 1;
        for (int i = 1; i <= n1; i++) {
            // as we need the prev row value, that is why we are iterating from the last
            for (int j = n2; j >= 1; j--)
                if (arr1[i - 1] == arr2[j - 1])
                    dp[j] = dp[j - 1] + dp[j];
        }
        return dp[n2];
    }

    // top-down approach or the tabulation form
    // we will use some space optimization here
    // we will use two arrays at a time for storing the current row and prev row
    private static void type4() {
        String s = "rabbbit";
        String t = "rabbit";
        int count = numDistinct4(s, t);
        System.out.println(count);
    }

    private static int numDistinct4(String s, String t) {
        char[] arr1 = s.toCharArray(), arr2 = t.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        int[] prev = new int[n2 + 1];
        // for n1 is 0 we will set 1
        prev[0] = 1;
        for (int i = 1; i <= n1; i++) {
            int[] curr = new int[n2 + 1];
            // for n1 is 0 we will set 1
            curr[0] = 1;
            for (int j = 1; j <= n2; j++)
                if (arr1[i - 1] == arr2[j - 1])
                    curr[j] = prev[j - 1] + prev[j];
                else
                    curr[j] = prev[j];
            // assigning curr to the prev
            prev = curr;
        }
        return prev[n2];
    }

    // top-down approach or the tabulation form
    // again we will use the same recurrence relation from previous types
    private static void type3() {
        String s = "rabbbit";
        String t = "rabbit";
        int count = numDistinct3(s, t);
        System.out.println(count);
    }

    private static int numDistinct3(String s, String t) {
        char[] arr1 = s.toCharArray(), arr2 = t.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        // if the substring is 0, then we will only set it to 1
        for (int i = 0; i <= n1; i++) dp[i][0] = 1;
        // we will use the same condition as the recursion here
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++)
                // if the character is same then we will have 2 options
                if (arr1[i - 1] == arr2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
        }
        return dp[n1][n2];
    }

    // recursion with memoization
    // same as the previous type with the same recurrence relation
    private static void type2() {
        String s = "rabbbit";
        String t = "rabbit";
        int count = numDistinct2(s, t);
        System.out.println(count);
    }

    public static int numDistinct2(String s, String t) {
        char[] arr1 = s.toCharArray(), arr2 = t.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return numDistinct2(n1, n2, arr1, arr2, dp);
    }

    static int numDistinct2(int n1, int n2, char[] arr1, char[] arr2, int[][] dp) {
        // if n2 is 0 that means the substring has no more characters to check, we can return 1
        if (n2 == 0) return 1;
        // if n1 is 0 means the parent string is consumed, we have nothing to check more
        if (n1 == 0) return 0;
        // if the cell is already calculated, then we will return the value
        if (dp[n1][n2] != -1) return dp[n1][n2];
        // if the current character matches, then we have two options
        // either to use the current char of the parent string or go for the next char
        if (arr1[n1 - 1] == arr2[n2 - 1])
            return dp[n1][n2] = numDistinct2(n1 - 1, n2 - 1, arr1, arr2, dp)
                    + numDistinct2(n1 - 1, n2, arr1, arr2, dp);
        else // char does not match, so we have to check for the next char of the parent string
            return dp[n1][n2] = numDistinct2(n1 - 1, n2, arr1, arr2, dp);
    }

    // brute force approach using recursion
    // here we will construct the recurrence relation
    // if the character are same then we have 2 options
    // either to remove characters from both string or remove from the original string and search again
    // if the characters are not same then we will remove from the original string and search again
    private static void type1() {
        String s = "rabbbit";
        String t = "rabbit";
        int count = numDistinct1(s, t);
        System.out.println(count);
    }

    public static int numDistinct1(String s, String t) {
        char[] arr1 = s.toCharArray(), arr2 = t.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        return numDistinct1(n1, n2, arr1, arr2);
    }

    static int numDistinct1(int n1, int n2, char[] arr1, char[] arr2) {
        // if n2 is 0 that means the substring has no more characters to check, we can return 1
        if (n2 == 0) return 1;
        // if n1 is 0 means the parent string is consumed, we have nothing to check more
        if (n1 == 0) return 0;
        // if the current character matches, then we have two options
        // either to use the current char of the parent string or go for the next char
        if (arr1[n1 - 1] == arr2[n2 - 1])
            return numDistinct1(n1 - 1, n2 - 1, arr1, arr2)
                    + numDistinct1(n1 - 1, n2, arr1, arr2);
        else // char does not match, so we have to check for the next char of the parent string
            return numDistinct1(n1 - 1, n2, arr1, arr2);
    }
}
