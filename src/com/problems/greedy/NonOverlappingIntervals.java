package com.problems.greedy;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/non-overlapping-intervals/description/
 * https://www.codingninjas.com/studio/problems/non-overlapping-intervals_3169341
 *
 * Solution video :
 * https://www.youtube.com/watch?v=nONCGxWoUfM
 *
 * */
public class NonOverlappingIntervals {
    // TODO check the solutions one more time
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int max = intervals[0][1];
        int min = max;
        for (int i = 1; i < intervals.length; i++) {
            max = Math.max(max, intervals[i][1]);
            min = Math.min(min, intervals[i][1]);
        }
        int shift = 1 - min;
        int maxIntervalRange = 2 + max - min;
        int[] rightEnds = new int[maxIntervalRange];
        for (int[] interval : intervals) {
            int left = interval[0] + shift;
            int right = interval[1] + shift;
            if (left > rightEnds[right])
                rightEnds[right] = left;
        }
        int start = 1;
        int count = 1;
        for (int i = 2; i < maxIntervalRange; i++) {
            if (start <= rightEnds[i]) {
                count++;
                start = i;
            }
        }
        int answer = intervals.length - count;
        System.out.println(answer);
    }

    // brute force
    private static void type1() {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        // intervals are sorted by their starting time and then to the ending time
        Arrays.sort(intervals, (i1, i2) -> {
            if (i1[0] != i2[0]) return Integer.compare(i1[0], i2[0]);
            return Integer.compare(i1[1], i2[1]);
        });
        int n = intervals.length;
        int previousEnd = intervals[0][1];
        int count = 0;
        int start, end;
        for (int i = 1; i < n; i++) {
            start = intervals[i][0];
            end = intervals[i][1];
            if (previousEnd <= start) {
                previousEnd = end;
            } else {
                count++;
                previousEnd = Math.min(previousEnd, end);
            }
        }
        System.out.println(count);
    }
}
