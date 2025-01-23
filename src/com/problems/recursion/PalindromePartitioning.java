package com.problems.recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/palindrome-partitioning/
 * https://www.codingninjas.com/codestudio/problems/799931
 * https://www.codingninjas.com/studio/problems/palindrome-partitioning_626181
 * https://www.interviewbit.com/problems/palindrome-partitioning/
 *
 * Solution link
 * https://www.youtube.com/watch?v=WBgsABoClE0
 *
 * https://takeuforward.org/data-structure/palindrome-partitioning/
 * */
public class PalindromePartitioning {

    public static void main(String[] args) {
        type1();
        type2();
    }


    // same as the previous just we will use some memoization trick
    // we can also compute the substring beforehand make it more memoized
    private static void type2() {
        String s = "aabc";
        List<List<String>> answer = new ArrayList<>();
        List<String> bucket = new ArrayList<>();

        int n = s.length();
        int[][] memo = new int[s.length() + 1][s.length() + 1];
        for (int i = 0; i <= n; i++) memo[i][i] = 1;

        partition2(s, 0, bucket, answer, memo);
        System.out.println(answer);
    }

    private static void partition2(String str, int idx, List<String> bucket, List<List<String>> answer, int[][] memo) {
        int n = str.length();
        if (idx == n) {
            answer.add(new ArrayList<>(bucket));
            return;
        }
        // from the start of the index, we will check for every substring
        // that it is a palindrome or not.
        // if it is a palindrome, then we will add that substring into the bucket
        // do the next recursion call from the next index
        for (int i = idx; i < n; i++) {
            if (isPalindrome2(str, idx, i, memo)) {
                bucket.add(str.substring(idx, i + 1));
                partition2(str, i + 1, bucket, answer, memo);
                bucket.remove(bucket.size() - 1);
            }
        }
    }

    private static boolean isPalindrome2(String s, int l, int r, int[][] memo) {
        if (memo[l][r] != 0) return memo[l][r] == 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                memo[l][r] = -1;
                return false;
            }
            l++;
            r--;
        }
        memo[l][r] = 1;
        return true;
    }

    // it is a basic recursion and backtracking
    private static void type1() {
        String s = "aabc";
        List<List<String>> answer = new ArrayList<>();
        List<String> bucket = new ArrayList<>();
        partition1(s, 0, bucket, answer);
        System.out.println(answer);
    }

    private static void partition1(String str, int idx, List<String> bucket, List<List<String>> answer) {
        int n = str.length();
        if (idx == n) {
            answer.add(new ArrayList<>(bucket));
            return;
        }
        // from the start of the index, we will check for every substring
        // that it is a palindrome or not.
        // if it is a palindrome, then we will add that substring into the bucket
        // do the next recursion call from the next index
        for (int i = idx; i < n; i++) {
            if (isPalindrome(str, idx, i)) {
                bucket.add(str.substring(idx, i + 1));
                partition1(str, i + 1, bucket, answer);
                bucket.remove(bucket.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int l, int r) {
        if (l == r) return true;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

}
