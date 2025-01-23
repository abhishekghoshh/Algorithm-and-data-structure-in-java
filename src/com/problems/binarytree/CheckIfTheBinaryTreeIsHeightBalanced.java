package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/balanced-binary-tree/description/
 * https://neetcode.io/problems/balanced-binary-tree
 * https://www.naukri.com/code360/problems/975497
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=Yt50Jfbd8Po&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=16
 * https://www.youtube.com/watch?v=QfJsau0ItOY
 * 
 * https://takeuforward.org/data-structure/check-if-the-binary-tree-is-balanced-binary-tree/
 * https://neetcode.io/solutions/balanced-binary-tree
 */
public class CheckIfTheBinaryTreeIsHeightBalanced {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// little optimized
	// it will calculate height in the same time and return that, but in between we will tweak the implementation
	// we will check if the abs(left-height - right-height) > 1 then that is not balanced, and we will return -1
	// now the tree can return -1 as height so after call height for left and right subtree
	// we will first check if it is not 1, then only we will do the remaining things
	private static void type2() {
		TNode root = TNode.withNodes(3, 9, 20, TNode.NULL, TNode.NULL, 15, 7);
		boolean isBalanced = isBalanced2(root);
		System.out.println(isBalanced);
	}

	private static boolean isBalanced2(TNode root) {
		return heightAndBalanceCheck(root) != -1;
	}

	private static int heightAndBalanceCheck(TNode root) {
		if (null == root) return 0;
		int leftH = heightAndBalanceCheck(root.left);
		// left subtree is not balanced
		if (leftH == -1) return -1;
		// right subtree is not balanced
		int rightH = heightAndBalanceCheck(root.right);
		if (rightH == -1) return -1;
		// if the height difference is greater than 1 then the tree is not balanced
		if (Math.abs(leftH - rightH) > 1) return -1;
		// now we will return the current height
		return 1 + Math.max(leftH, rightH);
	}

	private static void type1() {
		TNode root = TNode.withNodes(3, 9, 20, TNode.NULL, TNode.NULL, 15, 7);
		boolean isBalanced = isBalanced(root);
		System.out.println(isBalanced);
	}

	// first, it will check left height and right height,
	// and if that are balanced or not
	// then it will go to its left and right
	public static boolean isBalanced(TNode root) {
		if (root == null) return true;
		return Math.abs(height(root.left) - height(root.right)) <= 1
				&& isBalanced(root.left)
				&& isBalanced(root.right);
	}

	static int height(TNode root) {
		if (root == null) return 0;
		return 1 + Math.max(
				height(root.left),
				height(root.right)
		);
	}


}
