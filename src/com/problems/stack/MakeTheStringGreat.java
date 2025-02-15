package com.problems.stack;

import java.util.Stack;
/*
 *
 * problem links :
 * https://leetcode.com/problems/make-the-string-great/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=10tBWNjzvtw
 *
 * https://neetcode.io/solutions/make-the-string-great
 * */

// Tags : Array, Stack
public class MakeTheStringGreat {
    public static void main(String[] args) {
        type1();
        type2();
    }


    // using an array
    //  almost the same as previous.
    // we will use the same array to store the characters,
    // so we will use a prev variable here
    // in place changing
    private static void type2() {
        String s = "leEeetcode";
        String ans = makeGood2(s);
        System.out.println(ans);
    }

    private static String makeGood2(String s) {
        int diff = 'a' - 'A';
        char[] arr = s.toCharArray();
        // instead of stack, we will use a variable
        int prev = -1;
        for (char ch : arr) {
            if (prev != -1 && Math.abs(arr[prev] - ch) == diff)
                prev--;
            else
                arr[++prev] = ch;
        }
        // we will use a string builder to store the result
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= prev; i++) sb.append(arr[i]);
        return sb.toString();
    }

    // brute force using stack
    // we could use an array as stack, though.
    // we will check the last character of stack if the difference is 'a' - 'A'
    // then it is the same letter but different case, so we will pop it
    private static void type1() {
        String s = "leEeetcode";
        String ans = makeGood1(s);
        System.out.println(ans);
    }


    public static String makeGood1(String s) {
        int diff = 'a' - 'A';
        char[] arr = s.toCharArray();
        Stack<Character> st = new Stack<>();
        for (char ch : arr) {
            if (!st.isEmpty() && Math.abs(st.peek() - ch) == diff)
                st.pop();
            else
                st.push(ch);
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) sb.append(st.pop());
        sb.reverse();
        return sb.toString();
    }
}
