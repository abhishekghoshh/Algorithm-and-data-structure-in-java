package com.problems.binarytree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

import java.util.List;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/introduction-to-trees/1
 * https://practice.geeksforgeeks.org/problems/binary-tree-representation/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=_ANrF3FJm7I&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=2
 * https://www.youtube.com/watch?v=ctCpP0RFDFc&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=3
 * https://www.youtube.com/watch?v=hyLyW7rP24I&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=4
 *
 *
 * https://takeuforward.org/binary-tree/introduction-to-trees/
 * https://takeuforward.org/binary-tree/binary-tree-representation-in-c/
 * https://takeuforward.org/binary-tree/binary-tree-representation-in-java/
 */

public class IntroductionToBinaryTree {

	// TODO check these types later as well
	// Types of binary tree
	// 1. Full binary tree
	// 2. Complete binary tree
	// 3. Perfect binary tree
	// 4. Balanced binary tree
	// 5. Degenerate tree
	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		List<Integer> v = List.of(1, 2, 3, 4, 5, 6, 7);
		TNode root0 = new TNode(v.get(0));
		TNode root1 = new TNode(v.get(1));
		TNode root2 = new TNode(v.get(2));
		TNode root3 = new TNode(v.get(3));
		TNode root4 = new TNode(v.get(4));
		TNode root5 = new TNode(v.get(5));
		TNode root6 = new TNode(v.get(6));
		root0.left = root1;
		root0.right = root2;
		root1.left = root3;
		root1.right = root4;
		root2.left = root5;
		root2.right = root6;

		PrintUtl.print(root0);
	}

}
