package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/recover-binary-search-tree/
 * https://practice.geeksforgeeks.org/problems/fixed-two-nodes-of-a-bst/1
 * https://www.codingninjas.com/studio/problems/fix-bst_873137
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=ZWGW7FminDM&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=53
 * 
 * 
 */
public class RecoverBinarySearchTree {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	static TNode a = null;
	static TNode b = null;
	static TNode prev = new TNode(Integer.MIN_VALUE);

	// This derived the inspiration from the previous type
	// there we were first doing the inorder traversal then again iterating on that.
	// We could simply find the out-of-place nodes from the inorder traversal.
	// we will have to use a
	// TODO check it later one more time
	//  this solution is little complex
	// we can directly store the answer while traversing the tree
	private static void type3() {
		TNode root = TNode.makeBST(15);
		TNode p = root.search(6);
		TNode q = root.search(9);
		p.data = 9;
		q.data = 6;
		PrintUtl.inOrder(root);
		recoverTree(root);
		PrintUtl.inOrder(root);
	}

	public static void recoverTree(TNode root) {
		inorder(root);
		int temp = a.data;
		a.data = b.data;
		b.data = temp;
	}

	// it will do inorder traversal and also identify the nodes
	// we know the inorder traversal of a binary search tree will traverse the nodes
	// in an increasing order
	// we will use that to identify two out-of-place nodes
	static void inorder(TNode root) {
		if (root == null) return;
		inorder(root.left);
		if (root.data < prev.data && a == null) {
			// If this is a first violation, we can assume that consequent nodes are out of place
			// like for a sequence 9 7 8 6,
			// In the first violation we will mark 9 and 7 because that could also be an answer.
			a = prev;
			b = root;
		} else if (root.data < prev.data) {
			// But if this is a second violation, then we know that consequent nodes were failing
			// because this node is in out of place
			// second violation will occur when it comes to 6
			b = root;
		}
		// Mark this node as previous
		prev = root;
		inorder(root.right);
	}

	// The inorder of the BST will give us the sorted list,
	// we can store the list and traverse
	// then we will find the nodes in wrong places
	private static void type2() {
		TNode root = TNode.makeBST(15);
		TNode p = root.search(6);
		TNode q = root.search(9);
		p.data = 9;
		q.data = 6;

		List<TNode> inorder = new ArrayList<>();
		inorder(root, inorder);

		int curr, prev, next;
		int n = inorder.size();
		TNode first = null, second = null;


		// in the sorted list prev < curr < next property always holds.
		// so if we find anything other than this condition, then we surely know that the
		// item is in the wrong position.
		// we have to traverse the inorder list two times, one from the front and one from the back.
		// because of the first item where the rule breaks, the neighbor of the item will also break the rule,
		// so we have to traverse the list again from the back.
		// let's take an example 1 5 3 4 2 6
		// here out of place items are 2 and 5, but see 3 and 4 also broke the rule
		// so the items which broke the rule for the first time will be our culprit same for traversing from back
		for (int i = 0; i < n; i++) {
			curr = inorder.get(i).data;
			prev = (i == 0) ? Integer.MIN_VALUE : inorder.get(i - 1).data;
			next = (i == (n - 1)) ? Integer.MAX_VALUE : inorder.get(i + 1).data;
			if (prev > curr || curr > next) {
				first = inorder.get(i);
				break;
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			curr = inorder.get(i).data;
			prev = (i == 0) ? Integer.MIN_VALUE : inorder.get(i - 1).data;
			next = (i == (n - 1)) ? Integer.MAX_VALUE : inorder.get(i + 1).data;
			if (prev > curr || curr > next) {
				second = inorder.get(i);
				break;
			}
		}
		// now we will swap the item
		swap(first, second);

		PrintUtl.inOrder(root);
	}

	private static void swap(TNode first, TNode second) {
		int temp = first.data;
		first.data = second.data;
		second.data = temp;
	}

	private static void inorder(TNode root, List<TNode> inorder) {
		if (null == root) return;
		inorder(root.left, inorder);
		inorder.add(root);
		inorder(root.right, inorder);
	}


	// Brute force approach
	// traverses the BST and save it in the list and then sort it.
	// We know the in order of any BST will be sorted
	// now do inorder traversal and use a pointer on the list
	// set the node.val = list.get(ptr)
	// for two times the node value will be changed
	// other times it will not make any effect
	private static void type1() {
	}


}
