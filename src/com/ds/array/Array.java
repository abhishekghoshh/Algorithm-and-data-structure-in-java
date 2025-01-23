package com.ds.array;

import java.util.Iterator;

/*
 * Problem link :
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Qmt0QwzEmh0&list=PLDV1Zeh2NRsB6SWUrDFW2RmDotAfPbeHu&index=2
 * https://www.youtube.com/watch?v=2USMAwcRWHE&list=PLDV1Zeh2NRsB6SWUrDFW2RmDotAfPbeHu&index=2
 * https://www.youtube.com/watch?v=zUUkiEllHG0&list=PLDV1Zeh2NRsB6SWUrDFW2RmDotAfPbeHu&index=3
 * https://www.youtube.com/watch?v=PEnFFiQe1pM&list=PLDV1Zeh2NRsB6SWUrDFW2RmDotAfPbeHu&index=4
 * https://www.youtube.com/watch?v=tvw4v7FEF1w&list=PLDV1Zeh2NRsB6SWUrDFW2RmDotAfPbeHu&index=5
 */
public class Array<T> implements Iterable<T> {
    private T[] data;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAP = 1 << 3;

    public Array() {
        this(DEFAULT_CAP);
    }

    public Array(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        data = (T[]) new Object[capacity];
    }

    // Given an array make it a dynamic array!
    public Array(T[] array) {
        if (array == null) throw new IllegalArgumentException("Array cannot be null");
        data = java.util.Arrays.copyOf(array, array.length);
        capacity = size = array.length;
    }

    // Returns the size of the array
    public int size() {
        return size;
    }


    // Returns true/false on whether the array is empty
    public boolean isEmpty() {
        return size == 0;
    }


    // To get/set values without method call overhead you can do
    // array_obj.arr[index] instead, you can gain about 10x the speed!
    public T get(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Invalid index");
        return data[index];
    }

    public void set(int index, T elem) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Invalid index");
        data[index] = elem;
    }

    // Add an element to this dynamic array
    public void add(T elem) {
        if (size + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // double the size
            data = java.util.Arrays.copyOf(data, capacity); // pads with extra 0/null elements
        }
        data[size++] = elem;
    }

    // Removes the element at the specified index in this list.
    // If possible, avoid calling this method as it take O(n) time
    // to remove an element (since you have to reconstruct the array!)
    public void removeAt(int index) {
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        --size;
        --capacity;
    }


    // Search and remove an element if it is found in the array
    // If possible, avoid calling this method as it take O(n) time
    public boolean remove(T elem) {
        for (int i = 0; i < size; i++) {
            if (data[i] == elem) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    // Reverse the contents of this array
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            T tmp = data[i];
            data[i] = data[size - i - 1];
            data[size - i - 1] = tmp;
        }
    }

    // Perform a binary search on this array to find an element in O(log(n)) time
    // Make sure this array is sorted!
    // Returns a value < 0 if item is not found
    public int binarySearch(int key) {
        // if (index < 0) index = -index - 1; // If not found, this will tell you where to insert
        return java.util.Arrays.binarySearch(data, 0, size, key);
    }

    public void sort() {
        java.util.Arrays.sort(data, 0, size);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i != size;
            }

            @Override
            public T next() {
                return data[i++];
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder().append("[");
            for (int i = 0; i < size; i++) {
                sb.append(data[i]);
                if (i != size - 1) sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }
    }


    public static void main(String[] args) {
        Array<Integer> arr = new Array<>(14);
        arr.add(1);
        arr.add(122);
        System.out.println(arr);
        arr.set(1, 2);
        System.out.println(arr);
        arr.add(-1);
        arr.sort();
        System.out.println(arr);
        for (Integer integer : arr) System.out.print(integer + " ");
    }
}
