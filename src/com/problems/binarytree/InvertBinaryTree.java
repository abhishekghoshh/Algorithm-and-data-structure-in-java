package com.problems.binarytree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

/*
 *
 * problem links :
 * https://leetcode.com/problems/invert-binary-tree/description/
 * https://neetcode.io/problems/invert-a-binary-tree
 * https://www.geeksforgeeks.org/problems/mirror-tree/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=OnSn2XEQ4MY
 *
 * https://takeuforward.org/data-structure/check-for-symmetrical-binary-tree/
 * https://neetcode.io/solutions/invert-binary-tree
 *
 * */
public class InvertBinaryTree {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        TNode root = TNode.withCount(15);
        root = invertTree(root);
        PrintUtl.print(root);
    }

    public static TNode invertTree(TNode root) {
        if (null == root) return null;
        TNode left = root.left, right = root.right;
        // changing the left and right child
        root.left = right;
        root.right = left;
        // now recursively call on the left and right child
        invertTree(left);
        invertTree(right);
        return root;
    }
}
