package com.problems.heap;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/implement-a-priority-queue-_1743878
 *
 * Solution link :
 *
 *
 * */
public class ImplementPriorityQueue {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        List<Integer> heap = new ArrayList<>();
        Heap.push(heap, 3);
        System.out.println(Heap.pop(heap));
    }

    static class Heap {
        public static int pop(List<Integer> heap) {
            // Write you code here.
            if (heap.isEmpty()) return -1;
            int item = heap.get(0);
            int size = heap.size();
            int last = heap.get(size - 1);
            heap.remove(size - 1);
            if (heap.isEmpty()) return item;
            heap.set(0, last);
            heapify(heap, 0);
            return item;
        }

        private static void heapify(List<Integer> heap, int index) {
            int item = heap.get(index);
            int size = heap.size();
            int targetIndex = index;
            int left = 2 * index + 1, right = 2 * index + 2;
            if (left < size && heap.get(targetIndex) < heap.get(left)) targetIndex = left;
            if (right < size && heap.get(targetIndex) < heap.get(right)) targetIndex = right;
            if (targetIndex != index) {
                int temp = heap.get(targetIndex);
                heap.set(targetIndex, item);
                heap.set(index, temp);
                heapify(heap, targetIndex);
            }
        }

        // Code Snippet of the push function:
        public static void push(List<Integer> heap, int x) {
            heap.add(x);
            // Position of the current inserted element.
            int pos = heap.size() - 1;
            // Shifting the element up until it reaches
            // the topmost node if it is larger than its parent.
            while (pos > 0) {
                int parent = (pos - 1) / 2;
                if (heap.get(pos) > heap.get(parent)) {
                    // Swapping the elements.
                    int temp = heap.get(parent);
                    heap.set(parent, heap.get(pos));
                    heap.set(pos, temp);
                    pos = parent;
                } else {
                    // As the parent is larger, the element is now in its correct position.
                    break;
                }
            }
        }
    }


    // brute force approach
    private static void type1() {

    }
}
