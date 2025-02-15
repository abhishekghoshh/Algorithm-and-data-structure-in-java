package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * https://practice.geeksforgeeks.org/problems/count-number-of-nodes-in-a-binary-tree/1
 * https://www.codingninjas.com/studio/problems/nodes-in-complete-binary-tree_1281151
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=u-yWemKGWO0&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=33
 * 
 * https://takeuforward.org/binary-tree/count-number-of-nodes-in-a-binary-tree/
 */
public class CountTotalNodeInCompleteBinaryTree {

	// Given the root of a complete binary tree, return the number of the nodes in the tree.
	// According to Wikipedia, every level, except possibly the last, is
	// filled in a complete binary tree, and all nodes in the last level are as far left as possible.
	// It can have between 1 and 2h nodes inclusive at the last level h.
	// Design an algorithm that runs in less than O(n) time complexity.
	public static void main(String[] args) {
		type1();
		type2();

	}

	// out problem is to find the number of nodes in tree,
	// so we can apply any dfs or bfs to find the number of node
	// but by the problem statement it was said that the given tree is a complete
	// tree that means all nodes in the last level are as far left as possible,
	// so we could use that, we will go to all the tree
	// we will just check if left height = right height
	// if this property holds, then the current node is a complete tree
	// we can just return (2^h-1) as the number of nodes
	// else we can go to it's left and right node
	// but as per the problem statement as the tree is complete binary tree, so we
	// don't have to go too much to the left and right nodes to get the count
	private static void type2() {
		TNode root = TNode.withCount(16);
		int countNodes = countNodes1(root);
		System.out.println(countNodes);
	}

	public static int countNodes1(TNode root) {
		if (null == root) return 0;
		int lh = leftHeight(root);
		int rh = rightHeight(root);
		// we are using the bit wise operation to avoid math pow funtion
		// (1 << lh) - 1 is the same as the (2^h-1)
		if (lh == rh) return (1 << lh) - 1;
		return 1 + countNodes1(root.left) + countNodes1(root.right);
	}

	private static int leftHeight(TNode root) {
		int h = 0;
		while (null != root) {
			root = root.left;
			h++;
		}
		return h;
	}

	private static int rightHeight(TNode root) {
		int h = 0;
		while (null != root) {
			root = root.right;
			h++;
		}
		return h;
	}


	// brute force approach
	// TODO this is the normal approach to get all the node count in a binary tree
	//  this approach not optimized than the type 2, though it took less time in leetcode
	//  here we are going as deep as possible to find all the nodes,
	private static void type1() {
		TNode root = TNode.withCount(16);
		int countNodes = countNodes2(root);
		System.out.println(countNodes);
	}

	public static int countNodes2(TNode root) {
		if (root == null) return 0;
		return 1 + countNodes2(root.left) + countNodes2(root.right);
	}


}
