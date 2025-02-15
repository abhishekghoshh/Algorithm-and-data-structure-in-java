package com.problems.array;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/
 *
 * Solution link :
 *
 * Tags :
 * */
public class AdjacentIncreasingSubarraysDetection2 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // like previous but here we will not use another list to store the sequences
    private static void type2() {
        List<Integer> nums = List.of(2, 5, 7, 8, 9, 2, 3, 4, 3, 1);
        int ans = maxIncreasingSubarrays2(nums);
        System.out.println(ans);
    }

    private static int maxIncreasingSubarrays2(List<Integer> nums) {
        int n = nums.size();
        if (n == 2) return 1;
        int max = 0;
        // using prev and current for storing previous indices
        // here we will use 0th index as prev value else we have to treat with null values
        // (0, -1) seq means it has size 0
        Seq prevSeq = new Seq(0, -1), currSeq = new Seq(0, 0);
        int prev = nums.get(0);
        for (int i = 1; i < n; i++) {
            int curr = nums.get(i);
            // if current is greater than prev then we will extend the current seq else we will create a new seq
            if (prev < curr) {
                currSeq.end++;
            } else {
                // assigning current to previous
                prevSeq.start = currSeq.start;
                prevSeq.end = currSeq.end;
                // initializing new current seq
                currSeq.start = currSeq.end = i;
            }
            // we are considering current seq as it can hold 2 increasing sequences
            max = Math.max(max, currSeq.size() / 2);
            // now we are checking current with previous
            max = Math.max(max, Math.min(prevSeq.size(), currSeq.size()));
            prev = curr;
        }
        return max;
    }

    // directly going to the optimized version
    // time complexity O(2n) space complexity O(n)
    // here we will first find and store all the increasing sequence's index and store their starting and ending
    // then we will loop through the index and check the current and next window
    private static void type1() {
        List<Integer> nums = List.of(2, 5, 7, 8, 9, 2, 3, 4, 3, 1);
        int ans = maxIncreasingSubarrays1(nums);
        System.out.println(ans);
    }

    public static int maxIncreasingSubarrays1(List<Integer> nums) {
        int n = nums.size();
        if (n == 2) return 1;
        int max;
        List<Seq> seqList = new ArrayList<>();
        // storing the increasing sequences index
        int prev = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int curr = nums.get(i);
            if (prev < curr) {
                seqList.get(seqList.size() - 1).end++;
            } else {
                seqList.add(new Seq(i, i));
            }
            prev = curr;
        }
        // now we have two options, either a treat the current sequence as an individual seq
        // where we can divide the sequence as 2 equal size sequences and then both will be increasing subsequences
        // and k will be seq size divided by 2,
        // or we can check current seq, and it's previous sequence, they are already continuous
        // take a minimum size from both seq sizes and take the minimum as k
        int s = seqList.size();
        Seq prevSeq = seqList.get(0);
        max = prevSeq.size() / 2;
        for (int i = 1; i < s; i++) {
            Seq currSeq = seqList.get(i);
            // current sequence as an individual seq
            max = Math.max(max, currSeq.size() / 2);
            // checking the current and prev seq
            int minSeqSize = Math.min(prevSeq.size(), currSeq.size());
            max = Math.max(max, minSeqSize);
            // assigning current to the prev
            prevSeq = currSeq;
        }
        return max;
    }

    static class Seq {
        int start, end;

        public Seq(int start, int end) {
            this.start = start;
            this.end = end;
        }

        int size() {
            return end - start + 1;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
}
