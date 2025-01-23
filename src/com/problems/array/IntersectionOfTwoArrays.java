package com.problems.array;

import java.util.HashMap;
import java.util.Map;

import static com.util.PrintUtl.print;
/*
 *
 * problem links :
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=fwUTXaMom6U
 *
 * https://neetcode.io/solutions/intersection-of-two-arrays
 * */


// Tags : Array, Hashing
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // same as previous approach
    // but here we will use int array instead of hashmap as it is easy to use,
    // and we know the max range of the input which is 1000
    // we could use an int array
    // we iterate through the first array and mark the number as 1
    // then we will go to second array and mark the character with 2
    // but only for the number which is previously marked as 1, it will help us to discard the duplicates
    // we could also use a boolean array and instead of marking 2 we could mark that to false again
    private static void type3() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] ans = intersection3(nums1, nums2);
        print(ans);
    }

    public static int[] intersection3(int[] nums1, int[] nums2) {
        int N = 1000;
        int[] map = new int[N + 1];
        // marking the numbers as 1
        for (int num : nums1) {
            map[num] = 1;
        }
        // marking the characters as 2 only for the character which was one before
        // also counting as we need to store the result in a different array
        int count = 0;
        for (int num : nums2) {
            if (map[num] == 1) {
                count++;
                map[num] = 2;
            }
        }
        // iterating through the array and saving the answer in array
        int[] ans = new int[count];
        int idx = 0;
        for (int num = 0; num <= N; num++) {
            if (map[num] == 2) ans[idx++] = num;
        }
        return ans;
    }

    // optimized approach
    // a classic example of hashing
    // we will use a hashmap
    // we iterate through the first array and mark the number as 1
    // then we will go to second array and mark the character with 2
    // but only for the number which is previously marked as 1, it will help us to discard the duplicates
    private static void type2() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] ans = intersection2(nums1, nums2);
        print(ans);
    }

    public static int[] intersection2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        // marking the numbers as 1
        for (int num : nums1) {
            map.put(num, 1);
        }
        // marking the characters as 2 only for the character which was one before
        // also counting as we need to store the result in a different array
        int count = 0;
        for (int num : nums2) {
            if (map.getOrDefault(num, 0) == 1) {
                count++;
                map.put(num, 2);
            }
        }
        // iterating through the array and saving the answer in array
        int[] ans = new int[count];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), f = entry.getValue();
            if (f == 2) ans[idx++] = num;
        }
        return ans;
    }

    // brute force approach
    private static void type1() {

    }
}
