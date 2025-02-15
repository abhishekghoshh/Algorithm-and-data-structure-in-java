package com.problems.graph;

import java.util.*;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/course-schedule/
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * https://www.geeksforgeeks.org/problems/prerequisite-tasks/1
 * https://www.geeksforgeeks.org/problems/course-schedule/1
 *
 * https://www.naukri.com/code360/problems/course-schedule-ii_1069243
 *
 * Solution link :
 * https://www.youtube.com/watch?v=iTBaI90lpDQ&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=23
 * https://www.youtube.com/watch?v=WAOfKpxYHR8&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=24
 * 
 * https://takeuforward.org/data-structure/course-schedule-i-and-ii-pre-requisite-tasks-topological-sort-g-24/
 */
public class CourseSchedule {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// exactly the same as the type2
	private static void type3() {
		int numCourses = 4;
		int[][] prerequisites = {
				{1, 0},
				{2, 0},
				{3, 1},
				{3, 2}
		};
		boolean answer = canFinish(numCourses, prerequisites);
		System.out.println(answer);
	}

	// this is the problem of the course schedule 1
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		// create the adjacency list and add the nodes
		List<List<Integer>> adjacencyList = new ArrayList<>();
		for (int i = 0; i < numCourses; i++)
			adjacencyList.add(new ArrayList<>());
		for (int[] pair : prerequisites) {
			int start = pair[1];
			int end = pair[0];
			adjacencyList.get(start).add(end);
		}
		int[] visited = new int[numCourses];
		// we will loop through all the nodes and check if there is any cycle or not
		for (int i = 0; i < numCourses; i++)
			if (visited[i] == 0 && hasCycle2(i, visited, adjacencyList))
				return false;
		return true;
	}

	private static boolean hasCycle2(int start, int[] visited, List<List<Integer>> adjacencyList) {
		visited[start] = 2;
		for (int end : adjacencyList.get(start)) {
			// the visited value of end is 2, means we have started with end node in a previous dfs call,
			// but again we are getting the end node; that means it is a cycle
			if (visited[end] == 2) return true;
			// if end value is 0 means we can start dfs with this node
			// but if we are getting true from the dfs call of the end
			if (visited[end] == 0 && hasCycle2(end, visited, adjacencyList)) return true;
		}
		// after all the dfs calls we are setting it to 1
		// means we have completely visited the start node and explored all its connected nodes
		visited[start] = 1;
		return false;
	}

	// using dfs
	// we will just create the adjacency list
	private static void type2() {
		int numCourses = 4;
		int[][] prerequisites = {
				{1, 0},
				{2, 0},
				{3, 1},
				{3, 2}
		};
		int[] answer = findOrder2(numCourses, prerequisites);
		print(answer);
	}

	public static int[] findOrder2(int numCourses, int[][] prerequisites) {
		// create the adjacency list and add the nodes
		List<List<Integer>> adjacencyList = new ArrayList<>();
		for (int i = 0; i < numCourses; i++)
			adjacencyList.add(new ArrayList<>());
		for (int[] pair : prerequisites) {
			int start = pair[1];
			int end = pair[0];
			adjacencyList.get(start).add(end);
		}

		// we will use normal hasCycle method to find if there is any cycle or not
		// and will use a stack to add th nodes same as the topological sort
		int[] visited = new int[numCourses];
		Stack<Integer> stack = new Stack<>();

		// we will loop through all the nodes and check if there is any cycle or not
		for (int i = 0; i < numCourses; i++)
			if (visited[i] == 0 && hasCycle(i, visited, adjacencyList, stack))
				return new int[]{};

		int[] answer = new int[numCourses];
		int i = 0;
		// we will add the nodes in the stack
		// nodes at the top of the stack means they are visited last
		// they have a least dependency we can add that into the first in the answer
		while (!stack.isEmpty()) answer[i++] = stack.pop();
		return answer;
	}

	private static boolean hasCycle(int start, int[] visited, List<List<Integer>> adjacencyList, Stack<Integer> stack) {
		visited[start] = 2;
		for (int end : adjacencyList.get(start)) {
			// the visited value of end is 2, means we have started with end node in a previous dfs call,
			// but again we are getting the end node; that means it is a cycle
			if (visited[end] == 2) return true;
			// if end value is 0 means we can start dfs with this node
			// but if we are getting true from the dfs call of the end
			if (visited[end] == 0 && hasCycle(end, visited, adjacencyList, stack)) return true;
		}
		// after all the dfs calls we are setting it to 1
		// means we have completely visited the start node and explored all its connected nodes
		visited[start] = 1;
		// also we will add it to the stack, just like topological sort
		stack.push(start);
		return false;
	}

	// Course can be scheduled or not and print the sequence
	// There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
	// You are given an array prerequisites where prerequisites[i] = [ai, bi]
	// indicates that you must take course bi first if you want to take course ai
	// the pair [0, 1], indicates that to take course 0 you have to first take course 1.
	private static void type1() {
		int numCourses = 4;
		int[][] prerequisites = {
				{1, 0},
				{2, 0},
				{3, 1},
				{3, 2}
		};
		int[] answer = findOrder1(numCourses, prerequisites);
		print(answer);
	}

	public static int[] findOrder1(int numCourses, int[][] prerequisites) {
		List<List<Integer>> adjacencyList = new ArrayList<>();
		for (int i = 0; i < numCourses; i++)
			adjacencyList.add(new ArrayList<>());

		int[] indegree = new int[numCourses];
		// creating the adjacency list and in degree at the same place
		// if pair is [a,b] then a is dependent on b so b -> a
		for (int[] pair : prerequisites) {
			int start = pair[1];
			int end = pair[0];
			adjacencyList.get(start).add(end);
			indegree[end]++;
		}
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++)
			// if in degree is 0 that means there is no incoming edge,
			// so we can start with that course, we will start bfs from that node
			if (indegree[i] == 0) queue.offer(i);

		// queue is empty means there is no starting point
		if (queue.isEmpty()) return new int[]{};
		int[] answer = new int[numCourses];
		int size = 0;
		// if there is a cycle, then at some point indegree will never 0 for some nodes
		while (!queue.isEmpty()) {
			int start = queue.poll();
			answer[size++] = start;
			for (int end : adjacencyList.get(start)) {
				// everytime we come across start -> end, we decrease inDegree[end]
				indegree[end]--;
				// in degree is 0 means we have covered all its previous nodes
				if (indegree[end] == 0) queue.offer(end);
			}
		}
		if (size != numCourses) return new int[]{};
		return answer;
	}

}
