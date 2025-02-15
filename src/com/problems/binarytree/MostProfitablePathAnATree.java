package com.problems.binarytree;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/most-profitable-path-in-a-tree/description/
 *
 * Solution link :
 *
 */
public class MostProfitablePathAnATree {

    // todo incomplete
    public static void main(String[] args) {
        type1();
    }

    // we will do the dfs with the backtracking
    private static void type1() {
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {3, 4}};
        int bob = 3;
        int[] amount = {-2, 4, 2, -4, 6};
        int ans = mostProfitablePath1(edges, bob, amount);
        System.out.println(ans);
    }


    public static int mostProfitablePath1(int[][] edges, int bob, int[] amount) {
        int alice = 0;
        int n = edges.length + 1;
        // first, we will convert the edges into an adjacency list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n - 1; i++) {
            int start = edges[i][0], end = edges[i][1];
            if (adj[start] == null) adj[start] = new ArrayList<>();
            adj[start].add(end);
            if (adj[end] == null) adj[end] = new ArrayList<>();
            adj[end].add(start);
        }
        boolean[] visited1 = new boolean[n], visited2 = new boolean[n];
        visited1[alice] = visited2[bob] = true;
        return traverse1(alice, bob, visited1, visited2, adj, amount);
    }

    static int traverse1(int alice, int bob, boolean[] visited1, boolean[] visited2, List<Integer>[] adj, int[] amount) {
        // if alice has reached the leaf node, then we can directly return the amount associated with that node
        if (isLeafNode(alice, visited1, adj)) return amount[alice];
        // if bob has reached 0, then we will go till alice reached to any leaf node
        if (bob == 0) {
            int max = Integer.MIN_VALUE;
            int cost = amount[alice];
            amount[alice] = 0;
            for (int end1 : adj[alice]) {
                if (visited1[end1]) continue;
                visited1[end1] = true;
                int am = traverse1(end1, bob, visited1, visited2, adj, amount);
                // if the amount is -INF, then that means there is no possible path
                if (am == Integer.MIN_VALUE) continue;
                max = Math.max(max, cost + am);
                visited1[end1] = false;
            }
            amount[alice] = cost;
            return max;
        }
        // if bob reached any leaf node other than 0, then he has to return
        if (isLeafNode(bob, visited2, adj)) return Integer.MIN_VALUE;
        // we will move alice and bob simultaneously
        int max = Integer.MIN_VALUE;
        int cost1, cost2;
        // first, we will set the extract the cost
        if (alice == bob) {
            cost1 = cost2 = amount[alice] / 2;
            amount[alice] = 0;
        } else {
            cost1 = amount[alice];
            cost2 = amount[bob];
            amount[alice] = amount[bob] = 0;
        }
        for (int end1 : adj[alice]) {
            if (visited1[end1]) continue;
            visited1[end1] = true;
            for (int end2 : adj[bob]) {
                if (visited2[end2]) continue;
                visited2[end2] = true;
                int am = traverse1(end1, end2, visited1, visited2, adj, amount);
                // if the amount is -INF, then that means there is no possible path
                if (am == Integer.MIN_VALUE) continue;
                max = Math.max(max, cost1 + am);
                visited2[end2] = false;
            }
            visited1[end1] = false;
        }
        // but at last we will again reset it
        if (alice == bob) {
            amount[alice] = cost1 + cost2;
        } else {
            amount[alice] = cost1;
            amount[bob] = cost2;
        }
        return max;

    }

    static boolean isLeafNode(int start, boolean[] visited, List<Integer>[] adj) {
        for (int end : adj[start])
            if (!visited[end]) return false;
        return true;
    }

}
