package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses
 *
 * Solution link :
 *
 *
 */
public class MaximumNestingDepthOfTheParentheses {
    public static void main(String[] args) {
        type1();
    }

    // maximum depth can be seen as the maximum number of open parentheses
    // at a time
    // It is guaranteed that parentheses expression s is a valid parentheses string (denoted VPS)
    private static void type1() {
        String s = "(1+(2*3)+((8)/4))+1";
        char[] arr = s.toCharArray();
        int open = 0, max = 0;
        for (char ch : arr) {
            if (ch == '(') open++;
            if (ch == ')') open--;
            max = Math.max(max, open);
        }
        System.out.println(max);
    }
}
