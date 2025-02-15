package com.problems.graph;

import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/course-schedule-ii/description/
 * https://leetcode.com/problems/course-schedule-ii/solutions/293048/detecting-cycle-in-directed-graph-problem/
 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
 * https://www.codingninjas.com/studio/problems/detect-cycle-in-a-directed-graph-_920545
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=9twcmtQj4DU&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=19
 *
 * https://www.youtube.com/watch?v=uzVUw90ZFIg&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=15
 * 
 * https://takeuforward.org/data-structure/detect-cycle-in-a-directed-graph-using-dfs-g-19/
 * https://takeuforward.org/data-structure/detect-a-cycle-in-directed-graph-topological-sort-kahns-algorithm-g-23/
 */

public class CycleDetectionInDirectedGraph {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// TODO It will use DFS and single visited array instead of two arrays
	//  we will use 0 as not visited, 1 as visited and 2 as the path visited
	private static void type2() {
		int v = 10;
		List<List<Integer>> adjacencyList = List.of(
				List.of(1),
				List.of(2),
				List.of(3, 6),
				List.of(4),
				List.of(5),
				List.of(),
				List.of(4),
				List.of(8),
				List.of(9),
				List.of(7)
		);
		boolean hasCycle = isCyclic2(v, adjacencyList);
		System.out.println(hasCycle);
	}

	private static boolean isCyclic2(int v, List<List<Integer>> adjacencyList) {
		// we will use 0 as not visited, 1 as visited and 2 as the path visited
		int[] visited = new int[v];
		for (int i = 0; i < v; i++) {
			// check for all the component which is not visited yet
			if (visited[i] == 0 && hasCycle(i, visited, adjacencyList))
				return true;
		}
		return false;
	}

	private static boolean hasCycle(int start, int[] visited, List<List<Integer>> adjacencyList) {
		// visited and path visited
		visited[start] = 2;
		for (int end : adjacencyList.get(start)) {
			// if the node has been previously visited, but it has to be visited on the same path
			if (visited[end] == 2) return true;
			// when the node is not visited, then we call dfs from that node
			if (visited[end] == 0 && hasCycle(end, visited, adjacencyList))
				return true;
		}
		// resetting the path visited setting only visited
		visited[start] = 1;
		return false;
	}

	// TODO It will use DFS and two boolean array mark visited and path visited
	//  Check one more time if it still confuses you
	private static void type1() {
		int v = 10;
		List<List<Integer>> adjacencyList = List.of(
				List.of(1),
				List.of(2),
				List.of(3, 6),
				List.of(4),
				List.of(5),
				List.of(),
				List.of(4),
				List.of(8),
				List.of(9),
				List.of(7)
		);
		boolean hasCycle = isCyclic1(v, adjacencyList);
		System.out.println(hasCycle);
	}

	private static boolean isCyclic1(int v, List<List<Integer>> adjacencyList) {
		boolean[] visited = new boolean[v];
		// path visited is to track the nodes in a single path
		// because visited array will not be sufficed
		// if 1 points to 2 and 3 , 2 and 3 both points to 4
		// so 1 to 4 will from a cycle if we consider the visited array only,
		// and we will choose dfs here because we want to as deep as possible and in
		// this path if we come to any of the previous visited point then that will form a cycle,
		boolean[] pathVisited = new boolean[v];
		for (int i = 0; i < v; i++) {
			// check for all the component which is not visited yet
			if (!visited[i] && hasCycle(i, visited, pathVisited, adjacencyList))
				return true;
		}
		return false;
	}

	private static boolean hasCycle(int start, boolean[] visited, boolean[] pathVisited,
									List<List<Integer>> adjacencyList) {
		// at the start, we set visited and path visited to true
		visited[start] = true;
		// path visited to true because we are keeping the track which path our dfs is following
		pathVisited[start] = true;
		// traverse for adjacent nodes
		for (int end : adjacencyList.get(start)) {
			// if the node has been previously visited, but it has to be visited on the same path
			if (pathVisited[end]) return true;
			// when the node is not visited, then we call dfs from that node
			if (!visited[end] && hasCycle(end, visited, pathVisited, adjacencyList))
				return true;
		}
		// before returning false, we are setting a path visited to false as dfs call is
		// completed so the path from this node is traversed and no cycle found
		pathVisited[start] = false;
		return false;
	}


}
