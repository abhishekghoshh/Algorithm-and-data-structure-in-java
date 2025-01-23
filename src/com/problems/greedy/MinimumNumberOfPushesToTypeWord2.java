package com.problems.greedy;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/
 *
 * Solution link :
 *
 */
public class MinimumNumberOfPushesToTypeWord2 {
    public static void main(String[] args) {
        type1();
    }

    // simple greedy approach
    // first we will calculate the frequency and sort the frequencies.
    // we will only need the frequencies.
    // so for the first 8 frequencies we will map the items to the first letters of all 8 keypads
    // then for the second 8 we will do for the 2nd letters of all 8 keypads, and so on
    private static void type1() {
        String word = "aabbccddeeffgghhiiiiii";
        int ans = minimumPushes(word);
        System.out.println(ans);
    }

    public static int minimumPushes(String word) {
        int[] freq = new int[26];
        // we will take the frequency and sort the array
        for (char ch : word.toCharArray()) freq[ch - 'a']++;
        Arrays.sort(freq);
        // start with 2nd number and first letter
        int key = 2, num = 1;
        int ans = 0;
        // we will start from the reverse
        for (int i = 25; i >= 0; i--) {
            int f = freq[i];
            // if the frequency is 0, then we can stop
            if (f == 0) break;
            ans += f * num;
            key++;
            // if key is 10, then we will again reset it back to the 2 and increase the num
            if (key == 10) {
                key = 2;
                num++;
            }
        }
        return ans;
    }
}
