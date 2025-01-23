package com.algo.unionfind;

/*
 *
 *
 * When and where the union find is used:
 * Kruskal's minimum spanning tree algorithm
 * Grid percolation
 * Network connectivity
 * The Least common ancestor in tree
 * Image processing
 *
 *
 * */
public class UnionFind {
    // The number of elements in this union find
    private final int size;

    // Used to track the size of each of the component
    private final int[] count;

    // id[i] points to the parent of i, if id[i] = i then i is a root node
    private final int[] parent;

    // Tracks the number of components in the union find
    private int components;

    public UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");

        this.size = components = size;
        count = new int[size];
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i; // Link to itself (self root)
            count[i] = 1; // Each component is originally of size one
        }
    }

    // Find which component/set 'p' belongs to, takes amortized constant time.
    public int find(int point) {
        // Find the root of the component/set
        int root = point;
        while (root != parent[root])
            root = parent[root];

        // Compress the path leading back to the root.
        // Doing this operation is called "path compression"
        // and is what gives us amortized time complexity.
        while (point != root) {
            int next = parent[point];
            parent[point] = root;
            point = next;
        }

        return root;
    }

    // Return whether the elements 'p' and
    // 'q' are in the same components/set.
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Return the size of the components/set 'p' belongs to
    public int componentSize(int p) {
        return count[find(p)];
    }

    // Return the number of elements in this UnionFind/Disjoint set
    public int size() {
        return size;
    }

    // Returns the number of remaining components/sets
    public int components() {
        return components;
    }

    // Unify the components/sets containing elements 'p' and 'q'
    public void unify(int p, int q) {

        // These elements are already in the same group. If we add this edge, it will form a loop
        if (connected(p, q)) return;

        int root1 = find(p);
        int root2 = find(q);

        // Merge smaller component/set into the larger one.
        if (count[root1] > count[root2]) {
            count[root1] += count[root2];
            parent[root2] = root1;
            count[root2] = 0;
        } else {
            count[root2] += count[root1];
            parent[root1] = root2;
            count[root1] = 0;
        }
        // Since the roots found are different, we know that the number of components/sets has decreased by one
        components--;
    }
}
