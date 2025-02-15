package com.problems.array;

import java.util.ArrayList;
import java.util.List;

import static com.util.PrintUtl.print2D;

/*
 *
 * problem links:
 * https://leetcode.com/problems/pascals-triangle/description/
 * https://www.naukri.com/code360/problems/1089580
 * https://www.naukri.com/code360/problems/print-pascal-s-triangle_6917910
 *
 * Solution link:
 * https://www.youtube.com/watch?v=bR7mQgwQ_o8
 * https://www.youtube.com/watch?v=6FLvhQjZqvM&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=9
 *
 * https://takeuforward.org/data-structure/program-to-generate-pascals-triangle/
 * */
public class PascalTriangle {

	// for any row in pascal triangle,
	// the sum is 2^(n-1)
	public static void main(String[] args) {
		type1();
	}


	// Create all rows of pascal triangle
	//     1
	//    1 1
	//   1 2 1
	// 1 3 2 3 1
	private static void type1() {
		int numRows = 5;
		List<List<Integer>> triangle = generate1(numRows);
		print2D(triangle);
	}

	private static List<List<Integer>> generate1(int numRows) {
		List<List<Integer>> triangle = new ArrayList<>();
		// we will use prev and current arraylist to store the rows
		// also we will initialize for the first row
		List<Integer> prev = List.of(1);
		for (int row = 1; row < numRows; row++) {
			List<Integer> curr = new ArrayList<>();
			for (int col = 0; col <= row; col++) {
				// for the first and last element in the row it is 1
				if (col == 0 || col == row) {
					curr.add(1);
				} else {
					curr.add(prev.get(col - 1) + prev.get(col));
				}
			}
			triangle.add(curr);
			prev = curr;
		}
		return triangle;
	}

}
