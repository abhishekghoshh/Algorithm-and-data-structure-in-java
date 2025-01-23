package com.problems.binarytree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * Problem link :
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 * https://neetcode.io/problems/binary-tree-from-preorder-and-inorder-traversal
 * https://www.geeksforgeeks.org/problems/construct-tree-1/1
 * https://www.naukri.com/code360/problems/construct-binary-tree-from-inorder-and-preorder-traversal_920539
 *
 * Solution link :
 * https://www.youtube.com/watch?v=aZNaLrVebKQ&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=35
 * https://www.youtube.com/watch?v=ihj4IQGZ2zc
 * 
 * https://takeuforward.org/data-structure/construct-a-binary-tree-from-inorder-and-preorder-traversal/
 * https://neetcode.io/solutions/construct-binary-tree-from-preorder-and-inorder-traversal
 */
public class ConstructBinaryTreeFromInorderAndPreorder {
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}




	// recursively
	// here we will maintain two index one for preOrder and one for inOrder
	// TODO study later one more time
	private static void type3() {
		int[] preorder = {3, 9, 20, 15, 7};
		int[] inorder = {9, 3, 15, 20, 7};
		// makeTree method will recursively create the tree
		TNode root = buildTree3(preorder, inorder, Integer.MIN_VALUE);
		PrintUtl.preOrder(root);
		PrintUtl.inOrder(root);
	}

	private static int inOrderIndex = 0;
	private static int preOrderIndex = 0;

	private static TNode buildTree3(int[] preorder, int[] inorder, int stop) {
		if (preOrderIndex >= preorder.length) return null;

		if (inorder[inOrderIndex] == stop) {
			++inOrderIndex;
			return null;
		}
		TNode node = new TNode(preorder[preOrderIndex++]);
		node.left = buildTree3(preorder, inorder, node.data);
		node.right = buildTree3(preorder, inorder, stop);
		return node;
	}


	// todo same as previous but here we are using a class level variable rather than passing the preorder pos everytime
	//  as we are going we are incrementing the pos
	private static void type2() {
		int[] preorder = {3, 9, 20, 15, 7};
		int[] inorder = {9, 3, 15, 20, 7};
		TNode root = buildTree2(preorder, inorder);
		PrintUtl.preOrder(root);
		PrintUtl.inOrder(root);
	}

	private static TNode buildTree2(int[] preorder, int[] inorder) {
		int n = preorder.length;
		// we are building a map to find the inOrder index in O(1)
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++)
			map.put(inorder[i], i);
		// makeTree method will recursively create the tree
		return makeTree1(0, n - 1, preorder, map);
	}


	static int pos = 0;

	private static TNode makeTree1(int start, int end, int[] preorder, Map<Integer, Integer> map) {
		if (start > end) return null;
		// if start and end are same, then it has only one node, so we can directly return that
		if (start == end) return new TNode(preorder[pos++]);
		// first, we will find the root node from the post-order index
		// and decrement the post order index for the next right subtree
		int root = preorder[pos++];
		int rootI = map.get(root);
		TNode left = makeTree1(start, rootI - 1, preorder, map);
		// we have the left tree count, so we can easily construct the right tree recursion
		// preOrder root will be current preOrder root position + left subtree count + 1
		// as in the pre-order the sequence will be like root <- left <- right
		TNode right = makeTree1(rootI + 1, end, preorder, map);
		return new TNode(root, left, right);
	}

	// recursively
	// todo same as postorder question, here we get root from preorder[0] element
	// preorder and inorder consist of unique values.
	// so we can make a map to find the inorder index in O(1)
	// we will start from the preOrder because in preOrder we get the root node in 0th element,
	// then from that node we will get the left tree size and right tree size from the inOrder
	private static void type1() {
		int[] preorder = { 3, 9, 20, 15, 7 };
		int[] inorder = { 9, 3, 15, 20, 7 };
		TNode root = buildTree1(preorder, inorder);
		PrintUtl.preOrder(root);
		PrintUtl.inOrder(root);
	}

	private static TNode buildTree1(int[] preorder, int[] inorder) {
		int n = preorder.length;
		// we are building a map to find the inOrder index in O(1)
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++)
			map.put(inorder[i], i);
		// makeTree method will recursively create the tree
		return makeTree1(0, n - 1, 0, preorder, map);
	}


	// so we have to maintain three indices,
	// one for preOrder root position,
	// another from start node position of the inorder, and another is for end node position of the inorder.
	// first, we will get the root node value from the preOrder traversal
	private static TNode makeTree1(int start, int end, int pos, int[] preorder, Map<Integer, Integer> map) {
		if (start > end) return null;
		// if start and end are same, then it has only one node, so we can directly return that
		if (start == end) return new TNode(preorder[pos]);
		// finding the root node value
		int root = preorder[pos];
		// from the map we are getting the node's position in the inorder traversal
		int rootI = map.get(root);

		// again, we will recursively construct it's left and right child.
		// for the left tree preOrder root index will be (current preOrder root index + 1)
		// inorder start will be the same, but the ending node will be (current inorderRootIndex - 1)
		// because in inorder left tree will be previous to the root node
		TNode left = makeTree1(start, rootI - 1, pos + 1, preorder, map);
		// we have the left tree count, so we can easily construct the right tree recursion
		// preOrder root will be current preOrder root position + left subtree count + 1
		// as in the pre-order the sequence will be like root <- left <- right
		int leftCount = rootI - start;
		TNode right = makeTree1(rootI + 1, end, pos + leftCount + 1, preorder, map);
		return new TNode(root, left, right);
	}

}
