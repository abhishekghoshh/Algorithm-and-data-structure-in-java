package com.problems.binarysearch;

/*
 * Problem link :
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * https://www.codingninjas.com/studio/problems/search-in-a-sorted-2d-matrix_6917532
 *
 * Solution link :
 * https://www.youtube.com/watch?v=9ZbB397jU4k
 *
 * https://takeuforward.org/arrays/search-in-a-row-and-column-wise-sorted-matrix/
 * */
public class SearchInRowAndColumnSortedMatrix2 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    // binary search approach
    private static void type2() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int target = 5;
        int row = matrix.length;
        int column = matrix[0].length;
        int r = 0;
        int c = column - 1;
        boolean answer = false;
        while (r <= row - 1 && c >= 0) {
            if (matrix[r][c] == target) {
                answer = true;
                break;
            } else if (matrix[r][c] < target) r++;
            else c--;
        }
        System.out.println(answer);
    }

    // brute force approach
    private static void type1() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int target = 5;
        int row = matrix.length;
        int column = matrix[0].length;
        int x = -1, y = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                if (matrix[i][j] == target) {
                    x = i;
                    y = j;
                    break;
                }
            if (x != -1) break;
        }
        System.out.printf("arr[%d][%d] = %d%n", x, y, matrix[x][y]);
    }
}
