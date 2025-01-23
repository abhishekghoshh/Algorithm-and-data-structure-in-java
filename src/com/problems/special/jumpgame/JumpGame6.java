package com.problems.special.jumpgame;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
 *
 * problem links :
 * https://leetcode.com/problems/jump-game-vi/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=LiEcBMK5PQs&list=PLJtzaiEpVo2yaP5v5bq0-QJgU0lO3TrEi&index=6
 *
 * https://github.com/Algorithms-Made-Easy/Leetcode-Challenge/blob/main/1696.%20Jump%20Game%20VI
 * */
public class JumpGame6 {
    // todo check the solutions
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // this is the clever and efficient approach
    // this is quite a clever approach though it is having its own disadvantages which is based on the inputs
    // the time complexity may vary
    private static void type4() {
        int[] nums = {10, -5, -2, 4, 0, 3};
        int k = 3;
        int n = nums.length;
        // we will initialize a dp for storing all the sums
        int[] dp = new int[n];
        // for index 0 the max and sum is nums[0] and max index is 0
        dp[0] = nums[0];
        int max = nums[0];
        int maxIndex = 0;
        // we will start from 1
        for (int i = 1; i < n; i++) {
            int left = Math.max(0, i - k);
            // we will check if the current max index is less than k or not
            // which means it is out of range or not
            // if it is out of range then we will calculate the max again and for the range
            if (maxIndex < left) {
                max = Integer.MIN_VALUE;
                for (int j = left; j < i; j++) {
                    if (max <= dp[j]) {
                        max = dp[j];
                        maxIndex = j;
                    }
                }
            }
            // we will add the num with the current max either it is freshly calculated or precalculated
            dp[i] = max + nums[i];
            // we will also check if the sum for the current index is greater than the max
            // then we will update the max and max index
            if (dp[i] >= max) {
                max = dp[i];
                maxIndex = i;
            }
        }
        System.out.println(dp[n - 1]);
    }

    // optimized from the previous
    // ultimately it is a sliding window problem, this is similar to the problem of find max in every window of k
    // everytime we need to find the maximum element in almost constant time,
    // so we will use a Deque for this
    private static void type3() {
        int[] nums = {10, -5, -2, 4, 0, 3};
        int k = 3;
        int ans = maxResult3(nums, k);
        System.out.println(ans);
    }

    public static int maxResult3(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        // we will initialize dp[0] with num[0]
        dp[0] = nums[0];
        Deque<Integer> deque = new LinkedList<>();
        // we will initialize deque with 0
        deque.add(0);
        // we will store index in a decreasing manner,
        // so every time the queue peek will have the maximum value
        for (int i = 1; i < n; i++) {
            // remove numbers out-of-range k as i-k th element is from the last window
            // as the current range is from i-k+1 to i
            if (!deque.isEmpty() && deque.peek() < i - k) deque.poll();
            // as we are storing in a decreasing manner to peek will give the highest element
            dp[i] = nums[i] + dp[deque.peek()];
            // so currently the queue is having decreasing sequence from rear to front
            // and front has lowest, so to maintain this decreasing nature we need to remove element from front
            // if they are smaller than the current item or dp[i]
            while (!deque.isEmpty() && nums[deque.peekLast()] < dp[i])
                deque.pollLast();
            deque.add(i);
        }
        return dp[n - 1];
    }

    // it is better than the previous one
    // rather than checking in a loop if by somehow we could check little fast then we can save some time.
    // we will use a maxHeap to find the maximum item, how do we know it is in the index and index-k range
    // for that reason we will also store the index
    // if the top element is out of range then we will pop from queue
    // time complexity is O(n*log(k))
    private static void type2() {
        int[] nums = {10, -5, -2, 4, 0, 3};
        int k = 3;
        int ans = maxResult2(nums, k);
        System.out.println(ans);
    }

    public static int maxResult2(int[] nums, int k) {
        int n = nums.length;
        int sum = nums[0];
        // a max heap
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        // we will store the pair of (start,starting value)
        maxHeap.offer(new int[]{0, nums[0]});
        for (int i = 1; i < n; i++) {
            // if the top element is out of range then we will poll it
            while (!maxHeap.isEmpty() && i - maxHeap.peek()[0] > k)
                maxHeap.poll();
            // else we will take the top and from the heap
            int[] pair = maxHeap.peek();
            sum = nums[i] + pair[1];
            // and everytime we will store the current sum to the
            maxHeap.offer(new int[]{i, sum});
        }
        return sum;
    }

    // brute force approach
    // for every index we will check its previous k indices
    // and take the maximum
    // time complexity is O(n*k)
    // space complexity is O(n)
    private static void type1() {
        int[] nums = {10, -5, -2, 4, 0, 3};
        int k = 3;
        int ans = maxResult1(nums, k);
        System.out.println(ans);
    }

    public static int maxResult1(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        // for the first index it has no previous index, so we will set num[0] to dp[0]
        dp[0] = nums[0];
        // we will start from the index 1
        for (int i = 1; i < n; i++) {
            int max = Integer.MIN_VALUE;
            // we are checking the left boundary
            int boundary = Math.max(i - k, 0);
            // iterate and take the maximum
            for (int j = i - 1; j >= boundary; j--)
                max = Math.max(max, dp[j]);
            // setting the value to the dp[i]
            dp[i] = nums[i] + max;
        }
        return dp[n - 1];
    }

}
