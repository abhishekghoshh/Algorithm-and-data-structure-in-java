package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * https://practice.geeksforgeeks.org/problems/nodes-at-given-distance-in-binary-tree/1
 * https://www.codingninjas.com/studio/problems/print-nodes-at-distance-k-from-a-given-node_842560
 *
 * Solution link :
 * https://www.youtube.com/watch?v=i9ORlEy6EsI&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=31
 *
 * https://takeuforward.org/binary-tree/print-nodes-at-distance-k-in-a-binary-tree/
 */
public class NodesAtDistanceK {

    // TODO the solution of Striver is very complex and time-consuming.
    // better follow these 2 approaches
    // this is a problem of the least common ancestor
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // Optimized approach
    // rather storing in the stack and then iterating it we can just find the node
    // with k distances while finding the target
    private static void type3() {
        TNode root = TNode.withCount(31);
        TNode target = root.search(5);
        int k = 2;
        List<Integer> list = new ArrayList<>();
        findTarget(root, target, list, k);
        System.out.println(list);
    }

    private static int findTarget(TNode root, TNode target, List<Integer> list, int k) {
        if (null == root) return -1;
        if (root == target) {
            // if k ==0 then we can just add the current root and return -1
            // -1 is a flag to stop the findNode operation
            if (k == 0) {
                list.add(root.data);
                return -1;
            }
            addNodes(root.left, k - 1, list);
            addNodes(root.right, k - 1, list);
            return 1;
        }
        int leftDistance = findTarget(root.left, target, list, k);
        int rightDistance = findTarget(root.right, target, list, k);
        // it is the kth distance node from the target node, so
        //  we will add this node and return -1
        if (leftDistance == k || rightDistance == k) {
            list.add(root.data);
            return -1;
        }
        // the left distance is less than k, and it is not -1,
        // so we will traverse the opposite side of the child
        if (leftDistance != -1 && leftDistance < k) {
            addNodes(root.right, k - leftDistance - 1, list);
            return leftDistance + 1;
        }
        // the right distance is less than k, and it is not -1,
        // so we will traverse the opposite side of the child
        if (rightDistance != -1 && rightDistance < k) {
            addNodes(root.left, k - rightDistance - 1, list);
            return rightDistance + 1;
        }
        return -1;
    }

    // same as previous just a little optimized
    private static void type2() {
        TNode root = TNode.withCount(31);
        TNode target = root.search(5);
        int k = 2;
        List<Integer> list = distanceK2(root, target, k);
        System.out.println(list);
    }

    public static List<Integer> distanceK2(TNode root, TNode target, int k) {
        if (k == 0) return List.of(target.data);
        // we will store all the ancestors in the queue
        Queue<TNode> queue = new LinkedList<>();
        traverseTillTarget(root, target, queue);
        List<Integer> list = new ArrayList<>();
        TNode child = target;
        // from the target node, we will go both left and right side
        addNodes(child.left, k - 1, list);
        addNodes(child.right, k - 1, list);
        k--;
        // from the target node we will traverse back to root,
        // and we will traverse the opposite side of children
        while (!queue.isEmpty() && k >= 1) {
            TNode parent = queue.poll();
            // if the target is in left side then we will traverse in the right
            if (parent.left == child) addNodes(parent.right, k - 1, list);
            else addNodes(parent.left, k - 1, list);
            k--;
            child = parent;
        }
        // k == 0 and queue is not empty means the top node on queue is at kth distance from the target node
        // we will just store that specific node into the answer list
        if (k == 0 && !queue.isEmpty()) list.add(queue.poll().data);
        return list;
    }

    private static boolean traverseTillTarget(TNode root, TNode target, Queue<TNode> queue) {
        if (null == root) return false;
        if (root == target) return true;
        // if this is true means target is in left side
        if (traverseTillTarget(root.left, target, queue)) {
            queue.offer(root);
            return true;
        }
        // if this is true means target is in right side
        if (traverseTillTarget(root.right, target, queue)) {
            queue.offer(root);
            return true;
        }
        return false;
    }

    private static void addNodes(TNode node, int distance, List<Integer> list) {
        if (null == node) return;
        if (distance == 0) {
            list.add(node.data);
            return;
        }
        addNodes(node.left, distance - 1, list);
        addNodes(node.right, distance - 1, list);
    }

    // TODO replace the stack is queue
    // because in stack we are getting the root then child traversal

    // this is based on the lowest common ancestor.
    // we will store all the ancestors till root with the directions
    // then traverse the stack, and for every node we will find all the equal
    // distance nodes from different child.
    // when we reach the target, we will check all its child level
    private static void type1() {
        TNode root = TNode.withCount(31);
        TNode target = root.search(5);
        int k = 2;
        Stack<Pair> stack = new Stack<>();
        findPath(root, target, stack);
        int distance = stack.size() - 1;
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            TNode node = pair.node;
            int flag = pair.flag;
            if (distance < k) {
                if (flag == 1) {
                    findNodes(node.left, k - distance, 1, list);
                } else if (flag == -1) {
                    findNodes(node.right, k - distance, 1, list);
                } else {
                    findNodes(node.left, k - distance, 1, list);
                    findNodes(node.right, k - distance, 1, list);
                }
            } else if (distance == k) {
                list.add(node.data);
            }
            distance--;
        }
        System.out.println(list);
    }

    private static boolean findPath(TNode root, TNode target, Stack<Pair> stack) {
        if (null == root) return false;
        if (root.data == target.data) {
            stack.push(new Pair(root, 0));
            return true;
        }
        boolean left = findPath(root.left, target, stack);
        boolean right = findPath(root.right, target, stack);
        if (left) stack.push(new Pair(root, -1));
        if (right) stack.push(new Pair(root, 1));
        return left || right;
    }

    private static void findNodes(TNode node, int distance, int level, List<Integer> list) {
        if (null == node) return;
        if (level == distance) {
            list.add(node.data);
            return;
        }
        findNodes(node.left, distance, level + 1, list);
        findNodes(node.right, distance, level + 1, list);
    }


    public static class Pair {
        public TNode node;
        public int flag;

        public Pair(TNode node, int flag) {
            this.node = node;
            this.flag = flag;
        }

        @Override
        public String toString() {
            return "[=" + node.data + "," + flag + "]";
        }
    }
}
