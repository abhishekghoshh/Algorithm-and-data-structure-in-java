package com.problems.array;
/*
 *
 * problem links :
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=2pndAmo_sMA
 *
 * https://neetcode.io/solutions/range-sum-query-immutable
 * */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Tags : Prefix Sum, Array, Hashing, String
public class LargestSubstringBetweenTwoEqualCharacters {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // same as previous but here we wil use an int array as map
    // because it is easy to use an array rather than map
    // as the character range is limited
    private static void type3() {
        String s = "abca";
        int ans = maxLengthBetweenEqualCharacters3(s);
        System.out.println(ans);
    }

    public static int maxLengthBetweenEqualCharacters3(String s) {
        int n = s.length();
        // if the length is 1 then there will be no occurrence of same character
        if (n == 1) return -1;
        // here we will store the index of the character, and we will mark everything as -1
        int[] map = new int[26];
        Arrays.fill(map, -1);
        char[] arr = s.toCharArray();
        int max = -1;
        for (int i = 0; i < n; i++) {
            int pos = arr[i] - 'a';
            // we will store the character only first time we have seen it
            if (map[pos] == -1) {
                map[pos] = i;
            } else {
                // if the character exists then we will retrieve its index
                // and find the length of in between characters
                int start = map[pos];
                max = Math.max(max, (i - start - 1));
            }
        }
        return max;
    }

    // optimize approach
    // so all we need the index of the character
    // so we can just store the first occurrence of a character
    private static void type2() {
        String s = "abca";
        int ans = maxLengthBetweenEqualCharacters2(s);
        System.out.println(ans);
    }

    public static int maxLengthBetweenEqualCharacters2(String s) {
        int n = s.length();
        // if the length is 1 then there will be no occurrence of same character
        if (n == 1) return -1;
        // here we will store the index of the character
        Map<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        int max = -1;
        for (int i = 0; i < n; i++) {
            char ch = arr[i];
            // we will store the character only first time we have seen it
            if (!map.containsKey(ch)) {
                map.put(ch, i);
            } else {
                // if the character exists then we will retrieve its index
                // and find the length of in between characters
                int start = map.get(ch);
                max = Math.max(max, (i - start - 1));
            }
        }
        return max;
    }

    // brute force approach
    // for every character we will check if the same character is present previously or not
    private static void type1() {
        String s = "abca";
        int ans = maxLengthBetweenEqualCharacters1(s);
        System.out.println(ans);
    }

    public static int maxLengthBetweenEqualCharacters1(String s) {
        int n = s.length();
        // if the length is 1 then there will be no occurrence of same character
        if (n == 1) return -1;
        char[] arr = s.toCharArray();
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] == arr[j])
                    max = Math.max(max, (j - i - 1));
            }
        }
        return max;
    }
}
