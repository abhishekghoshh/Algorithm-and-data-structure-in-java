package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.*;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
 * https://www.codingninjas.com/codestudio/problems/893110
 * https://www.codingninjas.com/studio/problems/bottom-view-of-binary-tree_893110
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=0FtVY6I4pB8&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=24
 * 
 * https://takeuforward.org/data-structure/bottom-view-of-a-binary-tree/
 */
public class BottomViewOfBinaryTree {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// Same as type2 just we are using the level wise separation of the traversal.
	// Using iteration
	// using bfs or level wise traversal
	// as we are doing the level wise, so we are sure that the top most level is traversed first
	private static void type3() {
		TNode root = TNode.withCount(15);
		List<Integer> answer = new ArrayList<>();
		Map<Integer, Integer> map = new TreeMap<>();
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(root, 0));
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Pair pair = queue.poll();
				int axis = pair.axis;
				TNode node = pair.node;
				// if there is any value for that axis, then that will be replaced by the later level,
				// unlike the top view where we were adding the node data only when the axis was not in the key.
				// here we will replace the axis value every time
				// as it we go to level wise, so can directly replace it
				map.put(axis, node.data);
				if (node.left != null) queue.add(new Pair(node.left, axis - 1));
				if (node.right != null) queue.add(new Pair(node.right, axis + 1));
			}
		}
		// find the min axis and add the answer from the axis
		int minAxis = Integer.MAX_VALUE;
		for (int axis : map.keySet())
			if (minAxis > axis) minAxis = axis;

		while (map.containsKey(minAxis))
			answer.add(map.get(minAxis++));
		System.out.println(answer);
	}

	// using BFS or level wise traversal
	// using iteration
	private static void type2() {
		TNode root = TNode.withCount(15);
		List<Integer> answer = new ArrayList<>();
		// we can also use the treemap to traverse the node in a sorted manner,
		// but using hashmap is more efficient
		Map<Integer, Integer> map = new HashMap<>();
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(root, 0));
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			int axis = pair.axis;
			TNode node = pair.node;
			// if there is any value for that axis, then that will be replaced by the later level,
			// unlike the top view where we were adding the node data only when the axis was not in the key.
			// here we will replace the axis value every time
			// as it we go to level wise, so can directly replace it
			map.put(axis, node.data);
			if (node.left != null) queue.add(new Pair(node.left, axis - 1));
			if (node.right != null) queue.add(new Pair(node.right, axis + 1));
		}
		// find the min axis and add the answer from the axis
		int minAxis = Integer.MAX_VALUE;
		for (int axis : map.keySet())
			if (minAxis > axis) minAxis = axis;
		while (map.containsKey(minAxis))
			answer.add(map.get(minAxis++));
		System.out.println(answer);
	}

	public static class Pair {
		public int axis;
		public TNode node;

		public Pair(TNode node, int axis) {
			this.axis = axis;
			this.node = node;
		}
	}

	// using DFS
	// almost the same as top view,
	// this approach is very naive
	private static void type1() {
		TNode root = TNode.withCount(15);
		Map<Integer, Pair2> map = new HashMap<>();
		traverse(root, map, 0, 0);
		int minAxis = Integer.MAX_VALUE;
		for (int axis : map.keySet())
			if (minAxis > axis) minAxis = axis;
		List<Integer> answer = new ArrayList<>();
		while (map.containsKey(minAxis))
			answer.add(map.get(minAxis++).val);
		System.out.println(answer);
	}

	private static void traverse(TNode root, Map<Integer, Pair2> map, int axis, int level) {
		if (null == root) return;
		// just the opposite condition of top view
		if (!map.containsKey(axis) || (map.containsKey(axis) && map.get(axis).level <= level))
			map.put(axis, new Pair2(root.data, level));
		traverse(root.left, map, axis - 1, level + 1);
		traverse(root.right, map, axis + 1, level + 1);
	}

	public static class Pair2 {
		public int level;
		public int val;

		public Pair2(int val, int level) {
			this.level = level;
			this.val = val;
		}
	}
}
