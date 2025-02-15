package com.problems.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Problem link :
 * https://leetcode.com/problems/last-stone-weight/description/
 * https://neetcode.io/problems/last-stone-weight
 *
 * Solution link :
 * https://www.youtube.com/watch?v=B-QCq79-Vfw
 *
 * https://neetcode.io/solutions/last-stone-weight
 * */
public class LastStoneWeight {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo optimized approach using heap
    private static void type2() {
        int[] stones = {2, 7, 4, 1, 8, 1};
        int ans = lastStoneWeight2(stones);
        System.out.println(ans);
    }

    public static int lastStoneWeight2(int[] stones) {
        int n = stones.length;
        if (n == 1) return stones[0];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((s1, s2) -> Integer.compare(s2, s1));
        for (int s : stones) maxHeap.offer(s);
        while (maxHeap.size() > 1) {
            int s1 = maxHeap.poll();
            int s2 = maxHeap.poll();
            if (s1 != s2) {
                maxHeap.offer(Math.abs(s1 - s2));
            }
        }
        return !maxHeap.isEmpty() ? maxHeap.peek() : 0;
    }

    // using brute force approach
    private static void type1() {
        int[] stones = {2, 7, 4, 1, 8, 1};
        int ans = lastStoneWeight1(stones);
        System.out.println(ans);
    }

    public static int lastStoneWeight1(int[] stones) {
        int n = stones.length;
        while (n > 1) {
            Arrays.sort(stones);
            stones[n - 2] = stones[n - 1] - stones[n - 2];
            n--;
        }
        return stones[0];
    }
}
