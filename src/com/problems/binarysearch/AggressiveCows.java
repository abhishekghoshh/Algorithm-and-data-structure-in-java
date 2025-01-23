package com.problems.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/aggressive-cows_1082559
 * https://www.codingninjas.com/codestudio/problems/chess-tournament_981299
 * https://www.spoj.com/problems/AGGRCOW/
 *
 * 
 * 
 * Solution is :
 * https://www.youtube.com/watch?v=R_Mfw4ew-Vo
 * https://www.youtube.com/watch?v=wSOfYesTBRk&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=71
 *
 * https://takeuforward.org/data-structure/aggressive-cows-detailed-solution/
 * https://leetcode.com/discuss/general-discussion/1302335/aggressive-cows-spoj-fully-explained-c
 * */
public class AggressiveCows {

	// Given an array of length ?N?, where each element denotes the position of a
	// stall.
	// Now you have ?N?
	// Stalls and an integer ?K?
	// Which denotes the number of
	// cows that are aggressive.
	// To prevent the cows from hurting each other, you
	// need to assign the cows to the stalls, such that the minimum distance between
	// any two of them is as large as possible.
	// Return the largest minimum distance.
	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		List<Integer> stalls = new ArrayList<>(List.of(18, 27, 44, 77, 69, 19, 35, 83, 9, 64));
		int n = stalls.size();
		// if the input is not sorted, then we will sort the input increasing order
		Collections.sort(stalls);
		int k = 4;
		// low will be zero as at the minimum we can place two cows in same stall
		// high will be difference between first and last stall
		int low = 0, high = stalls.get(n - 1) - stalls.get(0), mid, answer = -1;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (allocationPossible(stalls, k, mid)) {
				answer = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.println(answer);

	}

	private static boolean allocationPossible(List<Integer> stalls, int k, int distance) {
		// number of cows that can be possible with this distance
		int cows = 1;
		int prevCow = stalls.get(0);
		for (int i = 1; i < stalls.size(); i++) {
			if (stalls.get(i) - prevCow >= distance) {
				cows++;
				prevCow = stalls.get(i);
			}
		}
		return cows >= k;
	}

	// brute force approach
	private static void type1() {
		int[] stalls = {0, 3, 4, 7, 10, 9};
		int k = 4;
		int n = stalls.length; //size of array
		//sort the stalls[]:
		Arrays.sort(stalls);
		int limit = stalls[n - 1] - stalls[0];
		for (int i = 1; i <= limit; i++) {
			if (!canWePlace(stalls, i, k)) {
				limit = i - 1;
				break;
			}
		}
		System.out.println(limit);
	}

	public static boolean canWePlace(int[] stalls, int dist, int cows) {
		int n = stalls.length; //size of array
		int cntCows = 1; //no. of cows placed
		int last = stalls[0]; //position of last placed cow.
		for (int i = 1; i < n; i++) {
			if (stalls[i] - last >= dist) {
				cntCows++; //place next cow.
				last = stalls[i]; //update the last location.
			}
			if (cntCows >= cows) return true;
		}
		return false;
	}
}
