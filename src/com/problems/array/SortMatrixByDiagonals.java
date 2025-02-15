package com.problems.array;

import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/sort-matrix-by-diagonals/description/
 *
 * Solution link
 *
 * */
public class SortMatrixByDiagonals {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we can make the previous one little simple
    // let's find the diagonal points
    // (0,0), (1,1), (2,2), (3,3), (4,4), (5,5)
    // (2,0), (3,1), (4,2), (5,3)
    // (0,3), (1,4), (2,5)
    // if we look closely then we will find that the difference between the points is same
    // it will range from -(n-1) to (n-1)
    private static void type2() {
        int[][] grid = {
                {1, 7, 3},
                {9, 8, 2},
                {4, 5, 6}
        };
        int[][] ans = sortMatrix2(grid);
        PrintUtl.print2D(ans);
    }

    private static int[][] sortMatrix2(int[][] grid) {
        int n = grid.length;
        return grid;
    }

    // using brute force approach
    private static void type1() {
        int[][] grid = {
                {1, 7, 3},
                {9, 8, 2},
                {4, 5, 6}
        };
        int[][] ans = sortMatrix1(grid);
        PrintUtl.print2D(ans);
    }

    public static int[][] sortMatrix1(int[][] grid) {
        int n = grid.length;
        List<Integer> list = new ArrayList<>();
        // from main diagonal
        for (int i = 0; i < n; i++) {
            list.add(grid[i][i]);
        }
        list.sort((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < n; i++) {
            grid[i][i] = list.get(i);
        }
        list.clear();

        // now row wise
        for (int i = 1; i < n; i++) {
            for (int x = i, y = 0; x < n; x++, y++) {
                list.add(grid[x][y]);
            }
            list.sort((a, b) -> Integer.compare(b, a));
            for (int x = i, y = 0; x < n; x++, y++) {
                grid[x][y] = list.get(y);
            }
            list.clear();
        }

        // now column wise
        for (int i = 1; i < n; i++) {
            for (int x = 0, y = i; y < n; x++, y++) {
                list.add(grid[x][y]);
            }
            list.sort(Integer::compare);
            for (int x = 0, y = i; y < n; x++, y++) {
                grid[x][y] = list.get(x);
            }
            list.clear();
        }

        return grid;
    }
}
