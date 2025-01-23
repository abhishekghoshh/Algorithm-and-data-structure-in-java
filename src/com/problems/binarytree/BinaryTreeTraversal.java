package com.problems.binarytree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/preorder-traversal/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=jmy0LaGET1I&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=5
 * 
 * https://takeuforward.org/binary-tree/binary-tree-traversal-inorder-preorder-postorder/
 */
public class BinaryTreeTraversal {

	public static void main(String[] args) {
		type1();
		type4();
	}

	// Level order traversal
	private static void type4() {
		TNode root = TNode.withCount(10);
		List<List<Integer>> answer = levelOrder(root);
		System.out.println(answer);
	}

	public static List<List<Integer>> levelOrder(TNode root) {
		List<List<Integer>> wrapList = new LinkedList<>();
		if (root == null) return wrapList;
		Queue<TNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int levelNum = queue.size();
			List<Integer> subList = new LinkedList<>();
			for (int i = 0; i < levelNum; i++) {
				if (queue.peek().left != null)
					queue.offer(queue.peek().left);
				if (queue.peek().right != null)
					queue.offer(queue.peek().right);
				subList.add(queue.poll().data);
			}
			wrapList.add(subList);
		}
		return wrapList;
	}
	// In order traversal
	// Pre order traversal
	// Post-order traversal
	private static void type1() {
		TNode root = TNode.withCount(10);
		List<Integer> inOrder = new ArrayList<>();
		inOrder(root, inOrder);
		List<Integer> preOrder = new ArrayList<>();
		preOrder(root, preOrder);
		List<Integer> postOrder = new ArrayList<>();
		postOrder(root, postOrder);

		PrintUtl.print(root);
		PrintUtl.print(inOrder, preOrder, postOrder);
	}


	static void inOrder(TNode root, List<Integer> inOrder) {
		if (root == null) return;
		inOrder(root.left, inOrder);
		inOrder.add(root.data);
		inOrder(root.right, inOrder);
	}

	static void preOrder(TNode root, List<Integer> preOrder) {
		if (root == null) return;
		preOrder.add(root.data);
		preOrder(root.left, preOrder);
		preOrder(root.right, preOrder);
	}

	static void postOrder(TNode root, List<Integer> postOrder) {
		if (root == null) return;
		postOrder(root.left, postOrder);
		postOrder(root.right, postOrder);
		postOrder.add(root.data);
	}

}
