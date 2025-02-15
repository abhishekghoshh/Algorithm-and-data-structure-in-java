package com.problems.recursion;

/*
 * Problem links:
 * https://leetcode.com/problems/word-search/description/
 * https://neetcode.io/problems/search-for-word
 * https://www.naukri.com/code360/problems/word-search---l_892986
 *
 * Solution link
 * https://www.youtube.com/watch?v=pfiQ_PS1g8E
 *
 * https://neetcode.io/solutions/word-search
 * https://takeuforward.org/data-structure/word-search-leetcode/
 * */
public class WordSearch1 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // same as previous
    // here we will not use any visited array
    // here we will change the input array to mark the cell as visited.
    // also we will use a freq array to check if the characters in the word are present on the board or not
    private static void type2() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        boolean exist = exist3(board, word);
        System.out.println(exist);
    }

    private static boolean exist3(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        // if the cell count is less than the word length, then we can return false
        if (m * n < word.length()) return false;
        char[] arr = word.toCharArray();
        // this is the little optimization that we have done
        // we will check if the characters in that word are present in the board or not
        int[] freq = new int[128];
        for (char[] row : board)
            for (char ch : row) freq[ch]++;

        for (char ch : arr) {
            freq[ch]--;
            // if the frequency is less than 0 means this character is not present in the board
            if (freq[ch] < 0) return false;
        }
        // now we will traverse the array
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == arr[0]
                        && hasWord2(board, i, j, 0, arr))
                    return true;
            }
        }
        return false;
    }

    private static boolean hasWord2(char[][] board, int i, int j, int idx, char[] arr) {
        // if we go to nth index, that means we have already found 0..n-1 indexes
        // we can return true from here
        if (idx == arr.length) return true;
        // if the coordinate is out of bound or the cell value is not arr[i] or the cell is already visited
        // then we will return false
        if (isOutOfBound(i, j, board)
                || board[i][j] != arr[idx]
                || board[i][j] == '-')
            return false;

        // we will change the cell value to an arbitrary character to mark it as visited
        char ch = board[i][j];
        board[i][j] = '-';

        // we will explore all directions
        if (hasWord2(board, i + 1, j, idx + 1, arr)
                || hasWord2(board, i - 1, j, idx + 1, arr)
                || hasWord2(board, i, j + 1, idx + 1, arr)
                || hasWord2(board, i, j - 1, idx + 1, arr)) {
            board[i][j] = ch;
            return true;
        }

        // at last, we will again set the cell value to its original char
        board[i][j] = ch;
        return false;
    }

    // todo using recursion and backtracking and a visited array
    private static void type1() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        boolean exist = exist1(board, word);
        System.out.println(exist);
    }

    static boolean exist1(char[][] board, String word) {
        char[] arr = word.toCharArray();
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // we will start the traversal for every cell if its value is the word's first character
                // if the traversal returns true, then we will also return true
                if (board[i][j] == arr[0]
                        && hasWord1(board, i, j, 0, arr, visited))
                    return true;
            }
        }
        return false;
    }

    private static boolean hasWord1(char[][] board, int i, int j, int idx, char[] arr, boolean[][] visited) {
        // if it is the last index on the string, then we can return true
        if (idx == arr.length) return true;
        // if the coordinate is out of bound or the cell value is not arr[i] or the cell is already visited,
        // then we will return false
        if (isOutOfBound(i, j, board)
                || board[i][j] != arr[idx]
                || visited[i][j])
            return false;
        // marking the index
        visited[i][j] = true;

        // we will go to all four directions and try to explore
        if (hasWord1(board, i + 1, j, idx + 1, arr, visited)
                || hasWord1(board, i - 1, j, idx + 1, arr, visited)
                || hasWord1(board, i, j + 1, idx + 1, arr, visited)
                || hasWord1(board, i, j - 1, idx + 1, arr, visited))
            return true;
        // at last, we will again set the visited cell value to false
        visited[i][j] = false;
        return false;
    }

    private static boolean isOutOfBound(int i, int j, char[][] board) {
        return i < 0 || i >= board.length || j < 0 || j >= board[0].length;
    }
}
