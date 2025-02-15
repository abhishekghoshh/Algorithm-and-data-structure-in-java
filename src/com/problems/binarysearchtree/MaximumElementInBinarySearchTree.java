package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 *
 * Solution link :
 *
 */
public class MaximumElementInBinarySearchTree {
    // for a valid BST the max will be in the right most node
    public static void main(String[] args) {
        type1();
        type2();
    }

    // iterative way
    private static void type2() {
        TNode root = TNode.makeBST(10001);
        TNode max = getMax2(root);
        System.out.println(max.data);
    }

    private static TNode getMax2(TNode root) {
        while (root.right != null)
            root = root.right;
        return root;
    }

    // recursive way
    private static void type1() {
        TNode root = TNode.makeBST(10001);
        TNode max = getMax1(root);
        System.out.println(max.data);
    }

    private static TNode getMax1(TNode root) {
        if (root.right == null) return root;
        return getMax1(root.right);
    }
}
