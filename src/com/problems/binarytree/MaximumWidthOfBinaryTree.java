package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/maximum-width-of-binary-tree/description/
 * https://www.geeksforgeeks.org/problems/maximum-width-of-tree/1
 * https://www.naukri.com/code360/problems/maximum-width-in-binary-tree_763671
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ZbybYvcVLks&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=29
 * https://www.youtube.com/watch?v=FPzLE2L7uHs
 *
 * https://takeuforward.org/data-structure/maximum-width-of-a-binary-tree/
 * https://neetcode.io/solutions/maximum-width-of-binary-tree
 */
public class MaximumWidthOfBinaryTree {

    public static void main(String[] args) {
        type1();
        type2();
    }

    // recursive way
    // we will save only the left most index for every layer
    private static void type2() {
        TNode root = TNode.withCount(15);
        int width = widthOfBinaryTree2(root);
        System.out.println(width);
    }

    public static int widthOfBinaryTree2(TNode root) {
        if (root == null) return 0;
        return widthOfBinaryTree2(root, 0, 0, new ArrayList<>());
    }

    private static int widthOfBinaryTree2(TNode root, int level, int index, List<Integer> leftIndex) {
        if (root == null) return 0;
        // we will store all the left side node indices, which is the same as left view though
        if (level == leftIndex.size()) leftIndex.add(index);
        // we will check the width with the left most index
        int currentLayerWidth = index - leftIndex.get(level) + 1;
        // we will also check if there is any higher width present or not in its left and right subtree
        int left = widthOfBinaryTree2(root.left, level + 1, index * 2, leftIndex);
        int right = widthOfBinaryTree2(root.right, level + 1, index * 2 + 1, leftIndex);
        // lastly, we will return the highest one among all
        return Math.max(currentLayerWidth, Math.max(left, right));
    }


    // if we have to count only the non-null node then we could just store the nodes
    // in queue and at last we can just take the size, but we have to also keep
    // track of the null nodes in between, so we will set numbers for each node and
    // in each level we will take the difference of the first node number to last
    // node number, the difference will tell us the nodes in between
    private static void type1() {
        TNode root = TNode.withCount(15);
        int max = widthOfBinaryTree1(root);
        System.out.println(max);
    }

    private static int widthOfBinaryTree1(TNode root) {
        LinkedList<Pair> deque = new LinkedList<>();
        deque.add(new Pair(root, 0));
        int max = 0;
        while (!deque.isEmpty()) {
            // checking the current width with the max
            int width = (deque.getLast().index - deque.getFirst().index) + 1;
            max = Math.max(max, width);
            // we will add a new level into deque from the current level
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Pair pair = deque.poll();
                int index = pair.index;
                TNode node = pair.node;
                if (null != node.left)
                    deque.offer(new Pair(node.left, 2 * index + 1));
                if (null != node.right)
                    deque.offer(new Pair(node.right, 2 * index + 2));
            }
        }
        return max;
    }

    public static class Pair {
        public TNode node;
        public int index;

        public Pair(TNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
