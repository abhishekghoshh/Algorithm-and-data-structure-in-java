package com.ds.heap;

import java.util.Arrays;

public class MinHeap {
    private final int[] heap;
    private int size;
    private final int maxsize;

    public MinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        heap = new int[this.maxsize];
    }

    public MinHeap(int[] array) {
        this.maxsize = array.length;
        this.size = array.length;
        this.heap = Arrays.copyOf(array, size);
        for (int i = this.size / 2 - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private boolean isLeaf(int pos) {
        return pos > (size / 2) - 1 && pos <= size;
    }

    private void swap(int fpos, int spos) {
        int tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void minHeapify(int pos) {
        if (isLeaf(pos)) return;
        int lowest = pos;
        if (leftChild(pos) < this.size && heap[lowest] > heap[leftChild(pos)]) lowest = leftChild(pos);
        if (rightChild(pos) < this.size && heap[lowest] > heap[rightChild(pos)]) lowest = rightChild(pos);

        if (lowest != pos) {
            swap(pos, lowest);
            minHeapify(lowest);
        }
    }

    public void insert(int element) {
        heap[size] = element;
        int current = size;
        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    public int extractMin() {
        int popped = heap[0];
        heap[0] = heap[size - 1];
        this.size--;
        minHeapify(0);
        return popped;
    }

    public int peek() {
        return heap[0];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public void print() {
        for (int i = 0; i < size / 2; i++) {
            System.out.print("Parent : " + heap[i]);
            if (leftChild(i) < size) {
                System.out.print(" Left Child : " + heap[leftChild(i)]);
            }
            if (rightChild(i) < size) {
                System.out.print(" Right Child : " + heap[rightChild(i)]);
            }
            System.out.println(); // for new line
        }
    }
}
