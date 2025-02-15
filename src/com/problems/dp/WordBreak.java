package com.problems.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/word-break/description/
 * https://neetcode.io/problems/word-break
 * https://www.naukri.com/code360/problems/word-break-1_758963
 *
 * Solution link:
 * https://www.youtube.com/watch?v=Sx9NNgInc3A
 *
 * https://neetcode.io/solutions/word-break
 * */
public class WordBreak {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // We will again use the dynamic programming here. However,
    // we will not use recursion here, we will use top-down approach
    // lets the word is abcdef, so we will start from 'f' and eventually we will go till 'a'.
    // And every time we will check if there can be any words starting from that letter or not.
    // We can create a map to contain all the words starting from the letters
    // also we will create a dp array of n length.
    // Let's say we are at 'c' and there is a word is "cd"
    // so now we have to check if the remaining part was constructable or not
    // dp[c index] = dp[c index + c length] + (cd is present in the string or not).
    // The (c index + c length) is the string is ef
    private static void type3() {
        String s = "abcd";
        List<String> words = new ArrayList<>(List.of("a", "abc", "b", "cd"));

        boolean ans = wordBreak3(s, words);
        System.out.println(ans);
    }

    private static boolean wordBreak3(String s, List<String> wordDict) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        // we will create a word map
        List<String>[] wordMap = new List[26];
        for (int i = 0; i < 26; i++) wordMap[i] = new ArrayList<>();
        for (String word : wordDict) {
            int ch = word.charAt(0) - 'a';
            wordMap[ch].add(word);
        }
        // dp to store if from that index the remaining string is constructable or not
        boolean[] dp = new boolean[n];

        // we will go from the last to first
        for (int i = n - 1; i >= 0; i--) {
            int ch = arr[i] - 'a';
            // if there is no word starting with this letter, then we will continue
            if (wordMap[ch] == null) continue;
            for (String word : wordMap[ch]) {
                int wLen = word.length();
                // if the current index + word size exceeding the string size, then we cannot accommodate the word
                if (i + wLen > n) continue;
                // if there is no remaining string left, the current word is spreading to the last letter, or dp[i + wLen] is true
                // which means remaining string can be made then dp[i] will
                if (i + wLen == n || dp[i + wLen]) {
                    boolean isWordAPrefix = true;
                    for (int j = 0; j < wLen; j++) {
                        if (arr[i + j] != word.charAt(j)) {
                            isWordAPrefix = false;
                            break;
                        }
                    }
                    dp[i] = isWordAPrefix;
                }
                // if dp[i] is already true, then we do not need to check for the next word
                if (dp[i]) break;
            }
        }
        // dp[0] is our final answer
        return dp[0];
    }

    // so we are going index wise
    // lets the string is aaaaaaaaa..., and the list is [aa,aaa]
    // so while computation we will, we can go like aa->aa->aa or aaa->aaa
    // in both ways we will go to index 6. and we have for the remaining.
    // we can use a memo array to check if the string starting from that index
    // is constructable, or not
    // 1 means yeas 0 means not initialized and -1 means cannot be
    private static void type2() {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>(List.of("cats", "dog", "sand", "and", "cat"));
        boolean hasWord = wordBreak2(s, wordDict);
        System.out.println(hasWord);
    }

    private static boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        int[] dp = new int[n + 1];
        return hasWord2(0, s.toCharArray(), wordDict, dp);
    }

    private static boolean hasWord2(int i, char[] arr, List<String> words, int[] dp) {
        int n = arr.length;
        // if we have reached the last character then we will return true as the word is made
        if (i == n) return true;
        // if the dp is already calculated then we will return true
        if (dp[i] != 0) return (dp[i] == 1);
        for (String word : words)
            if (hasPrefix(arr, word, i) && hasWord2(i + word.length(), arr, words, dp)) {
                dp[i] = 1;
                return true;
            }
        dp[i] = -1;
        return false;
    }


    // it is a brute force approach
    // we will get TLE in leetcode
    // we can use memoization to save some computation here
    private static void type1() {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>(List.of("cats", "dog", "sand", "and", "cat"));
        boolean hasWord = wordBreak1(s, wordDict);
        System.out.println(hasWord);
    }

    public static boolean wordBreak1(String s, List<String> wordDict) {
        return hasWord1(0, s.toCharArray(), wordDict);
    }

    private static boolean hasWord1(int i, char[] arr, List<String> wordDict) {
        int n = arr.length;
        // if we have reached the last character then we will return true as the word is made
        if (i == n) return true;
        for (String word : wordDict) {
            if (hasPrefix(arr, word, i) && hasWord1(i + word.length(), arr, wordDict)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasPrefix(char[] arr, String word, int start) {
        int n = word.length();
        if (start + n > arr.length) return false;
        for (int i = 0; i < n; i++)
            if (word.charAt(i) != arr[i + start]) return false;
        return true;
    }

}
