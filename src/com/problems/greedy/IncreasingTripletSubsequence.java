package com.problems.greedy;

/*
 * Problem link :
 * https://leetcode.com/problems/increasing-triplet-subsequence
 *
 * Solution link :
 * https://www.youtube.com/watch?v=-tqUs4Qt9TU
 * https://www.youtube.com/watch?v=yEFlGWOVH8g
 * */
public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        int[] nums = {1, 5, 0, 6, 4};
        boolean answer = increasingTriplet(nums);
        System.out.println(answer);
    }

    //TODO
    // see the youtube video
    // explain yourself
    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int minOne = Integer.MAX_VALUE, minTwo = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= minOne) minOne = num;
            else if (num <= minTwo) minTwo = num;
            else return true;
        }
        return false;
    }

    // brute force approach
    private static void type1() {

    }
}
