package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/floor-in-bst/1
 * https://www.naukri.com/code360/problems/floor-from-bst_625868
 * https://www.naukri.com/code360/problems/floor-from-bst_920457
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=xm_W1ub-K-w&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=43
 *
 * https://takeuforward.org/binary-search-tree/floor-in-a-binary-search-tree/
 */
public class FloorInBinarySearchTree {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		TNode root = TNode.makeBST(2, 3, 4, 5, 6, 7, 9, 10, 11, 13, 14);
		int key = 8;

		int floor = getFloor(root, key);
		System.out.println(floor);
	}

	private static int getFloor(TNode root, int target) {
		int floor = Integer.MIN_VALUE;
		while (null != root) {
			if (root.data == target) return root.data;
			else if (target < root.data) {
				root = root.left;
			} else {
				// if the target is greater than the root
				// that means root can be a flooring element
				floor = root.data;
				root = root.right;
			}
		}
		return floor;
	}

	// for a valid BST the floor of a value will be the inorder predecessor of the element.
	// as the in order traversal will give us the sorted list,
	// we can just traverse on that list, or we can also do a binary search on that to get the flooring element
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
