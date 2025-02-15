package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 * https://neetcode.io/problems/binary-tree-maximum-path-sum
 * https://www.naukri.com/code360/problems/794950
 * https://www.naukri.com/code360/problems/maximum-sum-path-of-a-binary-tree._1214968
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=WszrfSwMz58&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=18
 * https://www.youtube.com/watch?v=Osz-Vwer6rw&list=PL_z_8CaSLPWfxJPz2-YKqL9gXWdgrhvdn&index=4
 * https://www.youtube.com/watch?v=Hr5cWUld4vU
 * 
 * https://takeuforward.org/data-structure/maximum-sum-path-in-binary-tree/
 * https://neetcode.io/solutions/binary-tree-maximum-path-sum
 * */
public class MaximumPathSumBinaryTreeFromAnyNodeToAnyNode {

	// A path in a binary tree is a sequence of nodes where each pair of adjacent
	// nodes in the sequence has an edge connecting them. A node can only appear in
	// the sequence at most once. Note that the path does not need to pass through
	// the root.
	// The path sum of a path is the sum of the node's values in the path.
	// Given the root of a binary tree, return the maximum path sum of any non-empty path.
	public static void main(String[] args) {
		type1();
	}


	// todo simple problem with simple intuition
	// it is kind of the height of the tree problem but here we need to
	// but instead of height
	private static void type1() {
		TNode root = TNode.withNodes(1, 2, 3, -2, -1);
		int ans = maxPathSum1(root);
		System.out.println(ans);
	}

	static int max1;

	public static int maxPathSum1(TNode root) {
		max1 = Integer.MIN_VALUE;
		maxPath1(root);
		return max1;
	}

	private static int maxPath1(TNode root) {
		if (null == root) return 0;
		// we are not considering leaf node always, as we need any node to any node sum
		// if leaf to leaf sum required, then we should not discard any value
		int left = maxPath1(root.left);
		if (left < 0) left = 0; // if left is negative, then we will discard left
		int right = maxPath1(root.right);
		if (right < 0) right = 0; // if right is negative, then we will discard right
		// max path root.value + left sum + right sum
		max1 = Math.max(max1, root.val + left + right);
		// at this time left or right is either 0 or any positive value, max path sum between a left path and right path
		return root.val + Math.max(left, right);
	}
}
