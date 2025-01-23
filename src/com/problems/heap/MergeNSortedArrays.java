package com.problems.heap;

import java.util.*;

import static com.util.PrintUtl.print;

/*
 * 
 * problem links :
 * https://www.naukri.com/code360/problems/merge-k-sorted-arrays_975379
 * 
 * Solution link : 
 * https://www.youtube.com/watch?v=l8CuET0jlDU
 * */
public class MergeNSortedArrays {
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// same as type2
	// just a little more efficient
	// optimized approach using min heap
	// lets say there is k arrays
	// the total size of elements is n
	// worst time complexity will be O(n*log(k))
	// O(n*log(k)) for removal and O(n*log(k)) for inserting next smallest element
	// space complexity is O(k) for min heap
	private static void type3() {
		int[][] arrays = {
				{2, 6, 12, 34},
				{3},
				{1, 3, 9, 10, 20},
				{23, 34, 36, 40},
				{4, 5, 7, 8}
		};
		int size = 0;
		for (int[] arr : arrays) size += arr.length;

		int[] answer = new int[size];

		// we will store all the array index from the array
		// for getting the current lowest value
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(
				Comparator.comparingInt(pair -> arrays[pair.arrI][pair.i]));

		// we will save the array index and the 0
		// because in 0th position we will get the lowest element
		for (int i = 0; i < arrays.length; i++)
			minHeap.offer(new Pair(i, 0));

		int i = 0;
		while (!minHeap.isEmpty()) {
			// we will poll the lowest element
			Pair first = minHeap.poll();
			int n = arrays[first.arrI].length;
			// but here we will do the optimization,
			// we will check the top value of the next top element from heap
			// we will add from the current array
			// while it is lesser equal to than the second top
			if (!minHeap.isEmpty()) {
				Pair second = minHeap.peek();
				while (first.i < n && arrays[first.arrI][first.i] <= arrays[second.arrI][second.i])
					answer[i++] = arrays[first.arrI][first.i++];

				// if the array has reached to length,
				// then there is no point of add the array back to heap
				if (first.i < n)
					minHeap.offer(first);

			} else {
				// else means there is nop other array
				// left in the heap,
				// so we will add all the array element to the array
				while (first.i < n)
					answer[i++] = arrays[first.arrI][first.i++];
			}
		}
		print(answer);
	}

	// optimized approach using min heap
	// lets say there is k arrays
	// the total size of elements is n
	// worst time complexity will be O(n*log(k))
	// O(n*log(k)) for removal and O(n*log(k)) for inserting next smallest element
	// space complexity is O(k) for min heap
	private static void type2() {
		int[][] arrays = {
				{2, 6, 12, 34},
				{3},
				{1, 3, 9, 10, 20},
				{23, 34, 36, 40},
				{4, 5, 7, 8}
		};
		int size = 0;
		for (int[] arr : arrays) size += arr.length;

		int[] answer = new int[size];

		// we will store all the array index from the array
		// for getting the current lowest value
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(
				Comparator.comparingInt(pair -> arrays[pair.arrI][pair.i]));

		// we will save the array index and the 0
		// because in 0th position we will get the lowest element
		for (int i = 0; i < arrays.length; i++)
			minHeap.offer(new Pair(i, 0));

		int i = 0;
		while (!minHeap.isEmpty()) {
			// we will poll the lowest element
			Pair pair = minHeap.poll();
			// and add to the answer
			answer[i++] = arrays[pair.arrI][pair.i++];
			int n = arrays[pair.arrI].length;
			// if the array has reached to length,
			// then there is no point of add the array back to heap
			if (pair.i < n)
				minHeap.offer(pair);
		}
		print(answer);
	}

	private static class Pair {
		int arrI;
		int i;

		public Pair(int arrI, int i) {
			this.arrI = arrI;
			this.i = i;
		}
	}

	// brute force approach
	// time complexity O(size*log(size))
	// space complexity O(size)
	private static void type1() {
		int[][] arrays = {
				{2, 6, 12, 34},
				{3},
				{1, 3, 9, 10, 20},
				{23, 34, 36, 40},
				{4, 5, 7, 8}
		};

		int size = 0;
		for (int[] arr : arrays) size += arr.length;

		List<Integer> list = new ArrayList<>();
		for (int[] arr : arrays) for (int item : arr) list.add(item);
		int[] answer = new int[size];
		for (int i = 0; i < size; i++) answer[i] = list.get(i);
		Arrays.sort(answer);
		print(answer);
	}

}
