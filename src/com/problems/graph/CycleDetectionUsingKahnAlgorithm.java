package com.problems.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.util.GraphUtil.graphBuilder;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
 * https://www.naukri.com/code360/problems/detect-cycle-in-a-directed-graph_1062626
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=iTBaI90lpDQ&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=23
 * 
 * https://takeuforward.org/data-structure/kahns-algorithm-topological-sort-algorithm-bfs-g-22/
 * https://takeuforward.org/data-structure/detect-a-cycle-in-directed-graph-topological-sort-kahns-algorithm-g-23/
 */
public class CycleDetectionUsingKahnAlgorithm {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int v = 10;
		List<List<Integer>> adjacencyList =
				graphBuilder()
						.nodes(v)
						.edges(5)
						.edge(1, 2)
						.edge(2, 3)
						.edge(3, 4)
						.edge(4, 5)
						.edge(5, 1)
						.buildList();

		int[] inDegree = new int[v];
		for (List<Integer> nodes : adjacencyList)
			for (int node : nodes) inDegree[node]++;
		int size = 0;

		// if there is any cycle then there will be scenario that indegree will not be
		// zero for a node it can occur at start like 0->1->2->3->0
		// for this situation all the nodes will have in degree as 1
		// so the bfs will have no starting point
		// similarly it can occur at later point of time
		// like 0->1->2->3->1 here 0 has in degree as 0,
		// so we will have starting point but after traversing 0 there will not be any
		// other node for which indegree will be 0
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < v; i++)
			if (inDegree[i] == 0) queue.offer(i);

		while (!queue.isEmpty()) {
			int start = queue.poll();
			// we will increase the current node size everytime we poll node from queue
			size++;
			for (int node : adjacencyList.get(start)) {
				inDegree[node]--;
				// we will add them in queue when the indegree of the specific node become 0
				// that means we have already discovered all is previous nodes
				if (inDegree[node] == 0) queue.offer(node);
			}
		}
		// if node size is not v, then that means for some nodes it never went to in degree 0
		// so we can say that the graph has a cycle
		boolean hasCycle = size != v;
		System.out.println("hasCycle " + hasCycle);
	}
}
