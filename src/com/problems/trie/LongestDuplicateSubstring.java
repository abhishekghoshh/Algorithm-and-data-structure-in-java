package com.problems.trie;

/*
 * Problem link :
 * https://leetcode.com/problems/longest-duplicate-substring/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=FQ8hcOOzQMU -> Tech dose
 * https://www.youtube.com/watch?v=dcOSQjhfBcQ -> Pepcoding
 * 
 * 
 * https://gist.github.com/SuryaPratapK/bb3a2235634af464a493ee44d2240faf
 * 
 */
public class LongestDuplicateSubstring {

	// TODO complete this solution later
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}


	// using Rabin karp's rolling hash algorithm
	private static void type4() {

	}

	// using trie approach
	// this solution is optimized but time complexity O(n^2)
	// also space complexity is little high
	// it is giving TLE in leetcode
	private static void type3() {
		String s = "abcd";
		char[] arr = s.toCharArray();
		int n = arr.length;
		Node head = new Node();
		Node node;
		int start = 0, end = -1, len = 0;
		// (n-i > len) is an early optimization
		// if we already found a maxLen and
		// all the later starting i can not beat this maxLen
		// the can just break the loop
		for (int i = 0; i < n && (n - i > len); i++) {
			node = head;
			for (int j = i; j < n; j++) {
				int pos = arr[j] - 'a';
				if (node.nodes[pos] == null)
					node.nodes[pos] = new Node();
				node = node.nodes[pos];
				node.endsWith++;
				if (node.endsWith >= 2 && (j - i + 1) > len) {
					len = j - i + 1;
					end = j;
					start = i;
				}
			}
		}
		String ans = s.substring(start, end + 1);
		System.out.println(ans);
	}

	static class Node {
		final Node[] nodes = new Node[26];
		int endsWith = 0;
	}

	// using dynamic programming
	private static void type2() {

	}

	// brute force approach
	private static void type1() {

	}

}
