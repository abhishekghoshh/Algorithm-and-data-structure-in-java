package com.problems.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Problem link :
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
 * https://neetcode.io/problems/kth-largest-integer-in-a-stream
 * https://www.naukri.com/code360/problems/kth-largest-element-in-a-stream_800301
 *
 * Solution link :
 * https://www.youtube.com/watch?v=hOjcdrqMoQ8
 *
 * https://neetcode.io/solutions/kth-largest-element-in-a-stream
 * */
public class KthLargestElementInStream {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // todo best approach using min heap
    // we will maintain a min heap of k elements
    // every time we add anything we will also add into the heap
    private static void type3() {
        int[] nums = {4, 5, 8, 2};
        KthLargest3 kthLargest = new KthLargest3(3, nums);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }

    static class KthLargest3 {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int k;

        public KthLargest3(int k, int[] nums) {
            for (int num : nums) {
                minHeap.offer(num);
                if (minHeap.size() > k) minHeap.poll();
            }
            this.k = k;
        }

        public int add(int val) {
            minHeap.offer(val);
            if (minHeap.size() > k) minHeap.poll();
            return minHeap.peek();
        }
    }

    // todo insertion method from the insertion sort
    // we will maintain a sorted list
    private static void type2() {
        int[] nums = {4, 5, 8, 2};
        KthLargest2 kthLargest = new KthLargest2(3, nums);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }

    static class KthLargest2 {
        List<Integer> list = new ArrayList<>();
        int k;

        public KthLargest2(int k, int[] nums) {
            for (int num : nums) list.add(num);
            Collections.sort(list);
            this.k = k;
        }

        public int add(int val) {
            int n = list.size() - 1;
            while (n >= 0) {
                if (list.get(n) < val) break;
                n--;
            }
            list.add(n + 1, val);
            return list.get(list.size() - k);
        }
    }

    // todo Brute force approach
    private static void type1() {
        int[] nums = {4, 5, 8, 2};
        KthLargest1 kthLargest = new KthLargest1(3, nums);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }

    static class KthLargest1 {
        List<Integer> list = new ArrayList<>();
        int k;

        public KthLargest1(int k, int[] nums) {
            for (int num : nums) list.add(num);
            this.k = k;
        }

        public int add(int val) {
            list.add(val);
            Collections.sort(list);
            return list.get(list.size() - k);
        }
    }
}
