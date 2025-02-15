package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

import static com.util.PrintUtl.*;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/binary-search-trees_8160443
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=p7-9UvDQZ3w&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=40
 *
 * https://takeuforward.org/binary-search-tree/introduction-to-binary-search-trees/
 */
public class IntroductionToBinarySearchTree {

	// left < root < right
	// generally BST does not contain any duplicate,
	// but we can change that
	// we can change add an extra variable like count or frequency to check the
	// current number of occurrences of any number in BST
	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int[] inOrder = {2, 3, 5, 8, 9, 10};
		TNode root = buildInOrder(inOrder, 0, inOrder.length - 1);
		levelOrder(root);
		preOrder(root);
		inOrder(root);
		postOrder(root);
	}

	private static TNode buildInOrder(int[] inOrder, int left, int right) {
		if (left > right) return null;
		if (left == right) return new TNode(inOrder[left]);
		int mid = left + (right - left) / 2;
		TNode leftN = buildInOrder(inOrder, left, mid - 1);
		TNode rightN = buildInOrder(inOrder, mid + 1, right);
		return new TNode(inOrder[mid], leftN, rightN);
	}

	// for a valid BST the inorder of the BST will be in sorted order
	private static void type1() {
		int[] order = {2, 3, 5, 8, 9, 10};
		boolean isValidBST = isValidBST(order);
		System.out.println(isValidBST);
	}

	private static boolean isValidBST(int[] order) {
		int prev = Integer.MIN_VALUE;
		for (int num : order) {
			// if prev is greater than the current number, then that means it is not a valid BST
			if (prev >= num) return false;
			prev = num;
		}
		return true;
	}

}
