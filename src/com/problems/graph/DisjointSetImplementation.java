package com.problems.graph;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/disjoint-set-union-find/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=aBxjDBC4M1U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=46
 * 
 * https://takeuforward.org/data-structure/disjoint-set-union-by-rank-union-by-size-path-compression-g-46/
 */
public class DisjointSetImplementation {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// todo this is a better approach
	// Disjoint set using size
	// It will also use Path compression along the way
	// It uses a recursive approach to find and do the union method
	private static void type2() {
		DisjointSet disjointSet = new DisjointSetBySize(7);
		// union rank means adding one edge
		disjointSet.union(1, 2);
		disjointSet.union(2, 3);
		disjointSet.union(4, 5);
		disjointSet.union(6, 7);
		disjointSet.union(5, 6);

		// if 3 and 7 are in the same component or not
		checkIfSame(disjointSet.find(3), disjointSet.find(7));
		// adding 3 and 7 into the same component
		disjointSet.union(3, 7);
		// if 3 and 7 are in the same component or not
		checkIfSame(disjointSet.find(3), disjointSet.find(7));
	}

	static class DisjointSetBySize implements DisjointSet {
		final int[] parent;
		final int[] size;

		// first, we will create two arrays if storing parent node and the size of the node
		// initially all the node will be its parent node, and size will be 1
		public DisjointSetBySize(int n) {
			parent = new int[n + 1];
			size = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		// find the parent of the component in which the node belongs
		public int find(int node) {
			// if the node is itself parent, then it is the ultimate parent of the component
			if (node == parent[node]) return node;
			// recursively, we will find the ultimate parent
			int ultimateParent = find(parent[node]);
			// path compression by setting the ultimate parent
			// if they remove the next line then all node will just connect to its upper parent
			parent[node] = ultimateParent;
			return parent[node];
		}

		// we will unify the u's component and v's component
		public void union(int u, int v) {
			int parentOfU = find(u);
			int parentOfV = find(v);
			// if parent of the both nodes is same, then we will return
			if (parentOfU == parentOfV) return;
			// we will set the bigger size component as the ultimate parent
			if (size[parentOfU] < size[parentOfV]) {
				parent[parentOfU] = parentOfV;
				size[parentOfV] = size[parentOfV] + size[parentOfU];
			} else {
				parent[parentOfV] = parentOfU;
				size[parentOfU] = size[parentOfU] + size[parentOfV];
			}
		}
	}

	// Disjoint set using rank
	// It will also use Path compression along the way
	// It uses a recursive approach to find and do the union method
	private static void type1() {
		DisjointSet disjointSet = new DisjointSetByRank(7);
		// union rank means adding one edge
		disjointSet.union(1, 2);
		disjointSet.union(2, 3);
		disjointSet.union(4, 5);
		disjointSet.union(6, 7);
		disjointSet.union(5, 6);

		// if 3 and 7 are in the same component or not
		checkIfSame(disjointSet.find(3), disjointSet.find(7));
		// adding 3 and 7 into the same component
		disjointSet.union(3, 7);
		// if 3 and 7 are in the same component or not
		checkIfSame(disjointSet.find(3), disjointSet.find(7));
	}

	static class DisjointSetByRank implements DisjointSet {

		final int[] rank;
		final int[] parent;

		public DisjointSetByRank(int n) {
			rank = new int[n + 1];
			parent = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				rank[i] = 0;
				parent[i] = i;
			}
		}

		public int find(int node) {
			// if the node is itself parent, then it is the ultimate parent of the component
			if (node == parent[node]) return node;
			// recursively, we will find the ultimate parent
			int ultimateParent = find(parent[node]);
			// path compression by setting the ultimate parent
			// if they remove the next line then all node will just connect to its upper parent
			parent[node] = ultimateParent;
			return parent[node];
		}

		public void union(int u, int v) {
			int parentOfU = find(u);
			int parentOfV = find(v);
			if (parentOfU == parentOfV) return;
			if (rank[parentOfU] < rank[parentOfV]) {
				parent[parentOfU] = parentOfV;
				rank[parentOfV]++;
			} else {
				parent[parentOfV] = parentOfU;
				rank[parentOfU]++;
			}
		}

	}

	interface DisjointSet {
		int find(int node);

		void union(int u, int v);
	}

	public static void checkIfSame(int p1, int p2) {
		if (p1 == p2) System.out.println("Same");
		else System.out.println("Not Same");
	}

}
