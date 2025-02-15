package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
 * Problem link :
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * https://www.geeksforgeeks.org/problems/zigzag-tree-traversal/1
 * https://www.naukri.com/code360/problems/zigzag-binary-tree-traversal_920532
 *
 * Solution link :
 * https://www.youtube.com/watch?v=3OXWEdlIGl4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=20
 * https://www.youtube.com/watch?v=igbboQbiwqw
 *
 * https://takeuforward.org/data-structure/zig-zag-traversal-of-binary-tree/
 * https://neetcode.io/solutions/binary-tree-zigzag-level-order-traversal
 */

public class ZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // TODO explain this in the interview
    // best approach
    // rather than using an array list, we can also use linked list and add the item
    // either first or last based on the level
    private static void type3() {
        TNode root = TNode.withCount(15);
        List<List<Integer>> zigzagTraversal = new ArrayList<>();
        Queue<TNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> oneLevel = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TNode node = queue.poll();
                // if the level is odd length, then we will add normally else will add at first
                if (level % 2 == 1) oneLevel.addLast(node.data);
                else oneLevel.addFirst(node.data);
                // if there are left and right nodes, then we will add that
                if (null != node.left) queue.offer(node.left);
                if (null != node.right) queue.offer(node.right);
            }
            level++;
            zigzagTraversal.add(oneLevel);
        }
        System.out.println(zigzagTraversal);
    }

    // using recursion,
    // we will add a level list as per need
    // when we go to that level using DFS
    private static void type2() {
        TNode root = TNode.withCount(15);
        List<List<Integer>> zigzagTraversal = new ArrayList<>();
        // unlike other types, we will
        traverse(root, zigzagTraversal, 0);
        System.out.println(zigzagTraversal);
    }

    private static void traverse(TNode root, List<List<Integer>> zigzagTraversal, int level) {
        if (root == null) return;
        // that mean we have reached a new level
        // so, we have to add a new level
        if (zigzagTraversal.size() == level) zigzagTraversal.add(new LinkedList<>());
        LinkedList<Integer> list = (LinkedList<Integer>) zigzagTraversal.get(level);
        // if this level is even then we will add at last else we will add at first
        if (level % 2 == 0) list.addLast(root.data);
        else list.addFirst(root.data);
        traverse(root.left, zigzagTraversal, level + 1);
        traverse(root.right, zigzagTraversal, level + 1);
    }

    // todo iterative approach
    // zigzag traversal means for one level the list is normal
    // and for the second level the list is reversed.
    // so we can set a flag and topple it every time,
    // and based on it, we can reverse the one level
    private static void type1() {
        TNode root = TNode.withCount(15);
        List<List<Integer>> ans = zigzagLevelOrder1(root);
        System.out.println(ans);
    }

    private static List<List<Integer>> zigzagLevelOrder1(TNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> oneLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TNode node = queue.poll();
                oneLevel.add(node.data);
                if (null != node.left) queue.offer(node.left);
                if (null != node.right) queue.offer(node.right);
            }
            // for the even levels, we will reverse the nodes
            if (level % 2 == 1) reverse(oneLevel);
            level++;
            list.add(oneLevel);
        }
        return list;
    }

    private static void reverse(List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n / 2; i++) {
            int x = list.get(i);
            int y = list.get(n - i - 1);
            list.set(i, y);
            list.set(n - i - 1, x);
        }
    }

}
