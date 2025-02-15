package com.problems.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * problem links:
 * https://leetcode.com/problems/meeting-rooms/description/
 * https://www.lintcode.com/problem/920/
 *
 * Solution video:
 * https://www.youtube.com/watch?v=PaJxqZVPhbg
 *
 * https://leetcode.ca/all/252.html
 * https://nwthomas.medium.com/meeting-rooms-leetcode-920-7e9d06f38a23
 * */
public class MeetingRooms1 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
    }

    // brute force
    // but efficient approach
    private static void type1() {
        List<List<Integer>> intervals = new ArrayList<>(List.of(
                List.of(0, 30),
                List.of(5, 10),
                List.of(15, 20)
        ));
        boolean ans = canAttendMeetings(intervals);
        System.out.println(ans);
    }

    public static boolean canAttendMeetings(List<List<Integer>> intervals) {
        // sorting the intervals based on their starting time
        intervals.sort(Comparator.comparingInt(p -> p.get(0)));
        int i = 0, n = intervals.size();
        // we will initialize the end time with -inf
        int end = Integer.MIN_VALUE;
        while (i < n) {
            List<Integer> interval = intervals.get(i++);
            int newStartTime = interval.get(0);
            int newEndTime = interval.get(1);
            // we will check if the last meeting end time is greater than the current meeting starting time
            // so there is a conflict
            if (end > newStartTime) return false;
            // else we will set the current meeting end time to the end time
            end = newEndTime;
        }
        return true;
    }
}
