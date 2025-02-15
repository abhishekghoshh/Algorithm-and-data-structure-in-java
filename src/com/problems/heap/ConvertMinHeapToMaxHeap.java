package com.problems.heap;

import com.util.PrintUtl;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/implement-a-priority-queue-_1743878
 *
 * Solution link :
 *
 *
 * */
public class ConvertMinHeapToMaxHeap {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int[] minHeap = {1, 2, 3, 6, 7, 8};
        int n = minHeap.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(minHeap, i);
        }
        PrintUtl.print(minHeap);
    }

    private static void maxHeapify(int[] minHeap, int index) {
        int item = minHeap[index];
        int targetIndex = index;
        int len = minHeap.length;
        int left = 2 * index + 1, right = 2 * index + 2;
        if (left < len && minHeap[targetIndex] < minHeap[left]) targetIndex = left;
        if (right < len && minHeap[targetIndex] < minHeap[right]) targetIndex = right;
        if (targetIndex != index) {
            int temp = minHeap[targetIndex];
            minHeap[targetIndex] = item;
            minHeap[index] = temp;
            maxHeapify(minHeap, targetIndex);
        }
    }
}
