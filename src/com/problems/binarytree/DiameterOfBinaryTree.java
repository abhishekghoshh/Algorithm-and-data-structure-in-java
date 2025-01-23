package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 * https://neetcode.io/problems/binary-tree-diameter
 * https://www.naukri.com/code360/problems/920552
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=Rezetez59Nk&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=17
 * https://www.youtube.com/watch?v=zmPj_Ee3B8c&list=PL_z_8CaSLPWfxJPz2-YKqL9gXWdgrhvdn&index=3
 * https://www.youtube.com/watch?v=K81C31ytOZE
 * 
 * https://takeuforward.org/data-structure/calculate-the-diameter-of-a-binary-tree/
 * https://neetcode.io/solutions/diameter-of-binary-tree
 * */
public class DiameterOfBinaryTree {

	public static void main(String[] args) {
		type1();
	}


	// simple problem with simple intuition
	// we will compute height and side by we will compute the diameter,
	// but we need to store the diameter individually
	// we could either use a class level variable or send another object as a parameter in the method
	private static void type1() {
		TNode root = TNode.withCount(16);
		height(root);
		System.out.println(MAX_DIAMETER);
	}

	private static int MAX_DIAMETER = 0;

	private static int height(TNode root) {
		if (null == root) return 0;
		int leftH = height(root.left);
		int rightH = height(root.right);
		// we're just computing the diameter and updating the
		// diameter of a node is leftHeight + rightHeight
		MAX_DIAMETER = Math.max(MAX_DIAMETER, leftH + rightH);
		return 1 + Math.max(leftH, rightH);
	}

}
