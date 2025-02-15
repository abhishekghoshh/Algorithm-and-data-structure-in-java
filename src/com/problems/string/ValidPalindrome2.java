package com.problems.string;
/*
 *
 * problem links :
 * https://leetcode.com/problems/valid-palindrome-ii/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=JrxRYBwG6EI
 *
 * https://neetcode.io/solutions/valid-palindrome-ii
 * */


// Tags: String, Array, Two pointer
public class ValidPalindrome2 {
    // Given a string s, return true if the s can be palindrome after deleting at most one character from it.
    public static void main(String[] args) {
        type1();
        type2();
    }


    // very optimal approach
    // we will use 2 pointers start and end
    // we will increase start and decrease end till the characters are equal
    // once we find any equal character we have 2 choices
    // either to delete the starting character or to delete the ending character
    // if either of them is a palindrome then we have found our answer, and we will return true;
    private static void type2() {
        String s = "abca";
        boolean ans = validPalindrome(s);
        System.out.println(ans);
    }

    public static boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
        }
        return true;
    }

    static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        }
        return true;
    }

    // brute force
    private static void type1() {
        String s = "abca";
    }
}
