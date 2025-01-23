package com.problems.stack;

import java.util.Stack;
/*
 * Problem link :
 *
 * Solution link :
 *
 *
 *
 * */

// Tags : Stack, Array
public class BaseballGame {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // using an array
    // same as the previous type
    private static void type2() {
        String[] ops = {"5", "2", "C", "D", "+"};
        int ans = calPoints2(ops);
        System.out.println(ans);
    }

    private static int calPoints2(String[] ops) {
        int n = ops.length;
        int top = -1;
        int[] st = new int[n];
        for (String op : ops) {
            if ("+".equals(op)) {
                int first = st[top];
                int second = st[top - 1];
                st[++top] = first + second;
            } else if ("D".equals(op)) {
                int last = st[top];
                st[++top] = 2 * last;
            } else if ("C".equals(op)) {
                top--;
            } else {
                st[++top] = Integer.parseInt(op);
            }
        }
        int sum = 0;
        for (int i = 0; i <= top; i++) sum += st[i];
        return sum;
    }

    //  Optimized approach using stack
    private static void type1() {
        String[] ops = {"5", "2", "C", "D", "+"};
        int ans = calPoints1(ops);
        System.out.println(ans);

    }

    public static int calPoints1(String[] ops) {
        Stack<Integer> st = new Stack<>();
        for (String op : ops) {
            if ("+".equals(op)) {
                int first = st.pop();
                int second = st.peek();
                st.push(first);
                st.push((first + second));
            } else if ("D".equals(op)) {
                st.push(2 * st.peek());
            } else if ("C".equals(op)) {
                st.pop();
            } else {
                st.push(Integer.parseInt(op));
            }
        }
        int sum = 0;
        while (!st.isEmpty()) sum += st.pop();
        return sum;
    }
}
