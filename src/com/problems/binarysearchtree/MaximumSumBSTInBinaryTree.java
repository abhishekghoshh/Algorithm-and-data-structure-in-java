package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

import java.util.HashMap;
import java.util.Map;

import static com.ds.binarytree.TNode.NULL;

/*
 * Problem link :
 * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=zAz-WbqIaf8
 *
 */
public class MaximumSumBSTInBinaryTree {
    private static final int MIN = -50000;
    private static final int MAX = 50000;
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    private static void type3() {
        TNode root = TNode.withNodes(1, 4, 3, 2, 4, 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, 4, 6);
        int ans = maxSumBST(root);
        System.out.println(ans);
    }

    static int maxSum3 = MIN;

    // in the previous type, we have called check BST method twice for two scenarios.
    // but somehow if we can use only one function call to check if the subtree is BST.
    // with that information, if we can also calculate the current node is a part of the BST or not,
    // then the overall problem will be optimized.
    // so we will check left and right is an individual subtree or not at first.
    // then we need to know that if the highest of the left subtree is lesser than the root
    // and the lowest of the right subtree is greater than the node or not.
    // so we will use a custom data structure to hold all this information.
    // we will return null if any of the subtree is non BST
    // or the root does not satisfy the range criteria
    public static int maxSumBST(TNode root) {
        maxSumBST3(root);
        return Math.max(0, maxSum3);
    }

    public static Pair maxSumBST3(TNode root) {
        // here we will return sum 0 and isBST false
        // and as this is null, so we will return the min as INF and max -INF
        // so the null will be satisfied whether it will be left or the right side
        if (root == null)
            return new Pair(MAX, MIN, 0);
        int val = root.data;
        // checking if both are BST or not
        Pair leftSubtree = maxSumBST3(root.left);
        Pair rightSubtree = maxSumBST3(root.right);
        // if any of the subtree is null that means they are not BST, so the current node is also not BST
        if (leftSubtree == null || rightSubtree == null) return null;
        // or the root node is not following the range condition then also it is not a BST
        if (leftSubtree.max >= val || val >= rightSubtree.min) return null;
        // at this point, the current node is BST and the left and right subtree is also BST
        int sum = val + leftSubtree.sum + rightSubtree.sum;
        // updating the max sum
        maxSum3 = Math.max(maxSum3, sum);
        // returning the new pair with updated range and sum
        return new Pair(
                root.left != null ? leftSubtree.min : val,
                root.right != null ? rightSubtree.max : val,
                sum
        );
    }

    static class Pair {
        int min, max, sum;

        public Pair(int min, int max, int sum) {
            this.min = min;
            this.max = max;
            this.sum = sum;
        }
    }

    // this is better than previous
    // still it will get TLE in leetcode
    private static void type2() {
        TNode root = TNode.withNodes(1, 4, 3, 2, 4, 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, 4, 6);
        int ans = maxSumBST2(root);
        System.out.println(ans);
    }

    // so there can be 2 cases,
    // either this will be a BST or not
    // to check if this is a BST or not, we will check its left subtree and right and the current.
    // if this is not, then we will treat the left and right child as a starting of a new BST
    // we will save the sum in a map
    // and us the maxSum variable to store the max sum
    public static int maxSumBST2(TNode root) {
        isBST2(root, MIN, MAX);
        return Math.max(0, maxSum2);
    }

    static int maxSum2 = MIN;
    static Map<TNode, Integer> map2 = new HashMap<>();

    public static boolean isBST2(TNode root, int min, int max) {
        if (root == null) return true;
        int val = root.data;
        // this will if the current node, left subtree and right subtree is BST or not
        if (min < val && val < max && isBST2(root.left, min, val) && isBST2(root.right, val, max)) {
            int leftSum = (root.left != null) ? map2.get(root.left) : 0;
            int rightSum = (root.right != null) ? map2.get(root.right) : 0;
            int sum = val + leftSum + rightSum;
            map2.put(root, sum);
            maxSum2 = Math.max(maxSum2, sum);
            return true;
        }
        // we will treat the left and right subtree as a start of new subtree
        isBST2(root.left, MIN, MAX);
        isBST2(root.right, MIN, MAX);
        return false;
    }

    // brute force approach
    private static void type1() {

    }
}
