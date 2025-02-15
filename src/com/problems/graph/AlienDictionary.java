package com.problems.graph;

import java.util.*;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/alien-dictionary/1
 * https://www.naukri.com/code360/problems/alien-dictionary_630423
 * https://leetcode.com/problems/alien-dictionary
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=U3N_je7tWAs&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=26
 * 
 * https://takeuforward.org/data-structure/alien-dictionary-topological-sort-g-26/
 */
public class AlienDictionary {

	// This a classic problem of topological sort.
	// We just have to create a graph of letters and for that, we need to know
	// which letter is bigger than which letter.We can derive from the given words
	// then after that we can create a graph our last work will be to know the topo sort of the graph
	public static void main(String[] args) {
		type1();
		type2();
	}

	// todo using dfs and topological sort
	private static void type2() {
		String[] dict = {
				"baa",
				"abcd",
				"abca",
				"cab",
				"cad"
		};
		int n = 5;
		int k = 4;

		List<List<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i < k; i++) adjList.add(new ArrayList<>());

		// all the strings are in lexicographic order,
		// so the first string has lower value than the second,
		// so we need to find the characters for which the difference arises
		for (int i = 0; i < n - 1; i++) {
			char[] first = dict[i].toCharArray();
			char[] second = dict[i + 1].toCharArray();
			int j = 0, n2 = second.length, n1 = first.length;
			// we will increment the j till there is a different character
			while (j < n1 && j < n2 && first[j] == second[j]) j++;
			// we will convert the character to a zero-index integer
			if (j < n1 && j < n2) {
				// as the words are in lexicographical order,
				// so character in the first word will be lesser than the second word,
				// and we will draw edge from first[j] to second[j]
				int start = first[j] - 'a';
				int end = second[j] - 'a';
				adjList.get(start).add(end);
			}
		}

		// we will start the dfs for all unvisited nodes and after this the stack will be prepared
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[k];
		for (int i = 0; i < k; i++)
			if (!visited[i])
				dfs(i, visited, adjList, stack);

		// we will loop through the stack and add the letters into the ans
		StringBuilder dictionary = new StringBuilder();
		while (!stack.isEmpty()) {
			int start = stack.pop();
			// we need to translate the node value to character again
			char letter = (char) (start + 'a');
			dictionary.append(letter);
		}

		System.out.println(dictionary);
	}

	// simple topological sort using dfs
	private static void dfs(int start, boolean[] visited, List<List<Integer>> adjList, Stack<Integer> stack) {
		visited[start] = true;
		// we will loop through all its unvisited adjacent nodes
		for (int node : adjList.get(start))
			if (!visited[node])
				dfs(node, visited, adjList, stack);
		// and after that we will add that to the stack
		stack.push(start);
	}

	// todo using bfs and kahn algorithm
	private static void type1() {
		String[] dict = {
				"baa",
				"abcd",
				"abca",
				"cab",
				"cad"
		};
		int n = 5;
		int k = 4;

		List<List<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i < k; i++) adjList.add(new ArrayList<>());

		// all the strings are in lexicographic order,
		// so the first string has lower value than the second,
		// so we need to find the characters for which the difference arises
		for (int i = 0; i < n - 1; i++) {
			char[] first = dict[i].toCharArray();
			char[] second = dict[i + 1].toCharArray();
			int j = 0, n2 = second.length, n1 = first.length;
			// we will increment the j till there is a different character
			while (j < n1 && j < n2 && first[j] == second[j]) j++;
			// we will convert the character to a zero-index integer
			if (j < n1 && j < n2) {
				// as the words are in lexicographical order,
				// so character in the first word will be lesser than the second word,
				// and we will draw edge from first[j] to second[j]
				int start = first[j] - 'a';
				int end = second[j] - 'a';
				adjList.get(start).add(end);
			}
		}

		// we will calculate all the indegree from the adjacency list
		// and add starting nodes to the queue
		int[] indegree = new int[k];
		for (List<Integer> nodes : adjList)
			for (int node : nodes) indegree[node]++;
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < k; i++)
			if (indegree[i] == 0) queue.offer(i);

		// we will poll from the queue and store it to the string builder
		StringBuilder dictionary = new StringBuilder();
		while (!queue.isEmpty()) {
			int start = queue.poll();
			// we need to translate the node value to character again
			char letter = (char) (start + 'a');
			dictionary.append(letter);
			// we will loop through all its adjacent nodes
			for (int end : adjList.get(start)) {
				indegree[end]--;
				if (indegree[end] == 0) queue.offer(end);
			}
		}
		System.out.println(dictionary);
	}

}
