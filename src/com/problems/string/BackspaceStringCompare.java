package com.problems.string;
/*
 * Problem link :
 * https://leetcode.com/problems/backspace-string-compare/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=k2qrymM_DOo
 *
 * https://neetcode.io/solutions/backspace-string-compare
 */


import java.util.Stack;

// Tags: String, two pointers, Stack
public class BackspaceStringCompare {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimal approach,
    // so we will use 2 pointer on each of the string
    // one for traversing the array another for the index of the final
    // every time we see a character we will increment it and for # we will decrement int
    // we can use a different array to store the resultant string
    // but here we are using the same array to store the result string
    private static void type2() {
        String s = "ab##", t = "c#d#";
        boolean ans = backspaceCompare2(s, t);
        System.out.println(ans);
    }

    public static boolean backspaceCompare2(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        int m1 = 0, m2 = 0;
        // traversing on the fist array to find the resultant string
        for (int i = 0; i < n1; i++) {
            char ch = arr1[i];
            if (ch == '#') {
                m1--;
                if (m1 < 0) m1 = 0;
            } else {
                arr1[m1++] = ch;
            }
        }
        // traversing on the second array to find the resultant string
        for (int i = 0; i < n2; i++) {
            char ch = arr2[i];
            if (ch == '#') {
                m2--;
                if (m2 < 0) m2 = 0;
            } else {
                arr2[m2++] = ch;
            }
        }
        // if the length is not same then it is false
        if (m1 != m2) return false;
        // if the content is not same then also it is false
        for (int i = 0; i < m1; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    // optimal approach using stack
    // here we will store the resultant characters in a stack
    private static void type1() {
        String s = "ab##", t = "c#d#";
        boolean ans = backspaceCompare1(s, t);
        System.out.println(ans);
    }

    private static boolean backspaceCompare1(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        Stack<Character> st1 = new Stack<>(), st2 = new Stack<>();
        // traversing on the fist array to find the resultant string
        for (char c : arr1) {
            if (c == '#') {
                if (!st1.isEmpty()) st1.pop();
            } else {
                st1.push(c);
            }
        }
        // traversing on the second array to find the resultant string
        for (char c : arr2) {
            if (c == '#') {
                if (!st2.isEmpty()) st2.pop();
            } else {
                st2.push(c);
            }
        }
        // if the length is not same then it is false
        if (st1.size() != st2.size()) return false;
        // if the content is not same then also it is false
        while (!st1.isEmpty()) {
            if (st1.pop() != st2.pop()) return false;
        }
        return true;
    }
}
