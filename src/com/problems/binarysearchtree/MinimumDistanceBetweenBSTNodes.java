package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

/*
 * problem links :
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=joxx4hTYwcw
 *
 * https://neetcode.io/solutions/minimum-distance-between-bst-nodes
 * */
public class MinimumDistanceBetweenBSTNodes {
    public static void main(String[] args) {
        type1();
    }

    // if we do an inorder traversal we will get the sorted list
    // and in a sorted list we will get the minimum difference only by checking num[i-1] and nums[i]
    // so we will carry 2 variables prev and max
    private static void type1() {
        TNode root = TNode.withObjectNodes(1, 0, 48, null, null, 12, 49);
        int ans = minDiffInBST(root);
        System.out.println(ans);
    }


    static int prev = -1, min = Integer.MAX_VALUE;

    public static int minDiffInBST(TNode root) {
        traverse(root);
        return min;
    }

    private static void traverse(TNode root) {
        if (null == root) return;
        traverse(root.left);
        if (prev != -1) {
            min = Math.min(min, (root.val - prev));
        }
        prev = root.val;
        traverse(root.right);
    }
}
