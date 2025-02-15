package com.problems.trie;

/*
 * Problem link :
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
 * https://neetcode.io/problems/design-word-search-data-structure
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=h-F2jRUzpBo -> Tech Dose
 * https://www.youtube.com/watch?v=6O73KA53ayY -> Codebix
 *
 * https://www.youtube.com/watch?v=BTf05gs_8iU -> NeetCode
 * https://neetcode.io/solutions/design-add-and-search-words-data-structure
 *
 * https://www.youtube.com/watch?v=T0Cml8tb9UA -> Pepcoding
 * 
 */
public class WordDictionary {

	public static void main(String[] args) {
		type1();

	}

	// using trie
	private static void type1() {
		Trie trie = new Trie();
		trie.addWord("bad");
		trie.addWord("dad");
		trie.addWord("mad");
		System.out.println(trie.search("pad"));
		System.out.println(trie.search("bad"));
		System.out.println(trie.search(".ad"));
		System.out.println(trie.search("b.."));
	}

	private static class Trie {
		public static final int SIZE = 26;
		private final Node head;

		public Trie() {
			head = new Node();
		}

		// iteratively going through the word and adding the node
		public void addWord(String word) {
			Node node = head;
			for (char ch : word.toCharArray()) {
				int pos = ch - 'a';
				if (node.nodes[pos] == null)
					node.nodes[pos] = new Node();
				node = node.nodes[pos];
			}
			node.isEnd = true;
		}

		public boolean search(String word) {
			return search(word.toCharArray(), 0, head);
		}

		private boolean search(char[] arr, int i, Node node) {
			// if we have reached the end of the word, then we will just check if the current node is the end of the word
			if (i == arr.length) return node.isEnd;
			if (arr[i] == '.') {
				for (int pos = 0; pos < SIZE; pos++) {
					// if the node is null, then continue
					if (node.nodes[pos] == null) continue;
					// if the node is not null, then search for the next character
					if (search(arr, i + 1, node.nodes[pos]))
						return true;
				}
				return false;
			}
			int pos = arr[i] - 'a';
			if (node.nodes[pos] == null) return false;
			return search(arr, i + 1, node.nodes[pos]);
		}
	}

	private static class Node {
		Node[] nodes = new Node[26];
		boolean isEnd = false;
	}
}
