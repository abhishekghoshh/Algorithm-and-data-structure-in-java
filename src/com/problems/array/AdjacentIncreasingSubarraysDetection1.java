package com.problems.array;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/
 *
 * Solution link :
 *
 * Tags :
 * */
public class AdjacentIncreasingSubarraysDetection1 {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // more optimized
    // but inspired from the previous type
    private static void type3() {
        List<Integer> nums = List.of(2, 5, 7, 8, 9, 2, 3, 4, 3, 1);
        int k = 3;
        boolean ans = hasIncreasingSubarrays3(nums, k);
        System.out.println(ans);
    }

    private static boolean hasIncreasingSubarrays3(List<Integer> nums, int k) {
        int n = nums.size();
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
            // if curr seq itself can make 2 k length seq then we will return true
            if (currSeq.size() / 2 >= k) return true;
            // if the prev and current both sequences has at least k elements
            // then we know than we will be able to make k
            if (prevSeq.size() >= k && currSeq.size() >= k) return true;
            prev = curr;
        }
        return false;
    }

    // optimized approach
    // we will store all the indices of the increasing subsequences in a list
    private static void type2() {
        List<Integer> nums = List.of(2, 5, 7, 8, 9, 2, 3, 4, 3, 1);
        int k = 3;
        boolean ans = hasIncreasingSubarrays2(nums, k);
        System.out.println(ans);
    }

    private static boolean hasIncreasingSubarrays2(List<Integer> nums, int k) {
        int n = nums.size();
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
        // now we will check from the consequent Seq and check their size if equal to k
        Seq prevSeq = null;
        for (Seq currSeq : seqList) {
            // if curr seq itself can make 2 k length seq then we will return true
            if (currSeq.size() / 2 >= k) return true;
            // if the prev and current both sequences has at least k elements
            // then we know than we will be able to make k
            if (prevSeq != null
                    && prevSeq.size() >= k && currSeq.size() >= k) {
                return true;
            }
            prevSeq = currSeq;
        }
        return false;
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

    // brute force solution
    // we are checking 1st k window and 2nd k window
    // if both window satisfies increasing condition then we will return true
    private static void type1() {
        List<Integer> nums = List.of(2, 5, 7, 8, 9, 2, 3, 4, 3, 1);
        int k = 3;
        boolean ans = hasIncreasingSubarrays1(nums, k);
        System.out.println(ans);
    }

    public static boolean hasIncreasingSubarrays1(List<Integer> nums, int k) {
        int n = nums.size();
        int end = n - 2 * k;
        for (int i = 0; i <= end; i++) {
            // initializing the window
            int w1 = i, w2 = i + k, count = 1;
            if (w1 >= n || w2 >= n) break;
            // checking for the window
            while (count < k) {
                if (nums.get(w1) >= nums.get(w1 + 1)) break;
                if (nums.get(w2) >= nums.get(w2 + 1)) break;
                w1++;
                w2++;
                count++;
            }
            if (count == k) return true;
        }
        return false;
    }
}
