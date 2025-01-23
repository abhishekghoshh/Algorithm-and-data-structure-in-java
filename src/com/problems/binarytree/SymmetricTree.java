package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/symmetric-tree/
 * https://www.naukri.com/code360/problems/630426
 * https://www.naukri.com/code360/problems/symmetric-tree_981177
 *
 * Solution link :
 * https://www.youtube.com/watch?v=nKggNAiEpBE&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=26
 * https://www.youtube.com/watch?v=Mao9uzxwvmc
 *
 * https://takeuforward.org/data-structure/check-for-symmetrical-binary-tree/
 * https://neetcode.io/solutions/symmetric-tree
 */
public class SymmetricTree {

    public static void main(String[] args) {
        type1();
    }

    // to check symmetry of a tree
    // if we think of a center then that would be the root node
    // so from the root node we will check its left subtree and right subtree
    // lets say if the tree is like
    /*
     *       1
     *    2      2
     *  4   5  5   4
     * */
    // if we consider left subtree and right subtree different tree then from the diagram we can visualize that
    // left.left should be equal to right.right
    // and left.right should be equal to right.left
    // we will create a recurrence relation from the conditions
    private static void type1() {
        TNode root = TNode.withNodes(1, 2, 2, 3, 4, 4, 3);
        boolean isSymmetric = isSymmetric(root);
        System.out.println(isSymmetric);
    }

    private static boolean isSymmetric(TNode root) {
        if (null == root) return true;
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TNode root1, TNode root2) {
        // if both are null, then it is symmetric
        if (null == root1 && null == root2) return true;
        // if either is null, then it is not symmetric
        if (null == root1 || null == root2) return false;
        // if both is non-null, then first we will check the values of the node
        // as we are checking the symmetric, so we will now check cris-cross
        return (root1.data == root2.data)
                && isSymmetric(root1.left, root2.right)
                && isSymmetric(root1.right, root2.left);
    }
}
