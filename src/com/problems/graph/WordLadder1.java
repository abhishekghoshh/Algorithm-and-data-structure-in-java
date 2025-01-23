package com.problems.graph;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/word-ladder/
 * https://practice.geeksforgeeks.org/problems/word-ladder/1
 * https://www.naukri.com/code360/problems/word-ladder_1102319
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=tRPda0rcf8E&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=29
 * 
 * https://takeuforward.org/graph/word-ladder-i-g-29/
 */
public class WordLadder1 {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// todo tell this in the interview
	// here also we will use bfs,
	// but here we use some sort of caching
	// we will one letter different word in the same group
	private static void type4() {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
		int ans = ladderLength4(beginWord, endWord, wordList);
		System.out.println(ans);
	}

	private static int ladderLength4(String beginWord, String endWord, List<String> wordList) {
		// if the end word is not in the list, then we cannot construct the word
		if (!wordList.contains(endWord)) return 0;
		// this helper method will group all the similar words
		// like dog cog bog and the key will be #og
		Map<String, List<String>> wordMap = new HashMap<>();
		for (String word : wordList) groupWords(word, wordMap);
		// we will maintain a visited set
		Set<String> visited = new HashSet<>();
		visited.add(beginWord);
		Queue<String> queue = new LinkedList<>();
		// as we are starting from the beginWord, so we are adding it to the set
		queue.add(beginWord);
		int level = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String word = queue.poll();
				char[] copy = word.toCharArray();
				for (int j = 0; j < copy.length; j++) {
					char oldChar = copy[j];
					// assigning the wild card character
					copy[j] = '#';
					// checking how many words can be possible with this
					List<String> wordMapPair = wordMap.get(new String(copy));
					// again changing it back to the original character
					copy[j] = oldChar;
					if (null == wordMapPair) continue;
					// checking all the words
					for (String newWord : wordMapPair) {
						// if it is visited, then we are skipping the loop
						if (visited.contains(newWord)) continue;
						// if is the target word, then we can return the level directly
						// we are not waiting for the next level, that is why we are returning (level + 1)
						if (newWord.equals(endWord)) return level + 1;
						// then we are adding the new word to the queue and also set it to visit.
						queue.add(newWord);
						visited.add(newWord);
					}
				}
			}
			level++;
		}
		return 0;
	}

	// this method will build mapping for any word
	// like for hot it will create 3 mappings
	// #ot -> hot, h#t -> hot, ho# -> hot
	public static void groupWords(String word, Map<String, List<String>> wordMap) {
		char[] copy = word.toCharArray();
		for (int i = 0; i < copy.length; i++) {
			char oldChar = copy[i];
			copy[i] = '#';
			String key = new String(copy);
			if (!wordMap.containsKey(key)) wordMap.put(key, new ArrayList<>());
			wordMap.get(key).add(word);
			copy[i] = oldChar;
		}
	}

	// same as previous, just a little efficient than previous
	// here we will not use two separate set to track which is in the set and which is visited
	// TODO we shouldn't directly delete from the words set, because two different word can be transformed
	//  into the same word.As the words 'got' and 'not' can be changed into 'hot'
	//  but if we delete the word 'hot' at the first time when we find it from 'got'
	//  then the at the time of changing from 'not' to 'hot', we will not find the 'hot'
	//  word in the set but as we are going via level wise,
	//  and we are not saving the tracks, and we are only interested in the level count
	//  so, 'got' and 'not' will give us the same answer, we can directly delete it from the step
	private static void type3() {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
		int ans = ladderLength3(beginWord, endWord, wordList);
		System.out.println(ans);
	}

	private static int ladderLength3(String beginWord, String endWord, List<String> wordList) {
		// if the end word is not in the set, then we cannot construct the word
		Set<String> wordSet = new HashSet<>(wordList);
		if (!wordSet.contains(endWord)) return 0;
		// if the beginning word is present in the set, then we will remove it from the word set
		wordSet.remove(beginWord);
		// we will store current string and steps to the queue
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		int level = 1;
		// we will start the BFS traversal
		while (!queue.isEmpty()) {
			// we will check how many words are in the same level
			int size = queue.size();
			// then we will loop on that words of same level in queue
			for (int j = 0; j < size; j++) {
				String word = queue.poll();
				char[] copy = word.toCharArray();
				int n = copy.length;
				for (int i = 0; i < n; i++) {
					char oldChar = copy[i];
					for (char ch = 'a'; ch <= 'z'; ch++) {
						copy[i] = ch;
						String newWord = new String(copy);
						// after all the computation, we are again changing the position with the old character
						copy[i] = oldChar;
						// if is the target word, then we can return the level directly
						// we are not waiting for the next level, that is why we are returning (level + 1)
						if (newWord.equals(endWord)) return level + 1;
						// if wordSet doest not contain the new word, then we will skip for this word.
						if (!wordSet.contains(newWord)) continue;
						// else we will remove it from the word set and add it to the queue
						wordSet.remove(newWord);
						queue.offer(newWord);
					}
				}
			}
			level++;
		}
		return 0;
	}

	// efficient approach
	// we will do bfs
	// but here we will do level wise
	// we don't have to store the level here
	// we will pop all the words on each level
	// and after that we will check what are the words can be made from the current
	// words which is on the same level,
	// so in while loop if we do the level++ that will be sufficed
	private static void type2() {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
		int ans = ladderLength2(beginWord, endWord, wordList);
		System.out.println(ans);
	}

	private static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
		// if the end word is not in the set, then we cannot construct the word
		Set<String> wordSet = new HashSet<>(wordList);
		if (!wordSet.contains(endWord)) return 0;
		// we will maintain a set of visited words, and for O(1) retrieval we have converted the list to set
		Set<String> visited = new HashSet<>();
		// if the beginning word is present in the set, then we will add it to the visited set
		visited.add(beginWord);
		// we will store current string and steps to the queue
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		int level = 1;
		// we will start the BFS traversal
		while (!queue.isEmpty()) {
			// we will check how many words are in the same level
			int size = queue.size();
			// then we will loop on that words of same level in queue
			for (int j = 0; j < size; j++) {
				String word = queue.poll();
				char[] copy = word.toCharArray();
				int n = copy.length;
				for (int i = 0; i < n; i++) {
					char oldChar = copy[i];
					for (char ch = 'a'; ch <= 'z'; ch++) {
						copy[i] = ch;
						String newWord = new String(copy);
						// after all the computation, we are again changing the position with the old character
						copy[i] = oldChar;
						// if is the target word, then we can return the level directly
						// we are not waiting for the next level, that is why we are returning (level + 1)
						if (newWord.equals(endWord)) return level + 1;
						// if wordSet doest not contain the new word, or if it exists in the visited set, then we will skip for this word.
						if (!wordSet.contains(newWord) || visited.contains(newWord)) continue;
						// else we will add it to the visited set
						visited.add(newWord);
						queue.offer(newWord);
					}
				}
			}
			level++;
		}
		return 0;
	}

	// we will use bfs
	// we can change a letter of word at a time
	// "hit" -> "hot" -> "dot" -> "dog" -> cog"
	// and the transformed word must be in the word list
	// to search any word in list we will transform the list to set
	// so on each level we will derive all the words that is in the set
	// one we get our final word
	private static void type1() {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
		int ans = ladderLength1(beginWord, endWord, wordList);
		System.out.println(ans);
	}

	private static int ladderLength1(String beginWord, String endWord, List<String> wordList) {
		Set<String> wordSet = new HashSet<>(wordList);
		// if the end word is not in the set, then we cannot construct the word
		if (!wordSet.contains(endWord)) return 0;
		// we will maintain a set of visited words, and for O(1) retrieval we have converted the list to set
		Set<String> visited = new HashSet<>();
		// if the beginning word is present in the set, then we will add it to the visited set
		visited.add(beginWord);
		// we will store current string and steps to the queue
		Queue<WordPair> queue = new LinkedList<>();
		queue.offer(new WordPair(beginWord, 1));
		// now we will start the BFS traversal
		while (!queue.isEmpty()) {
			WordPair wordPair = queue.poll();
			String word = wordPair.word;
			int steps = wordPair.steps;
			// we return the steps as soon as the first occurrence of targetWord is found.
			if (word.equals(endWord)) return wordPair.steps;
			// Now, replace each character of word with char
			// from a-z then check if word exists in wordList.
			char[] copy = word.toCharArray();
			int n = copy.length;
			for (int i = 0; i < n; i++) {
				char oldCh = copy[i];
				for (char ch = 'a'; ch <= 'z'; ch++) {
					copy[i] = ch;
					String newWord = new String(copy);
					// after all the computation, we are again changing the position with the old character
					copy[i] = oldCh;
					// if wordSet doest not contain the new word, or if it exists in the visited set, then we will skip for this word.
					if (!wordSet.contains(newWord) || visited.contains(newWord)) continue;
					// else we will add it to the visited set
					visited.add(newWord);
					queue.offer(new WordPair(newWord, steps + 1));
				}
			}
		}
		return 0;
	}

	static class WordPair {
		public String word;
		public int steps;

		public WordPair(String word, int steps) {
			this.word = word;
			this.steps = steps;
		}
	}

}
