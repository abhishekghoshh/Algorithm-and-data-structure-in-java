package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 *
 * problem links :
 * https://leetcode.com/problems/range-sum-of-bst/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=uLVG45n4Sbg
 *
 * https://neetcode.io/solutions/range-sum-of-bst
 * */


public class RangeSumOfBST {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // using binary search property
    private static void type2() {
        TNode root = TNode.withCount(25);
        int low = 7, high = 15;
        int ans = rangeSumBST2(root, low, high);
        System.out.println(ans);
    }

    public static int rangeSumBST2(TNode root, int low, int high) {
        // if the root is null then we will return 0
        if (root == null) return 0;
        int sum = 0;
        // if the root val is in the range then we will add to the sum
        if (low <= root.val && root.val <= high) {
            sum += root.val;
        }
        // rather blindly calling the function for left and right subtree we will check if the root is greater than val or not
        // if it is then there might be some numbers in the left subtree
        if (root.val > low)
            sum += rangeSumBST1(root.left, low, high);
        // similar condition on the right side
        if (root.val < high)
            sum += rangeSumBST1(root.right, low, high);
        return sum;
    }

    // brute force approach
    private static void type1() {
        TNode root = TNode.withCount(25);
        int low = 7, high = 15;
        int ans = rangeSumBST1(root, low, high);
        System.out.println(ans);
    }

    public static int rangeSumBST1(TNode root, int low, int high) {
        // if the root is null then we will return 0
        if (root == null) return 0;
        int sum = 0;
        // if the root val is in the range then we will add to the sum
        if (low <= root.val && root.val <= high) {
            sum += root.val;
        }
        // now we will get the sum from left and the right
        sum += rangeSumBST1(root.left, low, high);
        sum += rangeSumBST1(root.right, low, high);
        return sum;
    }
}
