package com.problems.binarytree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link :
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 * https://www.naukri.com/code360/problems/construct-binary-tree-from-inorder-and-postorder-traversal_1266106
 * https://www.geeksforgeeks.org/problems/tree-from-postorder-and-inorder/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=LgLRTaEMRVc&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=36
 * https://www.youtube.com/watch?v=vm63HuIU7kw
 * 
 * https://takeuforward.org/data-structure/construct-binary-tree-from-inorder-and-postorder-traversal/
 * https://neetcode.io/solutions/construct-binary-tree-from-inorder-and-postorder-traversal
 */
public class ConstructBinaryTreeFromInorderAndPostorder {

	public static void main(String[] args) {
		type1();
		type2();
	}


	// todo this is same intuition as the previous type
	// The intuition is if we go root then right and left,
	// so we don't have to pass the post-order position variable in the recursion.
	// We can just make the global postOrder Index.Every time we hit a node.
	// We just decrement the index, and the index will be root node of the right subtree
	// that is why we will build the root then right subtree then left subtree
	private static void type2() {
		int[] postorder = {9, 15, 7, 20, 3};
		int[] inorder = {9, 3, 15, 20, 7};
		TNode root = buildTree2(postorder, inorder);
		PrintUtl.postOrder(root);
		PrintUtl.inOrder(root);
	}

	static int pos;

	private static TNode buildTree2(int[] postorder, int[] inorder) {
		int n = postorder.length;
		pos = n - 1;
		// we are building a map to find the inOrder index in O(1)
		Map<Integer, Integer> map = new HashMap<>();
		// putting (item,inorder index) in a map
		for (int i = 0; i < n; i++)
			map.put(inorder[i], i);
		// here we will no longer carry post order index
		return makeTree2(0, n - 1, postorder, map);
	}

	static TNode makeTree2(int start, int end, int[] postorder, Map<Integer, Integer> map) {
		if (start > end) return null;
		// if start and end are same, then it has only one node, so we can directly return that
		if (start == end) return new TNode(postorder[pos--]);
		// first, we will find the root node from the post-order index
		// and decrement the post order index for the next right subtree
		int root = postorder[pos--];
		int rootI = map.get(root);
		// for the post-order we will first go to right child first then the left child
		// because the post-order sequence is left <- right <- root
		TNode right = makeTree2(rootI + 1, end, postorder, map);
		TNode left = makeTree2(start, rootI - 1, postorder, map);
		return new TNode(root, left, right);
	}

	// recursively
	// postorder and inorder consist of unique values.
	// todo we know that postorder traversal is like => left - right - root
	// 	we know one thing that we can get root from postOrder in O(1) as it is the last item in the list
	//  and in inorder the seq is like => left - root - right
	//  now we know root so we can divide the inorder list but we need inorder index of the root
	//  for that we can precompute (num, inorder index) in a map
	// so we can make a map to find the inorder index in O(1)
	// we will start from the preOrder because in preOrder we get the root node in 0th element,
	// then from that node we will get the left tree size and right tree size from the inOrder
	private static void type1() {
		int[] postorder = { 9, 15, 7, 20, 3 };
		int[] inorder = { 9, 3, 15, 20, 7 };
		TNode root = buildTree1(postorder, inorder);
		PrintUtl.postOrder(root);
		PrintUtl.inOrder(root);
	}

	private static TNode buildTree1(int[] postorder, int[] inorder) {
		int n = postorder.length;
		// we are building a map to find the inOrder index in O(1)
		Map<Integer, Integer> map = new HashMap<>();
		// putting (item,inorder index) in a map
		for (int i = 0; i < n; i++)
			map.put(inorder[i], i);
		// we will start with inorder start and end index, and we will start from the postorder last index
		return makeTree1(0, n - 1, n - 1, postorder, map);
	}

	private static TNode makeTree1(int start, int end, int pos, int[] postOrder, Map<Integer, Integer> map) {
		if (start > end) return null;
		// if start and end are same, then it has only one node, so we can directly return that
		if (start == end) return new TNode(postOrder[pos]);
		// finding the root node value
		int root = postOrder[pos];
		// from the map we are getting the node's position in the inorder traversal
		int rootI = map.get(root);
		// again, we will recursively construct it's left and right child.
		// here we will create the right subtree first.
		// for the right tree postorderPos root index will be (current postOrder root index + 1)
		// for the right subtree inorder start will be (current inorder root index + 1)
		// for the right subtree inorder end will be the same inorder end
		TNode right = makeTree1(rootI + 1, end, pos - 1, postOrder, map);
		// we have the right tree count, so we can easily construct the left tree recursion
		// postOrder root will be current postOrder root position - right subtree count - 1
		// as in the post-order the sequence will be like left <- right <- root
		int rightSubtreeNodeCount = end - rootI;
		TNode left = makeTree1(start, rootI - 1, pos - rightSubtreeNodeCount - 1, postOrder, map);
		return new TNode(root, left, right);
	}

}
