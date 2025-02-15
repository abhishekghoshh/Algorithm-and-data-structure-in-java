package com.problems.array;

import com.util.PrintUtl;

import java.util.*;
/*
 * Problem link :
 * https://leetcode.com/problems/merge-intervals/
 * https://www.codingninjas.com/studio/problems/merge-all-overlapping-intervals_6783452
 * https://www.codingninjas.com/codestudio/problems/699917
 * 
 * Solution link:
 * https://www.youtube.com/watch?v=IexN60k62jo
 * https://www.youtube.com/watch?v=2JzRBPFYbKE&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=7
 *
 * https://takeuforward.org/data-structure/merge-overlapping-sub-intervals/
 * */


// Tags: Array, Greedy
public class MergeOverlappingSubIntervals {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int[][] intervals = {{1, 3}, {2, 6}, {8, 11}, {9, 10}, {15, 18}};
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int[] interval : intervals) {
			min = Math.min(min, interval[0]);
			max = Math.max(max, interval[0]);
		}

		int[] range = new int[max - min + 1];
		for (int[] interval : intervals) {
			range[interval[0] - min] = Math.max(interval[1] - min, range[interval[0] - min]);
		}

		int start = 0, end = 0;
		LinkedList<int[]> result = new LinkedList<>();
		for (int i = 0; i < range.length; i++) {
			if (range[i] == 0) {
				continue;
			}
			if (i <= end) {
				end = Math.max(range[i], end);
			} else {
				result.add(new int[]{start + min, end + min});
				start = i;
				end = range[i];
			}
		}
		result.add(new int[]{start + min, end + min});
		int[][] answer = result.toArray(new int[result.size()][]);
		PrintUtl.print2D(answer);
	}

	// optimized approach
	private static void type1() {
		int[][] intervals = {{1, 3}, {2, 6}, {8, 11}, {9, 10}, {15, 18}};
//		int[][] intervals = { { 1, 4 }, { 3, 5 }, { 0, 2 } };
		// given than intervals are sorted by their starting time
		// else we have to sort it manually
		Arrays.sort(intervals, Comparator.comparingInt(pair -> pair[0]));
		List<int[]> newIntervals = new ArrayList<>();
		newIntervals.add(intervals[0]);
		int[] last = intervals[0];
		for (int i = 1; i < intervals.length; i++) {
			if (last[1] >= intervals[i][0]) {
				last[1] = Math.max(last[1], intervals[i][1]);
			} else {
				newIntervals.add(intervals[i]);
				last = intervals[i];
			}
		}
		int[][] answer = new int[newIntervals.size()][2];
		for (int i = 0; i < newIntervals.size(); i++) {
			answer[i] = newIntervals.get(i);
		}
		PrintUtl.print2D(answer);
	}

}
