package com.problems.slidingwindow;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/frequency-of-the-most-frequent-element/description/
 *
 * Solution link :
 * Neetcode
 * https://www.youtube.com/watch?v=vgBrQ0NM5vE
 * Aryan Mittal :
 * https://www.youtube.com/watch?v=e0AFPpKcjw0
 *
 * https://takeuforward.org/arrays/find-the-highest-lowest-frequency-element/
 * https://neetcode.io/solutions/frequency-of-the-most-frequent-element
 */

public class FrequencyOfTheMostFrequentElement {
    public static void main(String[] args) {
        type1();
    }

    // todo using the sliding window approach
    //  time complexity O(nlog(n) + 2n)
    //  the intuition is kind of greedy
    // lets say if the nums array is [1,1,1,3,3,5]
    // and we are operating on 5 currently, and we need to choose other
    // numbers to convert them into 5, if we change 1 into 5 it will take 4 unit
    // whereas for 3 it will take only 2,
    // so we will try to change the closest element possible only
    // lets first sort the array then
    // now we will apply sliding window on this.
    // let's say we are on j th element, we will assume that the left side numbers
    // will be converted into this, if the left point is on i
    // so the total range is (j-i+1)
    // and our criteria will be (nums[j] * (j - i + 1)) - range-sum(j - i + 1) <= k
    // if it is less than equal to k then it is possible to convert all the numbers in that range
    // else we have to increment left till it does not fit into the criteria
    // we will also need to carry the range sum
    private static void type1() {
        int[] nums = {1, 4, 8, 13};
        int k = 5;
        int ans = maxFrequency1(nums, k);
        System.out.println(ans);
    }

    public static int maxFrequency1(int[] nums, int k) {
        // we need to sort at first
        Arrays.sort(nums);
        int n = nums.length;

        long sum = 0;
        int max = 0;
        // we will go from 0 to n and choose current number to be target number for all the left side numbers
        for (int right = 0, left = 0; right < n; right++) {
            // adding the current element in the range sum
            long num = nums[right];
            sum += num; // updating the sum
            // if num * range - range sum > k then we can not transform all the numbers to the current num
            // so we will decrease from left and shrink the window
            while (num * (right - left + 1) - sum > k) {
                // decrementing the left most element from that range
                sum -= nums[left++];
            }
            // checking the max frequency
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
