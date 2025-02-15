package com.problems.string;
/*
 * Problem link :
 * https://leetcode.com/problems/longest-palindrome/description/
 *
 * Solution link:
 * https://youtube.com/watch?v=J_Di2LmeLBQ
 * */
public class LongestPalindrome {
    public static void main(String[] args) {
        type1();
    }

    // Optimized approach
    // all even number characters will add into palindrome,
    // but out of all odd numbers characters first we have to make it into an even number character
    // and treat the single character separately.
    // Out of all single digit characters, only one of them will add to palindrome
    private static void type1() {
        String s = "abccccdd";
        char[] arr = s.toCharArray();
        // we will keep both small and cap frequency side by side
        // also will keep the array size as minimal as possible
        // first 26 for small and 2nd 26 for capital letters
        int[] freq = new int[52];
        for (int ch : arr) {
            // 97 is for small a
            if (ch >= 97) freq[ch - 97]++;
                // 65 is for capital A, but as we are keeping the capitals in the 2nd half
                // so adding 26
            else freq[ch - 65 + 26]++;
        }
        boolean hasOddLengthCharacter = false;
        int totalCount = 0;
        for (int f : freq) {
            // it means f is even number
            if ((f & 1) != 0) {
                hasOddLengthCharacter = true;
                // decrementing that odd character
                f--;
            }
            totalCount += f;
        }
        if (hasOddLengthCharacter) totalCount++;
        System.out.println(totalCount);
    }
}
