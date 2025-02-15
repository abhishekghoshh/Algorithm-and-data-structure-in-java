package com.problems.greedy;

import java.util.*;

import static com.util.ArrayUtil.list;

/*
 *
 * problem links:
 * https://neetcode.io/problems/meeting-schedule-ii
 * https://leetcode.com/problems/meeting-rooms-ii/
 * https://www.interviewbit.com/problems/meeting-rooms/
 * https://www.naukri.com/code360/problems/meeting-room-ii_893289
 *
 * Solution video:
 * https://www.youtube.com/watch?v=FdzJmTCVyJU
 *
 * https://leetcode.ca/all/253.html
 * */
public class MeetingRooms2 {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // similar to the previous but here we will not use an extra list to store the meeting timings
    // we will add both of the loop operations in a single loop
    private static void type3() {
        List<List<Integer>> meetings = list(
                list(1, 18),
                list(18, 23),
                list(15, 29),
                list(4, 15),
                list(2, 11),
                list(5, 13)
        );
        int ans = minRooms3(meetings);
        System.out.println(ans);
    }


    // 2 pointer approach
    public static int minRooms3(List<List<Integer>> meetings) {
        int n = meetings.size();
        int[] startTime = new int[n];
        int[] endTime = new int[n];

        for (int i = 0; i < n; i++) {
            startTime[i] = meetings.get(i).get(0);
            endTime[i] = meetings.get(i).get(1);
        }
        // we will sort both of the timings
        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int max = 0;
        int count = 0;
        int i1 = 0, i2 = 0;
        // if the current time is for starting time, then we will increment count,
        // if it is for closing, then hypothetically we will close the room and decrement the counter.
        // as both of the times are sorted,
        // we can easily assume the timings as an indication of the meeting starting or closing
        while (i2 < n) {
            if (i1 < n && startTime[i1] < endTime[i2]) {
                i1++;
                count++;
            } else {
                i2++;
                count--;
            }
            max = Math.max(max, count);
        }
        return max;
    }


    // greedy approach
    // we will try to imagine the meetings in a timeline,
    // if the meeting the starting or ending.
    // we will start with counter 0 and go with the timeline.
    // if there is a new meeting started, then we will hypothetically open a room and increment the counter
    // if closed, then we will hypothetically close the room and decrement the counter
    private static void type2() {
        List<List<Integer>> meetings = list(
                list(1, 18),
                list(18, 23),
                list(15, 29),
                list(4, 15),
                list(2, 11),
                list(5, 13)
        );
        int ans = minRooms2(meetings);
        System.out.println(ans);
    }

    // to place the timings of meetings in a timeline,
    // first we will sort both of the list and add that to the list
    // true if a new meeting started and false if there is no meeting.
    // the list of booleans will represent that if the meeting is starting or ending in an order
    public static int minRooms2(List<List<Integer>> meetings) {
        int n = meetings.size();
        int[] startTime = new int[n];
        int[] endTime = new int[n];
        // first, we will sort both of the arrays
        for (int i = 0; i < n; i++) {
            startTime[i] = meetings.get(i).get(0);
            endTime[i] = meetings.get(i).get(1);
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        // we will store the timings in an boolean array
        List<Boolean> timeline = new ArrayList<>();
        int i1 = 0, i2 = 0;
        while (i2 < n) {
            if (i1 < n && startTime[i1] < endTime[i2]) {
                i1++;
                timeline.add(true);
            } else {
                i2++;
                timeline.add(false);
            }
        }
        // based on if it is starting or ending, we will increment or decrement counter
        int count = 0, max = 0;
        for (boolean isStarting : timeline) {
            count += isStarting ? (+1) : (-1);
            max = Math.max(max, count);
        }
        return max;
    }


    // brute force approach using
    private static void type1() {
        List<List<Integer>> meetings = list(
                list(1, 18),
                list(18, 23),
                list(15, 29),
                list(4, 15),
                list(2, 11),
                list(5, 13)
        );
        int ans = minRooms1(meetings);
        System.out.println(ans);
    }

    public static int minRooms1(List<List<Integer>> meetings) {
        // sorting the intervals based on their starting time
        meetings.sort(Comparator.comparingInt(p -> p.get(0)));
        // we need a min heap which can return us the meeting with the least time
        PriorityQueue<List<Integer>> rooms = new PriorityQueue<>(Comparator.comparingInt(p -> p.get(1)));
        int i = 0, n = meetings.size();
        rooms.offer(meetings.get(i++));
        while (i < n && !rooms.isEmpty()) {
            List<Integer> meeting = meetings.get(i++);
            // if the current meeting start time is greater than the last meeting end time,
            // then we will allot a new booking
            if (rooms.peek().get(1) > meeting.get(0)) {
                rooms.offer(meeting);
            } else {
                // else we will accommodate
                List<Integer> lastMeeting = rooms.poll();
                lastMeeting.set(1, meeting.get(1));
                rooms.offer(lastMeeting);
            }
        }
        return rooms.size();
    }
}
