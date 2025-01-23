package com.problems.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link:
 * https://leetcode.com/problems/fruit-into-baskets/description/
 * https://www.naukri.com/code360/problems/fruits-and-baskets_985356
 * 
 * Solution:
 * Aditya Verma :
 * https://www.youtube.com/watch?v=seOKHXB_w74&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=12
 * Neetcode:
 * https://www.youtube.com/watch?v=yYtaV0G3mWQ
 *
 * https://neetcode.io/solutions/fruit-into-baskets
 */

public class FruitIntoBaskets {
	// You are visiting a farm that has a single row of fruit trees arranged from
	// left to right. The trees are represented by an integer array fruits where
	// fruits[i] is the type of fruit the ith tree produces.
	// You want to collect as much fruit as possible. However, the owner has some
	// strict rules that you must follow:
	// You only have two baskets, and each basket can only hold a single type of
	// fruit. There is no limit on the amount of fruit each basket can hold.
	// Starting from any tree of your choice, you must pick exactly one fruit from
	// every tree (including the start tree) while moving to the right. The picked
	// fruits must fit in one of your baskets.
	// Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
	// Given the integer array fruits, return the maximum number of fruits you can pick.
	public static void main(String[] args) {
		type1();
		// sliding window
		type2();
		type3();
		// we will just use prev and prev2 variables
		type4();
	}

	// TODO best possible solution, same as type5 just it is less complicated to understand
	// easy to understand and implement
	// we have 3 cases
	// current fruit is exactly prev type of fruit
	// current fruit is previous to previous type of fruit
	// current fruit is a completely new fruit
	// we will take two pointer
	// one for previous another for previous to previous fruit
	// also we will store the previous fruit count
	// if there is a new type of fruit then we will set that count to 1
	// because the current fruit will become my new prev fruit
	// we will change the pointers accordingly
	private static void type4() {
		int[] fruits = {6, 2, 1, 1, 3, 6, 6};
		int max = totalFruit5(fruits);
		System.out.println(max);
	}

	private static int totalFruit5(int[] fruits) {
		int max = 0;
		// we will use 2 variables prev and prev2 for storing the fruits which are
		int prev = -1, prev2 = -1;
		// count of the current series with prev and prev2 type of fruits
		int count = 0;
		// if the current fruit is not the previous fruit then we have 2 cases either it is prev2 or different fruit
		// if it is prev2 then the series should continue
		// but if it is a different fruit then we need the count of prevFruit [which we can find using a loop]
		int prevFruitContinuousCount = 0;
		for (int fruit : fruits) {
			if (fruit == prev) {
				count++;
				prevFruitContinuousCount++;
			} else {
				if (fruit == prev2) {
					count++; // continue the fruit series
				} else {
					count = prevFruitContinuousCount + 1; // create a new series with prevFruitContinuousCount and the current fruit
				}
				// we will now update all the variables
				prevFruitContinuousCount = 1;
				prev2 = prev;
				prev = fruit;
			}
			max = Math.max(max, count);
		}
		return max;
	}


	// same as previous but here we will use the array as freq map
	private static void type3() {
		int[] fruits = {6, 2, 1, 1, 3, 6, 6};
		int max = totalFruit3(fruits);
		System.out.println(max);
	}

	private static int totalFruit3(int[] fruits) {
		int n = fruits.length;
		int max = 0;
		int types = 0;
		// creating the frequency array
		int maxFruit = -1;
		for (int fruit : fruits) {
			maxFruit = Math.max(fruit, maxFruit);
		}
		int[] freq = new int[maxFruit + 1];
		// we will go through the array and add the fruits one by one
		for (int left = 0, right = 0; right < n; right++) {
			int fruit = fruits[right];
			// it is a new fruit
			if (freq[fruit] == 0) types++;
			freq[fruit]++;
			// if type is greater than 2 then we will shrink the window
			while (left < right && types > 2) {
				int leftFruit = fruits[left++];
				// it is the last fruit, so we will decrease the type
				if (freq[leftFruit] == 1) types--;
				freq[leftFruit]--;
			}
			max = Math.max((right - left + 1), max);
		}
		return max;
	}


	// todo optimized approach using sliding window
	//  this is max toys problem which is same as the max fruits problem
	private static void type2() {
		int[] fruits = {1, 2, 3, 2, 2};
		int max = totalFruit2(fruits);
		System.out.println(max);

		char[] toys = {'a', 'b', 'a', 'a', 'c', 'c', 'a', 'b'};
		max = totalToys1(toys);
		System.out.println(max);
	}

	private static int totalFruit2(int[] fruits) {
		int n = fruits.length;
		int max = 0;
		Map<Integer, Integer> map = new HashMap<>();
		int types = 0;
		// we will go through the array and add the fruits one by one
		for (int left = 0, right = 0; right < n; right++) {
			int fruit = fruits[right];
			// it is a new fruit
			if (map.getOrDefault(fruit, 0) == 0) types++;
			map.put(fruit, map.getOrDefault(fruit, 0) + 1);
			// if type is greater than 2 then we will shrink the window
			while (left < right && types > 2) {
				int leftFruit = fruits[left++];
				// it is the last fruit, so we will decrease the type
				if (map.get(leftFruit) == 1) types--;
				map.put(leftFruit, map.get(fruit) - 1);
			}
			max = Math.max((right - left + 1), max);
		}
		return max;
	}

	private static int totalToys1(char[] toys) {
		int n = toys.length;
		int max = 0;
		Map<Character, Integer> map = new HashMap<>();
		int types = 0;
		for (int left = 0, right = 0; right < n; right++) {
			char toy = toys[right];
			// it is a new toy
			if (map.getOrDefault(toy, 0) == 0) types++;
			map.put(toy, map.getOrDefault(toy, 0) + 1);
			// if type is greater than 2 then we will shrink the window
			while (left < right && types > 2) {
				char leftToy = toys[left++];
				// it is the last toy, so we will decrease the type
				if (map.get(leftToy) == 1) types--;
				map.put(leftToy, map.get(toy) - 1);
			}
			max = Math.max((right - left + 1), max);
		}
		return max;
	}


	// brute force approach
	private static void type1() {

	}



}
