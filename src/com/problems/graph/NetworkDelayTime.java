package com.problems.graph;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/network-delay-time/description/
 *
 * Solution link :
 *
 */
public class NetworkDelayTime {
    public static void main(String[] args) {
        type1();
        type2();
    }

    static int INF = (int) 1e9;


    // Same as previous without int array in the Queue
    private static void type2() {
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        int n = 4, k = 2;
        int ans = networkDelayTime2(times, n, k);
        System.out.println(ans);
    }


    public static int networkDelayTime2(int[][] times, int n, int k) {
        // first, we will create a directed adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] time : times) {
            adj.get(time[0]).add(new int[]{time[1], time[2]});
        }
        // we will use a distance array and fill INF other than the source node
        int[] dis = new int[n + 1];
        Arrays.fill(dis, INF);
        dis[k] = 0;
        // we will use a normal queue for storing
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);

        // we will apply the dijkstra
        while (!queue.isEmpty()) {
            int start = queue.poll();
            int prevTime = dis[start];
            // we will check all its neighbors
            for (int[] node : adj.get(start)) {
                int end = node[0];
                int time = node[1];
                int newTime = prevTime + time;
                if (newTime < dis[end]) {
                    dis[end] = newTime;
                    queue.offer(end);
                }
            }
        }
        // check the maximum time for the farthest node
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dis[i] == INF) return -1;
            maxTime = Math.max(maxTime, dis[i]);
        }
        return maxTime;
    }


    // using Dijkstra technique,
    // but here we will not use the Priority Queue
    // as we will go level wise from the previous nodes
    // we will not early return from any position
    // we will gradually create the distance array
    private static void type1() {
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        int n = 4, k = 2;
        int ans = networkDelayTime1(times, n, k);
        System.out.println(ans);
    }

    public static int networkDelayTime1(int[][] times, int n, int k) {
        // first, we will create a directed adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] time : times) {
            adj.get(time[0]).add(new int[]{time[1], time[2]});
        }
        // we will use a distance array and fill INF other than the source node
        int[] dis = new int[n + 1];
        Arrays.fill(dis, INF);
        dis[k] = 0;
        // we will use a normal queue for storing
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{k, 0});

        // we will apply the dijkstra
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int start = pair[0];
            int prevTime = pair[1];
            // we will check all its neighbors
            for (int[] node : adj.get(start)) {
                int end = node[0];
                int time = node[1];
                int newTime = prevTime + time;
                if (newTime < dis[end]) {
                    dis[end] = newTime;
                    queue.offer(new int[]{end, newTime});
                }
            }
        }
        // check the maximum time for the farthest node
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dis[i] == INF) return -1;
            maxTime = Math.max(maxTime, dis[i]);
        }
        return maxTime;
    }
}
