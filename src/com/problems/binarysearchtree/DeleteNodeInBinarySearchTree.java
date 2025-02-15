package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

/*
 * Problem link :
 * https://leetcode.com/problems/delete-node-in-a-bst/description/
 * https://www.geeksforgeeks.org/problems/delete-a-node-from-bst/1
 * https://www.naukri.com/code360/problems/delete-node-in-bst_920381
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=kouxiP_H5WE&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=45
 * https://www.youtube.com/watch?v=LFzAoJJt92M
 *
 * https://takeuforward.org/binary-search-tree/delete-a-node-in-binary-search-tree/
 * https://neetcode.io/solutions/delete-node-in-a-bst
 */
public class DeleteNodeInBinarySearchTree {

	public static void main(String[] args) {
		type1();
		type2();
	}


	// iterative way
	// first we are searching the node which is to be deleted
	private static void type2() {
		TNode root = TNode.withNodes(5, 3, 6, 2, 4, TNode.NULL, 7);
		root = deleteNode2(root, 3);
		PrintUtl.levelOrder(root);
	}


	public static TNode deleteNode2(TNode root, int target) {
		// if the root is null, then we do not have to do anything
		if (null == root) return null;
		// if the current root is the target, then we will delete the node
		if (root.data == target) return deleteNode(root);
		// we are also keeping track of the parent node
		TNode node = root, parent = null;
		// we need the parent node as we need to know that current node is in which side
		// because we need to assign a node after deleting the target node
		while (null != node) {
			// if the current node is the target node then we will use the parent node
			if (target == node.data) {
				if (parent.left == node)
					parent.left = deleteNode(node);
				else parent.right = deleteNode(node);
				return root;
			}
			// going to the left or right based on where target may reside
			parent = node;
			if (target < node.data) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return root;
	}

	private static void type1() {
		TNode root = TNode.withNodes(5, 3, 6, 2, 4, TNode.NULL, 7);
		root = deleteNode1(root, 3);
		PrintUtl.levelOrder(root);
	}

	// recursive way
	private static TNode deleteNode1(TNode root, int target) {
		// if the current node is null, then will return null,
		// this case will come only when there is no target
		if (null == root) return null;
		// if the current node is target, then we will delete the current node
		// and return the new node
		if (target == root.data) return deleteNode(root);
		// if the target is lesser than the root, then we will go to the left else right
		if (target < root.data) root.left = deleteNode1(root.left, target);
		else root.right = deleteNode1(root.right, target);
		return root;
	}


	// TODO In short we are going to the in order successor of the current node
	// we are passing the node which is to be deleted
	// if any of its child node is null then we are returning other one
	// if both of the children node is non-empty
	// then we know that all the left nodes is lesser than right node,
	// so we can do one of 2 ways
	// either we can attach the right subtree to the right of the highest node left
	// subtree, or we can attach the left subtree to the left of the lowest of right
	// subtree, here we have used the 2nd approach
	private static TNode deleteNode(TNode root) {
		// if one of the branches of the current node is empty, then we will return the other one
		// it will also handle the case where both of the branches are null
		if (null == root.left || null == root.right)
			return (null != root.left) ? root.left : root.right;
		// we are going to right, which will be greater than the node
		TNode node = root.right;
		// now we go to as left as possible to get the least element
		while (node.left != null) node = node.left;
		// the node is the in order successor of the root
		// we will assign root left child to node left child, this will preserve the root left child
		node.left = root.left;
		// root right child will be the new root
		return root.right;
	}

}
