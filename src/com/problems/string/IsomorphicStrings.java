package com.problems.string;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link :
 * https://leetcode.com/problems/isomorphic-strings
 * https://www.codingninjas.com/studio/problems/isomorphic-strings-_1117636
 *
 * Solution link:
 *
 *
 *
 * */

// Tags : String, hashing
public class IsomorphicStrings {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }


    // this is also same as previous but here we have organized the if else
    // here we will check one edge first
    private static void type3() {
        String s = "paper", t = "title";
        boolean ans = isIsomorphic3(s, t);
        System.out.println(ans);
    }

    private static boolean isIsomorphic3(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int n = arr1.length;
        int[] map1 = new int[128];
        int[] map2 = new int[128];
        for (int i = 0; i < n; i++) {
            int c1 = arr1[i], c2 = arr2[i];
            // from ch1->ch2, if there is no edge then we will set the edge
            // if there is already an edge then we will check if its ch1->ch2 or not
            if (map1[c1] == 0) {
                map1[c1] = c2;
            } else if (map1[c1] != c2) {
                return false;
            }
            // from ch2->ch1, if there is no edge then we will set the edge
            // if there is already an edge then we will check if its ch2->ch1 or not
            if (map2[c2] == 0) {
                map2[c2] = c1;
            } else if (map2[c2] != c1) {
                return false;
            }
        }
        return true;
    }

    // little optimized from previous approach
    // we will use array instead of map
    private static void type2() {
        String s = "paper", t = "title";
        boolean ans = isIsomorphic2(s, t);
        System.out.println(ans);
    }

    private static boolean isIsomorphic2(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int n = arr1.length;
        int N = 128;
        int[] map1 = new int[N];
        int[] map2 = new int[N];
        for (int i = 0; i < n; i++) {
            int ch1 = arr1[i], ch2 = arr2[i];
            // if there is no mapping as of now then we will add the mapping from ch1->ch2 and ch2->ch1
            if (map1[ch1] == 0 && map2[ch2] == 0) {
                map1[ch1] = ch2;
                map2[ch2] = ch1;
            } else if ((map1[ch1] != 0 && map2[ch2] == 0)
                    || (map1[ch1] == 0 && map2[ch2] != 0)
                    || (map1[ch1] != ch2 || map2[ch2] != ch1)) {
                // else if there is any mapping for one of the character already then we will return true
                return false;
            }
        }
        return true;
    }


    // Brute force
    // here we will use 2 map of [character,character]
    // first map => arr1 char , arr2 char
    // second map => arr2 char , arr1 char
    // so that there will be only one to one mapping from ch1 to ch2
    private static void type1() {
        String s = "paper", t = "title";
        boolean ans = isIsomorphic1(s, t);
        System.out.println(ans);
    }

    private static boolean isIsomorphic1(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int n = arr1.length;
        // this is for holding the mappings
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch1 = arr1[i], ch2 = arr2[i];
            // if there is no mapping as of now then we will add the mapping from ch1->ch2 and ch2->ch1
            if (!map1.containsKey(ch1) && !map2.containsKey(ch2)) {
                map1.put(ch1, ch2);
                map2.put(ch2, ch1);
            } else if ((!map1.containsKey(ch1) && map2.containsKey(ch2))
                    || (map1.containsKey(ch1) && !map2.containsKey(ch2))
                    || (map1.get(ch1) != ch2 || map2.get(ch2) != ch1)) {
                // else if there is any mapping for one of the character already then we will return true
                return false;
            }
        }
        return true;
    }
}
