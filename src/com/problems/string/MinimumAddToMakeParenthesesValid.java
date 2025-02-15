package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 * https://www.codingninjas.com/studio/problems/minimum-cost-to-make-string-valid_1115770
 *
 * Solution link :
 *
 *
 */
public class MinimumAddToMakeParenthesesValid {
    public static void main(String[] args) {
        type1();
    }

    // the optimized approach
    // we will go through from left to right
    // if open is less than one, means in the left there need
    // to be added in the left
    // we will also set to zero once open is negative
    // at the last we will check if open is more than 1 or not
    // that means in the right there need to be closing bracket
    private static void type1() {
        String s = "())";
        char[] arr = s.toCharArray();
        int n = arr.length;
        int open = 0, count = 0;
        for (char ch : arr) {
            if (ch == '(') open++;
            else open--;
            if (open < 0) {
                count++;
                open = 0;
            }
        }
        count += open;
        System.out.println(count);
    }
}
