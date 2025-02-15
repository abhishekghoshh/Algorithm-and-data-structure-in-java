package com.problems.array;

import java.util.ArrayList;
import java.util.List;


/*
 * Problem link:
 * https://leetcode.com/problems/find-the-difference-of-two-arrays/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=a4wqKR-znBE
 *
 * https://neetcode.io/solutions/find-the-difference-of-two-arrays
 */


// Tags: Array, hashing
public class FindTheDifferenceOfTwoArrays {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // 1 <= nums1.length, nums2.length <= 1000
    // -1000 <= nums1[i], nums2[i] <= 1000
    // optimized approach using hashing
    // but as we know the ranges, so we will use an array
    private static void type2() {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 4, 6};
        List<List<Integer>> ans = findDifference2(nums1, nums2);
        System.out.println(ans);
    }

    public static List<List<Integer>> findDifference2(int[] nums1, int[] nums2) {
        int N = 2001;
        int offset = 1000;
        // adding both the array to the set
        boolean[] set1 = new boolean[N];
        boolean[] set2 = new boolean[N];
        for (int num : nums1) set1[num + offset] = true;
        for (int num : nums2) set2[num + offset] = true;

        // not checking which element is present in one but not in another
        // that will be our set difference
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (set1[i] && !set2[i]) l1.add(i - offset);
            if (set2[i] && !set1[i]) l2.add(i - offset);
        }
        return List.of(l1, l2);
    }

    // brute force approach
    private static void type1() {

    }
}
