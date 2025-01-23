package com.problems.binarysearchtree;

import com.ds.binarytree.TNode;

import java.util.Arrays;

import static com.util.PrintUtl.preOrder;

/*
 * Problem link :
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * https://practice.geeksforgeeks.org/problems/preorder-to-postorder4423/1
 * https://www.codingninjas.com/studio/problems/construct-bst-from-preorder-traversal_2689307
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=UmJT3j26t1I&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=49
 * 
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	private static void type4() {
		int[] preorder = { 8, 5, 1, 7, 10, 12 };
		TNode root = bstFromPreorder3(preorder, Integer.MAX_VALUE, new Data());
		preOrder(root);
	}

	// here also we are using the same, but here we are calculating the index and
	// using the parent node value and upper bound.
	// if the value is more than the root, that means this is the right subtree
	// TODO check striver solution
	private static TNode bstFromPreorder3(int[] preorder, int bound, Data data) {
		if (data.index == preorder.length || preorder[data.index] > bound)
			return null;
		TNode root = new TNode(preorder[data.index++]);
		root.left = bstFromPreorder3(preorder, root.data, data);
		root.right = bstFromPreorder3(preorder, bound, data);
		return root;
	}

	static class Data {
		public int index = 0;
	}

	// same as a previous type,
	// but here we are pre-computing the next greater element in O(2n) time
	// in final make bst function we can just use the next greater element array
	private static void type3() {
		int[] preorder = { 8, 5, 1, 7, 10, 12 };
		int[] ngi = nextGreaterIndex(preorder);
		TNode root = bstFromPreorder2(preorder, ngi, 0, preorder.length - 1);
		preOrder(root);
	}

	public static int[] nextGreaterIndex(int[] preorder) {
		int n = preorder.length;
		int[] ngi = new int[n];
		int[] stack = new int[n];
		int top = -1;
		for (int i = n - 1; i >= 0; i--) {
			while (top != -1 && preorder[i] >= preorder[stack[top]]) top--;
			ngi[i] = (top == -1) ? n : stack[top];
			stack[++top] = i;
		}
		return ngi;
	}

	public static TNode bstFromPreorder2(int[] preorder, int[] ngi, int start, int end) {
		if (start == end) return new TNode(preorder[start]);
		else if (start > end || start < 0 || end >= preorder.length)
			return null;
		int mid = ngi[start];
		TNode root = new TNode(preorder[start]);
		root.left = bstFromPreorder2(preorder, ngi, start + 1, mid - 1);
		root.right = bstFromPreorder2(preorder, ngi, mid, end);
		return root;
	}

	private static void type2() {
		int[] preorder = { 8, 5, 1, 7, 10, 12 };
		TNode root = bstFromPreorder1(preorder, 0, preorder.length - 1);
		preOrder(root);
	}

	// we know that for a preorder the sequence is root - left - right
	// for a bst left < root < right
	// now we can make some assumptions like
	// so the first element is root in the sequence
	// and the right higher element for root will be the starting of right subtree
	// and starting index + 1 to higher element index - 1 will be the left subtree
	// now we can make a recursive call on array
	private static TNode bstFromPreorder1(int[] preorder, int start, int end) {
		if (start == end) return new TNode(preorder[start]);
		if (start > end || start < 0 || end >= preorder.length)
			return null;
		TNode root = new TNode(preorder[start]);
		int mid = start + 1;
		// finding the right higher value index
		while (mid <= end) {
			if (preorder[mid] > root.data) break;
			mid++;
		}
		root.left = bstFromPreorder1(preorder, start + 1, mid - 1);
		root.right = bstFromPreorder1(preorder, mid, end);
		return root;
	}

	// brute force approach
	// we know one thing that if we know inorder and preorder/postorder of any tree 
	// we can successfully make a unique tree
	private static void type1() {
		int[] preorder = { 8, 5, 1, 7, 10, 12 };
		int n = preorder.length;
		int[] inorder = Arrays.copyOf(preorder, n);
		// we know that inorder is always sorted
		Arrays.sort(inorder);
		// now we have inorder as well as preorder traversal, so we can make a unique tree
	}

}
