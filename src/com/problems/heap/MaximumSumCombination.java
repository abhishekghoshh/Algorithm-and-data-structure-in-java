package com.problems.heap;

import java.util.*;

import static com.util.PrintUtl.print;

/*
 * 
 * problem links :
 * https://www.codingninjas.com/codestudio/problems/k-max-sum-combinations_975322
 * https://www.interviewbit.com/problems/maximum-sum-combinations/
 * 
 * Solution link : 
 * https://www.youtube.com/watch?v=55TeHh37Ly8
 *
 * */
public class MaximumSumCombination {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// same as previous
	// just we will not store sum here
	// the sum will be calculated in the priority queue itself
	private static void type4() {
		int[] arr1 = {1, 4, 2, 3};
		int[] arr2 = {2, 5, 1, 6};
		int k = 4;
		int n = arr1.length;
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
				(p1, p2) -> (arr1[p2[0]] + arr2[p2[1]]) - (arr1[p1[0]] + arr2[p1[1]])
		);
		boolean[][] visited = new boolean[n][n];

		reverseSorted(arr1);
		reverseSorted(arr2);

		maxHeap.offer(new int[]{0, 0});
		visited[0][0] = true;

		int[] ans = new int[k];
		int left, right;
		for (int i = 0; i < k; i++) {
			int[] point = maxHeap.poll();
			left = point[0];
			right = point[1];
			ans[i] = arr1[left] + arr2[right];
			if (left + 1 < n && !visited[left + 1][right]) {
				maxHeap.offer(new int[]{left + 1, right});
				visited[left + 1][right] = true;
			}
			if (right + 1 < n && !visited[left][right + 1]) {
				maxHeap.offer(new int[]{left, right + 1});
				visited[left][right + 1] = true;
			}
		}
		print(ans);
	}

	// this will use a max heap
	// we know that the max element from the array will form the max sum
	// we will take that as a starting point.
	// first we will reverse sort both of the arrays,
	// then we will store the 0th index for both of the arrays.
	// as well as the sum of the arr1[ith] + arr2[jth]
	// we know that the next highest pair could be
	// either (i+1,j) or (i,j+1)
	// so we will also store those two indices.
	// we will do the process until there are k largest elements stored in the answer
	private static void type3() {
		int[] arr1 = {1, 4, 2, 3};
		int[] arr2 = {2, 5, 1, 6};
		int k = 4;
		int n = arr1.length;
		PriorityQueue<Point> maxHeap = new PriorityQueue<>(
				(point1, point2) -> point2.sum - point1.sum
		);
		boolean[][] visited = new boolean[n][n];
		// sorting both of the arrays in reverse
		reverseSorted(arr1);
		reverseSorted(arr2);

		maxHeap.offer(new Point(0, 0, arr1[0] + arr2[0]));
		// we will also use a visited array to check if the pair is already visited or not
		// because there is a multiple way to go to one point to another point
		visited[0][0] = true;

		int[] ans = new int[k];
		int left, right;
		for (int i = 0; i < k; i++) {
			Point point = maxHeap.poll();
			ans[i] = point.sum;
			left = point.left;
			right = point.right;
			// adding (i+1,j) pair
			if (left + 1 < n && !visited[left + 1][right]) {
				maxHeap.offer(new Point(left + 1, right, arr1[left + 1] + arr2[right]));
				visited[left + 1][right] = true;
			}
			// adding (i,j+1) th element
			if (right + 1 < n && !visited[left][right + 1]) {
				maxHeap.offer(new Point(left, right + 1, arr1[left] + arr2[right + 1]));
				visited[left][right + 1] = true;
			}
		}
		print(ans);
	}

	private static void reverseSorted(int[] arr) {
		Arrays.sort(arr);
		int bound = arr.length >> 1;
		for (int i = 0; i < bound; ++i) {
			int temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
	}

	private static class Point {
		public int left;
		public int right;
		public int sum;

		public Point(int left, int right, int sum) {
			this.left = left;
			this.right = right;
			this.sum = sum;
		}
	}

	// using priority queue or a min heap
	// to store only the top k elements
	private static void type2() {
		int[] arr1 = { 1, 4, 2, 3 };
		int[] arr2 = { 2, 5, 1, 6 };
		int k = 4;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (int item1 : arr1) {
			for (int item2 : arr2) {
				minHeap.offer(item1 + item2);
				if (minHeap.size() > k) minHeap.poll();
			}
		}
		List<Integer> list = new ArrayList<>();
		while (!minHeap.isEmpty()) list.add(minHeap.poll());

		Collections.reverse(list);
		System.out.println(list);
	}

	// brute force
	// time complexity (n^2 + n^2*log(n^2) + c)
	// space complexity O(c
	private static void type1() {
		int[] arr1 = { 1, 4, 2, 3 };
		int[] arr2 = { 2, 5, 1, 6 };
		int c = 4;
		List<Integer> list = new ArrayList<>();
		for (int item1 : arr1)
			for (int item2 : arr2)
				list.add(item1 + item2);

		list.sort((a, b) -> b - a);
		list = list.subList(0, c);
		System.out.println(list);
	}

}
