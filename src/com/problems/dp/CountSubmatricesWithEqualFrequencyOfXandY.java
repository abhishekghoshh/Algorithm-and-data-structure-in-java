package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/count-submatrices-with-equal-frequency-of-x-and-y/description/
 *
 * Solution link :
 *
 */
public class CountSubmatricesWithEqualFrequencyOfXandY {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // optimizing from the previous,
    // we do not need the 2-dimension array
    // we can just add the current difference of x and y to the dp array for the current row
    // also we do not need 2 separate leftX and leftY.
    // we could also calculate the differences from the left side and store it in one variable
    // todo we could more optimizations, but that we make it very complex to understand
    private static void type3() {
        char[][] grid = {
                {'X', 'Y', '.' },
                {'Y', '.', '.' }
        };
        int m = grid.length, n = grid[0].length;
        // we will only store the current row's value
        int[] dp = new int[n];
        int count = 0;
        // to store the least index of x
        int minColX = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int leftDiff = 0;
            for (int j = 0; j < n; j++) {
                // updating min column for x
                if (grid[i][j] == 'X') minColX = Math.min(minColX, j);
                // updating the differences of x and y from the left side
                leftDiff = (grid[i][j] == 'X' ? 1 : 0) - (grid[i][j] == 'Y' ? 1 : 0) + leftDiff;
                // storing the total difference from the top and from the left
                dp[j] = leftDiff + dp[j]; // we could also use += here
                // checking if the column is greater than the least index of x or not
                if (j >= minColX && dp[j] == 0) count++;
            }
        }
        System.out.println(count);
    }

    // optimized from previous approach
    // rather storing the total count of x and y, if we can store the difference
    // of the x and y, then we will not need 2 arrays for both dp values.
    // but there is a problem that we also need to track if there is at least one x present or not.
    // we will use a variable to check if there is already x present on the left side of the current cell or not.
    // however, again for the next row the value will be reset,
    // so the question is, can we use the previous row x position to check for the current row, yes we can.
    // for the current row also the previous row's x is a valid x to satisfy at one x condition.
    // so we will use one variable to track the least left side of x position
    // such that before that there will be no x.
    // for the current row, we will check if there is any new x on the left or not.
    // for at least one x condition, we will check if the current column is greater than equal to the
    // left most x or not.
    private static void type2() {
        char[][] grid = {
                {'X', 'Y', '.' },
                {'Y', '.', '.' }
        };
        int m = grid.length, n = grid[0].length;
        // dp to store the difference between x and y
        int[][] dp = new int[m][n];
        int count = 0;
        // to store the least index of x
        int minColX = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int leftX = 0, leftY = 0;
            for (int j = 0; j < n; j++) {
                // updating min column for x
                if (grid[i][j] == 'X') minColX = Math.min(minColX, j);
                // finding the difference from the top side
                int top = (i > 0 ? dp[i - 1][j] : 0);
                // finding from the left side and updating it with the current cell value
                leftX = (grid[i][j] == 'X' ? 1 : 0) + leftX;
                leftY = (grid[i][j] == 'Y' ? 1 : 0) + leftY;
                // calculating the current difference of total x and y
                dp[i][j] = leftX - leftY + top;
                // checking if the column is greater than the least index of x or not
                if (j >= minColX && dp[i][j] == 0) count++;
            }
        }
        System.out.println(count);
    }

    // Using iterative approach
    // Intution is like
    // if we can find the total x and y for current cell using the left side and top side value,
    // then our work will be simpler.
    // For that, we will use dpX and dpY arrays, which will hold total x and y
    // for finding the total x and y from the left we will use 2 variables leftX and leftY.
    // So for current cell, the value will be left + dp[i-1][j] + current cell value.
    // And in the same iteration, we will also check if at least one X present or not
    // and dpX and dpY value
    private static void type1() {
        char[][] grid = {
                {'X', 'Y', '.' },
                {'Y', '.', '.' }
        };

        int m = grid.length, n = grid[0].length;
        int[][] dpX = new int[m][n];
        int[][] dpY = new int[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            int leftX = 0, leftY = 0;
            for (int j = 0; j < n; j++) {
                // finding the top values
                int topX = i > 0 ? dpX[i - 1][j] : 0;
                int topY = i > 0 ? dpY[i - 1][j] : 0;
                // finding from the left side and updating it with the current cell value
                leftX = (grid[i][j] == 'X' ? 1 : 0) + leftX;
                leftY = (grid[i][j] == 'Y' ? 1 : 0) + leftY;
                // calculating the current total value of x and y
                dpX[i][j] = topX + leftX;
                dpY[i][j] = topY + leftY;
                // checking if the condition holds or not
                if (dpX[i][j] > 0 && dpX[i][j] == dpY[i][j]) count++;
            }
        }
        System.out.println(count);
    }
}
