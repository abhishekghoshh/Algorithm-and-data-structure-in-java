package com.problems.array;
/*
 *
 * problem links :
 * https://leetcode.com/problems/valid-sudoku/description/
 * https://neetcode.io/problems/valid-sudoku
 *
 * Solution link :
 * https://www.youtube.com/watch?v=TjFXEUCMqI8
 *
 * https://neetcode.io/solutions/valid-sudoku
 * */

// Tags : Arrays, hashing
public class ValidSudoku {
    public static void main(String[] args) {
        type1();
    }

    // this is very optimized approach
    // we are using rowSet, columnSet and gridSet to track if we can place a number or not
    // we are using boolean array here, but we could also use a HashSet
    // but array is easy to use and takes less space
    private static void type1() {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean ans = isValidSudoku(board);
        System.out.println(ans);
    }

    public static boolean isValidSudoku(char[][] board) {
        int n = 9;
        boolean[][] rowSet = new boolean[n][n];
        boolean[][] columnSet = new boolean[n][n];
        boolean[][] gridSet = new boolean[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == '.') continue;
                int cell = (board[r][c] - '1');
                int g = gridId(r, c);
                // checking if the cell is already placed in the same row or column or the grid or not
                if (rowSet[r][cell] || columnSet[c][cell] || gridSet[g][cell]) return false;
                // we will place the cell
                rowSet[r][cell] = columnSet[c][cell] = gridSet[g][cell] = true;
            }
        }
        return true;
    }

    // method to determine the grid id
    static int gridId(int r, int c) {
        return 3 * (r / 3) + (c / 3);
    }


}
