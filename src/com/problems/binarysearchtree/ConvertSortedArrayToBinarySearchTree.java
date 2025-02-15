package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

/*
 * Problem link :
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=0K0uCMYq5ng
 *
 * https://neetcode.io/solutions/convert-sorted-array-to-binary-search-tree
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        type1();
    }

    // using recursion,
    // we know there could be multiple ways for creating a binary search tree.
    // so we will use the divide and conquer approach,
    // we will take the middle element as root,
    // then the left side will be the left subtree and the right side will be the right subtree
    private static void type1() {
        int[] nums = {-10, -3, 0, 5, 9};
        TNode root = sortedArrayToBST(nums);
        PrintUtl.inOrder(root);
    }

    public static TNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public static TNode sortedArrayToBST(int[] nums, int start, int end) {
        // if start is greater than the end, that means there will be no node left
        if (start > end) return null;
        // else we will take the mid and treat that as root
        int mid = start + (end - start) / 2;
        TNode root = new TNode(nums[mid]);
        // we will take the left and right range and create the left and right subtree
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }
}
