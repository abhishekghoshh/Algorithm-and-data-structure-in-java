package com.problems.binarysearch;

/*
 *
 * problem links :
 * https://leetcode.com/problems/kth-missing-positive-number/description/
 * https://www.codingninjas.com/studio/problems/kth-missing-element_893215
 *
 * Solution link :
 * https://www.youtube.com/watch?v=uZ0N_hZpyps
 *
 * https://takeuforward.org/arrays/kth-missing-positive-number/
 * */
public class KthMissingPositiveNumber {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // binary search on answer approach
    // ideally in ith index i+1 item should be placed
    // nums[i]-(i+1) should be should but for our scenario
    // it will have some value
    // that value will be the count of missing number on the left
    // so will loop through until we find k missing element
    private static void type3() {
        int[] nums = {2, 3, 4, 7, 11};
        int k = 5;
        int n = nums.length;

        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int missing = nums[mid] - (mid + 1);
            if (missing < k) low = mid + 1;
            else high = mid - 1;
        }
        int answer = k + high + 1;
        System.out.println(answer);
    }

    // brute force approach
    // if we already start with k
    // and for every number we will shift the k
    // if the gap is too big that means k will be placed
    // in between, so we will break
    private static void type2() {
        int[] nums = {2, 3, 4, 7, 11};
        int k = 5;
        for (int num : nums)
            if (num <= k) k++; //shifting k
            else break;
        System.out.println(k);
    }

    // brute force approach
    // lets say example of k=4
    // arr is 1 3 5 6 10
    // if we take the variable answer
    // we have to shift the variable by k
    // and skip for the number found in the array
    private static void type1() {
        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;
        int ptr = 0, answer = 0, counter = 0, n = arr.length;
        int max = arr[n - 1];
        for (int i = 1; i < max; i++) {
            if (arr[ptr] == i) {
                ptr++;
            } else {
                counter++;
                if (counter == k) {
                    answer = i;
                    break;
                }
            }
        }
        if (counter < k) answer = max + (k - counter);
        System.out.println(answer);
    }
}
