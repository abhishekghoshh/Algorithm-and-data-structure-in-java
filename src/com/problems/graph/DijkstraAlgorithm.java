package com.problems.graph;

import java.util.*;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=V6H1qAeB-l4&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=32
 * https://www.youtube.com/watch?v=PATgNiuTP20&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=33
 * https://www.youtube.com/watch?v=3dINsjyfooY&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=34
 * 
 * https://takeuforward.org/data-structure/dijkstras-algorithm-using-priority-queue-g-32/
 * https://takeuforward.org/data-structure/dijkstras-algorithm-using-set-g-33/
 * 
 */
public class DijkstraAlgorithm {
	// Given a weighted, undirected and connected graph of V vertices and an adjacency list adj.
	// In that the adj[i] is a list of lists containing two integers where the first integer
	// of each list j denotes there is edge between 'i' and 'j'
	// and second integers corresponds to the weight of that edge.
	// You are given the source vertex S and You to Find the shortest distance of all the vertex's
	// from the source vertex S. You have to return a list of integers denoting
	// the shortest distance between each node and Source vertex S.
	// Note: The Graph doesn't contain any negative weight cycle.
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// this is a little update from the previous one
	// we will not store index and distance in the queue
	// we will only store the index
	// because we can derive the distance from the distance array
	private static void type3() {
		int v = 3;
		int s = 0;
		List<List<List<Integer>>> adj = List.of(
				List.of(
						List.of(1, 1),
						List.of(2, 6)
				),
				List.of(
						List.of(2, 3),
						List.of(0, 1)
				),
				List.of(
						List.of(1, 3),
						List.of(0, 6)
				)
		);
		int[] distance = new int[v];
		// setting all the values to infinity
		Arrays.fill(distance, Integer.MAX_VALUE);
		// distance from source to source is 0
		distance[s] = 0;

		// in the heap, we will store the next point only,
		// but we will use the distance array for minHeap property
		// we can use Queue and use the distance array similarly
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> distance[p]));
		minHeap.offer(s);

		while (!minHeap.isEmpty()) {
			int start = minHeap.poll();
			int prevDis = distance[start]; // taking prevDis from the distance array
			for (List<Integer> node : adj.get(start)) {
				int end = node.get(0), dis = node.get(1);
				// we will relax the edge and update new distance for the end node
				if (prevDis + dis < distance[end]) {
					distance[end] = prevDis + dis;
					minHeap.offer(end);
				}
			}
		}
		print(distance);
	}

	// todo Dijkstra is always solved using Priority Queue, but it can be solved using Queue also
	//  we can use TreeSet but that will be a over kill
	// similar to the previous type, but here we will use a priority queue or a min heap.
	// queue will introduce multiple unnecessary edges, so we can use priority queue
	// time complexity of Dijkstra algorithm using Priority Queue will be 2e*log(v)
	// where e is the number of edges and v is the number of nodes
	// for a dense graph one node we can go to all v-1 nodes total edges will be v*(v-1)
	// so e is almost equals to v^2
	// At max there will be all edges in the priority queue
	// and we have to relax all that edges
	// so the inner while loop can go e times and for max polling time of Priority queue will be log(e)
	// so total time will be e*log(e) which is e*log(v^2) => 2e*log(v)
	private static void type2() {
		int v = 3;
		int s = 0;
		List<List<List<Integer>>> adj = List.of(
				List.of(
						List.of(1, 1),
						List.of(2, 6)
				),
				List.of(
						List.of(2, 3),
						List.of(0, 1)
				),
				List.of(
						List.of(1, 3),
						List.of(0, 6)
				)
		);
		int[] distance = new int[v];
		// setting all the values to infinity
		Arrays.fill(distance, Integer.MAX_VALUE);
		// distance from source to source is 0
		distance[s] = 0;

		// in the heap, we will store the next point and distance to source.
		// the only difference from the previous is that we will use a priority queue
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(pair -> pair[1]));
		minHeap.offer(new int[] { s, 0 });

		while (!minHeap.isEmpty()) {
			int[] pair = minHeap.poll();
			int start = pair[0];
			int prevDis = pair[1];
			for (List<Integer> node : adj.get(start)) {
				int end = node.get(0), dis = node.get(1);
				// we will relax the edge and update new distance for the end node
				if (prevDis + dis < distance[end]) {
					distance[end] = prevDis + dis;
					minHeap.offer(new int[]{end, prevDis + dis});
				}
			}
		}
		print(distance);
	}

	// We will use a queue for storing the edges and current distance from the source,
	// and we will relax the edges everytime
	private static void type1() {
		int v = 3;
		int s = 0;
		List<List<List<Integer>>> adj = List.of(
				List.of(
						List.of(1, 1),
						List.of(2, 6)
				),
				List.of(
						List.of(2, 3),
						List.of(0, 1)
				),
				List.of(
						List.of(1, 3),
						List.of(0, 6)
				)
		);
		int[] distance = new int[v];
		// setting all the values to infinity
		Arrays.fill(distance, Integer.MAX_VALUE);
		// distance from source to source is 0
		distance[s] = 0;

		// in the queue, we will store the next point and distance to source
		Queue<int[]> minHeap = new LinkedList<>();
		minHeap.offer(new int[] { s, 0 });

		// we will start from the source node and explore all it's neighbor nodes and try to relax the edges.
		while (!minHeap.isEmpty()) {
			int[] pair = minHeap.poll();
			int start = pair[0];
			int prevDis = pair[1];
			for (List<Integer> node : adj.get(start)) {
				int dis = node.get(1);
				int end = node.get(0);
				if (prevDis + dis < distance[end]) {
					distance[end] = prevDis + dis;
					minHeap.offer(new int[]{end, prevDis + dis});
				}
			}
		}

		print(distance);
	}


}
