package com.problems.stack;

import java.util.Arrays;
import java.util.Stack;
/*
 * Problem link :
 * https://leetcode.com/problems/sum-of-subarray-ranges/
 * https://www.codingninjas.com/studio/problems/subarray-range-sum_8365419
 *
 * Solution link :
 * https://www.youtube.com/watch?v=HyzeCKUkRS0&list=PLVXdTUhgoFMqfRJzzdpJ5aeWnGKRgviKd&index=19
 */
public class SumOfSubarrayRanges {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // TODO check it later
    // took it from the leetcode's best solution for the problem
    private static void type4() {
        int[] nums = {3, 1, 2, 4};

        long sum = 0;
        int n = nums.length, top = -1;
        int[] stk = new int[n];
        long[] dp = new long[n];
        for (int i = 0; i < n; i++) {
            while (top > -1 && nums[stk[top]] > nums[i]) {
                top--;
            }
            if (top == -1) {
                dp[i] = (long) (i + 1) * ((long) nums[i]);
            } else {
                dp[i] = (long) (i - stk[top]) * ((long) nums[i]) + dp[stk[top]];
            }
            sum = sum - dp[i];
            stk[++top] = i;
        }
        Arrays.fill(dp, 0);
        top = -1;
        for (int i = 0; i < n; i++) {
            while (top > -1 && nums[stk[top]] < nums[i]) {
                top--;
            }
            if (top == -1) {
                dp[i] = (long) (i + 1) * ((long) nums[i]);
            } else {
                dp[i] = (long) (i - stk[top]) * ((long) nums[i]) + dp[stk[top]];
            }
            sum = sum + dp[i];
            stk[++top] = i;
        }
        System.out.println(sum);
    }

    // TODO best solution
    // explain it in the interview
    // same as type2
    // here we will use an array as stack
    private static void type3() {
        int[] nums = {3, 1, 2, 4};

        int n = nums.length;
        long sum = 0;
        int[] stack = new int[n];
        int top = -1;
        // Smallest
        for (int i = 0; i <= nums.length; i++) {
            while (top != -1 && (i == nums.length || nums[i] <= nums[stack[top]])) {
                int topIndex = stack[top--];
                long left = topIndex - (top == -1 ? -1 : stack[top]);
                long right = i - topIndex;
                sum -= left * right * nums[topIndex];
            }
            stack[++top] = i;
        }
        // Largest
        top = -1;
        for (int i = 0; i <= nums.length; i++) {
            while (top != -1 && (i == nums.length || nums[i] >= nums[stack[top]])) {
                int topIndex = stack[top--];
                long left = topIndex - (top == -1 ? -1 : stack[top]);
                long right = i - topIndex;
                sum += left * right * nums[topIndex];
            }
            stack[++top] = i;
        }
        System.out.println(sum);
    }

    // optimized approach
    // using the stack
    // if we have a fair understanding of the subarray minimum and subarray maximum
    // then understanding of this problem is easy,
    // so rather thinking about the individual minimum and maximum for every range
    // we can get the sum of minimum and sum of maximum
    // we will use the single pass to calculate subarray the smallest range
    // and the subarray largest range
    // if you still not understood then see Sum of mSubarray minimum last approach
    private static void type2() {
        int[] nums = {3, 1, 2, 4};

        long sum = 0;
        Stack<Integer> stack = new Stack<>();
        // Smallest
        for (int i = 0; i <= nums.length; i++) {
            while (!stack.isEmpty() && (i == nums.length || nums[i] <= nums[stack.peek()])) {
                int top = stack.pop();
                long left = top - (stack.isEmpty() ? -1 : stack.peek());
                long right = i - top;
                sum -= left * right * nums[top];
            }
            stack.push(i);
        }
        // Largest
        stack.clear();
        for (int i = 0; i <= nums.length; i++) {
            while (!stack.isEmpty() && (i == nums.length || nums[i] >= nums[stack.peek()])) {
                int top = stack.pop();
                long left = top - (stack.isEmpty() ? -1 : stack.peek());
                long right = i - top;
                sum += left * right * nums[top];
            }
            stack.push(i);
        }
        System.out.println(sum);
    }

    // brute force approach
    // time complexity O(n^2)
    private static void type1() {
        int[] nums = {3, 1, 2, 4};
        int n = nums.length;
        long ans = 0;
        int min, max;
        for (int i = 0; i < n; i++) {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                ans += (max - min);
            }
        }
        System.out.println(ans);
    }
}
