package com.problems.dp;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Problem link :
 * https://leetcode.com/problems/longest-string-chain/description/
 * https://www.naukri.com/code360/problems/longest-string-chain_3752111
 *
 * Solution link :
 * https://www.youtube.com/watch?v=YY8iBaYcc4g&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=46
 * https://www.youtube.com/watch?v=7b0V1gT_TIk
 *
 * https://takeuforward.org/data-structure/longest-string-chain-dp-45/
 * https://neetcode.io/solutions/longest-string-chain
 */

// Tags: Array, Hash Table, Two Pointers, String, Dynamic Programming, Sorting
public class LongestStringChain {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // todo check other submissions from the leetcode
    private static void type4() {
    }

    // todo explain this approach in the interview
    // exactly like the previous, but here we will use some clever optimizations
    private static void type3() {
        String[] words = {"bdca", "bda", "ca", "dca", "a"};
        int ans = longestStrChain2(words);
        System.out.println(ans);
    }

    public static int longestStrChain2(String[] words) {
        int n = words.length;
        if (n == 1) return 1;
        // we will convert everything into a char array, for faster checking
        char[][] arr = new char[n][];
        for (int i = 0; i < n; i++) arr[i] = words[i].toCharArray();
        // we will sort the array with their length, so that the lesser string comes at the left
        Arrays.sort(arr, Comparator.comparingInt(w -> w.length));
        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            char[] curr = arr[i];
            int prevMax = 0;
            int prevI = i - 1;
            // as the array is sorted, so we will start from i-1 and go till i>=0
            while (prevI >= 0) {
                char[] prev = arr[prevI];
                int n1 = curr.length, n2 = prev.length;
                // if the prevWord is smaller than currWord.len-1 then we can make answer from that word
                if (n2 + 1 < n1) break;
                if ((n1 == n2 + 1) && hasOneExtraLetter(curr, prev))
                    prevMax = Math.max(prevMax, dp[prevI]);
                prevI--; // decrementing the prev pointer
            }
            dp[i] = prevMax + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    // copying the logic from the longest increasing subsequence
    // where we are using a single dp array to store the longest increasing subsequence
    // for the current index
    private static void type2() {
        String[] words = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        int ans = longestStrChain1(words);
        System.out.println(ans);
    }

    public static int longestStrChain1(String[] words) {
        int n = words.length;
        if (n == 1) return n;
        // we will convert everything into a char array, for faster checking
        char[][] arr = new char[n][];
        for (int i = 0; i < n; i++) arr[i] = words[i].toCharArray();
        // we will sort the array with their length, so that the lesser string comes at the left
        Arrays.sort(arr, Comparator.comparingInt(w -> w.length));
        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            char[] currWord = arr[i];
            int prevMax = 0;
            for (int prev = 0; prev < i; prev++) {
                char[] prevWord = arr[prev];
                // we will check if the current word is a one-letter extra word or not
                if (hasOneExtraLetter(currWord, prevWord))
                    prevMax = Math.max(prevMax, dp[prev]);
            }
            dp[i] = prevMax + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // to compare two strings and check if they form a valid chain
    public static boolean hasOneExtraLetter(char[] w1, char[] w2) {
        int j = 0, n1 = w1.length, n2 = w2.length;
        // if the length different is not 1, then we will directly return false
        if (n2 + 1 != n1) return false;
        // we will increase j if the character matches
        for (char ch : w1)
            if (j < n2 && ch == w2[j]) j++;
        // if j is n2 then it is a match
        return j == n2;
    }

    // simple recursive brute force approach
    private static void type1() {

    }
}
