package com.problems.binarysearch;

/*
 *
 * problem links :
 * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/
 * https://www.codingninjas.com/studio/problems/smallest-divisor-with-the-given-limit_1755882
 *
 * Solution link :
 * https://www.youtube.com/watch?v=UvBKTVaG6U8
 *
 * https://takeuforward.org/arrays/find-the-smallest-divisor-given-a-threshold/
 * */
public class FindTheSmallestDivisorGivenThreshold {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // binary search on answer approach
    private static void type2() {
        int[] nums = {21212, 10101, 12121};
        int threshold = 1000000;
        int max = Integer.MIN_VALUE, answer = Integer.MAX_VALUE;
        for (int bloom : nums) if (bloom > max) max = bloom;
        int low = 1, high = max, mid, divisorSum;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            divisorSum = 0;
            for (int num : nums) divisorSum += (num / mid + (num % mid == 0 ? 0 : 1));
            if (divisorSum <= threshold) {
                if (mid < answer) answer = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        System.out.println(answer);
    }

    // brute force approach
    private static void type1() {

    }
}
