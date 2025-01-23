package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * problem links :
 * https://leetcode.com/problems/evaluate-boolean-binary-tree/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=9a_cP54jn8Q
 *
 * https://neetcode.io/solutions/evaluate-boolean-binary-tree
 * */
public class EvaluateBooleanBinaryTree {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        TNode root = TNode.makeBST(2, 1, 3, TNode.NULL, TNode.NULL, 0, 1);
        boolean ans = evaluateTree(root);
        System.out.println(ans);
    }

    public static boolean evaluateTree(TNode root) {
        // if node is null then we will return true
        if (null == root) return true;
        // if it is leaf node then we will check if it is 1 or not
        if (root.left == null && root.right == null) {
            return (root.val == 1);
        }
        // we will collect the values from left and right
        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);
        // if thw val is 2 then it is OR if the value is 3 then it is AND
        if (root.val == 2) {
            return left || right;
        } else {
            return left && right;
        }
    }
}
