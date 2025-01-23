package com.problems.segmenttree;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
 * https://www.spoj.com/problems/INVCNT/
 *
 * Solution link :
 * https://www.geeksforgeeks.org/inversion-count-in-array-using-merge-sort/
 * https://www.interviewbit.com/blog/count-inversions-of-an-array/
 * https://www.javatpoint.com/inversion-count
 *
 */
public class InversionCountWithNegativeIntegers {
    // see the last 30 minutes of codebeyond channel segment tree masterclass part 1
    // this problem can also be solved using the merge sort approach.
    // but here we will use the segment tree to solve, but the time complexity will be a little higher.
    // we can also use fenwick tree to solve this
    // we have already solved this problem for the positive numbers, but here we will expand it to the negative integers
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we have two approaches we can either use 2 segment trees
    // one for holding the negative numbers, one for holding the positive numbers
    // we will use only one segment tree here
    // we will take the max and min, where min will be in negative,
    // so we will use - min as offset and shift all the number by offset
    // todo check the InversionCount problem
    private static void type2() {
        int[] nums = {2, 5, -1, 3, 4, -1, -5, 4, -3, 3, 10, 2};
        int n = nums.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        // first we will be calculating the max number and will create an array for holding all of this
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int offset = -min;
        int[] freq = new int[max + offset + 1];
        for (int num : nums) freq[num + offset]++;
        // now we will use a sum segment tree to calculate the range frequency sums
        int count = 0;
        SumSegmentTree.SegmentTree segmentTree = new SumSegmentTree.SegmentTree(freq);
        for (int i = 0; i < n; i++) {
            // first we will decrease the number frequency because we are currently operating on it,
            // and it will not be required for the future calculations
            int num = nums[i] + offset;
            freq[num]--;
            // we will update a segment tree with current frequency and query range frequency sum for (0,num-1)
            segmentTree.update(num, freq[num]);
            count += segmentTree.sum(0, num - 1);
        }
        System.out.println(count);
    }


    // brute force
    // time complexity O(n^2)
    // space complexity O(1)
    private static void type1() {
        int[] nums = {2, 5, -1, 3, 4, -1, -5, 4, -3, 3, 10, 2};
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) count++;
            }
        }
        System.out.println("Count is " + count);
    }
}
