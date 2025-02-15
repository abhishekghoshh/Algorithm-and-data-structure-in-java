package com.problems.trie;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/extra-characters-in-a-string/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ONstwO1cD7c
 *
 * https://neetcode.io/solutions/extra-characters-in-a-string
 */

// Tags: Arrays, String, Recursion, Trie, Dynamic Programming
public class ExtraCharactersInAString {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo same as previous but here will use trie to match the words
    //  here also we have 2 cases, either to consider the character or not
    //  but here we can directly use trie to find if the current char is in the dictionary or not
    //  because trie has the knowledge of all the words so we do not need to check for any individual words
    private static void type2() {
        String s = "dwmodizxvvbosxxw";
        String[] dictionary = {"ox", "lb", "diz", "gu", "v", "ksv", "o", "nuq", "r", "txhe", "e", "wmo", "cehy", "tskz", "ds", "kzbu"};
        int ans = minExtraChar2(s, dictionary);
        System.out.println(ans);
    }

    private static int minExtraChar2(String s, String[] dictionary) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        // initializing the dp array with -1
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        Node trie = new Node();
        for (String word : dictionary) {
            addWord(word.toCharArray(), trie);
        }
        // now using trie, do the search
        return minExtraChar(arr, 0, dp, trie);
    }

    private static int minExtraChar(char[] arr, int start, int[] dp, Node trie) {
        int n = arr.length;
        if (start >= n) return 0;
        if (dp[start] != -1) return dp[start];
        // not considering the current character
        int min = 1 + minExtraChar(arr, start + 1, dp, trie);
        Node node = trie;
        for (int i = start; i < n; i++) {
            int pos = arr[i] - 'a';
            // if the character is not in the trie, then break
            // else goes to the next node till we do not find a word end
            if (node.nodes[pos] == null) break;
            node = node.nodes[pos];
            // if we find any word end, we will start a new recursion call from that point
            if (node.isEnd) {
                min = Math.min(min, minExtraChar(arr, i + 1, dp, trie));
            }
        }
        return dp[start] = min;
    }

    public static void addWord(char[] word, Node trie) {
        Node node = trie;
        for (char ch : word) {
            int pos = ch - 'a';
            if (node.nodes[pos] == null) {
                node.nodes[pos] = new Node();
            }
            node = node.nodes[pos];
        }
        node.isEnd = true;
    }

    static class Node {
        boolean isEnd = false;
        Node[] nodes = new Node[26];
    }

    // todo simple recursion
    //  but it will fail with simple recursion, so we will apply DP here
    //  this problem might look complicated but its just time consuming
    //  we can just simple recursion with simple dp, we do not need to use trie here
    //  there are two cases either to consider the character or not
    private static void type1() {
        String s = "dwmodizxvvbosxxw";
        String[] dictionary = {"ox", "lb", "diz", "gu", "v", "ksv", "o", "nuq", "r", "txhe", "e", "wmo", "cehy", "tskz", "ds", "kzbu"};
        int ans = minExtraChar1(s, dictionary);
        System.out.println(ans);
    }

    public static int minExtraChar1(String s, String[] dictionary) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        // initializing the dp array with -1
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        // creating a cache to store which word starts with which character
        List<char[]>[] map = new List[26];
        for (int i = 0; i < 26; i++) {
            map[i] = new ArrayList<>();
        }
        for (String word : dictionary) {
            map[word.charAt(0) - 'a'].add(word.toCharArray());
        }
        return minExtraChar1(arr, 0, map, dp);
    }

    static int minExtraChar1(char[] arr, int i, List<char[]>[] map, int[] dp) {
        int n = arr.length;
        if (i >= n) return 0;
        // if the value is already calculated, then return it
        if (dp[i] != -1) return dp[i];
        int pos = arr[i] - 'a';
        int min = 1 + minExtraChar1(arr, i + 1, map, dp);
        // if there are words with the current character, then we will check for all that words
        for (char[] word : map[pos]) {
            int wLen = word.length;
            int cost;
            if (wLen <= (n - i) && match(word, arr, i)) {
                // the word is in the original array then we do not need any cost
                cost = minExtraChar1(arr, i + wLen, map, dp);
            } else if (wLen > (n - i)) {
                // if word length exceeds the original word remaining length, then the word cannot be made
                cost = (n - i);
            } else {
                // the word does not match with the original word, so word length is the cost
                cost = wLen + minExtraChar1(arr, i + wLen, map, dp);
            }
            min = Math.min(min, cost);
        }
        return dp[i] = min;
    }

    static boolean match(char[] word, char[] arr, int i) {
        int j = 0;
        int n = word.length;
        while (j < n) {
            if (word[j] != arr[i + j]) return false;
            j++;
        }
        return true;
    }
}
