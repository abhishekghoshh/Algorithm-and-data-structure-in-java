package com.problems.slidingwindow;
/*
 *
 * problem links :
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=kEfPSzgL-Ss
 *
 * https://neetcode.io/solutions/maximum-number-of-vowels-in-a-substring-of-given-length
 * */


// Tags: Array, Sliding window
public class MaximumNumberOfVowelsInASubstringOfGivenLength {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // sliding window approach
    private static void type2() {
        String s = "abciiidef";
        int k = 3;
        int ans = maxVowels2(s, k);
        System.out.println(ans);
    }

    public static int maxVowels2(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int v = 0;
        // for the first window
        for (int i = 0; i < k; i++) {
            if (isVowel(arr[i])) v++;
        }
        int max = v;
        // for the remaining window
        for (int i = k; i < n; i++) {
            if (isVowel(arr[i])) v++;
            if (isVowel(arr[i - k])) v--;
            max = Math.max(max, v);
        }
        return max;
    }

    static boolean isVowel(char ch) {
        return switch (ch) {
            case 'a', 'e', 'i', 'o', 'u' -> true;
            default -> false;
        };
    }

    // brute force
    private static void type1() {

    }
}
