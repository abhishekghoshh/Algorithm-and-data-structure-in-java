package com.problems.hashing;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem link :
 * https://leetcode.com/problems/contains-duplicate/description/
 * https://neetcode.io/problems/duplicate-integer
 *
 * Solution link :
 *
 * https://takeuforward.org/data-structure/contains-duplicate-check-if-a-value-appears-atleast-twice/
 *
 * */

// Tags : Array, Hashing
public class ContainsDuplicate {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int[] nums = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        boolean ans = containsDuplicate1(nums);
        System.out.println(ans);
    }

    public static boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            // set add returns false that means item is already present
            if (!set.add(num)) return true;
        }
        return false;
    }
}
