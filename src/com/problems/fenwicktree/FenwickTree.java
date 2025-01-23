package com.problems.fenwicktree;


/*
 * Problem link :
 *
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Qe8qRhz3lzQ
 *
 */
public class FenwickTree {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        FenwickTree tree = new FenwickTree(nums);
        System.out.println(tree.rangeSum(9));
        System.out.println(tree.rangeSum(2, 8));
        System.out.println(tree.get(7));
        tree.add(5, 5);
        System.out.println(tree.rangeSum(2, 8));
        System.out.println(tree.rangeSum(9));
        System.out.println(tree.get(5));
    }
    // The size of the array holding the Fenwick tree values
    final int n;

    // This array contains the Fenwick tree ranges
    private final int[] tree;

    // Construct a Fenwick tree with an initial set of values.
    // The 'values' array MUST BE ONE BASED meaning values[0] does not get used, O(n) construction.
    public FenwickTree(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Values array cannot be null!");
        n = nums.length + 1;
        tree = new int[n];
        for (int i = 0; i < n - 1; i++) tree[i + 1] = nums[i];
        // Make a clone of the value array since we manipulate the array in place, destroying all its original content.
        // this is a quite effective approach
        // rather adding every element to its all parents, if we start from nums clone and just add it to its
        // immediate parent then we will have only 2n time complexity
        // as we are going from 1 to n
        // all the places already have their corresponding nums
        // so 1 will add to 2 and 2 will have num2 + num1
        // 2 will add to 4 so 4 will have num4 + num2 + num1
        // 3 will add to 4 so 4 will have num4 + num2 + num1 + num3
        // 4 will add to 8 so 8 will have num8 +  num4 + num2 + num1 + num3
        // like this we will construct the entire tree
        for (int i = 1; i < n; i++) {
            // we will add to its immediate parent only
            int parent = i + lsb(i);
            if (parent < n) tree[parent] += tree[i];
        }
    }

    // Computes the prefix sum from [1, i], O(log(n))
    // this is 1 indexed
    private int rangeSum(int i) {
        int sum = 0;
        while (i != 0) {
            sum += tree[i];
            i -= lsb(i);
        }
        return sum;
    }

    // Returns the sum of the interval [left, right], O(log(n))
    // left to right including left
    public int rangeSum(int left, int right) {
        if (right < left)
            throw new IllegalArgumentException("Make sure right >= left");
        return rangeSum(right) - rangeSum(left - 1);
    }

    // Returns the value of the least significant bit (LSB)
    private static int lsb(int i) {
        return i & -i;
    }


    // Get the value at index i
    public int get(int i) {
        return rangeSum(i, i);
    }

    // Add 'delta' to index 'i', O(log(n))
    public void add(int i, int delta) {
        while (i < n) {
            tree[i] += delta;
            i += lsb(i);
        }
    }

    // Set index i to be equal to v, O(log(n))
    public void put(int i, int num) {
        int delta = num - get(i);
        add(i, delta);
    }
}