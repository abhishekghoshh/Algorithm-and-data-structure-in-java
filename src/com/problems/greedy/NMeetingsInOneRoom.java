package com.problems.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/meeting-rooms/description/
 * https://www.naukri.com/code360/problems/1062658
 * https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1
 *
 * Solution video :
 * https://www.youtube.com/watch?v=II6ziNnub1Q&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=46
 *
 * https://takeuforward.org/data-structure/n-meetings-in-one-room/
 * */

/* 
 * There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i]) where start[i] 
 * is start time of meeting i and end[i] is finish time of meeting i.What is the maximum number of meetings 
 * that can be accommodated in the meeting room when only one meeting can be held in the meeting room at a particular time?
 * 
 * Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting.
 * */
public class NMeetingsInOneRoom {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// we will sort the meeting by its ending time
	// because once we know that the meeting ends, then only we can add another meeting
	private static void type2() {
		int[] start = {1, 3, 0, 5, 8, 5};
		int[] end = {2, 4, 6, 7, 9, 9};
		int n = start.length;
		int[][] meetings = new int[n][3];
		for (int i = 0; i < n; i++) {
			meetings[i][0] = i + 1;
			meetings[i][1] = start[i];
			meetings[i][2] = end[i];
		}
		Arrays.sort(meetings, Comparator.comparingInt(meeting -> meeting[2]));
		List<Integer> meetingIndexes = new ArrayList<>();
		int pos, startTime;
		meetingIndexes.add(meetings[0][0]);
		int count = 1, lastEndTime = meetings[0][2];
		for (int i = 1; i < n; i++) {
			pos = meetings[i][0];
			startTime = meetings[i][1];
			if (lastEndTime < startTime) {
				meetingIndexes.add(pos);
				count++;
				lastEndTime = meetings[i][1];
			}
		}
		System.out.println("Total meeting count is " + count);
		System.out.println("Total meetings is " + meetingIndexes);
	}

	private static void type1() {
		int[] start = {1, 3, 0, 5, 8, 5};
		int[] end = {2, 4, 6, 7, 9, 9};
		int n = start.length;
		List<Meeting> meetings = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			meetings.add(new Meeting(i + 1, start[i], end[i]));
		}
		meetings.sort(Comparator.comparingInt(meeting -> meeting.end));
		List<Integer> meetingIndexes = new ArrayList<>();
		meetingIndexes.add(meetings.get(0).position);
		int count = 1, endTime = meetings.get(0).end;
		for (int i = 1; i < n; i++) {
			if (endTime < meetings.get(i).start) {
				meetingIndexes.add(meetings.get(i).position);
				count++;
				endTime = meetings.get(i).end;
			}
		}
		System.out.println("Total meeting count is " + count);
		System.out.println("Total meetings is " + meetingIndexes);
	}

	private static class Meeting {
		public int position;
		public int start;
		public int end;

		public Meeting(int position, int start, int end) {
			this.position = position;
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Meeting [position=" + position + ", start=" + start + ", end=" + end + "]";
		}

	}
}
