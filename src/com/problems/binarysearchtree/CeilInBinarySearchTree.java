package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/implementing-ceil-in-bst/1
 * https://www.naukri.com/code360/problems/ceil-from-bst_920464
 *
 * Solution link :
 * https://www.youtube.com/watch?v=KSsk8AhdOZA&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=42
 *
 * https://takeuforward.org/binary-search-tree/ceil-in-a-binary-search-tree/
 */
public class CeilInBinarySearchTree {


	public static void main(String[] args) {
		type1();
		type2();
	}

	// optimized approach
	private static void type2() {
		TNode root = TNode.makeBST(2, 3, 4, 5, 6, 7, 9, 10, 11, 13, 14);
		int key = 8;

		int ceil = getCeil(root, key);
		System.out.println(ceil);
	}

	private static int getCeil(TNode root, int target) {
		int ceil = Integer.MAX_VALUE;

		// depending on the target value, we are deciding that which side should we go
		while (null != root) {
			if (root.data == target) return root.data;
			else if (target < root.data) {
				// if the target is lesser than the root value,
				// then root can be the ceil
				ceil = root.data;
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return ceil;
	}

	// for a valid BST the ceil of a value will be the inorder successor of the element.
	// as the inorder traversal will give us the sorted list,
	// we can just traverse on that list, or we can also do a binary search on that to get the ceiling element
	private static void type1() {
		TNode root = TNode.makeBST(2, 3, 4, 5, 6, 7, 9, 10, 11, 13, 14);
		int key = 8;
		List<Integer> inorder = new ArrayList<>();
		buildInorder(root, inorder);
		// we can do a binary search to get the answer
	}

	private static void buildInorder(TNode root, List<Integer> inorder) {
		if (root == null) return;
		buildInorder(root.left, inorder);
		inorder.add(root.data);
		buildInorder(root.right, inorder);
	}


}
