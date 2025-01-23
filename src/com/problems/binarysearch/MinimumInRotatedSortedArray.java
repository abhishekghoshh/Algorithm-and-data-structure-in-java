package com.problems.binarysearch;

/*
 * Problem link :
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 * https://neetcode.io/problems/find-minimum-in-rotated-sorted-array
 * https://www.naukri.com/code360/problems/rotated-array_1093219
 *
 * Solution link :
 * https://www.youtube.com/watch?v=nhEMDKMB44g
 * https://www.youtube.com/watch?v=4WmTRFZilj8&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=7
 *
 * Blogs :
 * https://takeuforward.org/data-structure/minimum-in-rotated-sorted-array/
 *
 * Tags :
 * Binary-Search
 * */
public class MinimumInRotatedSortedArray {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // binary search
    // similar to the type 2 with some additional if-clause
    // todo explain type2 in the interview
    private static void type4() {
        int[] arr = {4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3};
        int ans = findMin4(arr);
        System.out.println(ans);
    }

    // binary search approach
    private static int findMin4(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            //search space is already sorted
            //then arr[low] will always be
            //the minimum in that search space:
            if (arr[low] <= arr[high]) {
                ans = Math.min(ans, arr[low]);
                break;
            }
            //if left part is sorted:
            if (arr[low] <= arr[mid]) {
                // keep the minimum:
                ans = Math.min(ans, arr[low]);
                // Eliminate left half:
                low = mid + 1;
            } else {
                //if right part is sorted:=
                // keep the minimum:
                ans = Math.min(ans, arr[mid]);
                // Eliminate right half:
                high = mid - 1;
            }
        }
        return ans;
    }

    // binary search
    // similar to the type 2 with some additional if-clause
    // todo explain type2 in the interview
    private static void type3() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2, 3};
        int low = findMin3(nums);
        System.out.println(low);
    }

    // binary search approach
    private static int findMin3(int[] nums) {
        int n = nums.length;
        int low = 0, high = n - 1;
        int next, prev, mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            next = next(mid, n);
            prev = prev(mid, n);

            // todo terminating condition
            // if the mid is less than its next and prev, that means it is the starting element
            if (nums[prev] >= nums[mid] && nums[mid] <= nums[next]) {
                low = mid;
                break;
            } else if (nums[low] <= nums[mid] && nums[mid] <= nums[high]) {
                // low to high portion is sorted, so the answer will be low we can break here
                break;
            }

            // todo actual computation
            if (nums[low] <= nums[mid]) {
                // it is on the left side low to mid-elements are sorted
                low = mid + 1;
            } else {
                // it is on the right side mid to high elements are sorted
                high = mid - 1;
            }
        }
        return nums[low];
    }

    // binary search approach
    // todo explain this in the interview
    private static void type2() {
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};
        int ans = findMin2(arr);
        System.out.println(ans);
    }

    private static int findMin2(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            //if left part is sorted, the subarray is like [...2,4,5,6..]
            if (arr[low] <= arr[mid]) {
                // keep the minimum:
                ans = Math.min(ans, arr[low]);
                // Eliminate left half:
                low = mid + 1;
            } else {
                //if right part is sorted, the subarray is like [...8,9,10,11..]
                // keep the minimum:
                ans = Math.min(ans, arr[mid]);
                // Eliminate right half:
                high = mid - 1;
            }
        }
        return ans;
    }


    // brute force
    private static void type1() {
        int[] nums = {7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6};
        int ans = findMin1(nums);
        System.out.println(ans);
    }

    private static int findMin1(int[] nums) {
        int n = nums.length, next, prev, index = -1;
        for (int i = 0; i < n; i++) {
            next = next(i, n);
            prev = prev(i, n);
            if (nums[i] < nums[next] && nums[i] < nums[prev]) {
                index = i;
            }
        }
        return index;
    }

    private static int prev(int i, int n) {
        return (i - 1 + n) % n;
    }

    private static int next(int i, int n) {
        return (i + 1) % n;
    }
}
