package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem link :
 * https://leetcode.com/problems/binary-tree-right-side-view/description/
 * https://neetcode.io/problems/binary-tree-right-side-view
 * https://www.naukri.com/code360/problems/right-view_764605
 * https://www.geeksforgeeks.org/problems/right-view-of-binary-tree/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=KV4mRzTjlAk&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=25
 * https://www.youtube.com/watch?v=d4zLyf32e3I
 *
 * https://takeuforward.org/data-structure/right-left-view-of-binary-tree/
 * https://neetcode.io/solutions/binary-tree-right-side-view
 *
 */
public class RightViewOfBinaryTree {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // iterative way
    // we will go level wise
    private static void type2() {
        TNode root = TNode.withCount(15);
        List<Integer> rightView = rightViewTraversal(root);
        System.out.println(rightView);
    }

    static List<Integer> rightViewTraversal(TNode root) {
        List<Integer> rightView = new ArrayList<>();
        if (root == null) return rightView;
        Queue<TNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // current size of the level
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TNode node = queue.poll();
                // if the level is equal to the rightView size, then we will add the node data into the rightView
                // and we know this will be the right most child by default
                if (rightView.size() == level) rightView.add(node.data);
                // first we will add the right child then left child
                if (node.right != null) queue.offer(node.right);
                if (node.left != null) queue.offer(node.left);
            }
            // we will add one level
            level++;
        }
        return rightView;
    }

    // using dfs / recursion
    private static void type1() {
        TNode root = TNode.withCount(15);
        List<Integer> rightView = rightSideView1(root);
        System.out.println(rightView);
    }

    private static List<Integer> rightSideView1(TNode root) {
        List<Integer> rightView = new ArrayList<>();
        rightViewTraversal(root, rightView, 0);
        return rightView;
    }

    // it will go to most right, and if there is one level found, then it will add that to the list.
    // we don't need to store the right axis length
    // as the recursion is going to leftmost side
    // automatically the right will be added first
    private static void rightViewTraversal(TNode root, List<Integer> rightView, int level) {
        if (null == root) return;
        // if the level exceed the rightView list size, that means we have found another bottom level,
        // and by default, it is the left most child
        if (rightView.size() == level) rightView.add(root.data);
        rightViewTraversal(root.right, rightView, level + 1);
        rightViewTraversal(root.left, rightView, level + 1);
    }
}
