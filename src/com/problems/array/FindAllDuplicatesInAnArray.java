package com.problems.array;


import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Y8x0iAVEITo
 *
 * https://neetcode.io/solutions/find-all-duplicates-in-an-array
 * */

// Tags : Arrays, swap sort
public class FindAllDuplicatesInAnArray {
    // You must write an algorithm that runs in O(n) time and uses only constant auxiliary space,
    // excluding the space needed to store the output
    public static void main(String[] args) {
        type1();
    }

    // todo there is no brute force for this solution
    //  as there is constraint already given so there is only one possible solution (swap sort)
    private static void type1() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> ans = findDuplicates(nums);
        System.out.println(ans);
    }

    public static List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        int i = 0;
        // todo swap sort
        while (i < n) {
            int num = nums[i];
            // if (nums[i] == i + 1) then that mean nums[i] has the correct number
            // nums[num - 1] == num means nums[num - 1] has already the num
            // if we have a example like this 1 2 3 4 2
            // for i=1 this condition holds => nums[i] == i + 1
            // for i=4 this condition hold => nums[num - 1] == num) as there is already 2 in that place
            // and current 2 at 5th index is a duplicate item
            if (num == i + 1 || nums[num - 1] == num) {
                i++;
            } else {
                // ideally nums[num - 1] should hold num,
                // so we will place num in its correct place which is num-1
                // so will copy the value of nums[num - 1] to nums[i]
                nums[i] = nums[num - 1];
                nums[num - 1] = num;
            }
        }
        PrintUtl.print(nums);
        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) list.add(nums[i]);
        }
        return list;
    }

}
