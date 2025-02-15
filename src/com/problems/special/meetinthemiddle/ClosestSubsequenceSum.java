package com.problems.special.meetinthemiddle;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/closest-subsequence-sum
 *
 *
 * Solution link :
 * https://www.youtube.com/watch?v=naz_9njI0I0 -> Tech Dose
 *
 */
public class ClosestSubsequenceSum {

    // You are given an integer array nums and an integer goal.
    // You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal.
    // That is, if the sum of the subsequence's elements is the sum,
    // then you want to minimize the absolute difference abs(sum - goal).
    // Return the minimum possible value of abs(sum - goal).
    // Note that a subsequence of an array is an array formed by removing some elements
    // (possibly all or none) of the original array.
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // TODO man in the middle technique
    // here we will sort both first and second array
    // then we will apply two-pointer approach
    private static void type4() {
        int[] nums = {7, -9, 15, -2};
        int goal = -5;
        int ans = minAbsDifference4(nums, goal);
        System.out.println(ans);
    }

    public static int minAbsDifference4(int[] nums, int goal) {
        int n = nums.length;
        // if there is only 1 number, then we have 2 options either to choose that number or not.
        // if we don't, then the total sum will be 0 and if we do then the total sum will be nums[0]
        // so we will be taking the minimum of them
        if (n == 1) return Math.min(Math.abs(goal), Math.abs(goal - nums[0]));
        // some early optimization to return if the input is not valid
        // checking the total sum of all positive and negative numbers
        long max = 0;
        long min = 0;
        for (int num : nums)
            if (num >= 0) max += num;
            else min += num;
        // if by any ance the goal is exceeding the max sum or min sum,
        // then we know the min difference will be the distance from max/min sum to goal
        if (goal >= max) return (int) Math.abs(max - goal);
        if (goal <= min) return (int) Math.abs(min - goal);

        // Actual problem-solving starts here
        index = 0;
        int n1 = n / 2, n2 = n - n / 2;
        // creating the first half's power set
        int[] first = new int[1 << n1];
        powerSet3(0, n / 2, 0, nums, first);
        Arrays.sort(first);
        // creating the second half's power set
        index = 0;
        int[] second = new int[1 << n2];
        powerSet3(n / 2, n, 0, nums, second);
        Arrays.sort(second);

        int ans = Integer.MAX_VALUE;
        int i = 0, j = second.length - 1;

        // we will do a 2-pointer approach for finding a sum which is close to the goal

        while (i < first.length && j >= 0) {
            int sum = first[i] + second[j];
            ans = Math.min(ans, Math.abs(goal - sum));
            // if min is 0 then we will break
            if (ans == 0) break;
            // else we have two options, if the two-sum are then goal, then we will increment i else j
            if (sum < goal) i++;
            else j--;
        }
        return ans;
    }


    // TODO same as type2, but here we will generate all the sum using recursion
    private static void type3() {
        int[] nums = {7, -9, 15, -2};
        int goal = -5;
        int ans = minAbsDifference3(nums, goal);
        System.out.println(ans);
    }

    public static int minAbsDifference3(int[] nums, int goal) {
        int n = nums.length;
        // if there is only 1 number, then we have 2 options either to choose that number or not.
        // if we don't, then the total sum will be 0 and if we do then the total sum will be nums[0]
        // so we will be taking the minimum of them
        if (n == 1) return Math.min(Math.abs(goal), Math.abs(goal - nums[0]));
        // some early optimization to return if the input is not valid
        // checking the total sum of all positive and negative numbers
        long max = 0;
        long min = 0;
        for (int num : nums)
            if (num >= 0) max += num;
            else min += num;
        // if by any ance the goal is exceeding the max sum or min sum,
        // then we know the min difference will be the distance from max/min sum to goal
        if (goal >= max) return (int) Math.abs(max - goal);
        if (goal <= min) return (int) Math.abs(min - goal);

        // Actual problem-solving starts here
        index = 0;
        int n1 = n / 2, n2 = n - n / 2;
        // creating the first half's power set
        int[] first = new int[1 << n1];
        powerSet3(0, n / 2, 0, nums, first);
        // creating the second half's power set
        index = 0;
        int[] second = new int[1 << n2];
        powerSet3(n / 2, n, 0, nums, second);
        // we are sorting the power set of the 2nd half, so that we can do the binary search approach
        Arrays.sort(second);
        int ans = Integer.MAX_VALUE;
        for (int sum : first) {
            // while the sum is from the first half of the array,
            // we will try to find the target sum from the 2nd half
            int targetSum = goal - sum;
            // findClosestElement is a function that uses binary search approach and finds the closest sum
            // from the second array
            int closestSum = findClosestElement(targetSum, second);
            ans = Math.min(ans, Math.abs(targetSum - closestSum));
        }
        return ans;
    }

