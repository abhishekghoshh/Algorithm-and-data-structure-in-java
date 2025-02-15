package com.problems.array;

/*
 *
 * problem links :
 * https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=3YDBT9ZrfaU
 *
 * https://neetcode.io/solutions/minimum-number-of-swaps-to-make-the-string-balanced
 * */


// Tags: Arrays, Two Pointers, String, Stack, Greedy
public class MinimumNumberOfSwapsToMakeTheStringBalanced {
    public static void main(String[] args) {
        type1();
    }

    // todo check this solution again
    // We will be a little greedy here
    // The total number of unmatched opening brackets at the end will tell us how many swaps are necessary to balance the string
    // so, we keep track the number of unmatched closing brackets.
    // Each swap can fix two unmatched brackets, so the minimum number of swaps required is (unmatched+1)/2
    private static void type1() {
        String s = "]][][[";
        int ans = minSwaps3(s);
        System.out.println(ans);
    }

    public static int minSwaps3(String s) {
        int imbalance = 0; // This variable tracks the number of unmatched closing brackets
        int openBrackets = 0; // This variable tracks the number of unmatched opening brackets
        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            if (c == '[') {
                openBrackets++; // Increment for an opening bracket
            } else { // c == ']'
                if (openBrackets > 0) {
                    openBrackets--; // Match with an opening bracket
                } else {
                    imbalance++; // Unmatched closing bracket
                }
            }
        }
        // Each swap can fix two imbalances, hence divide by 2
        return (imbalance + 1) / 2;
    }


}
