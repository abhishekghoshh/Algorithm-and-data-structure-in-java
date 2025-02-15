package com.problems.slidingwindow;

import java.util.Arrays;
/*
 *
 * problem links :
 * https://leetcode.com/problems/longest-repeating-character-replacement/description/
 * https://neetcode.io/problems/longest-repeating-substring-with-replacement
 * https://www.naukri.com/code360/problems/longest-repeating-substring_980523
 *
 * Solution link :
 * https://www.youtube.com/watch?v=_eNhaDCr6P0
 * https://www.youtube.com/watch?v=gqXU1UyA8pk
 *
 * https://neetcode.io/solutions/longest-repeating-character-replacement
 * */


// Tags: Array, Sliding window
public class LongestRepeatingCharacterReplacement {


    /*
     * You are given a string s and an integer k. You can choose any character of the string and
     * change it to any other uppercase English character. You can perform this operation at most k times.
     *
     * Return the length of the longest substring containing the same letter you can get after performing the above operations.
     *
     * */

    /*
     * You should aim for a solution with O(n) time and O(m) space,
     * where n is the length of the given string and m is the number of unique characters in the string.
     * Which characters would you replace in a string to make all its characters unique? Can you think with respect to the frequency of the characters?
     * It is always optimal to replace characters with the most frequent character in the string. Why?
     * Because using the most frequent character minimizes the number of replacements required to make all characters in the string identical.
     * How can you find the number of replacements now?
     *
     * The number of replacements is equal to the difference between the length of the string and the frequency of the most frequent character in the string.
     * A brute force solution would be to consider all substrings, use a hash map for frequency counting,
     * and return the maximum length of the substring that has at most k replacements. This would be an O(n^2) solution. Can you think of a better way?
     *
     * We can use the sliding window approach. The window size will be dynamic, and we will shrink the window when the number of replacements exceeds k.
     * The result will be the maximum window size observed at each iteration.
     * */
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // todo optimized approach using the sliding window
    //  see the solution first then come up with the intuition
    //  lets take the series of A B C D E E E A B C F F A B F F F and k is 3
    private static void type3() {
        String s = "BAAAB";
        int k = 2;
        int max = characterReplacement3(s, k);
        System.out.println(max);

    }

    private static int characterReplacement3(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] freq = new int[26];
        // we will use 2 pointer
        int left = 0, right = 0;
        int max = 0;
        int currMaxFreq = 0;
        while (right < n) {
            int rightCh = arr[right++] - 'A';
            freq[rightCh]++;
            // update a maximum frequency of any character in the current window
            currMaxFreq = Math.max(currMaxFreq, freq[rightCh]);
            //  todo we will choose the character with the most freq to be the target character
            // We are allowed to have at most k replacements in the window...
            // So, if max character frequency + distance between beg and end is greater than k...
            // this means we have considered changing more than k characters.
            // So we will shrink the window from the left side
            // Then there are more characters in the window than we can replace, and we need to shrink the window...
            // todo let's say the target characters are on the left side so if we shrink then those will be deleted still we don't need to bother,
            //  Note: we don't need to update maxFreq here even though we are shrinking the window
            //  because we do not need to decrease the max frequency as the result will be impacted by only a higher max freq
            //  even if we are computing it wrong at some point still we don't need to decrease
            //  later if we find any higher maxFreq then only it will impact the answer
            while ((right - left + 1) - currMaxFreq > k) {
                int leftCh = arr[left++] - 'A'; // getting the left char and also incrementing left ptr
                freq[leftCh]--; // decreasing the character frequency
            }
            // Get the length of repeating character..
            max = Math.max(max, (right - left + 1));
        }
        return max;
    }


    // like the previous, but here we will not calculate everytime
    // once (right - left + 1) - maxF > k then we will shrink the left side and calculate maxF again
    private static void type2() {
        String s = "BAAAB";
        int k = 2;
        int max = characterReplacement2(s, k);
        System.out.println(max);
    }

    private static int characterReplacement2(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] freq = new int[26];
        int max = 0;
        int maxF = 0;
        for (int right = 0, left = 0; right < n; right++) {
            int pos = arr[right] - 'A';
            freq[pos]++;
            maxF = Math.max(maxF, freq[pos]);
            // max character changes is more than k, so we will try to shrink the left side
            while ((right - left + 1) - maxF > k) {
                int leftPos = arr[left++] - 'A';
                freq[leftPos]--;
                // and again calculating the maximum freq
                maxF = max(freq);
            }
            // Get the length of repeating character..
            max = Math.max(max, (right - left + 1));
        }
        return max;
    }

    private static int max(int[] freq) {
        int maxF = 0;
        for (int f : freq) {
            maxF = Math.max(maxF, f);
        }
        return maxF;
    }


    // for every index we will try to expand it the characters can be changed
    // into the character which has the max freq in that range
    private static void type1() {
        String s = "BAAAB";
        int k = 2;
        int max = characterReplacement1(s, k);
        System.out.println(max);

    }

    private static int characterReplacement1(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] freq = new int[26];
        int max = 0;
        for (int i = 0; i < n; i++) {
            int maxF = 0;
            for (int j = i; j < n; j++) {
                int pos = arr[j] - 'A';
                freq[pos]++;
                maxF = Math.max(maxF, freq[pos]);
                // max character changes is more than k
                if ((j - i + 1) - maxF > k) break;
                max = Math.max(max, (j - i + 1));
            }
            Arrays.fill(freq, 0);
        }
        return max;
    }
}
