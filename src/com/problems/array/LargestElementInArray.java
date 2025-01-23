package com.problems.array;

import com.util.ArrayUtil;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/largest-element-in-the-array-largest-element-in-the-array_5026279
 *
 * Solution link :
 * https://www.youtube.com/watch?v=37E9ckMDdTk&t=531s
 *
 * https://takeuforward.org/data-structure/find-the-largest-element-in-an-array/
 * */
public class LargestElementInArray {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // Using in place
    // time complexity O(n)
    private static void type2() {
        int[] arr = {2, 5, 1, 3, 0};
        int max = Integer.MIN_VALUE;
        for (int item : arr) max = Math.max(max, item);
        System.out.println(max);
    }

    // Using sorting
    // time complexity O(nlog(n))
    private static void type1() {
        int[] arr = {2, 5, 1, 3, 0};
        int[] copy = ArrayUtil.copy(arr);
        Arrays.sort(copy);
        System.out.println(copy[copy.length - 1]);
    }
}
