package com.problems.graph;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/graph-valid-tree/solutions/
 * https://www.naukri.com/code360/problems/graph-valid-tree_1376618
 * https://leetcode.ca/all/261.html
 * https://www.lintcode.com/problem/178/
 * https://www.geeksforgeeks.org/problems/is-it-a-tree/0
 *
 * Solution link :
 * https://www.youtube.com/watch?v=bXsUuownnoQ
 *
 * https://www.geeksforgeeks.org/check-given-graph-tree/
 */
public class GraphValidTree {
    // Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
    // write a function to check whether these edges make up a valid tree.
    public static void main(String[] args) {
        type1();
    }

    // the simplest way to check will be to use either BFS or DFS to check if there are any loops or not
    private static void type1() {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int n = 5;
        boolean ans = graphIsValidTree1(edges, n);
        System.out.println(ans);
    }

    private static boolean graphIsValidTree1(int[][] edges, int n) {
        List<Integer>[] nodes = new List[n];
        for (int i = 0; i < n; i++) nodes[i] = new ArrayList<>();
        // creating the adjacency list
        for (int[] edge : edges) {
            int start = edge[0], end = edge[1];
            nodes[start].add(end);
            nodes[end].add(start);
        }
        boolean[] visited = new boolean[n];
        // as there can be a case of [0 → 1 → 4],
        // we started from 0 then we will go to 1, and again it wants to go to 0
        // we will also carry a parent node.
        // if there is any cycle, then we will directly return false
        if (hasCycle1(0, -1, nodes, visited)) return false;

        // ideally after previous method all the nodes will be visited, if any node is not
        // that means there is a disconnected component
        for (boolean v : visited) if (!v) return false;
        return true;
    }

    private static boolean hasCycle1(int start, int parent, List<Integer>[] nodes, boolean[] visited) {
        if (visited[start]) return true;
        visited[start] = true;
        for (int end : nodes[start])
            if (end != parent && hasCycle1(end, start, nodes, visited))
                return true;
        return false;
    }
}
