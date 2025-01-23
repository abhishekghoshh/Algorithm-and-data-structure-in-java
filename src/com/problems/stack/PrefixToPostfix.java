package com.problems.stack;

import java.util.Stack;
/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/convert-prefix-to-postfix_8391014
 *
 * Solution link :
 * https://www.youtube.com/watch?v=jZxII0guwUo
 *
 *
 * */
public class PrefixToPostfix {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String s = "-/A+BC*DE";
        char[] arr = s.toCharArray();
        reverse(arr);
        Stack<String> stack = new Stack<>();
        String first, second, res;
        for (char ch : arr) {
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                first = stack.pop();
                second = stack.pop();
                res = first + second + ch;
                stack.push(res);
            } else stack.push("" + ch);
        }
        System.out.println(stack.peek());
    }

    private static void reverse(char[] arr) {
        int i = 0, j = arr.length - 1;
        char ch;
        while (i < j) {
            ch = arr[i];
            arr[i] = arr[j];
            arr[j] = ch;
            i++;
            j--;
        }
    }
}
