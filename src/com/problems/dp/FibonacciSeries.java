package com.problems.dp;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/nth-fibonacci-number_74156
 *
 *
 * Solution link :
 * https://www.youtube.com/watch?v=tyB0ztf0DNY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=2
 *
 * https://takeuforward.org/data-structure/dynamic-programming-introduction/
 * */

import java.util.Arrays;

public class FibonacciSeries {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // using tabulation with memory optimization
    // Time complexity O(n)
    // space complexity O(1)
    private static void type4() {
        int n = 10;
        int prev2 = 0, prev = 1, curr;
        for (int i = 2; i <= n; i++) {
            // current is sum of previous and previous to previous
            curr = prev + prev2;
            // after the sum, we will update prev2 and then prev
            prev2 = prev;
            prev = curr;
        }
        System.out.println(prev);
    }

    // using tabulation
    // Time complexity O(n)
    // space complexity O(n) for array
    private static void type3() {
        int n = 10;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        memo[1] = 1;
        // same as previous f(i) = f(i-1) + f(i-2)
        // but as it is bottom up approach,
        // we will start from the lowest input possible
        // we will go till n
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        System.out.println(memo[n]);
    }

    // using memoization
    // Time complexity O(n)
    // space complexity O(n) for stack space
    private static void type2() {
        int n = 10;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        int answer = fib(n, memo);
        System.out.println(answer);
    }

    private static int fib(int n, int[] memo) {
        if (n <= 1) return n;
        // checking if the recursion call is already happened or not
        if (memo[n] != -1) return memo[n];
        // before returning, we will also save the answer
        return memo[n] = fib(n - 1) + fib(n - 2);
    }

    // using Recursion
    // Time complexity O(2^n)
    // space complexity O(n) for stack space
    private static void type1() {
        int n = 10;
        int answer = fib(n);
        System.out.println(answer);
    }


    private static int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1)
                + fib(n - 2);
    }
}
