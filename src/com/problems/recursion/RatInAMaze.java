package com.problems.recursion;

import java.util.ArrayList;

/*
 * Problem link :
 * https://www.codingninjas.com/codestudio/problems/758966
 * https://www.codingninjas.com/studio/problems/rat-in-a-maze-_8842357
 * https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
 * 
 * Solution link:
 * https://www.youtube.com/watch?v=bLGZhJlt4y0
 *
 * https://www.youtube.com/watch?v=7AYJLrDxbBU&list=PL_z_8CaSLPWdbOTog8Jxk9XOjzUs3egMP&index=12
 * https://www.youtube.com/watch?v=4Wc_QCxr_WQ&list=PL_z_8CaSLPWdbOTog8Jxk9XOjzUs3egMP&index=14
 *
 * https://takeuforward.org/data-structure/rat-in-a-maze/
 * */
public class RatInAMaze {
	// Consider a rat placed at (0, 0) in a square matrix of order N * N. It has to
	// reach the destination at (N - 1, N - 1). Find all possible paths that the rat
	// can take to reach from source to destination. The directions in which the rat
	// can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right)
	// Value 0 at a cell in the matrix represents that it is blocked and rat cannot
	// move to it while value 1 at a cell in the matrix represents that rat can be
	// travel through it.
	// Note: In a path, no cell can be visited more than one time. If the source
	// cell is 0, the rat cannot move to any other cell.
	// Print is sorted order
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// we will use slight optimization
	// first we will not use any boolean visited matrix.
	// we will set the bit as 0 once we start visiting it, and once the recursion call is complete,
	// we will again set the bit to 1.
	// also we will not the string builder rather we will char array
	private static void type4() {
		int[][] mat = {
				{1, 0, 0, 0},
				{1, 1, 0, 1},
				{1, 1, 0, 0},
				{0, 1, 1, 1}
		};
		int n = mat.length;
		ArrayList<String> answer = findPath4(mat, n);
		System.out.println(answer);
	}

	private static ArrayList<String> findPath4(int[][] mat, int n) {
		ArrayList<String> answer = new ArrayList<>();
		char[] bucket = new char[2 * n - 2];
		if (mat[0][0] != 0)
			traverse4(0, 0, 0, mat, answer, bucket);
		return answer;
	}

	private static void traverse4(int i, int j, int steps, int[][] matrix, ArrayList<String> answer, char[] bucket) {
		int n = matrix.length;
		if (i == n - 1 && j == n - 1) {
			answer.add(new String(bucket));
			return;
		}
		for (int k = 0; k < 4; k++) {
			int x = i + di[k];
			int y = j + dj[k];
			char di = dir[k];
			if (!isOutOfBounds(x, y, matrix) && matrix[x][y] == 1) {
				bucket[steps] = di;
				matrix[x][y] = 0;
				traverse4(x, y, steps + 1, matrix, answer, bucket);
				matrix[x][y] = 1;
			}
		}
	}


	// same as type1 just we are creating di and dj array to go to a different direction
	private static final char[] dir = {'D', 'L', 'R', 'U'};
	private static final int[] di = {+1, 0, 0, -1};
	private static final int[] dj = {0, -1, 1, 0};

	private static void type3() {
		int[][] mat = {
				{1, 0, 0, 0},
				{1, 1, 0, 1},
				{1, 1, 0, 0},
				{0, 1, 1, 1}
		};
		int n = mat.length;
		ArrayList<String> answer = findPath3(mat, n);
		System.out.println(answer);
	}

	public static ArrayList<String> findPath3(int[][] mat, int n) {
		ArrayList<String> answer = new ArrayList<>();
		if (mat[0][0] != 0) {
			boolean[][] visited = new boolean[n][n];
			visited[0][0] = true;
			traverse3(0, 0, mat, answer, new StringBuilder(), visited);
		}
		return answer;
	}

	private static void traverse3(int i, int j, int[][] matrix, ArrayList<String> ans, StringBuilder bucket, boolean[][] visited) {
		int n = matrix.length;
		if (i == n - 1 && j == n - 1) {
			ans.add(bucket.toString());
			return;
		}
		for (int k = 0; k < 4; k++) {
			int x = i + di[k];
			int y = j + dj[k];
			char di = dir[k];
			if (!isOutOfBounds(x, y, matrix) && !visited[x][y] && matrix[x][y] == 1) {
				visited[i][j] = true;
				bucket.append(di);
				traverse3(x, y, matrix, ans, bucket, visited);
				bucket.deleteCharAt(bucket.length() - 1);
				visited[i][j] = false;
			}
		}
	}

