package com.problems.stack;

import java.util.Stack;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/prefix-to-infix_1215000
 *
 * Solution link :
 * https://www.youtube.com/watch?v=m7SGekhd1mQ
 *
 * https://takeuforward.org/data-structure/infix-to-postfix/
 * */
public class PostfixToInfix {
    public static void main(String[] args) {
        type1();
    }

    // we will go from start,
    // whenever we encounter any operand we will push it to stack
    // if there is any operand then we pop from the stack twice, and
    // we will do the operation, and again we put it to the stack
    // at last we pop the value from the stack and print it
    private static void type1() {
        String postfix = "ab+c+";
        char[] arr = postfix.toCharArray();
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder first, second, res;
        for (char ch : arr) {
            if (Character.isLetterOrDigit(ch)) {
                stack.push(new StringBuilder().append(ch));
            } else {
                second = stack.pop();
                first = stack.pop();
                res = new StringBuilder()
                        .append('(')
                        .append(first)
                        .append(ch)
                        .append(second)
                        .append(')');
                stack.push(res);
            }
        }
        StringBuilder answer = stack.pop();
        System.out.println(answer);
    }
}
