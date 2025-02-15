package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/path-sum/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=LSKQyOz_P8I
 *
 * https://neetcode.io/solutions/path-sum
 */
public class PathSum {
    public static void main(String[] args) {
        type1();
    }

    // recursive approach
    private static void type1() {
        TNode root = TNode.withCount(4);
        int targetSum = 7;
        boolean ans = hasPathSum(root, targetSum);
        System.out.println(ans);
    }

    public static boolean hasPathSum(TNode root, int targetSum) {
        // if the node is null then we will return false
        if (root == null) return false;
        // if the node is a leaf node then we will check if the target sum is equal to node value or not
        if (root.left == null && root.right == null) {
            return (targetSum == root.val);
        }
        // else we will check left subtree and right subtree
        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }
}
