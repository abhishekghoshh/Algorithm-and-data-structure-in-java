package com.problems.special.meetinthemiddle;

/*
 * Problem link :
 *
 * Solution link :
 *
 *
 */
public class FindStrictlyIncreasingTriplets {
    public static void main(String[] args) {
        type1();
    }

    // we can also solve it using merge sort approach in nlogn time
    // meet in the middle problem
    private static void type1() {
        int[] nums = {1, 4, 5, 9, 3, 7, 0, 8, 10};
        int n = nums.length;
        int count = 0;
        for (int i = 1; i < n - 1; i++) {
            int smallerCount = 0, biggerCount = 0;
            // we are checking all the smaller number from the left side
            for (int l = i - 1; l >= 0; l--) {
                if (nums[i] > nums[l]) smallerCount++;
            }
            // if left is 0 then we do not need to check more for the number
            if (smallerCount == 0) continue;
            // we are checking all the bigger numbers from the right side
            for (int r = i + 1; r < n; r++) {
                if (nums[i] < nums[r]) biggerCount++;
            }
//            System.out.println(nums[i] + " " + smallerCount + " " + biggerCount);
            // the answer for the current index will be the left*right
            count += smallerCount * biggerCount;
        }
        System.out.println(count);
    }
}
