package com.problems.trie;

/*
 * Problem link :
 * https://www.codingninjas.com/codestudio/problems/count-distinct-substrings_985292
 * https://www.codingninjas.com/codestudio/problems/number-of-distinct-substring_1465938
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=RV0QeTyHZxo&list=PLgUwDviBIf0pcIDCZnxhv0LkHf5KzG9zp&index=4
 * 
 * https://takeuforward.org/data-structure/number-of-distinct-substrings-in-a-string-using-trie/
 * 
 */
public class CountDistinctSubstrings {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// modified trie approach
	// time complexity O(n^2)
	private static void type2() {
		String str = "abab";
		char[] arr = str.toCharArray();
		int n = arr.length;
		// 1 for the empty substring
		int count = 1;
		// This will be trie root
		Node root = new Node();
		Node node;
		for (int i = 0; i < n; i++) {
			node = root;
			for (int j = i; j < n; j++) {
				int pos = arr[j] - 'a';
				// we are adding 1 to count every time we have encountered one new children
				// which is null, children is null means by adding the character we can make a
				// new substring
				if (node.nodes[pos] == null) {
					node.nodes[pos] = new Node();
					count++;
				}
				node = node.nodes[pos];
			}
		}
		System.out.println(count);
	}

	public static class Node {
		Node[] nodes = new Node[26];
	};

	// using trie
	// time complexity is O(n^3)
	private static void type1() {
		String str = "abab";
		char[] arr = str.toCharArray();
		// 1 for the empty substring
		int count = 1;
		Trie trie = new Trie();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				// we are adding the s.substring(i,j)
				if (trie.insert(arr, i, j)) count++;
			}
		}
		System.out.println(count);
	}

	public static class Trie {
		private final Node head;

		public Trie() {
			head = new Node();
		}

		public boolean insert(char[] arr, int start, int end) {
			Node node = head;
			for (int i = start; i <= end; i++) {
				int pos = arr[i] - 'a';
				if (node.nodes[pos] == null)
					node.nodes[pos] = new Node();
				node = node.nodes[pos];
			}
			// if the end is already set that means the word is not unique
			if (node.isEnd) return false;
			// word is unique, so we can set the end
			// and return true
			return node.isEnd = true;
		}

		public static class Node {
			public final Node[] nodes = new Node[26];
			public boolean isEnd;
		}
	}

}
