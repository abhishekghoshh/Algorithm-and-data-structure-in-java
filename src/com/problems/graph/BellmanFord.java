package com.problems.graph;

import java.util.Arrays;
import java.util.List;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=0vVofAhAYjc&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=41
 * 
 * https://takeuforward.org/data-structure/bellman-ford-algorithm-g-41/
 */
public class BellmanFord {
	// Given a weighted, directed and connected graph of V vertices and E edges,
	// Find the shortest distance of all the vertex's from the source vertex S.
	// Note: If the Graph contains a negative cycle, then return an array consisting of only -1.
	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int v = 3;
		List<List<Integer>> edges = List.of(
				List.of(0, 1, 5),
				List.of(1, 0, 3),
				List.of(1, 2, -1),
				List.of(2, 0, 1)
		);
		int s = 2;
		int[] dis = bellmanFord(v, edges, s);
		print(dis);
	}

	// the intuition is, to find the minimum distance we have to relax all the edges, but how many times?
	// if there are n nodes, then at max all n nodes will be at a straight line, so at max that will have n-1 edges
	// and to relax n-1 edges, we will at max need n-1 iterations.
	// we will relax all edges n-1 times.
	// so, ideally, after n-1 operation, all the nodes will have minimum distance.
	// then we will again try to relax all the edges, if at this time any edge tries to relax
	// then we will know that we are fallen into a negative cycle
	static int[] bellmanFord(int v, List<List<Integer>> edges, int s) {
		int INF = (int) (1e8);
		int[] dis = new int[v];
		Arrays.fill(dis, INF);
		dis[s] = 0;

		// we will relax all the edges n-1 times because if all the vertices are in straight line,
		// then only they have at max n-1 edges
		for (int i = 0; i < v - 1; i++) {
			for (List<Integer> edge : edges) {
				int source = edge.get(0);
				int destination = edge.get(1);
				int distance = edge.get(2);
				// dis[source] is Integer.MAX_VALUE means source node itself is not relaxed
				if (dis[source] != INF && (dis[source] + distance < dis[destination])) {
					dis[destination] = dis[source] + distance;
				}
			}
		}
		// if there is any negative cycle, then some edge will relax again, so we will try to relax one more time
		for (List<Integer> edge : edges) {
			int source = edge.get(0);
			int destination = edge.get(1);
			int distance = edge.get(2);
			// dis[source] is Integer.MAX_VALUE means source node itself is not relaxed
			if (dis[source] != INF && (dis[source] + distance < dis[destination]))
				return new int[]{-1};
		}
		return dis;
	}
}
