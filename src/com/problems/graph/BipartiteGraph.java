package com.problems.graph;

import com.util.PrintUtl;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem link :
 * https://leetcode.com/problems/is-graph-bipartite/
 * https://www.geeksforgeeks.org/problems/bipartite-graph/1
 * https://www.naukri.com/code360/problems/check-bipartite-graph-_920551
 * 
 * Solution link :
 * BFS:
 * https://www.youtube.com/watch?v=-vu34sct1g8&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=17
 * https://takeuforward.org/graph/bipartite-graph-bfs-implementation/
 *
 * DFS:
 * https://www.youtube.com/watch?v=KG5YFfR0j8A&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=18
 * https://takeuforward.org/graph/bipartite-graph-dfs-implementation/
 *
 */

public class BipartiteGraph {

	/*
	 * A bipartite graph is a graph where we can assign 2 different color to all the nodes such that
	 * no 2 adjacent node has the same color.
	 * Simple linear graph is always a bipartite graph.
	 * Problem arises when there is a loop exists, but if there is then depend on the node count in that loop
	 * we can say if it is bipartite or not
	 *
	 *
	 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
	 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
	 * More formally, for each v in graph[u], there is an undirected edge between node u and node v.
	 *
	 * The graph has the following properties:
	 * 1. There are no self-edges (graph[u] does not contain u).
	 * 2. There are no parallel edges (graph[u] does not contain duplicate values).
	 * 3. If v is in graph[u], then u is in graph[v] (the graph is undirected).
	 * 4. The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
	 *
	 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B
	 * such that every edge in the graph connects a node in set A and a node in set B.
	 *
	 * Return true if and only if it is bipartite.
	 * */
	// TODO To check if the graph is bipartite or not, we will color the adjacent nodes with +1 and -1
	//  if we can successfully then that means the graph can be divided into two group such that,
	//  one node of any group will always go to the any node of the second group
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// using DFS
	// TODO complete this question using cycle detection of a directed graph [If possible]
	private static void type3() {
		int numCourses = 4;
		int[][] prerequisites = {
				{1, 0},
				{2, 0},
				{3, 1},
				{3, 2}
		};
		int[] order = findOrder3(numCourses, prerequisites);
		PrintUtl.print(order);
	}

	// TODO complete this question using cycle detection of a directed graph
	public static int[] findOrder3(int numCourses, int[][] prerequisites) {
		int[][] graph = new int[numCourses][numCourses];
		return null;
	}

	// using DFS
	// TODO Our work is to color the nodes with two colors +1 and -1
	//  such that if one node is having + color then the adjacent node will not have the + color.
	// 	we will start DFS from every uncolored node, and mark that node to + color then,
	//  we will traverse the adjacent nodes and mark them with exactly opposite color
	//  if any point of time we see the adjacent node is already visited and have the same color,
	//  then we can say that we can not color the graph
	private static void type2() {
		int[][] adjacencyList = {
				{1, 2, 3},
				{0, 2},
				{0, 1, 3},
				{0, 2}
		};
		boolean isBipartite = isBipartite2(adjacencyList);
		System.out.println(isBipartite);
	}

	private static boolean isBipartite2(int[][] adjacencyList) {
		int v = adjacencyList.length;
		int[] colors = new int[v];
		// start from all components
		for (int i = 0; i < v; i++)
			// we will check if the ith node is already colored or not
			// if not then we will start DFS from that node
			if (colors[i] == 0 && !canColor2(i, 1, colors, adjacencyList))
				return false;
		// lastly, we are return true which means graph can be colored
		return true;
	}

	private static boolean canColor2(int start, int color, int[] colors, int[][] adjacencyList) {
		colors[start] = color;
		// finding all the adjacent node
		for (int end : adjacencyList[start]) {
			// anything other than 0 means the node is already colored
			// adjacent node color is opposite, so we don't have to do anything
			if (colors[end] == -color) continue;
			// if the adjacent color is the same, then we will directly return false
			if (colors[end] == color) return false;
			// else we will start dfs from the adjacent node with the opposite color.
			// if that DFS call returns false, then also we will return false
			if (!canColor2(end, -color, colors, adjacencyList)) return false;
		}
		// lastly, we are returning true as no DFS call returned false
		// and the graph can be colored with two colors
		return true;
	}

	// using BFS
	// TODO Our work is to color the nodes with two colors +1 and -1
	//  such that if one node is having + color then the adjacent node will not have the + color.
	// 	we will start BFS from every uncolored node, and mark that node to + color then,
	//  we will traverse the adjacent nodes and mark them with exactly opposite color
	//  if any point of time we see the adjacent node is already visited and have the same color,
	//  then we can say that we can not color the graph
	private static void type1() {
		int[][] adjacencyList = {
				{1, 2, 3},
				{0, 2},
				{0, 1, 3},
				{0, 2}
		};
		boolean isBipartite = isBipartite1(adjacencyList);
		System.out.println(isBipartite);
	}

	private static boolean isBipartite1(int[][] adjacencyList) {
		int v = adjacencyList.length;
		int[] colors = new int[v];
		// start from all components
		for (int i = 0; i < v; i++)
			// we will check if the ith node is already colored or not
			// if not then we will start BFS from that node
			if (colors[i] == 0 && !canColor1(i, adjacencyList, colors))
				return false;
		// lastly, we are return true which means graph can be colored
		return true;
	}

	// this is using BFS
	private static boolean canColor1(int node, int[][] adjacencyList, int[] colors) {
		// we will initialize the node color with 1, and add that node to the queue
		colors[node] = 1;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(node);
		// we will start bfs from the node
		while (!queue.isEmpty()) {
			int start = queue.poll();
			int color = colors[start];
			for (int end : adjacencyList[start]) {
				// anything other than 0 means the node is already colored
				// adjacent node color is opposite, so we don't have to do anything
				if (colors[end] == -color) continue;
				// if the color is same in adjacent node, then we will return false
				if (colors[end] == color) return false;
				// so the adjacent node is not yet colored, so we will add
				// opposite color to the adjacent node and add that to queue
				queue.offer(end);
				colors[end] = -color;
			}
		}
		return true;
	}

}
