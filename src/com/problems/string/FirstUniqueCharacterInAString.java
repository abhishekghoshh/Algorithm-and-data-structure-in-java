package com.problems.string;

import java.util.HashMap;
import java.util.Map;

/*
 *
 * problem links :
 * https://leetcode.com/problems/first-unique-character-in-a-string/description/
 * Solution link :
 * https://www.youtube.com/watch?v=rBENYgWy3xU
 *
 * https://neetcode.io/solutions/first-unique-character-in-a-string
 * */
public class FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // same as previous but here we are using the int array as map
    // as it is easy, and we know the range of character
    private static void type3() {
        String s = "leetcode";
        int ans = firstUniqChar3(s);
        System.out.println(ans);
    }

    static int firstUniqChar3(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        if (n == 1) return 0;
        // calculating the frequency
        int[] freq = new int[26];
        for (char ch : arr) {
            int pos = ch - 'a';
            freq[pos]++;
        }
        // now we are iterating from first to last and
        // checking if the freq of the character is 1 if 1 then we will return the index
        for (int i = 0; i < n; i++) {
            int pos = arr[i] - 'a';
            if (freq[pos] == 1) return i;
        }
        return -1;
    }

    // optimized approach
    // this is simple problem of hashing
    // we will use a map or [char,int] and store the freq of every character
    private static void type2() {
        String s = "leetcode";
        int ans = firstUniqChar2(s);
        System.out.println(ans);
    }

    static int firstUniqChar2(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        if (n == 1) return 0;
        // calculating the frequency
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : arr) {
            freq.put(ch, 1 + freq.getOrDefault(ch, 0));
        }
        // now we are iterating from first to last and
        // checking if the freq of the character is 1 if 1 then we will return the index
        for (int i = 0; i < n; i++) {
            if (freq.getOrDefault(arr[i], 0) == 1)
                return i;
        }
        return -1;
    }

    // brute force approach
    // for every character we will check if this is a unique character or not
    private static void type1() {
    }
}