    private static int index = 0;

    private static void powerSet3(int i, int end, int sum, int[] nums, int[] answer) {
        if (i == end) {
            answer[index++] = sum;
            return;
        }
        powerSet3(i + 1, end, sum + nums[i], nums, answer);
        powerSet3(i + 1, end, sum, nums, answer);
    }


    // using meet in the middle approach
    private static void type2() {
        int[] nums = {7, -9, 15, -2};
        int goal = -5;
        int ans = minAbsDifference2(nums, goal);
        System.out.println(ans);
    }

    public static int minAbsDifference2(int[] nums, int goal) {
        int n = nums.length;
        // if there is only 1 number, then we have 2 options either to choose that number or not.
        // if we don't, then the total sum will be 0 and if we do then the total sum will be nums[0],
        // so we will be taking the minimum of them
        if (n == 1) return Math.min(Math.abs(goal), Math.abs(goal - nums[0]));
        // some early optimization to return if the input is not valid
        // checking the total sum of all positive and negative numbers
        long max = 0;
        long min = 0;
        for (int num : nums)
            if (num >= 0) max += num;
            else min += num;
        // if by any ance the goal is exceeding the max sum or min sum,
        // then we know the min difference will be the distance from max/min sum to goal
        if (goal >= max) return (int) Math.abs(max - goal);
        if (goal <= min) return (int) Math.abs(min - goal);

        // Actual problem-solving starts here
        // we are diving the array, and finding the power set for both of the partitions
        int[] first = powerSet2(nums, 0, n / 2 - 1, 0);
        int[] second = powerSet2(nums, n / 2, n - 1, n / 2);
        // we are sorting the power set of the 2nd half, so that we can do the binary search approach
        Arrays.sort(second);
        int ans = Integer.MAX_VALUE;
        for (int sum : first) {
            // while the sum is from the first half of the array,
            // we will try to find the target sum from the 2nd half
            int targetSum = goal - sum;
            // findClosestElement is a function that uses binary search approach and finds the closest sum
            // from the second array
            int closestSum = findClosestElement(targetSum, second);
            ans = Math.min(ans, Math.abs(targetSum - closestSum));
        }
        return ans;
    }

    // time complexity O(2^n)
    // producing power set using the bit manipulation technique
    private static int[] powerSet2(int[] nums, int start, int end, int offset) {
        int n = end - start + 1;
        int bound = 1 << n;
        int[] res = new int[bound];
        int sum;
        for (int i = 0; i < bound; ++i) {
            sum = 0;
            for (int j = 0; j < n; ++j)
                if ((i & (1 << j)) != 0)
                    sum += nums[j + offset];
            res[i] = sum;
        }
        return res;
    }

    // binary search to find the closest possible number from the array
    private static int findClosestElement(int item, int[] arr) {
        int n = arr.length;
        if (item <= arr[0]) return arr[0];
        if (item >= arr[n - 1]) return arr[n - 1];
        int low = 0, high = n - 1, mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] == item) return item;
            else if (arr[mid] < item) low = mid + 1;
            else high = mid - 1;
        }
        // at this point low exceeds high
        // the sequence is like arr[high], item, arr[low]
        // so we will calculate the difference with low and high places
        int differenceWithLow = arr[low] - item;
        int differenceWithHigh = item - arr[high];
        return differenceWithLow < differenceWithHigh ? arr[low] : arr[high];
    }


    // TODO brute force approach
    // we will generate all the possible sum
    // and while generating we will try to find the closest subset sum to the goal
    private static void type1() {
        int[] nums = {7, -9, 15, -2};
        int goal = -5;
        int answer = minAbsDifference1(nums, goal);
        System.out.println(answer);
    }

    public static int minAbsDifference1(int[] nums, int goal) {
        Data data = new Data();
        subsetSum1(0, 0, nums, data, goal);
        return data.minAbsDifference;
    }

    // generating all the sums and also checking the lowest sum
    private static void subsetSum1(int i, int sum, int[] nums, Data data, int goal) {
        if (i == nums.length) {
            data.minAbsDifference = Math.min(data.minAbsDifference, Math.abs(goal - sum));
            return;
        }
        subsetSum1(i + 1, sum + nums[i], nums, data, goal);
        subsetSum1(i + 1, sum, nums, data, goal);
    }

    static class Data {
        int minAbsDifference = Integer.MAX_VALUE;
    }

}
