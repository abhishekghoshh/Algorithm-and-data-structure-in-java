package com.problems.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/sort-characters-by-frequency/description/
 * https://www.naukri.com/code360/problems/sorting-characters-by-frequency_1263699
 *
 * Solution link :
 * https://www.youtube.com/watch?v=OXdXc9HTrIg
 *
 * https://neetcode.io/solutions/sort-characters-by-frequency
 */

// Tags: Array, String, Hashing
public class SortCharactersByFrequency {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        String s = "cccaaa";
        String answer = frequencySort2(s);
        System.out.println(answer);
    }

    private static String frequencySort2(String s) {
        int[] count = new int[123];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        List<int[]> freq = new ArrayList<>();
        for (int i = 0; i < 123; i++) {
            if (count[i] > 0) {
                freq.add(new int[]{i, count[i]});
            }
        }

        freq.sort((a, b) -> {
            if (b[1] == a[1]) return a[0] - b[0];
            return b[1] - a[1];
        });

        StringBuilder res = new StringBuilder();
        for (int[] pair : freq) {
            char ch = (char) pair[0];
            int f = pair[1];
            for (int i = 0; i < f; i++) {
                res.append(ch);
            }
        }
        return res.toString();
    }

    // almost brute force approach
    // we will use an array all the characters and their frequencies
    // then sort the list
    private static void type1() {
        String s = "cccaaa";
        String answer = frequencySort1(s);
        System.out.println(answer);
    }

    private static String frequencySort1(String s) {
        char[] arr = s.toCharArray();
        Pair[] pairs = new Pair[128];
        for (char ch : arr) {
            if (pairs[ch] == null)
                pairs[ch] = new Pair(ch, 0);
            pairs[ch].n++;
        }
        Arrays.sort(pairs, (p1, p2) -> {
            if (p1 == null || p2 == null) return p1 == null ? 1 : -1;
            return Integer.compare(p2.n, p1.n);
        });
        int i = 0;
        for (Pair p : pairs) {
            if (p == null) continue;
            for (int j = 0; j < p.n; j++) {
                arr[i++] = p.ch;
            }
        }
        return new String(arr);
    }

    private static class Pair {
        public char ch;
        public int n;

        public Pair(char ch, int n) {
            this.ch = ch;
            this.n = n;
        }
    }
}
