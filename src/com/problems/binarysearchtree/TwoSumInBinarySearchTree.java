package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Problem link :
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 * https://www.geeksforgeeks.org/problems/find-a-pair-with-given-target-in-bst/1
 * https://www.naukri.com/code360/problems/pair-sum-in-bst._920493
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ssL3sHwPeb4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=52
 * 
 * 
 */
public class TwoSumInBinarySearchTree {

	// All three approaches are efficient,
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}


	// TODO study it one more time
	private static void type3() {
		TNode root = TNode.makeBST(15);
		int k = 25;
		boolean hasTwoSum = twoSum3(root, root, k);
		System.out.println(hasTwoSum);
	}

	// we will check all node value with the whole tree
	private static boolean twoSum3(TNode root, TNode node, int k) {
		if (node == null) return false;
		// we have added (k != 2 * node.data) because otherwise find would match with the node again
		// which is a wrong answer
		if ((k != 2 * node.data) && search(root, k - node.data)) return true;
		return twoSum3(root, node.left, k) || twoSum3(root, node.right, k);
	}

	// it is a simple search function to find k in BST
	public static boolean search(TNode root, int target) {
		if (root == null) return false;
		if (root.data > target) return search(root.left, target);
		else if (root.data < target) return search(root.right, target);
		else return true;
	}


	// so rather than storing the whole list, we can iterate once from front and once
	// from back, and meanwhile we can calculate.
	// we know how to iterate a BST, so we can similarly derive how can iterate the list from back
	private static void type2() {
		TNode root = TNode.makeBST(15);
		int k = 25;
		boolean hasTwoSum = twoSum2(root, k);
		System.out.println(hasTwoSum);
	}

	// we will use two pointers, front and end for traversing the BST
	// it is same as two sum problem in an array
	private static boolean twoSum2(TNode root, int k) {
		BSTIterator front = new BSTIterator(root, true);
		BSTIterator back = new BSTIterator(root, false);
		int start = front.next();
		int end = back.next();
		while (start < end) {
			int sum = start + end;
			if (sum == k) return true;
			if (sum < k) start = front.next();
			else end = back.next();
		}
		return false;
	}

	private static class BSTIterator {
		private final Stack<TNode> stack = new Stack<>();
		private final boolean isForward;

		public BSTIterator(TNode root, boolean isForward) {
			this.isForward = isForward;
			push(root);
		}

		private void push(TNode root) {
			while (root != null) {
				stack.push(root);
				root = isForward ? root.left : root.right;
			}
		}

		public int next() {
			TNode root = stack.pop();
			push(isForward ? root.right : root.left);
			return root.data;
		}

		public boolean hasNext() {
			return !stack.isEmpty();
		}

	}

	// this is similar to two sum problems in an array,
	// so we can store the inorder traversal in a list
	// which will be sorted
	// and then we can apply same two sum problem in the inorder list
	private static void type1() {
		TNode root = TNode.makeBST(15);
		int k = 25;
		boolean hasTwoSum = twoSum1(root, k);
		System.out.println(hasTwoSum);
	}

	private static boolean twoSum1(TNode root, int k) {
		List<Integer> inorder = new ArrayList<>();
		inorder(root, inorder);
		int start = 0, end = inorder.size() - 1;
		while (start < end) {
			int sum = inorder.get(start) + inorder.get(end);
			if (sum == k) return true;
			else if (sum < k) start++;
			else end--;
		}
		return false;
	}

	private static void inorder(TNode root, List<Integer> inorder) {
		if (null == root) return;
		inorder(root.left, inorder);
		inorder.add(root.data);
		inorder(root.right, inorder);
	}

}
