package com.problems.stack;

import com.util.PrintUtl;

import java.util.Stack;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/count-of-greater-elements-to-the-right_8365436
 *
 * Solution link :
 *
 * */
public class NumberOfGreaterElementsToTheRight {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // TODO complete this problem
    private static void type2() {
        int[] nums = {5, 2, 10, 4};
        int[] query = {0, 1};
        int n = nums.length;
        int q = query.length;
        int[] ngeCount = new int[n];
        int[] answer = new int[q];
        Stack<Integer> stack = new Stack<>();
        print(answer);
    }

    // brute force approach
    private static void type1() {
        int[] nums = {5, 2, 10, 4};
        int[] query = {0, 1};
        int n = nums.length;
        int q = query.length;
        int[] answer = new int[q];
        int qe;
        for (int x = 0; x < q; x++) {
            qe = query[x];
            for (int i = qe + 1; i < n; i++)
                if (nums[qe] < nums[i])
                    answer[x]++;
        }
        PrintUtl.print(answer);
    }
}
