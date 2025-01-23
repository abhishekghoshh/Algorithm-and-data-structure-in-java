package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 *
 * Solution link :
 *
 *
 * */
public class SecondMinimumValueInBinaryTree {

    // TODO if it was a binary search tree then we could do some optimization
    //  we could just search from the right side
    //  as that side holds the larger elements
    public static void main(String[] args) {
        type1();
    }


    // this brute force but it is still efficient
    private static void type1() {
        TNode root = new TNode(8)
                .left(new TNode(5))
                .right(new TNode(6));
        int ans = findSecondMinimumValue(root);
        System.out.println(ans);
    }

    // we are setting it Long max as the upper range of root value can be (2^31 - 1)
    static long first = Long.MAX_VALUE, second = Long.MAX_VALUE;

    public static int findSecondMinimumValue(TNode root) {
        traverse(root);
        return second != Long.MAX_VALUE ?
                (int) second : -1;
    }

    public static void traverse(TNode root) {
        if (null != root) {
            traverse(root.left);
            // checking the current values
            int data = root.data;
            if (data < first && data < second) {
                second = first;
                first = data;
            } else if (data > first && data < second) {
                second = data;
            }
            traverse(root.right);
        }
    }
}
