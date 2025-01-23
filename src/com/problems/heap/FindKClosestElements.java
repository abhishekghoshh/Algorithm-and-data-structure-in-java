package com.problems.heap;

import java.util.*;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/find-k-closest-elements/description/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=J8yLD-x7fBI&list=PL_z_8CaSLPWdtY9W22VjnPxG30CXNZpI9&index=5
 * https://www.youtube.com/watch?v=o-YDQzHoaKM
 *
 * https://neetcode.io/solutions/find-k-closest-elements
 * */
public class FindKClosestElements {
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}


	// TODO explain this approach int he interview or explain the heap approach first
	// todo best approach using binary search technique
	//  find the start of the list
	//  where the partition will be started
	private static void type4() {
		int[] arr = {-2, -1, 1, 2, 3, 4, 5};
		int k = 7;
		int x = 3;
		List<Integer> answer = findClosestElements4(arr, k, x);
		print(answer);
	}

	public static List<Integer> findClosestElements4(int[] arr, int k, int x) {
		int n = arr.length;
		int left = 0, right = n - k;
		// Binary search against the criteria described
		while (left < right) {
			// we are considering mid to be the start of the range
			int mid = left + ((right - left) >> 1);

			if (x - arr[mid] > arr[mid + k] - x)
				left = mid + 1;
			else
				right = mid;
		}
		// Create output in correct format
		List<Integer> result = new ArrayList<>(k);
		for (int i = left; i < left + k; i++)
			result.add(arr[i]);

		return result;
	}

	private static void type3() {
		int[] arr = {-2, -1, 1, 2, 3, 4, 5};
		int k = 7;
		int x = 3;
		List<Integer> answer = findClosestElements3(arr, k, x);
		print(answer);
	}

	private static List<Integer> findClosestElements3(int[] arr, int k, int x) {
		List<Integer> list = new ArrayList<>();

		return list;
	}

	// todo same as type1
	private static void type2() {
		int[] arr = {-2, -1, 1, 2, 3, 4, 5};
		int k = 7;
		int x = 3;
		List<Integer> answer = findClosestElements2(arr, x, k);
		print(answer);
	}

	private static List<Integer> findClosestElements2(int[] arr, int x, int k) {
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
				(p1, p2) -> {
					if (p1[0] != p2[0])
						return Integer.compare(p2[0], p1[0]);
					return Integer.compare(p2[1], p1[1]);
				}
		);
		for (int num : arr) {
			int d = Math.abs(num - x);
			maxHeap.offer(new int[]{d, num});
			if (maxHeap.size() > k) maxHeap.poll();
		}
		List<Integer> ans = new ArrayList<>();
		while (!maxHeap.isEmpty()) ans.add(maxHeap.poll()[1]);
		Collections.sort(ans);
		return ans;
	}

	// todo a very optimized approach Using max heap
	//  we will create a max heap but we will only store k elements
	//  once there is k+1 element then we will pop the top most element which is the highest among them
	private static void type1() {
		int[] arr = {-2, -1, 1, 2, 3, 4, 5};
		int k = 7;
		int x = 3;
		int[] answer = findClosestElements1(k, arr, x);
		print(answer);
	}

	private static int[] findClosestElements1(int k, int[] arr, int x) {
		PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
				(p1, p2) -> {
					if (p1.d != p2.d)
						return Integer.compare(p2.d, p1.d);
					return Integer.compare(p2.num, p1.num);
				}
		);
		for (int num : arr) {
			int d = Math.abs(num - x);
			maxHeap.offer(new Pair(d, num));
			if (maxHeap.size() > k) maxHeap.poll();
		}
		int[] ans = new int[k];
		for (int i = 0; i < k; i++) {
			ans[i] = maxHeap.poll().num;
		}
		Arrays.sort(ans);
		return ans;
	}

	static class Pair {
		int num, d;

		public Pair(int num, int d) {
			this.num = num;
			this.d = d;
		}
	}

}
