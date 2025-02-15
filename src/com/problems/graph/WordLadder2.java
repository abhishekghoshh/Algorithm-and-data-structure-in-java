package com.problems.graph;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/word-ladder-ii/
 * https://practice.geeksforgeeks.org/problems/word-ladder-ii/1
 * https://www.naukri.com/code360/problems/shortest-transition-paths_8391015
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=DREutrv2XD0&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=30
 * https://takeuforward.org/graph/g-30-word-ladder-ii/
 *
 * https://www.youtube.com/watch?v=AD4SFl7tu7I&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=31
 * https://takeuforward.org/graph/word-ladder-ii-optimised-approach-g-31/
 */
public class WordLadder2 {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	private static void type3() {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");

		List<List<String>> answer = findLadders3(wordList, beginWord, endWord);
		System.out.println(answer);
	}

	private static List<List<String>> findLadders3(List<String> wordList, String beginWord, String endWord) {
		// Push all values of wordList into a set
		// to make deletion from it easier and in less time complexity.
		Set<String> set = new HashSet<>(wordList);
		// if set does not contain the end word then we can directly return
		if (!set.contains(endWord)) return new ArrayList<>();

		// Perform BFS traversal and push the string in the queue
		// as soon as they’re found in the wordList.
		Queue<String> queue = new LinkedList<>();
		queue.add(beginWord);

		// Create a map of type word->level to get the idea
		// on which level the word comes after the transformations.
		Map<String, Integer> levels = new HashMap<>();
		// beginWord initialised with level 1.
		levels.put(beginWord, 1);
		set.remove(beginWord);
		int wordLen = beginWord.length();

		while (!queue.isEmpty()) {
			String word = queue.poll();
			int steps = levels.get(word);
			// Break out if the word matches the endWord
			if (word.equals(endWord)) break;
			// Replace each character of the word with letters from a-z
			// and check whether the transformed word is present in the
			// wordList or not, if yes then push to queue
			for (int i = 0; i < wordLen; i++) {
				char[] arr = word.toCharArray();
				char prevCh = arr[i];
				for (char ch = 'a'; ch <= 'z'; ch++) {
					if (ch == prevCh) continue;
					arr[i] = ch;
					String newWord = new String(arr);
					if (set.contains(newWord)) {
						queue.add(newWord);
						set.remove(newWord);
						// push the word along with its level in the map data structure.
						levels.put(newWord, steps + 1);
					}
				}
				arr[i] = prevCh;
			}
		}
		// A list for storing the final answer.
		List<List<String>> ans = new ArrayList<>();
		// If we reach the endWord, we stop and move to step-2,that is to perform reverse dfs traversal.
		if (levels.containsKey(endWord)) {
			// initializing arraylist with some default length
			List<String> seq = new ArrayList<>(levels.get(endWord));
			seq.add(endWord);
			dfs(beginWord, endWord, seq, ans, levels);
		}
		return ans;
	}

	private static void dfs(String beginWord, String word, List<String> seq, List<List<String>> ans, Map<String, Integer> map) {
		// Function for implementing backtracking using the created map
		// in reverse order to find the transformation sequence in less time.
		// If word equals beginWord, we’ve found one of the sequences to reverse the sequence and return.
		if (word.equals(beginWord)) {
			// before adding to the answer, we are reversing it
			List<String> bucket = new ArrayList<>(seq);
			Collections.reverse(bucket);
			ans.add(bucket);
			return;
		}
		int steps = map.get(word);
		// Replace each character of the word with letters from a-z
		// and check whether the transformed word is present in the map
		// and at the previous level or not.
		int len = word.length();
		for (int i = 0; i < len; i++) {
			char[] arr = word.toCharArray();
			char prevCh = arr[i];
			for (char ch = 'a'; ch <= 'z'; ch++) {
				if (ch == prevCh) continue;
				arr[i] = ch;
				String newWord = new String(arr);
				if (map.containsKey(newWord) && map.get(newWord) + 1 == steps) {
					seq.add(newWord);
					dfs(beginWord, newWord, seq, ans, map);
					// pop the current word from the back of the list, to traverse other possibilities.
					seq.remove(seq.size() - 1);
				}
			}
			arr[i] = prevCh;
		}
	}


	// todo this will also get TLE in leetcode
	private static void type2() {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");

		List<List<String>> answer = findLadders2(wordList, beginWord, endWord);
		System.out.println(answer);
	}

