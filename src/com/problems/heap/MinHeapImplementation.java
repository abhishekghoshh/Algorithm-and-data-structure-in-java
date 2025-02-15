package com.problems.heap;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/implement-a-priority-queue-_1743878
 *
 * Solution link :
 *
 *
 * */
public class MinHeapImplementation {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
    }

    static class MinHeap {
        int[] heap;
        int len = 0, size;

        // Constructor for the class.
        MinHeap(int size) {
            // Write your code here.
            this.size = size;
            heap = new int[size];
        }

        // Implement the function to remove minimum element.
        int extractMinElement() {
            // Write you code here.
            if (len == 0) return -1;
            int item = heap[0];
            int last = heap[--len];
            if (len == 0) return item;
            heap[0] = last;
            heapify(0);
            return item;
        }

        private void heapify(int index) {
            int item = heap[index];
            int targetIndex = index;
            int left = 2 * index + 1, right = 2 * index + 2;
            if (left < len && heap[targetIndex] > heap[left]) targetIndex = left;
            if (right < len && heap[targetIndex] > heap[right]) targetIndex = right;
            if (targetIndex != index) {
                int temp = heap[targetIndex];
                heap[targetIndex] = item;
                heap[index] = temp;
                heapify(targetIndex);
            }
        }

        // Implement the function to delete an element.
        void deleteElement(int ind) {
            // Write you code here.
            if (ind >= len) return;
            int item = heap[ind];
            int last = heap[--len];
            if (len == 0) return;
            heap[ind] = last;
            heapify(ind);
        }

        // Implement the function to insert 'val' in the heap.
        void insert(int val) {
            // Write you code here.
            if (len == size) return;
            heap[len++] = val;
            int pos = len - 1;
            while (pos > 0) {
                int parent = (pos - 1) / 2;
                if (heap[pos] < heap[parent]) {
                    // Swapping the elements.
                    int temp = heap[parent];
                    heap[parent] = heap[pos];
                    heap[pos] = temp;
                    pos = parent;
                }
                // As the parent is larger, the element is now in its correct position.
                else break;
            }
        }
    }
}
