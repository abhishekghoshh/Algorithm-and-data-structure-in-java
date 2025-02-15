package com.problems.special.meetinthemiddle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Problem link :
 * https://cses.fi/problemset/task/1628/
 *
 * Solution link :
 *
 *
 * https://codeforces.com/blog/entry/95571
 */
public class SubsetSum {
    // we have already solved the subset sum using power set, recursion and dynamic programming approach,
    // but here the constraints are quite different
    // if the size of the array is too large then we cannot perform power set approach
    // if n <= 32
    // then power set approach will take 2^n which will be 2^32
    // generally 2^27 gives tle so if we use power set on array of size 32 then it will give us TLE
    // in this scenario meet in the middle will be used
    // if we can split the array, compute the sums and then check it using a set then we will not hit TLE
    public static void main(String[] args) {
        type1();
        type2();
    }

    // this is exactly the previous type
    // here we have to find the count the subsets
    private static void type2() {
        int[] arr = {1, 2, 3};
        int x = 3;
        int ans = countSubsets(arr, x);
        System.out.println(ans);
    }

    static int countSubsets(int[] arr, int x) {
        int n = arr.length;
        int count = 0;
        // Split the array into two halves
        int mid = n / 2;
        int[] firstHalf = new int[1 << mid];
        int[] secondHalf = new int[1 << (n - mid)];
        // Calculate all possible subset sums for the first half
        generateSubsets(arr, 0, mid, firstHalf);
        // Calculate all possible subset sums for the second half
        generateSubsets(arr, mid, n, secondHalf);
        // Sort the second half array
        Arrays.sort(secondHalf);
        // For each subset sum in the first half, find the number of subset sums in the second half that add up to x
        for (int sum : firstHalf) {
            count += countOccurrences(secondHalf, x - sum);
        }
        return count;
    }

    // we will use power set approach to generate all the subsets
    static void generateSubsets(int[] arr, int start, int end, int[] subsetSums) {
        int n = subsetSums.length;
        for (int num = 0; num < n; num++) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                int mask = 1 << (i - start);
                if ((num & mask) != 0) {
                    sum += arr[i];
                }
            }
            subsetSums[num] = sum;
        }
    }

    // we will use binary search approach to find the first and last occurrences of the number then return the count
    public static int countOccurrences(int[] arr, int target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);
        // If the target is not found, return 0
        if (first == -1) return 0;
        // Count of occurrences is last index - first index + 1
        return last - first + 1;
    }


    public static int firstOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int firstOccurrence = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                firstOccurrence = mid;
                right = mid - 1; // Search for the leftmost occurrence
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return firstOccurrence;
    }

    public static int lastOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int lastOccurrence = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                lastOccurrence = mid;
                left = mid + 1; // Search for the rightmost occurrence
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return lastOccurrence;
    }



    // we will split the array and use the power set approach to compute all the subsets for the both partition arrays,
    // then we will check if there is any subset whose sum is equal to X.
    // for simplicity n will be even number
    private static void type1() {
        int[] arr = {1, 2, 1, 3, 2, 4, 5, 8};
        int x = 18;
        boolean ans = hasSubsetSum1(arr, x);
        System.out.println(ans);
    }

    // here the time complexity is 2 * O(2^n/2) for generating all the subset sum
    // and in the worst possible scenario log(2^n/2) for adding the item in the set and searching it,
    //  so the total is 2 * 2^(n/2) * log(2^n/2)
    // in other words n * 2^(n/2)
    // if n is 32 then it will be 32 * 2^16 => 2^21, so we will not get any TLE in the code submission
    private static boolean hasSubsetSum1(int[] arr, int x) {
        int n = arr.length;
        int size = n / 2, offset = n - n / 2, bits = n / 2;
        int N = 1 << size;
        // first, we are creating all subset sums on the first half of the array,
        // and it that to the set, also checking if any of the sum is equal to x then we will return
        Set<Integer> set = new HashSet<>();
        for (int k = 0; k < N; k++) {
            int sum = 0, num = k;
            for (int b = 0; b < bits; b++) {
                int bit = (num & 1);
                num = num >> 1;
                if (bit == 1) sum += arr[b];
            }
            // we can also skip this as in the following loop there will be a subset sum of 0
            if (sum == x) return true;
            set.add(sum);
        }
        // now we will find the subset sum for the remaining portion of the array
        // and check if the reminder is present in the set or not
        for (int k = 0; k < N; k++) {
            int sum = 0, num = k;
            for (int b = 0; b < bits; b++) {
                int bit = (num & 1);
                num = num >> 1;
                // using offset here
                if (bit == 1) sum += arr[b + offset];
            }
            // we can also skip this as in the previous loop there will be a subset sum of 0
            if (sum == x) return true;
            if (set.contains(x - sum)) return true;
        }
        return false;
    }
}
