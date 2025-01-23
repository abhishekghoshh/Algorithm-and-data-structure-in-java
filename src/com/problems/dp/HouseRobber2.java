package com.problems.dp;

/*
 *
 * problem links :
 * https://leetcode.com/problems/house-robber-ii/description/
 * https://neetcode.io/problems/house-robber-ii
 * https://www.naukri.com/code360/problems/house-robber-ii_839733
 *
 * Solution link :
 * https://www.youtube.com/watch?v=3WaxQMELSkw&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=7
 * https://www.youtube.com/watch?v=rWAJCfYYOvM
 *
 * https://takeuforward.org/data-structure/dynamic-programming-house-robber-dp-6/
 * https://neetcode.io/solutions/house-robber-ii
 * */
public class HouseRobber2 {
    // the question states that the houses are in circle, and 0th house and nth house are adjacent
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we can use the same array,
    // we can just manipulate the indices rather than creating 2 new arrays
    private static void type2() {
    }

    // same as house robber 1, with a constraint that
    // if we are taking the 0th element, then we cannot take n-1 th element.
    // todo lets make the intuition, if we cut the circle it will be a straight line
    //  which makes it same question like robber 1, but if we take first then we can not the last
    //  easiest solution would be make 2 arrays nums[0,n-1) and nums[1,n)
    //  apply robber problem solution for each nums and take the max
    // so can make two arrays, one with the 0th element and another n-1 th element,
    // and we can take the max from both.
    // so 0 and n-1th will never be in the same array
    private static void type1() {
        int[] nums = {1, 2, 3, 1};
        int answer = rob(nums);
        System.out.println(answer);
    }

    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        // creating 2 individual arrays
        int[] nums1 = new int[n - 1];
        int[] nums2 = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        return Math.max(robOne(nums1), robOne(nums2));
    }

    public static int robOne(int[] nums) {
        int n = nums.length;
        int prev2 = nums[0];
        int prev = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            // taking the current home
            int takeCurrentHome = nums[i] + prev2;
            // not taking the current home
            int notTakeCurrentHome = prev;
            int curr = Math.max(takeCurrentHome, notTakeCurrentHome);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
}
