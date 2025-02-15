package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/normal-bst-to-balanced-bst_920472
 * https://www.geeksforgeeks.org/problems/normal-bst-to-balanced-bst/1
 *
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ElUqyFsddvo
 * https://www.youtube.com/watch?v=ceGBg3g18js
 *
 * https://www.geeksforgeeks.org/convert-normal-bst-balanced-bst/
 */
public class ConvertNormalBinarySearchTreeToBalanced {
    public static void main(String[] args) {
        type1();
    }

    // This approach is pretty optimized
    // first we will get the imbalanced tree and get the inorder traversal of the tree.
    // The inOrder traversal always gives us the sorted list.
    // Then with this inOrder traversal we can create a balanced BST
    // it is very easy to create a balanced bst if the inorder is given
    // TODO rather creating a list of Integer we could use a list of Tree node
    //  we could directly save the tree node in the list
    //  and while rebuilding the Balanced Binary Search Tree we could use the same nodes
    private static void type1() {
        TNode root = TNode.imbalancedBST(16);
        PrintUtl.levelOrder(root);
        PrintUtl.inOrder(root);

        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        root = build(inorder, 0, inorder.size() - 1);

        PrintUtl.levelOrder(root);
        PrintUtl.inOrder(root);
    }

    private static void inorder(TNode root, List<Integer> inorder) {
        if (root == null) return;
        inorder(root.left, inorder);
        inorder.add(root.data);
        inorder(root.right, inorder);
    }

    // it will make a balanced BST
    // we will always make size/2 th element as root
    private static TNode build(List<Integer> nums, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TNode left = build(nums, l, mid - 1);
        TNode right = build(nums, mid + 1, r);
        return new TNode(nums.get(mid), left, right);
    }
}
