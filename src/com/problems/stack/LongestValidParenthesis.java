package com.problems.stack;

import java.util.Arrays;
import java.util.Stack;

/*
 *
 * problem links :
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=VdQuwtEd10M&t=648s&pp=ygUZTG9uZ2VzdCBWYWxpZCBQYXJlbnRoZXNlcw%3D%3D
 * https://www.youtube.com/watch?v=qC5DGX0CPFA
 *
 * https://github.com/Algorithms-Made-Easy/Leetcode-Challenge/blob/main/32.%20Longest%20Valid%20Parentheses
 * */

// Tags : Array, String, Dynamic Programming
public class LongestValidParenthesis {
    public static void main(String[] args) {
        type1();
        type2();
        type3(); // most optimal approach
        type4();
    }

    // this is too complex to implement in an interview
    // This is an example of dynamic programming
    // once we find any () we are trying to explore it's left side and right side
    // we can surely explore on right side but to exploration on left is tricky
    // so, we will do one thing once we find any balanced parenthesis we will save the right index
    // and left index to an array
    // and for left side exploration we will try to check if there is any ending of balanced parenthesis
    // on the left side, if we find any then we will change left to starting of left side
    // balanced parenthesis
    private static void type4() {
        String s = ")(((((()())()()))()(()))(";
        char[] chArr = s.toCharArray();
        int n = chArr.length;
        int left = 0, right = 1;
        int[] leftMemo = new int[n];
        Arrays.fill(leftMemo, -1);
        int maxLen = 0;
        while (right < n) {
            // we will try to expand once we find any ()
            if (chArr[left] == '(' && chArr[right] == ')') {
                while (left >= 0 && right < n) {
                    // left side exploration
                    if (left > 0 && leftMemo[left - 1] != -1) {
                        left = leftMemo[left - 1];
                    } else if (right + 2 < n && chArr[right + 1] == '(' && chArr[right + 2] == ')') {
                        right += 2;
                    } else if (left >= 1 && right + 1 < n && chArr[left - 1] == '(' && chArr[right + 1] == ')') {
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }
                leftMemo[right] = left;
                maxLen = Math.max(maxLen, right - left + 1);
                left = right + 1;
                right = right + 2;
            } else {
                left++;
                right++;
            }
        }
        System.out.println(maxLen);
    }

    // this is the most optimal approach
    // time complexity is O(2n)
    // space complexity is O(1)
    private static void type3() {
        String s = ")(((((()())()()))()(()))(";
        char[] chArr = s.toCharArray();
        int n = chArr.length;
        int open = 0, close = 0;
        int max = 0;
        // exploration from left to right
        for (char c : chArr) {
            if (c == '(') open++;
            else close++;
            if (open == close)
                max = Math.max(max, open + close);
            else if (close > open)
                open = close = 0;

        }

        // exploration from right to left
        open = close = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (chArr[i] == '(') open++;
            else close++;
            if (open == close)
                max = Math.max(max, open + close);
            else if (open > close)
                open = close = 0;
        }
        System.out.println(max);
    }

    // TODO explain code walkthrough later
    private static void type2() {
        String s = ")(((((()())()()))()(()))(";
        char[] chArr = s.toCharArray();
        int n = chArr.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (chArr[i] == '(') {
                st.push(i);
            } else {
                st.pop();
                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    int len = i - st.peek();
                    max = Math.max(max, len);
                }
            }
        }
        System.out.println(max);
    }

    // brute force approach
    // time complexity is O(n^3)
    // it will get time time limit exceeds
    // We will check for every (i,j) if it is a valid parenthesis or not
    // and get the max out of all pairs
    // but here is a problem
    // if we have checked for an i,j then we don't have to check for a pair inside (i,j)
    // that is purely redundant
    private static void type1() {
        String s = ")(((((()())()()))()(()))(";
        char[] chArr = s.toCharArray();
        int n = chArr.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > 0 && j - i >= 1; j--)
                if (isValidParenthesis(chArr, i, j))
                    max = Math.max(max, j - i + 1);
        }
        System.out.println(max);
    }

    // time complexity is O(N)
    private static boolean isValidParenthesis(char[] chArr, int start, int end) {
        if ((end - start + 1) % 2 != 0 || chArr[start] == ')' || chArr[end] == '(')
            return false;
        int openBraces = 0;
        for (int i = start; i <= end; i++) {
            if (chArr[i] == '(') {
                openBraces++;
            } else {
                if (openBraces > 0) openBraces--;
                else return false;
            }
        }
        return openBraces == 0;
    }
}
