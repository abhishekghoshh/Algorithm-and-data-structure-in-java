package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/longest-happy-prefix/description/
 * https://www.codingninjas.com/studio/problems/longest-prefix-which-is-suffix_3146849
 *
 * Solution link :
 *
 */
public class LongestHappyPrefix {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    // Using Z function
    // by definition Z function will give us the answer
    // by z function the lps array or Longest-Prefix-Suffix Array
    //
    private static void type2() {
        String s = "level";
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] lps = new int[n];
        int i = 1, j = 0;
        while (i < n) {
            if (arr[i] == arr[j]) {
                lps[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        String answer = s.substring(0, lps[n - 1]);
        System.out.println(answer);
    }

    // brute force approach
    private static void type1() {
        String s = "level";
        char[] arr = s.toCharArray();
        int n = arr.length;
        int max = 0;
        for (int i = 1; i < n; i++) {
            int left = 0, right = n - i;
            while (left < i) {
                if (arr[left] != arr[right]) break;
                left++;
                right++;
            }
            if (left == i) max = Math.max(max, i);
        }
        String answer = s.substring(0, max);
        System.out.println(answer);
    }
}