	private static List<List<String>> findLadders2(List<String> wordList, String beginWord, String endWord) {
		List<List<String>> answer = new LinkedList<>();
		// rather than maintaining a visited set, we will maintain a set
		// and everytime we encounter any word we will remove that from set
		Set<String> wordSet = new HashSet<>(wordList);
		// if end word is not present, then will return the empty list directly
		if (!wordSet.contains(endWord)) return answer;
		// as we are starting from the beginning word, so we will remove that
		wordSet.remove(beginWord);
		// this helper method will group all the similar words
		// like dog cog bog and the key will be #og
		Map<String, LinkedList<String>> wordsKeyMap = new HashMap<>();
		for (String word : wordList) groupWords(word, wordsKeyMap);

		// we are using a linked list specifically for the fast operation
		Queue<LinkedList<String>> queue = new LinkedList<>();

		LinkedList<String> seq = new LinkedList<>();
		seq.add(beginWord);
		queue.offer(seq);

		LinkedList<String> toBeDeletedWords = new LinkedList<>();

		while (!queue.isEmpty()) {
			// we will go level wise, so we need the current size of the queue
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				seq = queue.poll();
				// we have to change it from the last word of the sequence
				String word = seq.getLast();
				char[] copy = word.toCharArray();
				for (int j = 0; j < copy.length; j++) {
					char oldChar = copy[j];
					copy[j] = '#';
					List<String> wordKeyPair = wordsKeyMap.get(new String(copy));
					copy[j] = oldChar;
					if (null == wordKeyPair) continue;
					for (String newWord : wordKeyPair) {
						// if the end word is not present, then we will skip
						if (!wordSet.contains(newWord)) continue;
						// creating a new seq from the old seq and new word
						LinkedList<String> newSeq = new LinkedList<>(seq);
						newSeq.add(newWord);
						// also adding it to the queue
						queue.offer(newSeq);
						// we are adding these words in the toBeDeleted list, to delete in future
						toBeDeletedWords.add(newWord);
						// if at any level we find the end-word, then we will add that to the answer
						// we know it is the lowest level possible because we are going to the level wise
						if (newWord.equals(endWord)) answer.add(newSeq);
					}
				}
			}
			// and at last we may loop through the transformed words that are to be deleted
			for (String word : toBeDeletedWords) wordSet.remove(word);
			// if the answer array is not empty, that means there is least one answer exist,
			// so we can stop the loop here
			if (!answer.isEmpty()) return answer;
			// again we are clearing the toBeDeletedWords list
			toBeDeletedWords.clear();
		}
		return answer;
	}

	public static void groupWords(String word, Map<String, LinkedList<String>> wordsKeyMap) {
		char[] copy = word.toCharArray();
		for (int i = 0; i < copy.length; i++) {
			char oldChar = copy[i];
			copy[i] = '#';
			String key = new String(copy);
			copy[i] = oldChar;
			if (!wordsKeyMap.containsKey(key)) wordsKeyMap.put(key, new LinkedList<>());
			wordsKeyMap.get(key).add(word);
		}
	}

	// not the most optimized approach,
	// we will use the same technique as the WordLadder1 type2 problem.
	// but rather than storing the string only to queue,
	// we will store the list of string or the sequence,
	// but this answer will only be accepted in geekforgeeks not leetcode
	private static void type1() {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
		List<List<String>> answer = findLadders1(wordList, beginWord, endWord);
		System.out.println(answer);
	}

	private static List<List<String>> findLadders1(List<String> wordList, String beginWord, String endWord) {
		// rather than maintaining a visited set, we will maintain a set
		// and everytime we encounter any word we will remove that from set
		Set<String> wordSet = new HashSet<>(wordList);
		// if end word is not present, then will return the empty list directly
		if (!wordSet.contains(endWord)) return new ArrayList<>();

		List<List<String>> answer = new ArrayList<>();
		// as we are starting from the beginning word, so we will remove that
		wordSet.remove(beginWord);

		// we are using a linked list specifically for the fast operation
		Queue<LinkedList<String>> queue = new LinkedList<>();

		LinkedList<String> seq = new LinkedList<>();
		seq.add(beginWord);
		queue.offer(seq);

		LinkedList<String> toBeDeletedWords = new LinkedList<>();

		while (!queue.isEmpty()) {
			// we will check how many words are in the same level
			int size = queue.size();
			// then we will loop on that words of same level in queue
			for (int j = 0; j < size; j++) {
				seq = queue.poll();
				String word = seq.getLast();
				char[] copy = word.toCharArray();
				for (int i = 0; i < copy.length; i++) {
					// saving the current char for future usage
					char oldChar = copy[i];
					for (char ch = 'a'; ch <= 'z'; ch++) {
						copy[i] = ch;
						String newWord = new String(copy);
						// again replacing with the old char
						copy[i] = oldChar;
						// check if it exists in the set and push it in the queue.
						if (!wordSet.contains(newWord)) continue;
						// creating a new seq from the old seq and new word
						LinkedList<String> newSeq = new LinkedList<>(seq);
						newSeq.add(newWord);
						// also adding it to the queue
						queue.offer(newSeq);
						// we are adding these words in the toBeDeleted list, to delete in future
						toBeDeletedWords.add(newWord);
						// if at any level we find the end-word, then we will add that to the answer
						// we know it is the lowest level possible because we are going to the level wise
						if (newWord.equalsIgnoreCase(endWord)) answer.add(newSeq);
					}
				}
			}
			// and at last we may loop through the transformed words that are to be deleted
			for (String word : toBeDeletedWords) wordSet.remove(word);
			// if the answer array is not empty, that means there is least one answer exist
			// so we can stop the loop here
			if (!answer.isEmpty()) return answer;
			// again we are clearing the toBeDeletedWords list
			toBeDeletedWords.clear();
		}
		return answer;
	}

}
