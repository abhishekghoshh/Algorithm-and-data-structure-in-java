package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/merge-two-bsts_920474
 * https://www.geeksforgeeks.org/problems/merge-two-bst-s/1
 *
 * Solution link :
 * https://youtube.com/watch?v=LY5hbvFSJqM
 *
 * https://www.geeksforgeeks.org/merge-two-bsts-with-limited-extra-space/
 */
public class MergeTwoBinarySearchTree {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // TODO This is the most optimized and Simple approach
    //  explain this in the interview
    // traverses both binary search trees then collect it in separate lists,
    // as we know both of them are sorted list
    // so, we can perform merge two sorted list operations to merge them in linear time,
    // Time complexity is O(2*(m+n))
    private static void type3() {
        TNode root1 = TNode.makeBST(2, 4, 8, 10, 7);
        TNode root2 = TNode.makeBST(1, 5, 0, 3, 6);

        List<Integer> inorder1 = new LinkedList<>();
        inorder(root1, inorder1);
        List<Integer> inorder2 = new LinkedList<>();
        inorder(root2, inorder2);
        List<Integer> inorder = merge(inorder1, inorder2);
        System.out.println(inorder);
    }

    private static List<Integer> merge(List<Integer> inorder1, List<Integer> inorder2) {
        List<Integer> inorder = new LinkedList<>();
        int n1 = inorder1.size(), n2 = inorder2.size();
        int i1 = 0, i2 = 0;
        while (i1 < n1 && i2 < n2) {
            if (inorder1.get(i1) < inorder2.get(i2))
                inorder.add(inorder1.get(i1++));
            else
                inorder.add(inorder2.get(i2++));
        }
        while (i1 < n1) inorder.add(inorder1.get(i1++));
        while (i2 < n2) inorder.add(inorder2.get(i2++));
        return inorder;
    }

    private static void inorder(TNode root, List<Integer> inorder) {
        if (root == null) return;
        inorder(root.left, inorder);
        inorder.add(root.data);
        inorder(root.right, inorder);
    }


    // linear approach
    // time complexity O(m+n)
    // we can also use a linked list
    // first we will create an inorder sorted list
    // by traversing the first tree, then we will
    // traverse the second tree and add the nodes in between
    // TODO we could use normal recursive inorder instead of iterative inorder
    //  but normal inorder is giving StackOverflow error
    //  This is also very optimized approach but it is hard to code and little hard to explain
    private static void type2() {
        TNode root1 = TNode.makeBST(2, 4, 8, 10, 7);
        TNode root2 = TNode.makeBST(1, 5, 0, 3, 6);


        Node start = inorder(root1);
        addInBetween(root2, start);
        start = start.next;
        List<Integer> list = new LinkedList<>();
        while (start.val != Integer.MAX_VALUE) {
            list.add(start.val);
            start = start.next;
        }
        System.out.println(list);
    }

    static class Node {
        public Node next;
        int val;

        public Node(int val) {
            this.val = val;
        }

        public Node addNext(Node node) {
            Node next = this.next;
            this.next = node;
            node.next = next;
            return node;
        }
    }

    // iterative inorder
    private static Node inorder(TNode root) {
        Stack<TNode> stack = new Stack<>();
        Node start = new Node(Integer.MAX_VALUE);
        Node inorder = start;
        TNode node = root;
        while (null != node || !stack.isEmpty()) {
            while (null != node) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // add to the inorder list
            inorder = inorder.addNext(new Node(node.data));

            node = node.right;
        }
        inorder.addNext(new Node(Integer.MAX_VALUE));
        return start;
    }

    // same as iterative inorder but here we are adding nodes in between
    private static void addInBetween(TNode root, Node inorder) {
        Stack<TNode> stack = new Stack<>();
        TNode node = root;
        while (null != node || !stack.isEmpty()) {
            while (null != node) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // add to the inorder list
            while (node.data >= inorder.next.val)
                inorder = inorder.next;
            inorder = inorder.addNext(new Node(node.data));

            node = node.right;
        }
    }

    // Merge both of the binary search trees first
    // take any of the tree as source tree and take other one tree as the supplier of the new nodes.
    // After merging do a simple inorder traversal
    // time complexity m*log(n) for merging and for inorder is just O(m+n)
    private static void type1() {
        TNode root1 = TNode.makeBST(2, 4, 8, 10, 7);
        TNode root2 = TNode.makeBST(1, 5, 0, 3, 6);
        merge(root1, root2);
        List<Integer> inorder = new ArrayList<>();
        inorder(root1, inorder);
        System.out.println(inorder);
    }

    private static void merge(TNode root, TNode node) {
        if (node == null) return;
        addNode(root, node);
        if (node.left != null) {
            TNode left = node.left;
            node.left = null;
            merge(root, left);
        }
        if (node.right != null) {
            TNode right = node.right;
            node.right = null;
            merge(root, right);
        }
    }

    private static TNode addNode(TNode root, TNode node) {
        if (root == null) return node;
        if (node.data < root.data)
            root.left = addNode(root.left, node);
        else
            root.right = addNode(root.right, node);
        return root;
    }


}
