package com.problems.heap;

import com.util.PrintUtl;

import java.util.*;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/replace-each-element-of-array-with-its-corresponding-rank_975384
 *
 * Solution link :
 * https://takeuforward.org/data-structure/replace-elements-by-its-rank-in-the-array/
 *
 * */
public class ReplaceElementsByItsRankInArray {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // optimized approach
    // here first we will store all the element in the heap
    // we have an advantage here
    // even if there is no guarantee that all the items will be distinct
    // but the two same items will have the same rank
    private static void type3() {
        List<Integer> arr = List.of(1, 2, 6, 9, 2);

        List<Integer> ans = new ArrayList<>();
        // first we will store all the element in the heap
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.addAll(arr);
        int k = 1;
        Map<Integer, Integer> map = new HashMap<>();
        // then one by one we will poll
        // from the heap
        // and compute its rank
        while (!heap.isEmpty()) {
            int top = heap.poll();
            // heap can have duplicate elements
            if (!map.containsKey(top)) map.put(top, k++);
        }
        for (int num : arr) ans.add(map.get(num));

        PrintUtl.print(ans);
    }

    // using min heap
    private static void type2() {
        List<Integer> arr = List.of(1, 2, 6, 9, 2);
        int n = arr.size();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(pair -> pair[1]));
        for (int i = 0; i < n; i++)
            minHeap.offer(new int[]{i, arr.get(i)});
        int[] ranks = new int[n];
        int rank = 1;
        while (!minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            ranks[pair[0]] = rank;
            // as there might be more than one item
            // with the same rank
            while (!minHeap.isEmpty() && pair[1] == minHeap.peek()[1])
                ranks[minHeap.poll()[0]] = rank;
            rank++;
        }
        List<Integer> answer = new ArrayList<>();
        for (int data : ranks) answer.add(data);

        PrintUtl.print(answer);
    }

    // brute force approach
    private static void type1() {
        List<Integer> arr = List.of(1, 2, 6, 9, 2);

    }
}
