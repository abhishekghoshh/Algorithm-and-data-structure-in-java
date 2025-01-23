package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/same-tree/description/
 * https://neetcode.io/problems/same-binary-tree
 * https://www.naukri.com/code360/problems/799364
 *
 * Solution link :
 * https://www.youtube.com/watch?v=BhuvF_-PWS0&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=19
 * https://www.youtube.com/watch?v=vRbbcKXCxOw
 * 
 * https://takeuforward.org/data-structure/check-if-two-trees-are-identical/
 * https://neetcode.io/solutions/same-tree
 */
public class CheckTwoTreesAreIdenticalOrNot {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		TNode root1 = TNode.withCount(20);
		TNode root2 = TNode.withCount(20);
		boolean isIdentical = isSameTree(root1, root2);
		System.out.println(isIdentical);
	}

	private static boolean isSameTree(TNode root1, TNode root2) {
		// if both nodes are null, then both are identical
		if (null == root1 && null == root2) return true;
		// if either one node is null, then the trees are not identical
		if (null == root1 || null == root2) return false;
		// if both nodes have some value, then first we will check the values,
		// or then it will check left subtree for both trees and then it will check right subtree
		return root1.data == root2.data
				&& isSameTree(root1.left, root2.left)
				&& isSameTree(root1.right, root2.right);
	}

}
