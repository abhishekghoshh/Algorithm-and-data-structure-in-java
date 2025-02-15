package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/description/
 * https://www.naukri.com/code360/problems/count-square-submatrices-with-all-ones_3751502
 *
 * Solution link :
 * https://www.youtube.com/watch?v=auS1fynpnjo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=57
 *
 * https://takeuforward.org/data-structure/count-square-submatrices-with-all-1s-dp-on-rectangles-dp-56/
 */
public class CountSquareSubmatricesWithAllOnes {
    public static void main(String[] args) {
        type1();
    }

    // we will directly use the tabulation approach
    // this is not intuitive from the start.
    // the intuition is we will try to find that the current cell is right bottom
    // for how many cells, and we will do it for every cell.
    // we will create a dp array of the same size as the matrix.
    // for 0th row and column, we will just copy the matrix value into dp.
    // for those cells, there are no cells on the left side and upper side.
    // for other cells, we will take the minimum of the left, upper and top-left diagonal cell and add 1.
    // let's say on the left,top and on diagonal; the values are x, so there is x length square.
    // so if we could add the current cell, then we can make x+1 length square.
    // but if the values are different, then we can take the minimum out of them and add 1

    // we could do multiple optimizations in this solution like
    // wer can use the same matrix and store the results on that.
    // also, we can do the sum in the same loop where we are calculating the dp matrix
    private static void type1() {
        int[][] matrix = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };

        int m = matrix.length, n = matrix[0].length;
        // we will create a dp array of the same size as the matrix.
        int[][] dp = new int[m][n];
        // for 0th row and column, we will just copy the matrix value into dp.
        for (int i = 0; i < m; i++) dp[i][0] = matrix[i][0];
        for (int i = 0; i < n; i++) dp[0][i] = matrix[0][i];

        // we will skip 0th row and column
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // we will calculate for only for the cells having 1 value
                if (matrix[i][j] == 1)
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])
                    );
            }
        }

        int sum = 0;
        // calculate the sum for all the cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                sum += dp[i][j];
        }
        System.out.println(sum);
    }
}
