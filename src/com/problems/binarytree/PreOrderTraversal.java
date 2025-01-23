package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.util.PrintUtl.print;
/*
 * Problem link :
 * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 * https://www.naukri.com/code360/problems/preorder-binary-tree_5948
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=RlUu72JrOCQ&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=6
 * https://www.youtube.com/watch?v=Bfqd8BsPVuw&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=10
 * https://www.youtube.com/watch?v=afTpieEZXck
 * 
 * https://takeuforward.org/data-structure/preorder-traversal-of-binary-tree/
 * https://neetcode.io/solutions/binary-tree-preorder-traversal
 */

public class PreOrderTraversal {
	public static void main(String[] args) {
		type1();
		type2();
	}

	// it is quite simple
	// because we have to print root -> left -> right
	// using iteration
	private static void type2() {
		TNode root = TNode.withCount(7);
		List<Integer> preOrder = preOrder(root);
		print(root);
		print(preOrder);
	}

	private static List<Integer> preOrder(TNode root) {
		List<Integer> preOrder = new ArrayList<>();
		Stack<TNode> stack = new Stack<>();
		stack.add(root);
		while (!stack.isEmpty()) {
			TNode node = stack.pop();
			// we will add the root here, then traverse its left and right
			preOrder.add(node.data);
			// We are pushing the right first then left because
			// on the next iteration left will be popped first
			if (null != node.right) stack.push(node.right);
			if (null != node.left) stack.push(node.left);
		}
		return preOrder;
	}

	// With recursion
	private static void type1() {
		TNode root = TNode.withCount(7);
		List<Integer> preOrder = new ArrayList<>();
		preOrder(root, preOrder);
		print(root);
		print(preOrder);
	}

	private static void preOrder(TNode root, List<Integer> preOrder) {
		if (null == root) return;
		preOrder.add(root.data);
		preOrder(root.left, preOrder);
		preOrder(root.right, preOrder);
	}
}
