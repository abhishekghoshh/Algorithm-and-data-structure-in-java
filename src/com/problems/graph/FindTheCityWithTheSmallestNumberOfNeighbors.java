package com.problems.graph;

/*
 * Problem link :
 * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
 * https://practice.geeksforgeeks.org/problems/city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/0
 *
 * Solution link :
 * https://www.youtube.com/watch?v=PwMVNSJ5SLI&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=43
 *
 * https://takeuforward.org/data-structure/find-the-city-with-the-smallest-number-of-neighbours-at-a-threshold-distance-g-43/
 */
public class FindTheCityWithTheSmallestNumberOfNeighbors {

    public static void main(String[] args) {
        type1();
        type2();
    }

    // similar to the previous one
    // Using the floyd warshall technique to find minimum distance from all nodes to all node
    private static void type2() {
        int n = 5;
        int[][] edges = {
                {0, 1, 2},
                {0, 4, 8},
                {1, 2, 3},
                {1, 4, 2},
                {2, 3, 1},
                {3, 4, 1}
        };
        int distanceThreshold = 2;
        // let's create the matrix first
        int[][] matrix = new int[n][n];
        for (int[] edge : edges) {
            matrix[edge[0]][edge[1]] = edge[2];
            matrix[edge[1]][edge[0]] = edge[2];
        }
        // for simplicity, we will use 10^5 as INF
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // for i==j it will always be 0, so we will skip it
                if (i != j && matrix[i][j] == 0) matrix[i][j] = (int) 1e5;
            }
        }
        // floyd warshall technique
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // floyd warshall technique of [i][j] => [i][k]+[k][j]
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        int cityMax = n, city = -1;
        for (int i = 0; i < n; i++) {
            int cityCount = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != -1 && matrix[i][j] <= distanceThreshold)
                    cityCount++;
            }
            // for every city, there will be +1 for including itself,
            // as it will be added for all the cities, so it will not affect our answer
            if (cityCount <= cityMax) {
                cityMax = cityCount;
                city = i;
            }
        }
        System.out.println(city);
    }

    // Using the floyd warshall technique to find minimum distance from all nodes to all node
    private static void type1() {
        int n = 5;
        int[][] edges = {
                {0, 1, 2},
                {0, 4, 8},
                {1, 2, 3},
                {1, 4, 2},
                {2, 3, 1},
                {3, 4, 1}
        };
        int distanceThreshold = 2;
        // let's create the matrix
        int[][] matrix = new int[n][n];
        for (int[] edge : edges) {
            matrix[edge[0]][edge[1]] = edge[2];
            matrix[edge[1]][edge[0]] = edge[2];
        }
        // first, we will replace cell with 0 value with -1, which means there is no edge
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (matrix[i][j] == 0) matrix[i][j] = -1;
            }
        }

        // floyd warshall technique [i][j] => [i][k]+[k][j]
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                // i == k means we do not need to check anything
                if (i == k) continue;
                for (int j = 0; j < n; j++) {
                    // similarly we can skip for [i,j] and [j,[k]
                    if (i == j || j == k) continue;
                    // if [i,k] and [k,j] has not connected, yet then we can skip the current iteration
                    if (matrix[i][k] == -1 || matrix[k][j] == -1) continue;
                    // if [i,j] is not yet connected or [i][j] > [i][k]+[k][j] then we will update
                    if (matrix[i][j] == -1 || matrix[i][j] > matrix[i][k] + matrix[k][j])
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                }
            }
        }
        int cityMax = Integer.MAX_VALUE, city = -1;
        // for every point we will traverse the row
        for (int i = 0; i < n; i++) {
            int cityCount = 0;
            for (int j = 0; j < n; j++) {
                // if i == j that means it is the same city
                // if the value is -1, then we cannot go from i to j
                if (i == j || matrix[i][j] == -1) continue;
                if (matrix[i][j] <= distanceThreshold) cityCount++;
            }
            if (cityCount <= cityMax) {
                cityMax = cityCount;
                city = i;
            }
        }
        System.out.println(city);
    }

}
