package com.problems.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
/*
 * problem link:
 * https://leetcode.com/problems/minimize-the-maximum-edge-weight-of-graph/description/
 *
 * Solution link :
 *
 * */


public class MinimizeTheMaximumEdgeWeightOfGraph {
    /*
     * You have to remove some edges from this graph (possibly none), so that it satisfies the following conditions:
     * Node 0 must be reachable from all other nodes.
     * The maximum edge weight in the resulting graph is minimized.
     * Each node has at most threshold outgoing edges.
     *
     * Return the minimum possible value of the maximum edge weight after removing the necessary edges.
     * If it is impossible for all conditions to be satisfied, return -1.
     * */

    // todo check it later it is very close to be solved
    public static void main(String[] args) {
        type1();
    }


    // todo my intention is to reverse the edges
    //  after reversing the edge we will try to relax an edge
    //  now as the edges are reversed so we will check the indegree now
    //  // we are reversing the edge here now outgoing nodes of source means ingoing nodes
    //  then apply dijkstra algorithm
    private static void type1() {
        int n = 5;
        int[][] edges = {
                {1, 2, 1},
                {1, 3, 3},
                {1, 4, 5},
                {2, 3, 2},
                {3, 4, 2},
                {4, 0, 1}
        };
        int threshold = 1;
        int ans = minMaxWeight(n, edges, threshold);
        System.out.println(ans);
    }

    public static int minMaxWeight(int n, int[][] edges, int threshold) {
        int max = 0;
        int[][] mat = new int[n][n];

        int[] in = new int[n];


        int[] dis = mat[0];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;

        int[] lowestInwardEdges = new int[n];
        Arrays.fill(lowestInwardEdges, Integer.MAX_VALUE);
        lowestInwardEdges[0] = 0;

        for (int[] e : edges) {
            int src = e[0], end = e[1], d = e[2];
            if (mat[end][src] == 0 || mat[end][src] > d)
                mat[end][src] = d;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.d));
        pq.add(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int src = p.node;
            int prevD = p.d;
            for (int end = 0; end < n; end++) {
                int d = mat[src][end];
                if (d == 0) continue;
                int newD = prevD + d;
                if (in[end] < threshold && newD < dis[end]) {
                    dis[end] = newD;
                    pq.offer(new Pair(end, newD));
                    in[end]++;
                    lowestInwardEdges[end] = Math.min(lowestInwardEdges[end], d);
                } else if (in[end] <= threshold && newD == dis[end] && d < lowestInwardEdges[end]) {
                    lowestInwardEdges[end] = d;
                    pq.offer(new Pair(end, newD));
                }
            }
        }
        for (int d : dis) {
            if (d == Integer.MAX_VALUE) return -1;
        }
        for (int e : lowestInwardEdges) {
            max = Math.max(max, e);
        }
        return max;
    }

    static class Pair {
        int node;
        int d;

        public Pair(int node, int d) {
            this.node = node;
            this.d = d;
        }
    }
}