	// using visited matrix
	// same as previous with little refactoring
	private static void type2() {
		int[][] mat = {
				{1, 0, 0, 0},
				{1, 1, 0, 1},
				{1, 1, 0, 0},
				{0, 1, 1, 1}
		};
		int n = mat.length;
		ArrayList<String> answer = findPath2(mat, n);
		System.out.println(answer);
	}

	public static ArrayList<String> findPath2(int[][] mat, int n) {
		ArrayList<String> answer = new ArrayList<>();
		if (mat[0][0] != 0) {
			boolean[][] visited = new boolean[n][n];
			traverse2(mat, 0, 0, answer, visited, new StringBuilder());
		}
		return answer;
	}

	private static void traverse2(int[][] mat, int i, int j, ArrayList<String> answer, boolean[][] visited, StringBuilder bucket) {
		int n = mat.length;
		if (i == n - 1 && j == n - 1) {
			answer.add(bucket.toString());
			return;
		}
		// if out of bounds or there is no path or the point is already visited
		if (isOutOfBounds(i, j, mat) || mat[i][j] == 0 || visited[i][j]) return;

		visited[i][j] = true;
		// all directions are L R U D
		// if we sort alphabetically then D L R U

		// go down
		traverse2(mat, i + 1, j, answer, visited, bucket.append("D"));
		bucket.deleteCharAt(bucket.length() - 1);
		// go left
		traverse2(mat, i, j - 1, answer, visited, bucket.append("L"));
		bucket.deleteCharAt(bucket.length() - 1);
		// go right
		traverse2(mat, i, j + 1, answer, visited, bucket.append("R"));
		bucket.deleteCharAt(bucket.length() - 1);
		// go up
		traverse2(mat, i - 1, j, answer, visited, bucket.append("U"));
		bucket.deleteCharAt(bucket.length() - 1);
		visited[i][j] = false;
	}

	private static boolean isOutOfBounds(int i, int j, int[][] matrix) {
		return i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0;
	}

	// using visited matrix
	private static void type1() {
		int[][] mat = {
				{1, 0, 0, 0},
				{1, 1, 0, 1},
				{1, 1, 0, 0},
				{0, 1, 1, 1}
		};
		int n = mat.length;
		ArrayList<String> answer = findPath1(mat, n);
		System.out.println(answer);
	}

	public static ArrayList<String> findPath1(int[][] mat, int n) {
		ArrayList<String> answer = new ArrayList<>();
		if (mat[0][0] != 0) {
			boolean[][] visited = new boolean[n][n];
			visited[0][0] = true;
			traverse1(0, 0, n, mat, visited, new StringBuilder(), answer);
		}
		return answer;
	}

	private static void traverse1(int i, int j, int n, int[][] mat, boolean[][] visited, StringBuilder bucket, ArrayList<String> answer) {
		// we have reached the destination
		if (i == n - 1 && j == n - 1) {
			answer.add(bucket.toString());
			return;
		}
		// this is out of bound
		if (i >= n || j >= n) return;
		// all directions are L R U D
		// if we sort alphabetically then D L R U

		// go down if it is having a cell and not visited
		if (i + 1 < n && mat[i + 1][j] == 1 && !visited[i + 1][j]) {
			visited[i + 1][j] = true;
			traverse1(i + 1, j, n, mat, visited, bucket.append("D"), answer);
			visited[i + 1][j] = false;
			bucket.deleteCharAt(bucket.length() - 1);
		}
		// go left if it is having a cell and not visited
		if (j > 0 && mat[i][j - 1] == 1 && !visited[i][j - 1]) {
			visited[i][j - 1] = true;
			traverse1(i, j - 1, n, mat, visited, bucket.append("L"), answer);
			visited[i][j - 1] = false;
			bucket.deleteCharAt(bucket.length() - 1);
		}
		// go right if it is having a cell and not visited
		if (j + 1 < n && mat[i][j + 1] == 1 && !visited[i][j + 1]) {
			visited[i][j + 1] = true;
			traverse1(i, j + 1, n, mat, visited, bucket.append("R"), answer);
			visited[i][j + 1] = false;
			bucket.deleteCharAt(bucket.length() - 1);
		}

		// go up if it is having a cell and not visited
		if (i > 0 && mat[i - 1][j] == 1 && !visited[i - 1][j]) {
			visited[i - 1][j] = true;
			traverse1(i - 1, j, n, mat, visited, bucket.append("U"), answer);
			visited[i - 1][j] = false;
			bucket.deleteCharAt(bucket.length() - 1);
		}
	}
}
