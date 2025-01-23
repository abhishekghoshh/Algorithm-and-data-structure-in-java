package com.problems.binarysearch;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1
 * https://www.codingninjas.com/studio/problems/ceiling-in-a-sorted-array_1825401
 *
 * Solution link :
 * https://www.youtube.com/watch?v=6zhGS79oQ4k&t=1374s
 * https://www.youtube.com/watch?v=5cx0xerA8XY&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=10
 * https://www.youtube.com/watch?v=uiz0IxPCUeU&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=11
 *
 * https://takeuforward.org/arrays/floor-and-ceil-in-sorted-array/
 * */
public class FloorAndCeilInSortedArray {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
    }

    private static void type1() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12};
        int target = 10;
        int floor = floor(arr, target);
        int ceil = ceil(arr, target);
        int[] answer = new int[]{
                floor != -1 ? arr[floor] : -1,
                ceil != -1 ? arr[ceil] : -1,
        };
        print(answer);
    }

    private static int ceil(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int index = -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= target) {
                if (arr[mid] == target) return mid;
                index = mid;
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            }
        }
        return index;
    }

    private static int floor(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int index = -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] <= target) {
                if (arr[mid] == target) return mid;
                index = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return index;
    }
}
