package com.problems.string;
/*
 *
 * problem links :
 * https://leetcode.com/problems/find-first-palindromic-string-in-the-array/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=4JA5MW772N0
 *
 * https://neetcode.io/solutions/find-first-palindromic-string-in-the-array
 * */


// Tags: String, Array, Two pointer
public class FindFirstPalindromicStringInTheArray {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String[] words = {"abc", "car", "ada", "racecar", "cool"};
        String ans = firstPalindrome(words);
        System.out.println(ans);
    }

    public static String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word, 0, word.length() - 1))
                return word;
        }
        return "";
    }

    static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        }
        return true;
    }
}
