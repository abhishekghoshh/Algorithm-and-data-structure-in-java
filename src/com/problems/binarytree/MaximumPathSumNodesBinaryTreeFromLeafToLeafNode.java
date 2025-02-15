package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
 * https://www.naukri.com/code360/problems/maximum-path-sum-between-two-leaves_794950
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=ArNyupe-XH0&list=PL_z_8CaSLPWfxJPz2-YKqL9gXWdgrhvdn&index=5
 * 
 * https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
 * 
 * */
public class MaximumPathSumNodesBinaryTreeFromLeafToLeafNode {

	public static void main(String[] args) {
		type1();
	}


	// this is brute force but it is an efficient approach
	private static void type1() {
		TNode root = TNode.withNodes(6, -9, -10);
		long ans = findMaxSumPath(root);
		System.out.println(ans);
	}

	static long max = Long.MIN_VALUE;

	public static long findMaxSumPath(TNode root) {
		max = Long.MIN_VALUE;
		traverse(root);
		return max != Long.MIN_VALUE ?
				max : -1;
	}


	// as we are taking from the leaf, so we cannot discard any value
	private static int traverse(TNode root) {
		// if this is null, then it will return 0
		if (null == root) return 0;
		// if this is a leaf node, then it will return the node value
		if (null == root.left && null == root.right) return root.data;

		// at this point, either of sides must be non-zero
		int left = traverse(root.left);
		int right = traverse(root.right);

		// if both of its left and right children are non-empty, that means
		// it is a proper parent node with at least one child in its both sides.
		// we could check now if the path is the max path or not
		if (null != root.left && null != root.right)
			max = Math.max(max, root.data + left + right);

		// lastly, we will return the max out of left and right
		return root.data + Math.max(left, right);
	}
}
