package com.problems.stack;

import java.util.Stack;

/*
 * problem links :
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
 * https://neetcode.io/problems/evaluate-reverse-polish-notation
 *
 * Solution link :
 * https://www.youtube.com/watch?v=iu0082c4HDE
 *
 * https://neetcode.io/solutions/evaluate-reverse-polish-notation
 * */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo simple intuition if there is an operator we will pop twice
    //  and we will use the operator with the popped elements and we will push the result in the stack
    //  else we will push the element in the stack
    private static void type2() {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int ans = evalRPN2(tokens);
        System.out.println(ans);
    }

    public static int evalRPN2(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[n];
        int top = -1;
        for (String token : tokens) {
            if ("+".equals(token)) {
                int num1 = stack[top--], num2 = stack[top--];
                stack[++top] = num2 + num1;
            } else if ("*".equals(token)) {
                int num1 = stack[top--], num2 = stack[top--];
                stack[++top] = num2 * num1;
            } else if ("/".equals(token)) {
                int num1 = stack[top--], num2 = stack[top--];
                stack[++top] = num2 / num1;
            } else if ("-".equals(token)) {
                int num1 = stack[top--], num2 = stack[top--];
                stack[++top] = num2 - num1;
            } else {
                stack[++top] = Integer.parseInt(token);
            }
        }
        return stack[top];
    }

    // todo simple intuition if there is an operator we will pop twice
    //  and we will use the operator with the popped elements and we will push the result in the stack
    //  else we will push the element in the stack
    private static void type1() {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int ans = evalRPN1(tokens);
        System.out.println(ans);
    }

    public static int evalRPN1(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+".equals(token)) {
                int num1 = stack.pop(), num2 = stack.pop();
                stack.push(num2 + num1);
            } else if ("*".equals(token)) {
                int num1 = stack.pop(), num2 = stack.pop();
                stack.push(num2 * num1);
            } else if ("/".equals(token)) {
                int num1 = stack.pop(), num2 = stack.pop();
                stack.push(num2 / num1);
            } else if ("-".equals(token)) {
                int num1 = stack.pop(), num2 = stack.pop();
                stack.push(num2 - num1);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
