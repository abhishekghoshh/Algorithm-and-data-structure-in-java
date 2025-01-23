package com.problems.string;
/*
 * Problem link :
 * https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ejBwc2oE7ck
 *
 * https://neetcode.io/solutions/check-if-two-string-arrays-are-equivalent
 */

// Tags: String, two pointers
public class CheckIfTwoStringArraysAreEquivalent {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo explain this in the interview
    // we can solve it in one line like
    // return String.join("",word1).equals(String.join("",word2))
    // but it will create each string every time
    // we could also use 2 sting builder and using 2 arrays we could fill both the string builders
    // but that will also be a brute force

    // going over one word and letter at a time
    // if the current word is completely exhausted then we will go to the next word
    private static void type2() {
        String[] word1 = {"abc", "d", "defg"};
        String[] word2 = {"abcddefg"};
        boolean ans = arrayStringsAreEqual2(word1, word2);
        System.out.println(ans);
    }

    public static boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        int n1 = word1.length, n2 = word2.length;
        int i1 = 0, i2 = 0;
        int j1 = 0, j2 = 0;
        // traversing the string array
        while (j1 < n1 && j2 < n2) {
            String w1 = word1[j1], w2 = word2[j2];
            // checking the current character
            if (w1.charAt(i1++) != w2.charAt(i2++)) return false;
            // if we have completely exhausted then we will go to the next word in array 1
            if (i1 == w1.length()) {
                i1 = 0;
                j1++;
            }
            // if we have completely exhausted then we will go to the next word in array 2
            if (i2 == w2.length()) {
                i2 = 0;
                j2++;
            }
        }
        // if both of the pointer exceed the last word that means the resultant words are same
        return (j1 == n1 && j2 == n2);
    }

    // using string join to join the first array and convert into one string
    // same for the 2nd string array
    // then compare the both
    private static void type1() {
        String[] word1 = {"abc", "d", "defg"};
        String[] word2 = {"abcddefg"};
        boolean ans = arrayStringsAreEqual1(word1, word2);
        System.out.println(ans);
    }

    public static boolean arrayStringsAreEqual1(String[] word1, String[] word2) {
        return String.join("", word1).equals(String.join("", word2));
    }
}
