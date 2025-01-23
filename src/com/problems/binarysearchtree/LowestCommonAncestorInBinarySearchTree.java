package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * https://neetcode.io/problems/lowest-common-ancestor-in-binary-search-tree
 * https://www.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-bst/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=cX_kPV_foZc&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=48
 * https://www.youtube.com/watch?v=gs2LMfuOR9k
 * 
 * https://takeuforward.org/binary-search-tree/lca-in-binary-search-tree/
 * https://neetcode.io/solutions/lowest-common-ancestor-of-a-binary-search-tree
 *
 */
public class LowestCommonAncestorInBinarySearchTree {

	// todo Lowest Common Ancestor of a Binary Search Tree is quite easier than Lowest Common Ancestor of a Binary Tree
	//  in binary search tree we have left < root < right
	//  so if p and q both less than root so the common ancestor will be in the left
	//  if p and q both greater than root so common ancestor will be on the right
	//  the last condition is p and q are in opposite side, then the current node is then common ancestor
	public static void main(String[] args) {
		type1();
		type2();
	}


	// we will use the bst property left<root<right
	// same like iterative one
	// we will go left if root data is greater than both p and q.
	// we will go right if both p and q are bigger
	// while traversing anywhere if we find that p and q are in opposite side
	// that node will be the common ancestor recursive ways
	private static void type2() {
		TNode root = TNode.makeBST(15);
		TNode p = new TNode(7);
		TNode q = new TNode(15);
		TNode node = lca2(root, p, q);
		System.out.println(node);
	}

	private static TNode lca2(TNode root, TNode p, TNode q) {
		if (null == root) return null;
		if (root.data > p.data && root.data > q.data)
			// node is greater than both of them
			return lca2(root.left, p, q);
		else if (root.data < p.data && root.data < q.data)
			// node data is lesser than both of them
			return lca2(root.right, p, q);
		else
			// both p and q are in opposite side
			return root;
	}

	// we will use the bst property left<root<right
	// we will go left if root data is greater than both p and q.
	// we will go right if both p and q are bigger
	// while traversing anywhere if we find that p and q is in opposite side
	// that node will be the common ancestor
	// iterative ways
	private static void type1() {
		TNode root = TNode.makeBST(15);
		TNode p = new TNode(7);
		TNode q = new TNode(15);
		TNode ans = lca1(root, p, q);
		System.out.println(ans);
	}

	private static TNode lca1(TNode root, TNode p, TNode q) {
		while (null != root) {
			// root is greater than both of them
			if (root.data > p.data && root.data > q.data)
				root = root.left;
				// root data is lesser than both of them
			else if (root.data < p.data && root.data < q.data)
				root = root.right;
			else
				// both p and q are in opposite side
				return root;

		}
		return null;
	}

}
