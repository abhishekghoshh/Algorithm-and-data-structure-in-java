package com.problems.array;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=cQ1Oz4ckceM
 *
 * https://neetcode.io/solutions/two-sum-ii-input-array-is-sorted
 * */
public class TwoSum2InputArrayIsSorted {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimal approach
    // if the array is already sorted then we can just use 2 pointer on the start and the end
    // and take the sum of numbers[end] and numbers[start] and then take the difference
    // if the difference is less than 0 that means we have to increase the sum,
    // which can only be achieved by increasing start pointer, if the diff is greater than 0 then we need to decrease the sum
    // if the diff is 0 then we will return the ans
    private static void type2() {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] ans = twoSum2(numbers, target);
        print(ans);
    }

    public static int[] twoSum2(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int diff = numbers[end] + numbers[start] - target;
            if (diff == 0) {
                return new int[]{start + 1, end + 1};
            } else if (diff > 0) {
                end--;
            } else {
                start++;
            }
        }
        return new int[]{-1, -1};
    }

    // brute force approach
    private static void type1() {
    }
}
