package com.problems.string;

/*
 *
 * problem links :
 * https://leetcode.com/problems/word-pattern/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=W_akoecmCbM
 *
 * */
public class WordPattern {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String pattern = "abba", s = "dog cat cat dog";
        boolean ans = wordPattern(pattern, s);
        System.out.println(ans);
    }

    public static boolean wordPattern(String pattern, String s) {
        // we will split the words by space
        String[] words = s.split(" ");
        char[] arr = pattern.toCharArray();
        int n = arr.length;
        // check if the length is not same then it not possible to create the mapping
        if (n != words.length) return false;
        // we could make a map of (Character,String) but as we know the character size beforehand which is 26,
        // so we will use a string array of size 26
        // also if we use an array we could also check the reverse mapping very easily which string maps to which character
        String[] map = new String[26];
        for (int i = 0; i < n; i++) {
            int pos = arr[i] - 'a';
            String word = words[i];
            // if map[pos] is null but the word is already mapped to a different position then we will return false directly
            if (map[pos] == null && hasKey(map, word)) return false;
            // if the map position has some word already and which is not the current word then we will return false
            if (map[pos] != null && !map[pos].equals(word)) return false;
            // create the mapping of character -> word
            map[pos] = word;
        }
        return true;
    }

    static boolean hasKey(String[] map, String val) {
        for (String word : map) {
            if (val.equals(word)) return true;
        }
        return false;
    }
}
