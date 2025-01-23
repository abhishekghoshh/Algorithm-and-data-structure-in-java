package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem link :
 * https://www.naukri.com/code360/problems/920519
 * https://www.naukri.com/code360/problems/left-view-of-binary-tree_625707
 * https://www.geeksforgeeks.org/problems/left-view-of-binary-tree/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=KV4mRzTjlAk&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=25
 *
 * https://takeuforward.org/data-structure/right-left-view-of-binary-tree/
 */
public class LeftViewOfBinaryTree {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // iterative way
    // we will go level wise
    private static void type2() {
        TNode root = TNode.withCount(15);
        List<Integer> leftView = leftViewTraversal(root);
        System.out.println(leftView);
    }


    static List<Integer> leftViewTraversal(TNode root) {
        List<Integer> leftView = new ArrayList<>();
        if (root == null) return leftView;
        Queue<TNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // current size of the level
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TNode node = queue.poll();
                // if the level is equal to the leftView size, then we will add the node data into the leftView
                // and we know this will be the left most child by default
                if (leftView.size() == level) leftView.add(node.data);
                // first we will add the left child then right child
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // we will add one level
            level++;
        }
        return leftView;
    }

    // using dfs / recursion
    private static void type1() {
        TNode root = TNode.withCount(15);
        List<Integer> leftView = new ArrayList<>();
        leftViewTraversal(root, leftView, 0);
        System.out.println(leftView);
    }

    // it will go to most left, and if there is one level found, then it will add that to the list.
    // we don't need to store the left axis length
    // as the recursion is going to leftmost side
    // automatically the left will be added first
    private static void leftViewTraversal(TNode root, List<Integer> leftView, int level) {
        if (null == root) return;
        // if the level exceed the leftview list size, that means we have found another bottom level
        // and by default, it is the left most child
        if (leftView.size() == level) leftView.add(root.data);
        leftViewTraversal(root.left, leftView, level + 1);
        leftViewTraversal(root.right, leftView, level + 1);
    }

}
