package com.problems.string;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=EQ5jTZdEn8Y
 *
 * */

// Tags : String, hashing
public class FindWordsThatCanBeFormedByCharacters {
    public static void main(String[] args) {
        type1();
    }

    // optimized approach
    // use either hashmap or int array as freq map both will act same
    // we use int array as it is easy to handle
    private static void type1() {
        String[] words = {"cat", "bt", "hat", "tree"};
        String chars = "atach";
        int ans = countCharacters(words, chars);
    }

    public static int countCharacters(String[] words, String chars) {
        int[] freq = new int[26];
        // first we will create freq map for the seed text
        for (char ch : chars.toCharArray()) freq[ch - 'a']++;
        // we will use freq2 to calculate the freq for the other text
        int[] freq2 = new int[26];
        int total = 0;
        for (String word : words) {
            // calculating freq for the individual word
            for (char ch : word.toCharArray()) freq2[ch - 'a']++;
            // now check if freq2 can be fitted into seed text character freq
            if (canFit(freq2, freq)) total += word.length();
            // cleaning up the freq2
            Arrays.fill(freq2, 0);
        }
        return total;
    }

    static boolean canFit(int[] freq2, int[] freq) {
        for (int i = 0; i < 26; i++)
            if (freq2[i] > freq[i]) return false;
        return true;
    }
}
