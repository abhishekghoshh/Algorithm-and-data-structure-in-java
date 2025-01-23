package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://www.interviewbit.com/problems/path-to-given-node/
 * https://www.codingninjas.com/studio/problems/all-root-to-leaf-paths-in-binary-tree._983599
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=fmflMqVOC7k&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=27
 * 
 * https://takeuforward.org/data-structure/print-root-to-node-path-in-a-binary-tree/
 */
public class PrintRootToNodePath {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		TNode root = TNode.withCount(100);
		int target = 56;
		List<Integer> list = allRootToLeaf(root, target);
		System.out.println(list);
	}

	public static List<Integer> allRootToLeaf(TNode root, int target) {
		List<Integer> list = new ArrayList<>();
		find(root, target, list);
		reverse(list);
		return list;
	}

	private static boolean find(TNode root, int target, List<Integer> list) {
		// if node is null then we are unable to find the target
		if (null == root) return false;
		// if the current node is target or either in left subtree or right subtree we have
		// found the target, then we will add the node to the current list and return true
		if (root.data == target
				|| (find(root.left, target, list)
				|| find(root.right, target, list))) {
			list.add(root.data);
			return true;
		}
		// if we have not found the target, then we will simply return false
		return false;
	}

	private static void reverse(List<Integer> list) {
		int n = list.size();
		for (int i = 0; i < n / 2; i++) {
			int num1 = list.get(i);
			int num2 = list.get(n - i - 1);
			list.set(i, num2);
			list.set(n - i - 1, num1);
		}
	}

}
