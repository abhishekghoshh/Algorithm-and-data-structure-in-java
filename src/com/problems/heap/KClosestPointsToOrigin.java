package com.problems.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/k-closest-points-to-origin/description/
 * https://neetcode.io/problems/k-closest-points-to-origin
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=XC4EotTewro&list=PL_z_8CaSLPWdtY9W22VjnPxG30CXNZpI9&index=8
 * https://www.youtube.com/watch?v=rI2EBUEMfTk
 *
 * https://www.geeksforgeeks.org/hoares-vs-lomuto-partition-scheme-quicksort/
 * https://www.techiedelight.com/quick-sort-using-hoares-partitioning-scheme/
 * https://neetcode.io/solutions/k-closest-points-to-origin
 * */

// Tags: Array, Heap
public class KClosestPointsToOrigin {

	public static void main(String[] args) {
		// this is good in leetcode but in interview it has the higher time complexity
		type1();
		type2();
		// discuss these 2 in the interview
		type3();
		type4();
	}

	private static int distance(int[] point) {
		return point[0] * point[0] + point[1] * point[1];
	}


	// same as previous
	// just a little optimized
	private static void type4() {
		int[][] points = {{3, 3}, {5, -1}, {-2, 4}, {-3, 3}, {1, 3}};
		int k = 3;
		int[][] answer = kClosest4(points, k);
		print(answer);
	}

	private static int[][] kClosest4(int[][] points, int k) {
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>((p1, p2) -> distance(p2) - distance(p1));

		for (int[] point : points) {
			maxHeap.add(point);
			if (maxHeap.size() > k) maxHeap.poll();
		}
        return maxHeap.toArray(new int[0][0]);
	}

	// todo optimized using maxHeap
	private static void type3() {
		int[][] points = {{3, 3}, {5, -1}, {-2, 4}, {-3, 3}, {1, 3}};
		int k = 3;
		int[][] answer = kClosest3(points, k);
		print(answer);
	}

	private static int[][] kClosest3(int[][] points, int k) {
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>((p1, p2) -> (p2[2] - p1[2]));
		for (int[] point : points) {
			int d = distance(point);
			if (maxHeap.size() < k) {
				maxHeap.offer(new int[]{point[0], point[1], d});
			} else if (d < maxHeap.peek()[2]) {
				maxHeap.poll();
				maxHeap.offer(new int[]{point[0], point[1], d});
			}
		}
		int[][] answer = new int[k][2];
		for (int i = 0; i < k; i++) {
			int[] point = maxHeap.poll();
			answer[i][0] = point[0];
			answer[i][1] = point[1];
		}
		return answer;
	}

	//
	// TODO not best approach but it is not required to explain it in the interview
	//  Use quick select algorithm with hoarse partition that is more complicated than lomuto but more efficient.
	//  time complexity is nlog(n) using heap it was nlog(k)
	private static void type2() {
		int[][] points = {{3, 3}, {5, -1}, {-2, 4}, {-3, 3}, {1, 3}};
		int k = 3;
		int[][] answer = kClosest2(points, k);
		print(answer);
	}

	private static int[][] kClosest2(int[][] points, int k) {
		int left = 0, right = points.length - 1;
		int length = points.length;
		while (length != k) {
			length = partition(points, left, right);
			if (length < k)
				left = length;
			else
				right = length;
		}
		return Arrays.copyOf(points, k);
	}

	private static int partition(int[][] points, int left, int right) {
		int mid = left + (right - left) / 2;
		int distance = distance(points[mid]);

		while (left <= right) {
			if (distance(points[left]) >= distance) {
				// this mean in right there is closer point, so we will swap that and decrement right
				int[] temp = points[left];
				points[left] = points[right];
				points[right--] = temp;
			} else {
				left++;
			}
		}
		// after this we will have (0 to left) elements which are closest to origin
		return left;
	}

	// todo brute force approach using normal sort
	//  to sort the array then return only k elements
	private static void type1() {
		int[][] points = {{3, 3}, {5, -1}, {-2, 4}, {-3, 3}, {1, 3}};
		int k = 3;
		int[][] answer = kClosest1(points, k);
		print(answer);
	}

	private static int[][] kClosest1(int[][] points, int k) {
		Arrays.sort(points, Comparator.comparingInt(KClosestPointsToOrigin::distance));
		return Arrays.copyOf(points, k);
	}
}
