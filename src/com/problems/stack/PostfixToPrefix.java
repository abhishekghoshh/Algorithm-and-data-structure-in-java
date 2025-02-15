package com.problems.stack;

import java.util.Stack;

/*
 *
 * problem links :
 * https://www.naukri.com/code360/problems/postfix-to-prefix_1788455
 *
 * Solution link :
 * https://www.youtube.com/watch?v=smQ88h1qzQY
 *
 *
 * */
public class PostfixToPrefix {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String exp = "ab+cd-*";
        char[] arr = exp.toCharArray();
        Stack<String> stack = new Stack<>();
        String first, second, res;
        for (char ch : arr) {
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                second = stack.pop();
                first = stack.pop();
                res = ch + first + second;
                stack.push(res);
            } else stack.push("" + ch);
        }
        System.out.println(stack.peek());
    }

}
