package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.List;

import static com.ds.binarytree.TNode.NULL;

/*
 * Problem link :
 * https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
 * https://www.codingninjas.com/codestudio/problems/time-to-burn-tree_630563
 * https://practice.geeksforgeeks.org/problems/burning-tree/1
 * https://www.codingninjas.com/studio/problems/time-to-burn-tree_1469067
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=2r5wLmQfD6g&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=32
 * 
 * 
 * 
 */
public class MinimumTimeTakenToBurnDownBinaryTree {
	// Given a binary tree and a node data called target. Find the minimum time
	// required to burn the complete binary tree if the target is set on fire. It is
	// known that in 1 second, all nodes connected to a given node get burned. That
	// is its left child, right child, and parent.
	// Note: The tree contains unique values.
	// There is a problem of the least common ancestor
	// same as PrintAllNodesInBinaryTreeAtDistanceKFromTargetNode problem
	public static void main(String[] args) {
		// TODO NOTE the solution of Striver is very complex and time-consuming.
		type1();
		type2();
		type3();
		type4();
	}

	private static int max = 0;

	// This is the best solution possible
	private static void type4() {
		TNode root = TNode.withNodes(1, 5, 3, NULL, 4, 10, 6, NULL, NULL, 9, 2);
		int start = 3;
		height(root, start);
		System.out.println(max);
	}

	// here the height and distance is calculated simultaneously
	// once we find the target we will return -1
	// here minus distance is regarded as distance from root to target
	// positive distance means the height
	private static int height(TNode root, int target) {
		if (root == null) return 0;
		int left = height(root.left, target);
		int right = height(root.right, target);
		// if the target node is found, then we will return the negative distance
		if (root.data == target) {
			max = Math.max(left, right);
			return -1;
		}
		// if the left is less than 0, then the target is on the left side,
		// and (-left) is the distance from the target.
		// so on the right side we got the height of the right side tree,
		// and in left side we got the distance from the target
		// the total will be right + (-left)
		// after calculation of the max, we will return (left-distance + 1)
		//  as the target is on the left, and we incremented it
		if (left < 0) {
			max = Math.max(max, right - left);
			return left - 1;
		}
		// same as the left side of calculation
		// if the right is less than 0, then the target is on the right side,
		// and (-right) is the distance from the target.
		// so on the left side we got the height of the left side tree,
		// and in right side we got the distance from the target
		// the total will be left + (-right)
		// after calculation of the max, we will return (right-distance + 1)
		//  as the target is in the right, and we incremented it
		if (right < 0) {
			max = Math.max(max, left - right);
			return right - 1;
		}
		// if both left and right is positive means we are yet to find the target,
		// we will simply return the max height + 1
		return Math.max(left, right) + 1;
	}

	// this is a very optimized approach
	// here we will find the target in the tree, and on the same time we will
	// calculate the height of the other side of the target node
	private static void type3() {
		TNode root = TNode.withNodes(1, 5, 3, NULL, 4, 10, 6, NULL, NULL, 9, 2);
		int target = 3;
		Data data = new Data();
		findTarget3(root, target, data);
		System.out.println(data.maxTime);
	}

	private static int findTarget3(TNode root, int target, Data data) {
		if (null == root) return -1;
		if (root.data == target) {
			data.maxTime = Math.max(height(root.left), height(root.right));
			return 1;
		}
		int leftDistance = findTarget3(root.left, target, data);
		if (leftDistance != -1) {
			data.maxTime = Math.max(data.maxTime, leftDistance + height(root.right));
			return leftDistance + 1;
		}
		int rightDistance = findTarget3(root.right, target, data);
		if (rightDistance != -1) {
			data.maxTime = Math.max(data.maxTime, rightDistance + height(root.left));
			return rightDistance + 1;
		}
		return -1;
	}

	static class Data {
		int maxTime = Integer.MIN_VALUE;
	}

	// same as previous
	// here we will simplify some things
	// we will use a normal list to store the node in reverse.
	// we are adding the nodes into the list from target node then
	// the parent node and likewise the root node.
	// so with the normal list also we will get the nodes in reverse
	// also we do not need to store the direction of target node if it is left or right.
	// we can check parents to get are they left or right
	private static void type2() {
		TNode root = TNode.withCount(31);
		int target = 5;

		List<TNode> traversal = new ArrayList<>();
		findTarget2(root, target, traversal);
		TNode child = traversal.get(0);
		TNode parent = null;
		int maxLevel = Math.max(height(child.left), height(child.right));
		for (int i = 1; i < traversal.size(); i++) {
			parent = traversal.get(i);
			if (parent.left == child)
				maxLevel = Math.max(height(parent.right) + i, maxLevel);
			else
				maxLevel = Math.max(height(parent.left) + i, maxLevel);
			child = parent;
		}
		System.out.println(maxLevel);
	}

	private static boolean findTarget2(TNode root, int target, List<TNode> traversal) {
		if (root == null) return false;
		// either root is the target or target is found either its left side or right side
		if ((root.data == target)
				|| findTarget2(root.left, target, traversal)
				|| findTarget2(root.right, target, traversal)) {
			traversal.add(root);
			return true;
		}
		return false;
	}

	// this is also optimized approach
	// store all the parents in stack
	private static void type1() {
		TNode root = TNode.withCount(31);
		int target = 5;

		List<Pair> traversalList = new ArrayList<>();
		findTarget1(root, target, traversalList);
		// we know the first item will be the target node
		Pair pair = traversalList.get(0);
		TNode parent = pair.parent;
		int max = Math.max(height(parent.left), height(parent.right));

		for (int distanceFromTarget = 1; distanceFromTarget < traversalList.size(); distanceFromTarget++) {
			pair = traversalList.get(distanceFromTarget);
			parent = pair.parent;
			if (pair.isTargetInLeft)
				max = Math.max(height(parent.right) + distanceFromTarget, max);
			else
				max = Math.max(height(parent.left) + distanceFromTarget, max);
		}
		System.out.println(max);
	}

	private static int height(TNode node) {
		if (null == node) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	private static boolean findTarget1(TNode root, int target, List<Pair> traversalList) {
		if (null == root) return false;
		if (root.data == target) {
			// for the target node, isTargetInLeft value will not be required later
			// here true value is a dummy value
			traversalList.add(new Pair(root, true));
			return true;
		}
		// checking the left side
		if (findTarget1(root.left, target, traversalList)) {
			traversalList.add(new Pair(root, true));
			return true;
		}
		// checking the right side
		if (findTarget1(root.right, target, traversalList)) {
			traversalList.add(new Pair(root, false));
			return true;
		}
		return false;
	}

	static class Pair {
		TNode parent;
		boolean isTargetInLeft;

		public Pair(TNode parent, boolean isTargetInLeft) {
			this.parent = parent;
			this.isTargetInLeft = isTargetInLeft;
		}
	}

}
