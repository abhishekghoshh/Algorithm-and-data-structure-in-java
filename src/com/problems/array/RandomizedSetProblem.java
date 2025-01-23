package com.problems.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/*
 * Problem link :
 * https://leetcode.com/problems/insert-delete-getrandom-o1/description/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=j4KwhBziOpg
 *
 * https://neetcode.io/solutions/insert-delete-getrandom-o1
 */
public class RandomizedSetProblem {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		RandomizedSet randomizedSet = new RandomizedSet();
		randomizedSet.insert(1);
		randomizedSet.insert(10);
		randomizedSet.insert(20);
		randomizedSet.insert(30);
		for (int i = 0; i < 10; i++) System.out.println(randomizedSet.getRandom());
	}

	static class RandomizedSet {
		Map<Integer, Integer> cache;
		int[] arr;
		int n = 0;
		Random random;

		public RandomizedSet() {
			arr = new int[100001];
			cache = new HashMap<>();
			random = new Random();
		}

		public boolean insert(int val) {
			if (isPresent(val)) return false;
			cache.put(val, n);
			arr[n++] = val;
			return true;
		}

		private boolean isPresent(int val) {
			return cache.containsKey(val);
		}

		public boolean remove(int val) {
			if (!isPresent(val)) return false;
			// setting the last element to the index of the removed element
			int i = cache.get(val);
			int lastElement = arr[n - 1];
			cache.put(lastElement, i);
			swap(arr, n - 1, i);
			cache.remove(val); // removing the val
			n--; // decreasing the size
			return true;
		}

		private void swap(int[] arr, int i, int j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}

		public int getRandom() {
			return arr[random.nextInt(n)];
		}
	}
}
