package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/minimum-element-in-bst_8160462
 *
 * Solution link :
 *
 */
public class MinimumElementInBinarySearchTree {
    // for a valid BST the minimum element is in the leftest node
    public static void main(String[] args) {
        type1();
        type2();
    }

    // iterative way
    private static void type2() {
        TNode root = TNode.makeBST(10001);
        TNode min = getMin2(root);
        System.out.println(min.data);
    }

    private static TNode getMin2(TNode root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    // recursive way
    private static void type1() {
        TNode root = TNode.makeBST(10001);
        TNode min = getMin1(root);
        System.out.println(min.data);
    }

    private static TNode getMin1(TNode root) {
        if (root.left == null) return root;
        return getMin1(root.left);
    }
}
