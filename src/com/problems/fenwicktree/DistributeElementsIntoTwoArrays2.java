package com.problems.fenwicktree;

import com.util.PrintUtl;

import java.util.LinkedList;

/*
 * Problem link :
 * https://leetcode.com/problems/distribute-elements-into-two-arrays-ii/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=zcwOff4MvbQ&list=PLEL7R4Pm6EmBxBrEq8g2L3MF3W3Shnk58&index=2&pp=iAQB
 */
public class DistributeElementsIntoTwoArrays2 {
    // todo complete it later after all the preparation
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo this solution is correct but it is getting memory timeout, so we have to optimize the memory usage
    // we will use a fenwick tree to count
    // but here we have to use how many element is greater on that array
    // but with fenwick tree we can easily find how many are lesser than the current element,
    // so to find greater count we will do array size - lesser count including itself
    // we will use simple fenwick tree
    private static void type2() {
        int[] nums = {5, 14, 3, 1, 2};
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
        // we will create 2 fenwick trees and 2 arrays
        int[] tree1 = new int[N], tree2 = new int[N];
        // we will use an array, we will add the arr1 from the left side and add arr2 on the right side,
        // so that we do not need to create 2 arrays
        int[] ans = new int[n];
        int left = 0, right = n - 1;
        // adding first number to the first tree and first num
        ans[left++] = nums[0];
        update(tree1, N - 1, nums[0] + offset, 1);
        // adding second number to the second tree and first num
        ans[right--] = nums[1];
        update(tree2, N - 1, nums[1] + offset, 1);
        for (int i = 2; i < n; i++) {
            int size1 = left, size2 = n - right - 1;
            int num = nums[i], numWithOffset = nums[i] + offset;
            // finding the greater count
            int count1 = size1 - query(tree1, numWithOffset);
            int count2 = size2 - query(tree2, numWithOffset);
            // now we will update the tree and the
            if (count1 > count2) {
                ans[left++] = num;
                update(tree1, N - 1, numWithOffset, 1);
            } else if (count1 < count2) {
                ans[right--] = num;
                update(tree2, N - 1, numWithOffset, 1);
            } else {
                if (size1 <= size2) {
                    ans[left++] = num;
                    update(tree1, N - 1, numWithOffset, 1);
                } else {
                    ans[right--] = num;
                    update(tree2, N - 1, numWithOffset, 1);
                }
            }
        }
        // now we will reverse the arr2 array
        // the final answer will be actual answer
        int start = right + 1, end = n - 1;
        while (start < end) {
            int temp = ans[start];
            ans[start] = ans[end];
            ans[end] = temp;
            start++;
            end--;
        }
        PrintUtl.print(ans);
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
    private static void type1() {
        int[] nums = {5, 14, 3, 1, 2};
        int n = nums.length;
        LinkedList<Integer> list1 = new LinkedList<>(), list2 = new LinkedList<>();
        list1.addLast(nums[0]);
        list2.addLast(nums[1]);
        for (int i = 2; i < n; i++) {
            int size1 = list1.size(), size2 = list2.size();
            int num = nums[i];
            int count1 = greaterCount(list1, num);
            int count2 = greaterCount(list2, num);
            if (count1 > count2)
                list1.addLast(num);
            else if (count1 < count2)
                list2.addLast(num);
            else {
                if (size1 <= size2)
                    list1.addLast(num);
                else
                    list2.addLast(num);
            }
        }
        int[] ans = new int[n];
        int i = 0;
        for (int item : list1) ans[i++] = item;
        for (int item : list2) ans[i++] = item;
        PrintUtl.print(ans);
    }

    private static int greaterCount(LinkedList<Integer> l, int num) {
        int c = 0;
        for (int item : l)
            if (num < item) c++;
        return c;
    }
}
