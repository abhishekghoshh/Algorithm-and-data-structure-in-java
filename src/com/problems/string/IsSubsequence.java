package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/is-subsequence/description/
 *
 * Solution link :
 *
 *
 */

// Tags : Array, String
public class IsSubsequence {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    // time complexity O(n)
    private static void type2() {
        String s = "abc", t = "ahbgdc";
        boolean ans = isSubsequence2(s, t);
        System.out.println(ans);
    }

    public static boolean isSubsequence2(String s, String t) {
        char[] arr1 = s.toCharArray(), arr2 = t.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        // if n1 is 0, that means it is empty and empty string is subsequence of all the string
        if (n1 == 0) return true;
        // if n1 > n2 means s has more characters, so it will never be a subsequence
        if (n1 > n2) return false;
        // now we will take a variable and increment it once there is a match in the t string,
        // and we will check if idx value is equal to n1 or not
        int idx = 0;
        for (char ch : arr2) {
            if (arr1[idx] == ch) idx++;
            if (idx == n1) return true;
        }
        return false;
    }

    // brute force approach
    private static void type1() {
    }
}
