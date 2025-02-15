package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/length-of-last-word/description/
 *
 * Solution link :
 *
 *
 */

// Tags : Array, String
public class LengthOfLastWord {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    // time complexity O(n)
    // we will start from the last and skip till there is a character
    // from that point we will start again and go till there is a space or till the start
    private static void type2() {
        String s = "Hello World";
        int ans = lengthOfLastWord2(s);
        System.out.println(ans);
    }

    public static int lengthOfLastWord2(String s) {
        int n = s.length();
        int c = 0, i = n - 1;
        // skip all the trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') i--;
        // going till we encounter a space
        while (i >= 0 && s.charAt(i) != ' ') {
            i--;
            c++;
        }
        return c;
    }

    // brute force approach
    private static void type1() {
    }
}
