package com.problems.hashing;
/*
 * Problem link :
 * https://leetcode.com/problems/contains-duplicate-ii/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ypn0aZ0nrL4
 *
 * https://neetcode.io/solutions/contains-duplicate-ii
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Tags : Array, Hashing, Sliding window
public class ContainsDuplicate2 {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // we could also use a sliding window approach. however, that is not the most optimized approach
    // we could first maintain a window of size k using a hashset.
    // and we will slide through the nums array
    // if there is a repetition of any number, then we will return true;
    private static void type3() {
    }


    // similar to the previous type, but here we will not store the entire list.
    // we understand one thing we need only prev index for the current num
    // if (i-prev) is lesser than equal to k then we will return true.
    // so we will iterate over the array and store the current index
    // if there is already an index present then that must be the prev index for the current num
    private static void type2() {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        boolean ans = containsNearbyDuplicate2(nums, k);
        System.out.println(ans);
    }

    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            // if the num is already present, then we will check (i-prev) value
            if (map.containsKey(num) && (i - map.get(num)) <= k)
                return true;
            // updating the index for the num
            map.put(num, i);
        }
        return false;
    }

    // brute force
    // we can just store a list of indices for a particular number
    // if the indices list size is 1 then we can skip
    // else we will check what is the difference between two adjacent index values.
    // because if the difference between then is greater than k
    // then we any other index combination will not work either.
    // we will take a previous variable and iterate over the list and update the prev variable
    private static void type1() {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        boolean ans = containsNearbyDuplicate1(nums, k);
        System.out.println(ans);
    }

    public static boolean containsNearbyDuplicate1(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        // storing the indices list
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (!map.containsKey(num))
                map.put(num, new ArrayList<>());
            map.get(num).add(i);
        }
        // iterating over the list of indices
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> indices = entry.getValue();
            if (indices.size() == 1) continue;
            int prev = -(k + 1);
            for (int curr : indices) {
                // if the difference is lesser than equal to k, then we will return true
                if (curr - prev <= k) return true;
                prev = curr;
            }
        }
        return false;
    }
}
