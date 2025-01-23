package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 * https://neetcode.io/problems/valid-binary-search-tree
 * https://www.geeksforgeeks.org/problems/check-for-bst/1
 * https://www.naukri.com/code360/problems/check-bst_5975
 *
 * Solution link :
 * https://www.youtube.com/watch?v=f-sj7I5oXEI&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=47
 * https://www.youtube.com/watch?v=s6ATEkipzow
 *
 * https://takeuforward.org/binary-search-tree/check-if-a-tree-is-a-binary-search-tree-or-binary-tree/
 * https://neetcode.io/solutions/validate-binary-search-tree
 */
public class CheckIfTreeIsBinarySearchTree {

	public static void main(String[] args) {
		type1();
	}

	// we know one logic of a binary search tree
	// left side will be lower than the root and right side will be higher than the root.
	// so, we will create an imaginary boundary any lowest number and any highest number,
	// and every node we visit we will change shrink the boundary
	private static void type1() {
		TNode root = TNode.makeBST(15);
		boolean isValidBst = isValidBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
		System.out.println(isValidBst);
	}

	private static boolean isValidBst(TNode root, long min, long max) {
		// if the root is null, then we do not need to check
		if (null == root) return true;
		// if the root is lesser equal to min or higher equal to max, then it broke the condition
		if (root.data <= min || root.data >= max) return false;
		// now for the left subtree root is the upper limit
		// for the right subtree, root is the lower limit
		return isValidBst(root.left, min, root.data)
				&& isValidBst(root.right, root.data, max);
	}

}
