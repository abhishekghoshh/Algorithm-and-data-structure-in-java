package com.problems.graph;

import com.util.GraphUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.util.GraphUtil.graphBuilder;
import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/topological-sort/1
 * https://www.naukri.com/code360/problems/topological-sorting_973003
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=73sneFXuTEg&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=22
 * 
 * https://takeuforward.org/data-structure/kahns-algorithm-topological-sort-algorithm-bfs-g-22/
 */
public class KahnAlgorithm {

	public static void main(String[] args) {
		type1();
	}

	// TODO check one more time if it still confuses you
	private static void type1() {
		int v = 6;
		List<List<Integer>> adjacencyList = graphBuilder()
				.nodes(v)
				.start(0).end()
				.start(1).end()
				.start(2).end(3)
				.start(3).end(1)
				.start(4).end(0, 1)
				.start(5).end(0, 2)
				.buildList();
		GraphUtil.print(adjacencyList);

		// first, we will calculate indegree of all the nodes, if any node has no indegree
		// then it is the starting nodes, and we can assume that from these nodes there are edges to other nodes
		int[] indegree = new int[v];
		for (List<Integer> nodes : adjacencyList)
			for (int node : nodes) indegree[node]++;

		// if the in degree of a node is 0, that means that is the starting node
		// so, we will add it to the queue, as our starting point of the BFS
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < v; i++)
			if (indegree[i] == 0) queue.offer(i);

		int[] answer = new int[v];
		int index = 0;
		// everytime we will decrease the indegree of the adjacent node,let's say node 5 has edge from 2 and 4,
		// so indegree[5] = 2 indegree[5] will 0 when we have discovered 2 and 4 successfully
		while (!queue.isEmpty()) {
			int start = queue.poll();
			answer[index++] = start;
			for (int end : adjacencyList.get(start)) {
				// indegree denotes there is an edge between start -> end, and
				// as we are traversing it, we will decrease it
				indegree[end]--;
				// we will add them in queue when the indegree of the specific node become 0
				// that means we have already discovered all is previous nodes.
				// and it was that last edge to end node, so now we will add it to the queue
				// so the queue will eventually have the nodes in sorted manner
				if (indegree[end] == 0) queue.offer(end);
			}
		}
		print(answer);
	}

}
