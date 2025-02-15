package com.problems.fenwicktree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=WpttRpfw28U&list=PLEL7R4Pm6EmBxBrEq8g2L3MF3W3Shnk58&index=3
 */
public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // same as the inversion count problem using fenwick tree
    // but here we will do differently
    // we will not create the fenwick tree first and decrease the count
    // rather we will start from clean tree
    // we do it from the last and increase the frequency of num by one
    // and the last we reverse the ans
    // we will also tackle negative numbers here
    private static void type2() {
        int[] nums = {2, 0, 5, -1, -3, 1, 3, 4, 0, 2, -1, 0, 1, 0};
        int n = nums.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        // first we will be calculating the max number and will create an array for holding all of this
        for (int num : nums) {
            if (max < num) max = num;
            if (min > num) min = num;
        }
        // as fenwick tree is 1 indexed, so we will use offset and -min+1 not -min
        int offset = -min + 1;
        int N = max + offset + 1;
        // here we will use an empty tree
        // we will start from the last, and we will increase the frequency by one
        // here we will not need the freq array even
        // it is a clever approach as we are skipping the fenwick tree building time and freq array time and space
        int[] tree = new int[N];
        List<Integer> list = new ArrayList<>();
        // now here we will take num from last and increase the frequency by 1
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i] + offset;
            update(tree, N - 1, num, 1);
            list.add(query(tree, num - 1));
        }
        Collections.reverse(list);
        System.out.println(list);
    }

    static int query(int[] tree, int i) {
        int bit = i;
        int sum = 0;
        while (bit > 0) {
            sum += tree[bit];
            bit -= (bit & (-bit)); // removing the last set bit
        }
        return sum;
    }

    static void update(int[] tree, int n, int i, int addition) {
        int bit = i;
        while (bit <= n) {
            tree[bit] += addition;
            bit += (bit & (-bit)); // adding the last set bit
        }
    }


    // brute force
    // time complexity O(n^2)
    // space complexity O(1)
    private static void type1() {
        int[] nums = {2, 0, 5, -1, -3, 1, 3, 4, 0, 2, -1, 0, 1, 0};
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) count++;
            }
            list.add(count);
        }
        Collections.reverse(list);
        System.out.println(list);
    }
}
