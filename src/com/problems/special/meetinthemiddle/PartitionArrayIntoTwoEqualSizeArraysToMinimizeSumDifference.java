package com.problems.special.meetinthemiddle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Problem link:
 * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/
 *
 *
 * Solution link:
 * https://www.youtube.com/watch?v=wKeGeBN3u18 → Tech dose
 * https://www.youtube.com/watch?v=45TkgKy0GS0 → Aryan Mittal
 * https://www.youtube.com/watch?v=JUFHwaZjO_M → Happy Engineering With Anmol
 * https://www.youtube.com/watch?v=R9n_Hq2YDhs → Programming Live with Larry
 */
public class PartitionArrayIntoTwoEqualSizeArraysToMinimizeSumDifference {

    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // check other leetcode solutions for some more optimizations,
    // but the core logic will be the same
    private static void type4() {

    }

    // using meet in the middle approach with the 2-pointer approach
    private static void type3() {
        int[] nums = {38, 41, 97, 41, 59, 48, 55, 88};
        int ans = minimumDifference3(nums);
        System.out.println(ans);
    }

    private static int minimumDifference3(int[] nums) {
        int n = nums.length / 2;
        int sum = 0;
        for (int num : nums) sum += num;
        List<Integer>[] part1 = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) part1[i] = new ArrayList<>();
        List<Integer>[] part2 = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) part2[i] = new ArrayList<>();

        // power set approach to find all the sum but here
        // we will also store the sums along with how many numbers required making that sum
        int N = (1 << n);
        for (int num = 0; num < N; num++) {
            int p1 = 0, p2 = 0, count = 0;
            for (int d = 0; d < n; d++) {
                int bit = (num >> d) & 1;
                if (bit == 1) {
                    count++;
                    p1 += nums[d];
                    p2 += nums[n + d];
                }
            }
            part1[count].add(p1);
            part2[count].add(p2);
        }

        // unlike the last, we will sort both part and do a two pointer
        // as we will only process 1st n/2 from part1 and last n/2 from part2,
        // then we will apply the 2-pointer
        for (int i = 0; i <= n / 2; i++) {
            Collections.sort(part1[i]);
            Collections.sort(part2[n - i]);
        }

        int ans = Integer.MAX_VALUE;
        // taking the ceil if the total sum is negative
        int targetSum = (int) Math.ceil((double) sum / 2);
        // now we will use binary search to find the closest
        for (int i = 0; i <= n / 2; i++) {
            // we will calculate all the sum for part1[i] and part2[n-i] and take the minimum
            List<Integer> list1 = part1[i], list2 = part2[n - i];
            int s1 = list1.size(), s2 = list2.size();
            // first pointer at 0th element on the first array and
            // second pointer is at last element on the 2nd array
            int low = 0, high = s2 - 1;
            // applying the 2-pointer approach
            while (low < s1 && high >= 0) {
                int closestSum = list1.get(low) + list2.get(high);
                ans = Math.min(ans, Math.abs(sum - 2 * closestSum));
                // if the sum is odd, then we cannot directly return 0, we have to return 1
                // so generalizing the return value as abs(sum - 2 * closestSum)
                if (closestSum == targetSum) return Math.abs(sum - 2 * closestSum);
                if (closestSum < targetSum) low++;
                else high--;
            }
        }
        return ans;
    }

    // using meet in the middle approach with the binary search approach
    private static void type2() {
        int[] nums = {2, -1, 0, 4, -2, -9};
        int ans = minimumDifference2(nums);
        System.out.println(ans);
    }

    // optimized approach
    private static int minimumDifference2(int[] nums) {
        int n = nums.length / 2;
        int sum = 0;
        for (int num : nums) sum += num;
        List<Integer>[] part1 = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) part1[i] = new ArrayList<>();
        List<Integer>[] part2 = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) part2[i] = new ArrayList<>();

        // power set approach to find all the sum but here
        // we will also store the sums along with how many numbers required making that sum
        int N = (1 << n);
        for (int num = 0; num < N; num++) {
            int p1 = 0, p2 = 0, count = 0;
            for (int d = 0; d < n; d++) {
                int bit = (num >> d) & 1;
                if (bit == 1) {
                    count++;
                    p1 += nums[d];
                    p2 += nums[n + d];
                }
            }
            part1[count].add(p1);
            part2[count].add(p2);
        }

        // if we can sort the right side, at least the last n/2 elements
        // as we will only process 1st n/2 from part1 and last n/2 from the par2
        // then we will apply the binary search
        for (int i = n / 2; i <= n; i++) Collections.sort(part2[i]);

        int ans = Integer.MAX_VALUE;
        // now we will use binary search to find the closest
        for (int i = 0; i <= n / 2; i++) {
            // we will calculate all the sum for part1[i] and part2[n-i] and take the minimum
            for (int p1 : part1[i]) {
                int targetP2 = sum / 2 - p1;
                int closestP2 = findClosestElement(targetP2, part2[n - i]);
                int sum1 = p1 + closestP2;
                ans = Math.min(ans, Math.abs(sum - 2 * sum1));
            }
        }
        return ans;
    }

    // binary search to find the closest possible number from the array
    private static int findClosestElement(int item, List<Integer> arr) {
        int n = arr.size();
        if (item <= arr.get(0)) return arr.get(0);
        if (item >= arr.get(n - 1)) return arr.get(n - 1);
        int low = 0, high = n - 1, mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            int midElem = arr.get(mid);

            if (midElem == item) return item;
            else if (midElem < item) low = mid + 1;
            else high = mid - 1;
        }
        // at this point low exceeds high
        // the sequence is like arr[high], item, arr[low]
        // so we will calculate the difference with low and high places
        int differenceWithLow = arr.get(low) - item;
        int differenceWithHigh = item - arr.get(high);
        return differenceWithLow < differenceWithHigh ? arr.get(low) : arr.get(high);
    }

    // TODO Brute force with Man in the middle technique
    private static void type1() {
        int[] nums = {2, -1, 0, 4, -2, -9};
        int ans = minimumDifference1(nums);
        System.out.println(ans);
    }

    // so we will divide the array into 2 equal parts
    // and build a power set for both parts,
    // but for this time along with the sums, we will also store
    // how many numbers are taken to produce the sum.
    // we will do this for both parts.
    // so for part1 there will be a map of 0=>{0},...n={sum}
    // same for part2 we will have a map of 0=>{0},...n={sum}
    // now we will produce sum1 by adding part1[i] + part2[n-i]
    // and sum2 will be totalSum - sum
    public static int minimumDifference1(int[] nums) {
        int n = nums.length / 2;
        int sum = 0;
        for (int num : nums) sum += num;
        List<Integer>[] part1 = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) part1[i] = new ArrayList<>();
        List<Integer>[] part2 = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) part2[i] = new ArrayList<>();

        // power set approach to find all the sum but here
        // we will also store the sums along with how many numbers required making that sum
        int N = (1 << n);
        for (int num = 0; num < N; num++) {
            int p1 = 0, p2 = 0, count = 0;
            for (int d = 0; d < n; d++) {
                int bit = (num >> d) & 1;
                if (bit == 1) {
                    count++;
                    p1 += nums[d];
                    p2 += nums[n + d];
                }
            }
            part1[count].add(p1);
            part2[count].add(p2);
        }

        int ans = Integer.MAX_VALUE;
        // we do not need to go till n
        // let's take an example [a1,b1,c1,d1] and [a2,b2,c2,d2]
        // if we only check for 1 num from the left and 3 num from the right
        // then one occurrence s1 = a1 + b2 + c2 + d2 and then s2 will be b1 + c1 + d1 + a2
        // now if we consider 3 items from the left and 1 from the right
        // then one occurrence will be s1 = b1 + c1 + d1 + a2 and then s2 will be a1 + b2 + c2 + d2
        // so essentially after n/2 cases will be duplicate
        for (int i = 0; i <= n / 2; i++) {
            // we will calculate all the sum for part1[i] and part2[n-i] and take the minimum
            for (int p1 : part1[i]) {
                for (int p2 : part2[n - i]) {
                    int sum1 = p1 + p2;
                    ans = Math.min(ans, Math.abs(sum - 2 * sum1));
                    if (ans == 0) return ans;
                }
            }
        }
        return ans;
    }
}
