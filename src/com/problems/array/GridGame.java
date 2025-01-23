package com.problems.array;
/*
 *
 * problem links :
 * https://leetcode.com/problems/grid-game/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=N4wDSOw65hI
 *
 * https://neetcode.io/solutions/grid-game
 * */

// Tags: Arrays, Greedy**, prefix sum
public class GridGame {
    public static void main(String[] args) {
        type1();
    }

    // optimized solution
    // this is a greedy approach
    // let's take the grid as
    // 2, 5, 4
    // 1, 5, 1
    // the first robot can switch row either on 0 or 1 or 2.
    // seeing the question, our first thought process might to maximize the points collected by robot 1
    // that seems right but that is not actually.
    // the priority of the first robot is to minimize the points collected by robot 2,
    // and he would switch the rows in that way.
    // let's imagine the robot1 switched on index 1, and the grid became
    // 0, 0, 4
    // 1, 0, 0
    // so the robot2 can either go to (0 0 4 0) or (0 1 0 0)
    // if we look close we could see that the robot1 path broke the grid into 2 parts such that
    // the robot2 can collect the points either from the top-right or bottom-left
    // and robot2 will take the max out of both ways, and robot1 has to minimize that maximum value
    // so for 2 parts we could use 1 suffix array for the top right
    // and 1 prefix array for the bottom left
    // todo we could also use just one variable to compute the prefix sum along the way
    // todo we could also use another variable for total sum and everytime we will decrement the current cell value
    //  that will produce the suffix sum,
    private static void type1() {
        int[][] grid = {
                {2, 5, 4},
                {1, 5, 1}
        };
        long ans = gridGame1(grid);
        System.out.println(ans);
    }

    public static long gridGame1(int[][] grid) {
        int n = grid[0].length;
        // calculating the suffix sum
        long[] suffixSumArr = new long[n];
        long sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += grid[0][i];
            suffixSumArr[i] = sum;
        }
        long prefixSum = 0;
        // we take the prefix sum of (0, i-1) and suffix sum of [i+1, n]
        // and take the max of them
        long min = Long.MAX_VALUE;
        // switching on every possible index
        for (int i = 0; i < n; i++) {
            long suffixSum = (i + 1 < n) ? suffixSumArr[i + 1] : 0;
            long localMax = Math.max(prefixSum, suffixSum);
            min = Math.min(min, localMax);
            // updating the prefix sum
            prefixSum += grid[1][i];
        }
        return min;
    }
}
