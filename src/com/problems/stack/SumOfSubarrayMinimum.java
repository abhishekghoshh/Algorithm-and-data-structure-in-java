package com.problems.stack;

import java.util.Stack;

/*
 * Problem link :
 * https://leetcode.com/problems/sum-of-subarray-minimums/description/
 * https://www.naukri.com/code360/problems/sum-of-subarray-minimums_8365431
 *
 * Solution link :
 * https://www.youtube.com/watch?v=5vt98BZq_9A&list=PLVXdTUhgoFMqfRJzzdpJ5aeWnGKRgviKd&index=18
 * https://www.youtube.com/watch?v=9-TXIVEXX2w
 * https://www.youtube.com/watch?v=DA9fG6sRKsc
 * https://www.youtube.com/watch?v=aX1F2-DrBkQ
 *
 * https://neetcode.io/solutions/sum-of-subarray-minimums
 * */
public class SumOfSubarrayMinimum {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    private static final int MOD = 1000000007;

    // todo Best solution
    //  similar to the maximum area of the histogram problem
    //  just check it one more time
    //  it will calculate the range in one pass and one stack

    //  todo has some issues in leetcode, check it later
    private static void type4() {
        int[] nums = {71, 55, 82, 55};
        int sum = sumSubarrayMins4(nums);
        System.out.println(sum);
    }

    private static int sumSubarrayMins4(int[] nums) {
        long sum = 0;
        Stack<Integer> st = new Stack<>();
        // we are maintaining a monotonic increasing array here
        // like the type2 or type3, we will not do it in two different loops
        // if the stack is current 1 3 6 and the current item is 4
        // so look for stack top element we have found the range
        // in left it has 3 and in right it has 4
        // we don't need an extra loop to calculate everything
        // but this loop will run till n
        // on the last pass we will pop one by one and calculate the range
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            while (!st.isEmpty() && (i == n || nums[i] <= nums[st.peek()])) {
                int top = st.pop();
                long left = top - (st.isEmpty() ? -1 : st.peek());
                long right = i - top;
                sum += (left * right * nums[top]) % MOD;
            }
            st.push(i);
        }
        return (int) sum;
    }


    // todo same as type2 but here we will use an array as stack
    private static void type3() {
        int[] nums = {71, 55, 82, 55};
        int answer = sumSubarrayMins3(nums);
        System.out.println(answer);
    }

    private static int sumSubarrayMins3(int[] nums) {
        int n = nums.length;
        // for each value we have left and right contribution will be (Left * Right) * Element
        // Left Stack
        long[] leftRange = leftRange3(nums, n);
        long[] rightRange = rightRange3(nums, n);

        // for each value we have left and right contribution will be (Left * Right) * Element
        long res = 0;
        for (int i = 0; i < n; i++) {
            long prod = (leftRange[i] * rightRange[i]) % MOD;
            long total = nums[i] * prod;
            res = (res + total) % MOD;
        }
        return (int) res;
    }
    private static long[] leftRange3(int[] nums, int n) {
        long[] leftRange = new long[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            while (top != -1 && nums[i] < nums[stack[top]])
                top--;
            // total distance if less element not found = i+1
            // distance = i-Left_st.peek()
            leftRange[i] = (top == -1) ? (i + 1) : (i - stack[top]);
            stack[++top] = i;
        }
        return leftRange;
    }

    private static long[] rightRange3(int[] nums, int n) {
        long[] rightRange = new long[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = n - 1; i >= 0; i--) {
            while (top != -1 && nums[i] <= nums[stack[top]])
                top--;
            rightRange[i] = (top == -1) ? (n - i) : (stack[top] - i);
            stack[++top] = i;
        }
        return rightRange;
    }


    // todo optimized approach using stack
    //  rather finding the minimum element for every range
    //  if we can find if an element is minimum for how many ranges then the answer will be same
    //  I know this intuition is kind of tricky to think but if we remember this one line then the question is solved
    //  rather creating all the subarrays and then find the minimum we will find the range of subarray for every element
    //  suppose we are considering a subarray 1 3 5 2 4 6 1
    // we are finding range of subarray for element 2
    // for the element 2 the range is from 3 5 2 4 6
    // on the left it is 3 5 2 and on the right it is 2 4 6
    // so including 2 we can make 3 subarrays in left and 3 in right,
    // so now we just have the find the lower index in left and right
    // which will be the boundary, or we can say stack.peek()
    // if stack is empty then count will be i+1
    // like for 5 4 3 => for the element 3 there is element lesser in left
    // so the stack will be empty
    // on the left we can make i + 1 or 2+1=> 3 subarrays => 5 4 3 then 4 3 then only 3
    // similar on the right side
    private static void type2() {
        int[] nums = {71, 55, 82, 55};
        int answer = sumSubarrayMins2(nums);
        System.out.println(answer);
    }

    private static int sumSubarrayMins2(int[] nums) {
        int n = nums.length;
        // Left Stack
        long[] leftRange = leftRange2(nums, n);
        // Right Stack
        long[] rightRange = rightRange2(nums, n);

        // for each value we have left and right contribution will be (Left * Right) * Element
        long res = 0;
        for (int i = 0; i < n; i++) {
            long prod = (leftRange[i] * rightRange[i]) % MOD;
            long total = (nums[i] * prod) % MOD;
            res = (res + total) % MOD;
        }
        return (int) res;
    }

    // todo one thing to notice is that in the left range we have used nums[i] < nums[stack.peek()
    //  while in right range we have used nums[i] <= nums[stack.peek()
    //  ideally it should be same condition, as there can be duplicate numbers
    //  so for at least one of the stack we are using strict monotonic increasing and for another it is just increasing stack

    private static long[] leftRange2(int[] nums, int n) {
        long[] leftRange = new long[n];
        // monotonic increasing stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.empty() && nums[i] < nums[stack.peek()])
                stack.pop();
            leftRange[i] = stack.isEmpty() ? (i + 1) : i - stack.peek();
            stack.push(i);
        }
        return leftRange;
    }

    private static long[] rightRange2(int[] nums, int n) {
        long[] rightRange = new long[n];
        // monotonic increasing stack
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.empty() && nums[i] <= nums[stack.peek()])
                stack.pop();
            rightRange[i] = stack.isEmpty() ? (n - i) : stack.peek() - i;
            stack.push(i);
        }
        return rightRange;
    }



    // simple brute force using 2 loops
    // for every range we will calculate the min
    private static void type1() {
        int[] arr = {71, 55, 82, 55};
        int n = arr.length;
        int ans = sumSubarrayMins1(n, arr);
        System.out.println(ans);
    }

    private static int sumSubarrayMins1(int n, int[] arr) {
        int ans = 0;
        int min;
        for (int i = 0; i < n; i++) {
            min = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                min = Math.min(min, arr[j]);
                ans = (ans + min) % MOD;
            }
        }
        return ans;
    }

}
