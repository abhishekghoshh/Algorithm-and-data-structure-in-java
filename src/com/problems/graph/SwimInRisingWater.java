package com.problems.graph;

import java.util.PriorityQueue;

/*
 * Problem link :
 * https://leetcode.com/problems/swim-in-rising-water/
 *
 * Solution link :
 * Using Dijkstra
 * https://www.youtube.com/watch?v=Wq1NibUMrNU
 *
 * Using DFS and Binary search
 * https://www.youtube.com/watch?v=z-6f0KkSbvU
 */
public class SwimInRisingWater {
    public static void main(String[] args) {
        type1();
        type2();
    }


    // todo this is more optimized approach
    //  we will use the DFS and the binary search here
    private static void type2() {
        int[][] grid = {
                {0, 2},
                {1, 3}
        };
        int ans = swimInWater2(grid);
        System.out.println(ans);
    }

    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // we will use a clever approach here
    // we know that value of cell will range from 0 to n^2 -1
    // if we can apply binary search on time and see what is the lowest time that we can attain
    // for every [time] we will check if we can reach to the end
    // we will check that if we need to wait on any cell for time t
    public static int swimInWater2(int[][] grid) {
        int n = grid.length;
        int low = 0, high = n * n - 1;
        // we will use binary search on time
        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean[][] visited = new boolean[n][n];
            // we will check if we can reach from (0,0) to (n-1,n-1) with time with value of mid
            // if yes then we will shrink the upper limit
            if (reachableOrNot(0, 0, n, mid, grid, visited)) {
                high = mid;
            } else {
                // If not reachable, then we will increase the lower limit
                low = mid + 1;
            }
        }
        return low;
    }

    private static boolean reachableOrNot(int r, int c, int n, int t, int[][] grid, boolean[][] visited) {
        // Base case: if we reach the bottom-right corner
        if (r == n - 1 && c == n - 1) return true;
        // Mark the current cell as visited
        visited[r][c] = true;
        // Explore all possible directions
        for (int[] dir : dirs) {
            int x = r + dir[0];
            int y = c + dir[1];
            // if the current cell and the adjacent cell is lesser than the time
            // then we do not have to wait to go to the other cell, so our way will not be blocked
            if (isInBounds(x, y, n) && !visited[x][y] && grid[r][c] <= t && grid[x][y] <= t) {
                // Recursively check if we can reach the destination
                if (reachableOrNot(x, y, n, t, grid, visited)) return true;
            }
        }
        // If we can't reach the destination
        return false;
    }

    // todo this is easy approach
    //  we will use Dijkstra here
    private static void type1() {
        int[][] grid = {
                {0, 2},
                {1, 3}
        };
        int ans = swimInWater1(grid);
        System.out.println(ans);
    }

    // our intuition is the answer will be maximum time on a certain path
    // because once we reach to the cell which have the maximum value.
    // all the other grid cells had the lesser value but if we wait till the max cell value
    // eventually we do not have to wait for any other cell on that path

    // so we will use a priority queue for storing all the adjacent cells
    // and out of that we will choose the minimum adjacent cell
    // once we start operating on any cell at that time only we will calculate the max variable
    // as we are choosing that cell as a part of our path
    public static int swimInWater1(int[][] grid) {
        int n = grid.length;
        if (n == 1) return 0;
        boolean[][] visited = new boolean[n][n];
        // min heap starting with 0,0
        PriorityQueue<Point> minHeap = new PriorityQueue<>((p1, p2) -> p1.height - p2.height);
        minHeap.offer(new Point(grid[0][0], 0, 0));
        int max = grid[0][0];
        visited[0][0] = true;
        // we will check till we reach the n-1,n-1 cell
        while (!minHeap.isEmpty()) {
            Point p = minHeap.poll();
            int height = p.height, r = p.x, c = p.y;
            max = Math.max(max, height);
            if (r == n - 1 && c == n - 1) return max;
            // we will traverse all the adjacent unvisited nodes
            for (int[] dir : dirs) {
                int x = r + dir[0], y = c + dir[1];
                if (isInBounds(x, y, n) && !visited[x][y]) {
                    visited[x][y] = true;
                    minHeap.offer(new Point(grid[x][y], x, y));
                }
            }
        }
        return -1;
    }

    static boolean isInBounds(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static class Point {
        int height;
        int x, y;

        Point(int height, int x, int y) {
            this.height = height;
            this.x = x;
            this.y = y;
        }
    }
}
