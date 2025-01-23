package com.problems.recursion;

import java.util.Arrays;

/*
 * Problem links:
 * https://www.naukri.com/code360/problems/more-subsequence_8842355
 * Solution link
 *
 * */
public class MoreSubsequence {
    // TODO check the solution one more time
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we will use dynamic programming to count all the subsequences and return the answer
    private static void type2() {
        String a = "ab";
        String b = "dd";
        String answer = moreSubsequence2(a, b);
        System.out.println(answer);
    }

    public static String moreSubsequence2(String a, String b) {
        if (count2(a.toCharArray()) >= count2(b.toCharArray())) {
            return a;
        } else {
            return b;
        }
    }

    private static int count2(char[] str) {
        int n = str.length;
        int[] dp = new int[n + 1];
        int[] indices = new int[256];
        Arrays.fill(indices, -1);

        dp[0] = 1;
        // if the characters are unique then total number of subsequences is 2^n
        for (int i = 1; i <= n; i++) {
            int ch = str[i - 1];
            // by 2^n logic, we will multiply with 2 everytime, but there might be some repeating characters
            dp[i] = 2 * dp[i - 1];
            // if the indices of ch is not -1, then this character is a repeating character
            // if the character is repeating, then we will decrement the number of subsequences for this character
            // when it was added last time
            if (indices[ch] != -1) dp[i] -= dp[indices[ch]];
            // everytime we will update the indices array
            indices[ch] = i - 1;
        }
        return dp[n];
    }

    // we will use normal recursion to create all the distinct subsequence and will check which has more
    private static void type1() {

    }
}
