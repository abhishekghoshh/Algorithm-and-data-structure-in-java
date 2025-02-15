package com.problems.binarysearch;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/find-a-peak-element-ii/description/
 * https://www.codingninjas.com/studio/problems/find-peak-element_7449073
 *
 * Solution link :
 * https://www.youtube.com/watch?v=nGGp5XBzC4g
 *
 * */
public class FindPeakElementII {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // using binary search approach
    private static void type3() {
        int[][] mat = {{10, 20, 15}, {21, 30, 14}, {7, 16, 32}};
        int[] answer = findPeakGrid(mat);
        print(answer);
    }

    public static int[] findPeakGrid(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int low = 0, high = m, mid;
        int row, left, right;
        while (low <= high) {
            mid = low + (high - low) / 2;
            row = maxRowForCol(mat, n, mid);
            left = mid > 0 ? mat[row][mid - 1] : -1;
            right = mid < m - 1 ? mat[row][mid + 1] : -1;
            if (mat[row][mid] > left && mat[row][mid] > right) return new int[]{row, mid};
            else if (mat[row][mid] < left) high = mid - 1;
            else low = mid + 1;
        }
        return new int[]{-1, -1};
    }

    public static int maxRowForCol(int[][] mat, int n, int col) { 
        int ind = -1;
        int max = -1;
        for (int i = 0; i < n; i++) {
            if (max < mat[i][col]) {
                max = mat[i][col];
                ind = i;
            }
        }
        return ind;
    }

    // using dfs
    private static void type2() {
        int[][] mat = {{10, 20, 15}, {21, 30, 14}, {7, 16, 32}};
        int[][] dp = new int[mat.length][mat[0].length];
        dfs(dp, mat, 0, 0);
        print(answer);
    }

    static int min = Integer.MIN_VALUE;
    static int[] answer = new int[2];

    public static void dfs(int[][] dp, int[][] mat, int i, int j) {
        if (i < 0 || j < 0 || i > mat.length - 1 || j > mat[0].length - 1) return;
        if (dp[i][j] == 1) return;
        dp[i][j] = 1;
        if (min < mat[i][j]) {
            answer[0] = i;
            answer[1] = j;
            min = mat[i][j];
            dfs(dp, mat, i + 1, j);
            dfs(dp, mat, i, j + 1);
            dfs(dp, mat, i - 1, j);
            dfs(dp, mat, i, j - 1);
        }
    }

    // brute force approach
    private static void type1() {
        int[][] mat = {{10, 20, 15}, {21, 30, 14}, {7, 16, 32}};
        int n = mat.length;
        int m = mat[0].length;
        int left, right, up, down, item;
        int[] answer = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                left = j > 0 ? mat[i][j - 1] : -1;
                right = j < m - 1 ? mat[i][j + 1] : -1;
                up = i > 0 ? mat[i - 1][j] : -1;
                down = i < n - 1 ? mat[i + 1][j] : -1;
                item = mat[i][j];
                if (item > left && item > right && item > up && item > down) {
                    answer = new int[]{i, j};
                    break;
                }
            }
            if (answer != null) break;
        }
        print(answer);
    }
}
