package com.problems.array;

import java.util.HashMap;
import java.util.Map;
/*
 *
 * problem links :
 * https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=aoHbYlO8vDg
 *
 * https://neetcode.io/solutions/maximum-product-of-the-length-of-two-palindromic-subsequences
 * */


// Tags: Array, String, Bit masking, Bit Manipulation, Dynamic programming,
public class MaximumProductOfTheLengthOfTwoPalindromicSubsequences {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo we can do a little optimization from the previous approach
    // rather computing for all mask1 * mask2
    // we can check for mask1 with (mask1 + 1 ... N)
    // lets take an example of 1, we will compute with (2 ... N)
    // it will be a waste to do 2,3,4,5 to do with 1 again in their iteration
    // todo we can use another optimization here, we could use an array instead of the hashmap
    private static void type2() {
        String s = "leetcodecom";
        int ans = maxProduct2(s);
        System.out.println(ans);
    }

    private static int maxProduct2(String s) {
        int n = s.length();
        int N = 1 << n;
        char[] arr = s.toCharArray();
        int max = 0;
        int[] palindromes = new int[N + 1];

        for (int mask = 1; mask < N; mask++) {
            StringBuilder subSeq = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subSeq.append(arr[i]);
                }
            }
            if (isPalindrome(subSeq)) {
                palindromes[mask] = subSeq.length();
            }
        }
        for (int mask1 = 1; mask1 <= N; mask1++) {
            if (palindromes[mask1] == 0) continue;
            for (int mask2 = mask1 + 1; mask2 <= N; mask2++) {
                if (palindromes[mask2] == 0) continue;
                if ((mask1 & mask2) != 0) continue;
                int len = palindromes[mask1] * palindromes[mask2];
                max = Math.max(max, len);
            }
        }
        return max;
    }

    // brute force approach, though it is also very good,
    // so for every letter we have two choices, either to take it or not.
    // so we will try to create all the strings and check if it is a palindrome, or not
    // we can check two strings palindrome by comparing their indices.
    // an optimized approach would be to use bitmask a bit while creating the string,
    // and we can compare 2 bitmasks with a simple & operator
    // if the result is 0 that means there are no common indices
    private static void type1() {
        String s = "leetcodecom";
        int ans = maxProduct1(s);
        System.out.println(ans);
    }

    public static int maxProduct1(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int max = 0;
        Map<Integer, Integer> palindromes = new HashMap<>();

        for (int mask = 1; mask < (1 << n); mask++) {
            StringBuilder subSeq = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subSeq.append(arr[i]);
                }
            }
            if (isPalindrome(subSeq)) {
                palindromes.put(mask, subSeq.length());
            }
        }

        for (int mask1 : palindromes.keySet()) {
            for (int mask2 : palindromes.keySet()) {
                if ((mask1 & mask2) == 0) {
                    int len = palindromes.get(mask1) * palindromes.get(mask2);
                    max = Math.max(max, len);
                }
            }
        }
        return max;
    }

    private static boolean isPalindrome(StringBuilder s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
