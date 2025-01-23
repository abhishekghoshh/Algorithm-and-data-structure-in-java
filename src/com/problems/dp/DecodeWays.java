package com.problems.dp;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/decode-ways/description/
 * https://neetcode.io/problems/decode-ways
 *
 * Solution link :
 * https://www.youtube.com/watch?v=6aEyTjOwlJU
 *
 * https://neetcode.io/solutions/decode-ways
 * */
public class DecodeWays {
    // todo check again
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }


    // we will use the iterative approach here
    // todo complete it later
    private static void type3() {
    }

    // same approach
    // here we will use a dp array to store the results
    // we will use one dimensional dp array
    // dp[i] means count of decoding from i to n
    private static void type2() {
        String s = "226";
        int ans = numDecodings2(s);
        System.out.println(ans);
    }

    public static int numDecodings2(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return numDecodings2(0, n, arr, dp);
    }

    public static int numDecodings2(int i, int n, char[] arr, int[] dp) {
        // if i == n means we have exhausted the entire string, so we can use this decoding, hence returning 1
        if (i == n) return 1;
        // if 'i' is calculated then we will return
        if (dp[i] != -1) return dp[i];
        int count = 0;
        int num = arr[i] - '0';
        // return 0 if the current index on 0
        if (num == 0) return dp[i] = 0;
        // going to the next character
        count += numDecodings2(i + 1, n, arr, dp);
        // checking if we can take the next character or not
        if (i + 1 <= n - 1) {
            num = (num * 10) + (arr[i + 1] - '0');
            // if the num is in range [1,26] then only we will consider it
            if (num >= 1 && num <= 26)
                count += numDecodings2(i + 2, n, arr, dp);
        }
        return dp[i] = count;
    }

    // using recursion
    // intuition is very simple
    // if the current index has the '0' so we will not be able to make any string we will return 0 directly
    // so there is possibility with the current index
    // only take it and take it and take the next index as well
    private static void type1() {
        String s = "226";
        int ans = numDecodings1(s);
        System.out.println(ans);
    }

    public static int numDecodings1(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        return numDecodings1(0, n, arr);
    }

    public static int numDecodings1(int i, int n, char[] arr) {
        // if i == n means we have exhausted the entire string, so we can use this decoding, hence returning 1
        if (i == n) return 1;
        int count = 0;
        int num = arr[i] - '0';
        // return 0 if the current index on 0
        if (num == 0) return 0;
        count += numDecodings1(i + 1, n, arr);
        // checking if we can take the next character or not
        if (i + 1 <= n - 1) {
            num = (num * 10) + (arr[i + 1] - '0');
            // if the num is in range [1,26] then only we will consider it
            if (num >= 1 && num <= 26)
                count += numDecodings1(i + 2, n, arr);
        }
        return count;
    }
}
