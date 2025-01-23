package com.problems.string;

/*
 * Problem link:
 * https://leetcode.com/problems/sum-of-beauty-of-all-substrings/description/
 * https://www.codingninjas.com/studio/problems/sum-of-beauty-of-all-substrings_8143656
 *
 * Solution:
 *
 * */
public class SumOfBeautyOfAllSubstrings {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // slightly optimized from brute force,
    // we are skipping the find max iteration
    private static void type2() {
        String s = "aabcb";
        char[] arr = s.toCharArray();
        int n = arr.length;
        int sum = 0;
        int[] freq;
        int max, min, ch;
        for (int i = 0; i < n; i++) {
            freq = new int[26];
            max = Integer.MIN_VALUE;
            for (int j = i; j < n; j++) {
                ch = arr[j] - 'a';
                freq[ch]++;
                if (freq[ch] > max) max = freq[ch];
                min = Integer.MAX_VALUE;
                for (int num : freq) if (num != 0 && num < min) min = num;
                sum += (max - min);
            }
        }
        System.out.println(sum);
    }

    // brute force approach
    private static void type1() {
        String s = "aabcb";
        char[] arr = s.toCharArray();
        int n = arr.length;
        int sum = 0;
        int[] freq;
        for (int i = 0; i < n; i++) {
            freq = new int[26];
            for (int j = i; j < n; j++) {
                freq[arr[j] - 'a']++;
                sum += beautyOfString(freq);
            }
        }
        System.out.println(sum);
    }

    private static int beautyOfString(int[] freq) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : freq) {
            if (num != 0 && num > max) max = num;
            if (num != 0 && num < min) min = num;
        }
        return max - min;
    }
}
