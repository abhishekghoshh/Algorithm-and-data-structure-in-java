package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

/*
 * Problem link :
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/description/
 * https://www.geeksforgeeks.org/problems/insert-a-node-in-a-bst/1
 * https://www.naukri.com/code360/problems/insert-into-a-binary-search-tree_1279913
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=FiFiNvM29ps&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=44
 * https://www.youtube.com/watch?v=Cpg8f79luEA
 *
 * https://takeuforward.org/binary-search-tree/insert-a-given-node-in-binary-search-tree/
 * https://neetcode.io/solutions/insert-into-a-binary-search-tree
 *
 */
public class InsertIntoABinarySearchTree {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// as this is an iterative approach we need to have a track of the parent node as well
	private static void type2() {
		TNode root = TNode.withNodes(4, 2, 7, 1, 3);
		int val = 5;
		root = insertIntoBST2(root, val);
		PrintUtl.levelOrder(root);
	}

	private static TNode insertIntoBST2(TNode root, int val) {
		if (root == null) return new TNode(val);
		// iteratively going to the specific node and alsa updating the parent node
		TNode parent = root, node = root;
		while (null != node) {
			parent = node;
			if (val < node.val) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		if (val < parent.val) parent.left = new TNode(val);
		else parent.right = new TNode(val);
		return root;
	}


	// recursive way of adding nodes to the leaf
	// recursively traverse to the specific position
	// each time it will either go to the left side if target < root-val
	// or go to the right side if root-val < target
	private static void type1() {
		TNode root = TNode.withNodes(4, 2, 7, 1, 3);
		PrintUtl.levelOrder(root);
		root = insertIntoBST1(root, 5);
		PrintUtl.levelOrder(root);

	}

	public static TNode insertIntoBST1(TNode root, int target) {
		// if the root is null, then we will assign a new node
		if (root == null) return new TNode(target);
		// if the target is lesser than the root, then we will go to the left side
		// we will go until the root is null
		if (target < root.data)
			root.left = insertIntoBST1(root.left, target);
		else
			root.right = insertIntoBST1(root.right, target);
		return root;
	}
}
