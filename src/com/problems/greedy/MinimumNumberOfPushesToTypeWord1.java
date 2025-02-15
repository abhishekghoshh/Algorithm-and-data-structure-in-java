package com.problems.greedy;

/*
 * Problem link :
 * https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i/description/
 *
 * Solution link :
 *
 */
public class MinimumNumberOfPushesToTypeWord1 {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String word = "xycdefghij";
        int ans = minimumPushes(word);
        System.out.println(ans);
    }

    // this is a simple greedy approach,
    // we will check the letters and map it into the keypad keys
    // the order is not mandatory.
    // once all the 8 characters are reached, we will increase the counter
    // it will act as the second key in the keypad
    public static int minimumPushes(String word) {
        int n = word.length();
        int keys = 8, num = 1;
        int ans = 0;
        while (n > 0) {
            // if the characters are not a multiple of keys, then we will take
            // the remaining letters, else we will always take keys=8
            ans += num * Math.min(keys, n);
            // we will increase the f for the next letter in the keypad
            num++;
            // we will decrease the 8 characters from the word
            n -= keys;
        }
        return ans;
    }
}
