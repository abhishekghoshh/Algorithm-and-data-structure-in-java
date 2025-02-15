package com.problems.bitmanipulation;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem link :
 * https://leetcode.com/problems/find-the-difference/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=oFmv4N4z00c
 * https://neetcode.io/solutions/find-the-difference
 */
public class FindTheDifference {
    // similar to the find the extra and missing number problem
    public static void main(String[] args) {
        type1();
        type2();
    }


    // todo optimized approach using xor
    //  characters can be represented as the ascii integers
    //  all the characters which are present in the both strings will be cancelled
    //  only once character will be remaining
    private static void type2() {
        String s = "abcd", t = "abcde";
        char ans = findTheDifference2(s, t);
        System.out.println(ans);
    }

    private static char findTheDifference2(String s, String t) {
        int xor = 0;
        for (char ch : s.toCharArray()) {
            xor ^= ch;
        }
        for (char ch : t.toCharArray()) {
            xor ^= ch;
        }
        return (char) xor;
    }

    // todo brute force approach using a set
    private static void type1() {
        String s = "abcd", t = "abcde";
        char ans = findTheDifference1(s, t);
        System.out.println(ans);
    }

    public static char findTheDifference1(String s, String t) {
        Set<Character> set = new HashSet<>();
        // adding all the elements of s to the set
        for (char ch : s.toCharArray()) {
            set.add(ch);
        }
        // now checking from t if the element is present in the set
        for (char ch : t.toCharArray()) {
            if (set.contains(ch)) return ch;
        }
        return '-';
    }
}
