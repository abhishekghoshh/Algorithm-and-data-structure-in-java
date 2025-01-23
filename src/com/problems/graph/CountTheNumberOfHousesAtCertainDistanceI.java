package com.problems.graph;

import com.util.PrintUtl;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-i
 *
 * Solution link :
 *
 */
public class CountTheNumberOfHousesAtCertainDistanceI {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // todo please check the leetcode top solutions for the optimized code
    private static void type3() {
    }

    // exactly like the previous type,
    // but here we will not create the adjacency list.
    // rather we will individually check for the next house and the previous house
    // and if the start node is either x or y then we will calculate for y or x
    private static void type2() {
        int n = 3, x = 1, y = 3;
        int[] ans = countOfPairs2(n, x, y);
        PrintUtl.print(ans);
    }

    public static int[] countOfPairs2(int n, int x, int y) {
        int[] ans = new int[n];
        int[][] dis = new int[n + 1][n + 1];
        // we will initialize distances with INF
        for (int[] row : dis) Arrays.fill(row, INF);
        // finally, we will take a queue and will relax all the edges and create the distance matrix
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            dis[i][i] = 0;
            queue.offer(new int[]{i, 0});
            while (!queue.isEmpty()) {
                int[] pair = queue.poll();
                int start = pair[0], prevDis = pair[1];
                int prevNode = start - 1, afterNode = start + 1, newDis = prevDis + 1;
                // we will individually check for the next and the previous array
                if (prevNode >= 1 && dis[i][prevNode] > newDis) {
                    dis[i][prevNode] = newDis;
                    queue.offer(new int[]{prevNode, newDis});
                }
                if (afterNode <= n && dis[i][afterNode] > newDis) {
                    dis[i][afterNode] = newDis;
                    queue.offer(new int[]{afterNode, newDis});
                }
                // if x and y is same, then we will not consider that edge because that will be a self loop
                if (x == y) continue;
                // else we will check if the start node is either of x or y
                if (start == x && dis[i][y] > newDis) {
                    dis[i][y] = newDis;
                    queue.offer(new int[]{y, newDis});
                }
                if (start == y && dis[i][x] > newDis) {
                    dis[i][x] = newDis;
                    queue.offer(new int[]{x, newDis});
                }
            }
        }
        // we will take the distance matrix and calculate the array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int f = dis[i][j];
                if (f == 0) continue;
                ans[f - 1]++;
            }
        }
        return ans;
    }

    // first, we will create a graph from all the houses,
    // then we will compute the distances from all the nodes to all the nodes.
    // we will create a 2D array of n X n matrix for that
    // then finally we will take the distances from the matrix and put it in the array
    private static void type1() {
        int n = 3, x = 1, y = 3;
        int[] ans = countOfPairs1(n, x, y);
        PrintUtl.print(ans);
    }

    static int INF = (int) 1e9;

    public static int[] countOfPairs1(int n, int x, int y) {
        int[] ans = new int[n];
        int[][] dis = new int[n + 1][n + 1];
        // we will initialize distances with INF
        for (int[] row : dis) Arrays.fill(row, INF);
        // we will create a adjacency list from all the houses
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i < n; i++) {
            if (adj[i] == null) adj[i] = new ArrayList<>();
            adj[i].add(i + 1);
            if (adj[i + 1] == null) adj[i + 1] = new ArrayList<>();
            adj[i + 1].add(i);
        }
        // if x!=y then we will create that edge
        if (x != y) {
            if (adj[x] == null) adj[x] = new ArrayList<>();
            adj[x].add(y);
            if (adj[y] == null) adj[y] = new ArrayList<>();
            adj[y].add(x);
        }
        // finally, we will take a queue and will relax all the edges and create the distance matrix
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            dis[i][i] = 0;
            queue.offer(new int[]{i, 0});
            while (!queue.isEmpty()) {
                int[] pair = queue.poll();
                int start = pair[0], prevDis = pair[1];
                for (int end : adj[start]) {
                    int newDis = prevDis + 1;
                    if (dis[i][end] > newDis) {
                        dis[i][end] = newDis;
                        queue.offer(new int[]{end, newDis});
                    }
                }
            }
        }
        // we will take the distance matrix and calculate the array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int f = dis[i][j];
                if (f == 0) continue;
                ans[f - 1]++;
            }
        }
        return ans;
    }
}
