package com.problems.graph;

import com.util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/counting-graphs_8357237
 * https://www.codingninjas.com/studio/problems/creating-and-printing_1214551
 * https://practice.geeksforgeeks.org/problems/print-adjacency-list-1587115620/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=M3_pLsDdeuU&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn
 * https://www.youtube.com/watch?v=3oI-34aPMWM&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=2
 * https://www.youtube.com/watch?v=OsNklbh9gYI&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=3
 * 
 * https://takeuforward.org/graph/introduction-to-graph/
 * https://takeuforward.org/graph/graph-representation-in-c/
 */
public class GraphRepresentation {

	static Scanner scanner = FileUtil.inputFile();

	public static void main(String[] args) {
		maximumPossibleGraphsWithNVertices();
		printDirectedAdjacencyList();
		undirectedGraphUsingMatrix();
		undirectedGraphUsingAdjacencyList();
		directedGraphUsingMatrix();
		directedGraphUsingAdjacencyList();
		weightedGraphUsingMatrix();
		weightedGraphUsingAdjacencyList();
	}

	private static void maximumPossibleGraphsWithNVertices() {
		// Given an integer n representing the number of vertices.
		// Find out how many undirected graphs (not necessarily connected) can be constructed out of a
		// given n number of vertices.
		// Explanation: There are total n*(n-1)/2 possible edges.
		// For every edge, there are to possible options, either we pick it or don't pick.
		// So the total number of possible graphs is 2 ^ n(n-1)/2.

		int n = 5;
		long ans = (long) Math.pow(2, (double) (n * (n - 1)) / 2);
		System.out.printf("maximum number of graphs possible with " +
				"%d nodes are %d with the formula 2 ^ n*(n-1)/2 %n", n, ans);
	}

	private static void printDirectedAdjacencyList() {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		adj.add(new ArrayList<>(List.of(1, 4)));
		adj.add(new ArrayList<>(List.of(0, 2, 3, 4)));
		adj.add(new ArrayList<>(List.of(1, 3)));
		adj.add(new ArrayList<>(List.of(1, 2, 4)));
		adj.add(new ArrayList<>(List.of(0, 1, 3)));
		int v = 5;
		System.out.println("Below is the adjacency list representation");
		for (int i = 0; i < adj.size(); i++) {
			System.out.print(i + " -> ");
			System.out.print("[");
			for (int j = 0; j < adj.get(i).size(); j++) {
				System.out.print(adj.get(i).get(j));
				if (j != adj.get(i).size() - 1) System.out.print(", ");
			}
			System.out.print("]");
			System.out.println();
		}
	}

	private static void undirectedGraphUsingMatrix() {
		int n = scanner.nextInt();
		int e = scanner.nextInt();
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 0; i < e; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			graph[start][end] = 1;
			graph[end][start] = 1;
		}
		System.out.println("Undirected graph using matrix");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void undirectedGraphUsingAdjacencyList() {
		int n = scanner.nextInt();
		int e = scanner.nextInt();
		List<Integer>[] graph = new List[n + 1];
		for (int i = 1; i <= n; i++)
			graph[i] = new ArrayList<>();
		for (int i = 0; i < e; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			graph[start].add(end);
			graph[end].add(start);
		}
		System.out.println("Undirected graph using adjacency list");
		for (int i = 1; i <= n; i++) {
			System.out.println(i + " -> " + graph[i]);
		}
	}

	private static void directedGraphUsingMatrix() {
		int n = scanner.nextInt();
		int e = scanner.nextInt();
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 0; i < e; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			graph[start][end] = 1;
		}
		System.out.println("Directed graph using matrix");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void directedGraphUsingAdjacencyList() {
		int n = scanner.nextInt();
		int e = scanner.nextInt();
		List<Integer>[] graph = new List[n + 1];
		for (int i = 1; i <= n; i++)
			graph[i] = new ArrayList<>();
		for (int i = 0; i < e; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			graph[start].add(end);
		}
		System.out.println("Directed graph using adjacency list");
		for (int i = 1; i <= n; i++) {
			System.out.println(i + " -> " + graph[i]);
		}
	}

	private static void weightedGraphUsingMatrix() {
		int n = scanner.nextInt();
		int e = scanner.nextInt();
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 0; i < e; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			int weight = scanner.nextInt();
			graph[start][end] = weight;
		}
		System.out.println("Weighted directed graph using matrix");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void weightedGraphUsingAdjacencyList() {
		int n = scanner.nextInt();
		int e = scanner.nextInt();
		List<Pair>[] graph = new List[n + 1];
		for (int i = 1; i <= n; i++)
			graph[i] = new ArrayList<>();
		for (int i = 0; i < e; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			int weight = scanner.nextInt();
			graph[start].add(new Pair(end, weight));
		}
		System.out.println("Weighted directed graph using adjacency list");
		for (int i = 1; i <= n; i++) {
			System.out.println(i + " -> " + graph[i]);
		}
	}


	public static class Pair {
		public int vertex;
		public int weight;

		public Pair(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + vertex;
			result = prime * result + weight;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (vertex != other.vertex)
				return false;
			if (weight != other.weight)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "[end=" + vertex + ", weight=" + weight + "]";
		}

	}
}
