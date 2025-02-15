package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Problem link :
 * https://leetcode.com/problems/boundary-of-binary-tree/#
 * https://www.codingninjas.com/codestudio/problems/boundary-traversal_790725
 * https://www.codingninjas.com/studio/problems/boundary-traversal-of-binary-tree_790725
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=0ca1nvR0be4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=21
 * 
 * 
 * https://takeuforward.org/data-structure/boundary-traversal-of-a-binary-tree/
 */
public class BoundaryTraversalInBinaryTree {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// Iterative way
	private static void type2() {
		TNode root = TNode.withCount(21);
		List<Integer> boundary = new ArrayList<>();
		if (!isLeaf(root)) boundary.add(root.data);
		addLeftBoundary(root, boundary);
		addLeaves(root, boundary);
		addRightBoundary(root, boundary);
		System.out.println(boundary);
	}


	static void addLeftBoundary(TNode root, List<Integer> boundary) {
		TNode node = root.left;
		while (node != null) {
			if (!isLeaf(node)) boundary.add(node.data);
			node = (node.left != null) ? node.left : node.right;
		}
	}

	static void addLeaves(TNode root, List<Integer> boundary) {
		if (root == null) return;
		if (isLeaf(root)) {
			boundary.add(root.data);
			return;
		}
		addLeaves(root.left, boundary);
		addLeaves(root.right, boundary);
	}

	static void addRightBoundary(TNode root, List<Integer> boundary) {
		TNode node = root.right;
		Stack<Integer> stack = new Stack<>();
		while (node != null) {
			if (!isLeaf(node)) stack.add(node.data);
			node = (node.right != null) ? node.right : node.left;
		}
		while (!stack.isEmpty()) boundary.add(stack.pop());
	}


	// recursive approach
	private static void type1() {
		TNode root = TNode.withCount(21);
		List<Integer> answer = new ArrayList<>();
		leftView(root, answer);
		bottomView(root, answer);
		// as for right view it will be reversed, so we are creating a stack
		// later we will iterate through it and add the node in the final array
		Stack<Integer> stack = new Stack<>();
		rightView(root, stack);
		// as we don't need the root, we will not iterate till empty
		// we will loop until the size is 1
		while (stack.size() > 1) answer.add(stack.pop());
		System.out.println(answer);
	}

	private static void leftView(TNode root, List<Integer> answer) {
		// we are also not taking the leaf node as it will be added in the bottom view
		if (null == root || isLeaf(root)) return;
		answer.add(root.data);
		if (null != root.left) leftView(root.left, answer);
		else leftView(root.right, answer);
	}

	private static void bottomView(TNode root, List<Integer> answer) {
		if (null == root) return;
		// we are only adding for the leaf node
		if (isLeaf(root)) {
			answer.add(root.data);
			return;
		}
		bottomView(root.left, answer);
		bottomView(root.right, answer);
	}


	private static void rightView(TNode root, Stack<Integer> stack) {
		// we are also not taking the leaf node as it will be added in the bottom view
		if (null == root || isLeaf(root)) return;
		stack.push(root.data);
		if (null != root.right) rightView(root.right, stack);
		else rightView(root.left, stack);
	}


	static Boolean isLeaf(TNode root) {
		return (root.left == null) && (root.right == null);
	}

}
