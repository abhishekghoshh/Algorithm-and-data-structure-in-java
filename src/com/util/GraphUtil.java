package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphUtil {

	public static int[][] undirectedGraphWithMatrix(Scanner scanner) {
		int n = scanner.nextInt();
		int e = scanner.nextInt();
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 0; i < e; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			graph[start][end] = 1;
			graph[end][start] = 1;
		}
		return graph;
	}

	public static int[][] directedGraphWithMatrix(Scanner scanner) {
		int n = scanner.nextInt();
		int e = scanner.nextInt();
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 0; i < e; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			graph[start][end] = 1;
		}
		return graph;
	}

	public static List<Integer>[] undirectedGraphWithList(Scanner scanner) {
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
		return graph;
	}

	public static List<Integer>[] directedGraphWithList(Scanner scanner) {
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
		return graph;
	}

	public static List<Integer>[] transform(int[][] graph) {
		int n = graph.length - 1;
		List<Integer>[] graphWithList = new List[n + 1];
		for (int i = 1; i <= n; i++)
			graphWithList[i] = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			for (int j : graph[i])
				graphWithList[i].add(j);
		}
		return graphWithList;
	}

	public static int[][] transform(List<Integer>[] graphWithList) {
		int n = graphWithList.length - 1;
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			if (graph[i] == null)
				continue;
			for (int j : graphWithList[i]) {
				graph[i][j] = 1;
			}
		}
		return graph;
	}

	public static List<List<Integer>> adjacencyList(int[][] graph) {
		List<List<Integer>> adjacencyList = new ArrayList<>();
		for (int[] row : graph) {
			ArrayList<Integer> vertices = new ArrayList<>();
			for (int end : row) vertices.add(end);
			adjacencyList.add(vertices);
		}
		return adjacencyList;
	}

	public static void print(ArrayList<ArrayList<Integer>> adjacencyList) {
		int n = adjacencyList.size();
		System.out.println("Undirected graph using adjacency list");
		for (int i = 1; i <= n; i++) {
			System.out.println(i + " -> " + adjacencyList.get(i));
		}
	}

	public static void print(List<List<Integer>> adjacencyList) {
		int n = adjacencyList.size();
		System.out.println("Undirected graph using adjacency list");
		for (int i = 0; i < n; i++) {
			System.out.println(i + " -> " + adjacencyList.get(i));
		}
	}

	public static void print(int[][] graph) {
		int n = graph.length - 1;
		System.out.println("Undirected graph with matrix");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void print(List<Integer>[] graph) {
		int n = graph.length;
		System.out.println("Undirected graph using adjacency list");
		for (int i = 1; i <= n; i++) {
			System.out.println(i + " -> " + graph[i]);
		}
	}

	public static AdjacencyListBuilder graphBuilder() {
		return new AdjacencyListBuilder();
	}

	public static class AdjacencyListBuilder {
		private int nodes;
		private final List<AdjacentNode> adjacentNodes = new ArrayList<>();


		public AdjacencyListBuilder nodes(int nodes) {
			this.nodes = nodes;
			return this;
		}

		public Edges edges(int edges) {
			return new Edges(nodes, edges);
		}

		public AdjacentNode start(int start) {
			AdjacentNode adjacentNode = new AdjacentNode(start, this);
			this.adjacentNodes.add(adjacentNode);
			return adjacentNode;
		}

		public List<List<Integer>> buildList() {
			List<List<Integer>> adjacencyList = new ArrayList<>();
			for (int i = 0; i < nodes; i++)
				adjacencyList.add(new ArrayList<>());
			for (AdjacentNode adjacentNode : adjacentNodes)
				for (int end : adjacentNode.ends)
					adjacencyList.get(adjacentNode.start).add(end);
			return adjacencyList;
		}

		public int[][] buildGraph() {
			int[][] graph = new int[nodes][nodes];
			for (AdjacentNode adjacentNode : adjacentNodes)
				for (int end : adjacentNode.ends)
					graph[adjacentNode.start][end] = 1;
			return graph;
		}

		public static class Edges {
			int[][] edges;
			int currentIndex = 0;
			int nodes;

			public Edges(int nodes, int edges) {
				this.edges = new int[edges][edges];
				this.nodes = nodes;
			}

			public Edges edge(int start, int end) {
				if (currentIndex == edges.length) throw new RuntimeException("can not hold any more edges");
				edges[currentIndex][0] = start;
				edges[currentIndex][1] = end;
				currentIndex++;
				return this;
			}

			public List<List<Integer>> buildList() {
				List<List<Integer>> adjacencyList = new ArrayList<>();
				for (int i = 0; i < nodes; i++)
					adjacencyList.add(new ArrayList<>());
				for (int i = 0; i < currentIndex; i++)
					adjacencyList.get(edges[i][0]).add(edges[i][1]);
				return adjacencyList;
			}
		}

		public static class AdjacentNode {
			int start;
			int[] ends;
			private final AdjacencyListBuilder adjacencyListBuilder;

			public AdjacentNode(int start, AdjacencyListBuilder adjacencyListBuilder) {
				this.start = start;
				this.adjacencyListBuilder = adjacencyListBuilder;
			}

			public AdjacencyListBuilder end(int... ends) {
				this.ends = ends;
				return adjacencyListBuilder;
			}
		}
	}
}
