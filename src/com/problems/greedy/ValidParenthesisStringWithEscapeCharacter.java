package com.problems.greedy;

/*
 *
 * problem links :
 * https://leetcode.com/problems/lemonade-change/description/
 * https://www.codingninjas.com/studio/problems/lemonade-change_8224112
 *
 * Solution video :
 *
 * */
public class ValidParenthesisStringWithEscapeCharacter {
    public static void main(String[] args) {
        type1();
    }

    // TODO check one more time
    private static void type1() {
        String s = "(*))";
        boolean possible = checkValidString1(s);
        System.out.println(possible);
    }

    // as * can either be ( or ) so we will take two variables
    // one is min another is max
    // the count of opening parenthesis will be in that range
    public static boolean checkValidString1(String s) {
        // open parentheses count in range [cmin, cmax]
        int cmin = 0;  // Minimum number of open parentheses
        int cmax = 0; // Maximum number of open parentheses
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cmin++;
                cmax++;
            } else if (c == ')') {
                cmax--;
                cmin--;
            } else { // '*' character
                cmax++; // if `*` become `(` then openCount++
                cmin--; // if `*` become `)` then openCount--
                // if `*` become `` then nothing happens,
                // So openCount will be in the new range [cmin-1, cmax+1]
            }
            // If at any point, the high becomes negative, the string is not valid
            // Currently, don't have enough open parentheses to match close parentheses-> Invalid
            // For example: ())(
            if (cmax < 0) return false;
            // It's invalid if open parentheses count < 0 that's why cmin can't be negative
            cmin = Math.max(0, cmin);
        }
        // The string is valid if the minimum number of open parentheses is 0
        return cmin == 0;
    }

}
