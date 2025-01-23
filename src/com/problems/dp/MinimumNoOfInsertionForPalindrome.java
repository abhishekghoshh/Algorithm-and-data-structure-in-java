package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 * https://www.naukri.com/code360/problems/minimum-insertions-to-make-palindrome_985293
 *
 * Solution link :
 * https://www.youtube.com/watch?v=AEcRW4ylm_c&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=31
 *
 * https://www.youtube.com/watch?v=xPBLEj41rFU&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=30
 * https://takeuforward.org/data-structure/minimum-insertions-to-make-string-palindrome-dp-29/
 */
public class MinimumNoOfInsertionForPalindrome {

    public static void main(String[] args) {
        type3();
        type4();
        type5();
    }

    // TODO same approach for all three types
    // another way to create the longest palindromic subsequence
    // TODO check it later one more time
    private static void type5() {
        String s = "abcbcxcbe";
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                int next = dp[j];
                if (arr[i] == arr[j]) dp[j] = 2 + max;
                max = Math.max(max, next);
            }
        }
        int max = 0;
        for (int i : dp) max = Math.max(i, max);
        int count = n - max;
        System.out.println(count);
    }

    // same as previous
    // here we will count the longest palindromic subsequence in a different way
    // this type is derived from the longest palindromic substring solution
    private static void type4() {
        String s = "abcbcxcbe";
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];

        // it will handle all 1 length characters
        for (int i = 0; i < n; i++) dp[i][i] = 1;

        // it will handle all the characters starting from 2
        for (int len = 2; len <= n; len++) {
            for (int start = 0; start + len <= n; start++) {
                int end = start + len - 1;
                // if start and end are the same character, then we will check for 1 less character on both sides
                if (arr[start] == arr[end])
                    dp[start][end] = dp[start + 1][end - 1] + 2;
                else // else we will check for 1 character less from two sides separately
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
            }
        }
        int count = n - dp[0][n - 1];
        System.out.println(count);
    }


    // let's say the string is aaxbaa, here the longest palindromic subsequence is aabaa,
    // if we can insert x after b, then it will be aaxbxaa.
    // that means if we can add the characters in the string which is not part of the longest palindromic subsequence
    // in their proper place in the actual string, then the string will become palindrome.
    // so the answer will be the len(string) - len(longest palindromic substring)
    private static void type3() {
        String s = "abcbcxcbe";
        char[] arr = s.toCharArray();
        int n = arr.length;
        // rather creating another array, we could directly use the same array
        // and in place of this condition arr[i - 1] == reversed[j - 1]
        // we could use arr[i - 1] == arr[n - j], which is same essentially
        // so use could save some space and computation
        char[] reversed = new char[n];
        for (int i = 0; i < n; i++) reversed[i] = arr[n - 1 - i];

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i - 1] == reversed[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int count = n - dp[n][n];
        System.out.println(count);
    }
}
