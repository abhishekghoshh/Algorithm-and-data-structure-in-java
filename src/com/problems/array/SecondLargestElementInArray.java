package com.problems.array;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/ninja-and-the-second-order-elements_6581960
 *
 * Solution link :
 * https://www.youtube.com/watch?v=37E9ckMDdTk&t=811s
 *
 * https://takeuforward.org/data-structure/find-second-smallest-and-second-largest-element-in-an-array/
 * */
public class SecondLargestElementInArray {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // Using two variables
    private static void type2() {
        int[] arr = {1, 2, 4, 6, 7, 5};
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int item : arr) {
            if (item > max1) {
                max2 = max1;
                max1 = item;
            } else if (item > max2) {
                max2 = item;
            }
            if (item < min1) {
                min2 = min1;
                min1 = item;
            } else if (item < min2) {
                min2 = item;
            }
        }
        System.out.println(min1 + " , " + max2);
    }

    // using sort
    private static void type1() {
        int[] arr = {1, 2, 4, 6, 7, 5};
        int n = arr.length;
        Arrays.sort(arr);
        System.out.println(arr[1] + " , " + arr[n - 2]);
    }
}
