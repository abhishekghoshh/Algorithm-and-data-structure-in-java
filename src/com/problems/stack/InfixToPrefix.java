package com.problems.stack;

import java.util.Stack;
/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/prefix-to-infix_1215000
 *
 * Solution link :
 * https://www.youtube.com/watch?v=snDDBjT8jYA
 *
 * https://takeuforward.org/data-structure/infix-to-prefix/
 * */
public class InfixToPrefix {
    public static void main(String[] args) {
        type1();
    }

    // we will do this in four steps
    // first we will reverse the string
    // then we will change ( to ) and ) to (
    // then we will calculate the postfix expression,
    // then again we will reverse the postfix expression
    private static void type1() {
        String infix = "(p+q)*(c-d)";
        char[] arr = infix.toCharArray();
        reverse(arr);
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == '(') arr[i] = ')';
            else if (arr[i] == ')') arr[i] = '(';
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder answer = new StringBuilder();
        for (char ch : arr) {
            if (Character.isLetterOrDigit(ch)) answer.append(ch);
            else if (ch == '(') stack.push(ch);
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') answer.append(stack.pop());
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) answer.append(stack.pop());
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) answer.append(stack.pop());
        String ans = answer.reverse().toString();
        System.out.println(ans);
    }

    private static void reverse(char[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            char ch = arr[start];
            arr[start] = arr[end];
            arr[end] = ch;
            start++;
            end--;
        }
    }

    static int precedence(char ch) {
        if (ch == '+' || ch == '-') return 1;
        else if (ch == '*' || ch == '/') return 2;
        else if (ch == '^') return 3;
        return -1;
    }
}
