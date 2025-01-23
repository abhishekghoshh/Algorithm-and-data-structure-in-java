package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 * https://www.geeksforgeeks.org/problems/search-a-node-in-bst/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=KcNt6v_56cc&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=41
 * 
 * https://takeuforward.org/data-structure/search-in-a-binary-search-tree-2/
 */
public class SearchInBinarySearchTree {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// iterative way
	private static void type2() {
		TNode root = TNode.makeBST(15);
		System.out.println(root.searchBST(2));

		TNode node = searchBST2(root, 2);
		System.out.println(node);
	}

	private static TNode searchBST2(TNode root, int target) {
		while (null != root) {
			if (target == root.data) return root;
			else if (target < root.data) root = root.left;
			else root = root.right;
		}
		return null;
	}

	// recursive way
	private static void type1() {
		TNode root = TNode.makeBST(15);
		System.out.println(root.searchBST(2));

		TNode node = searchBST1(root, 2);
		System.out.println(node);
	}

	public static TNode searchBST1(TNode root, int val) {
		if (null == root || root.data == val) return root;
		return val < root.data ?
				searchBST1(root.left, val) :
				searchBST1(root.right, val);
	}
}
