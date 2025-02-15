package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/remove-outermost-parentheses/
 * https://www.codingninjas.com/studio/problems/maximum-nesting-depth-of-the-parentheses_8144741
 *
 * Solution is :
 *
 * */
public class RemoveOutermostParentheses {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    private static void type2() {
        String s = "(()())(())(()(()))";
        StringBuilder answer = new StringBuilder();
        int counter = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // for the starting of the parenthesis counter will be 0
                // so we will be ignoring that character
                if (counter != 0) answer.append(c);
                counter++;
            } else {
                counter--;
                // for the end of the parenthesis counter will be 0 again
                // so we will be ignoring that character
                if (counter != 0) answer.append(c);
            }
        }
        System.out.println(answer);
    }

    // brute force approach
    private static void type1() {
        String s = "(()())(())(()(()))";
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] indices = new int[n];
        int idx = 1, i = 1, opening = 1;
        while (i < n) {
            opening += arr[i] == '(' ? 1 : -1;
            if (opening == 0) {
                indices[idx++] = i;
                if (i != n - 1) indices[idx++] = i + 1;
            }
            i++;
        }
        idx = 0;
        StringBuilder answer = new StringBuilder();
        for (i = 0; i < n; i++) {
            if (indices[idx] == i) {
                idx++;
                continue;
            }
            answer.append(arr[i]);
        }
        System.out.println(answer);
    }
}
