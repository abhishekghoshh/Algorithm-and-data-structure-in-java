package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.LinkedList;
import java.util.Queue;

import static com.ds.binarytree.TNode.NULL;

/*
 * Problem link :
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * https://neetcode.io/problems/depth-of-binary-tree
 * https://www.naukri.com/code360/problems/height-of-binary-tree_4609628
 * https://www.naukri.com/code360/problems/841416
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=eD3tmO66aBA&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=15
 * https://www.youtube.com/watch?v=aqLTbtWh40E&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=5
 * https://www.youtube.com/watch?v=hTM3phVI6YQ
 *
 * https://takeuforward.org/data-structure/maximum-depth-of-a-binary-tree/
 * https://neetcode.io/solutions/maximum-depth-of-binary-tree
 * */
public class HeightOfBinaryTree {
	public static void main(String[] args) {
		type1();
		type2();
	}

	// level wise traversal using a queue
	// take a integer level variable and increment that in every iteration
	private static void type2() {
		TNode root = TNode.withNodes(3, 9, 20, NULL, NULL, 15, 7);
		int height = height2(root);
		System.out.println(height);

	}

	private static int height2(TNode root) {
		if (null == root) return 0;
		Queue<TNode> queue = new LinkedList<>();
		// as there is a root that is not null, that is why we are adding level as 1
		int level = 1;
		queue.offer(root);
		while (!queue.isEmpty()) {
			// n is the number of the node on that specific level,
			// polling all the nodes in that level
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				TNode node = queue.poll();
				if (null != node.left) queue.offer(node.left);
				if (null != node.right) queue.offer(node.right);
			}
			// after polling all the nodes
			// if the nodes are capable of adding its left or right node in the queue.
			// then queue will not be empty, and there should be a new level
			// if there is any node added in queue, that means there will be a new level
			if (!queue.isEmpty()) level++;
		}
		return level;
	}


	// using recursion,
	// it will use the recursion stack
	public static void type1() {
		TNode root = TNode.withNodes(3, 9, 20, NULL, NULL, 15, 7);
		int height = height1(root);
		System.out.println(height);
	}

	private static int height1(TNode root) {
		if (null == root) return 0;
		return 1 + Math.max(
				height1(root.left),
				height1(root.right)
		);
	}


}
