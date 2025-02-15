package com.problems.heap;

import java.util.*;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 * https://www.naukri.com/code360/problems/k-most-frequent-elements_3167808
 * https://neetcode.io/problems/top-k-elements-in-list
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=7VoJn544QrM&list=PL_z_8CaSLPWdtY9W22VjnPxG30CXNZpI9&index=6
 * https://www.youtube.com/watch?v=YPTqKIgVk-k
 *
 * https://neetcode.io/solutions/top-k-frequent-elements
 * */

// Tags : Array, Heap, Hashing
public class KMostFrequentElements {

	// todo check type 1->4 one by one and remember the steps
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// same as previous
	// just here we will not store the frequency in the heap
	// it will be in the comparator
	// -104 <= nums[i] <= 104
	private static void type4() {
		int[] nums = {1, 1, 1, 2, 2, 3};
		int k = 2;
		int[] answer = topKFrequent4(nums, k);
		print(answer);
	}

	private static int[] topKFrequent4(int[] nums, int k) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i : nums) {
			if (i < min) min = i;
			if (i > max) max = i;
		}
		// we are using a frequency array instead of a map
		int offset = -min, N = max - min + 1;
		int[] freq = new int[N];
		for (int i : nums) freq[i + offset]++;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(num -> freq[num]));
		for (int i = 0; i < freq.length; i++) {
			minHeap.offer(i);
			if (minHeap.size() > k) minHeap.poll();
		}
		int[] answer = new int[k];
		// adding to the ans array from the back
		while (!minHeap.isEmpty()) answer[--k] = minHeap.poll() - offset;
		return answer;
	}

	// Same as the previous type
	// just here we are using the frequency array to store the count
	// this has some disadvantages like if one number is if the min is -99999 and max 99999
	// but most of the elements belongs to -100 to 100
	// then will waste multiple spaces by creating a large frequency array
	private static void type3() {
		int[] nums = {1, 1, 1, 2, 2, 3};
		int k = 2;
		int[] answer = topKFrequent3(nums, k);
		print(answer);
	}

	private static int[] topKFrequent3(int[] nums, int k) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i : nums) {
			if (i < min) min = i;
			if (i > max) max = i;
		}
		// we are using a frequency array instead of a map
		int offset = -min, N = max - min + 1;
		int[] freq = new int[N];
		for (int i : nums) freq[i + offset]++;
		// we will use a min heap, so that element with lower frequency will be popped out
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparing(pair -> pair.f));
		for (int i = 0; i < freq.length; i++) {
			minHeap.offer(new Pair(i, freq[i]));
			if (minHeap.size() > k) minHeap.poll();
		}
		int[] answer = new int[k];
		// adding to the ans array from the back
		while (!minHeap.isEmpty()) answer[--k] = minHeap.poll().num - offset;
		return answer;
	}

	// Using heap
	// optimized approach
	private static void type2() {
		int[] nums = {1, 1, 1, 2, 2, 3};
		int k = 2;
		int[] answer = topKFrequent2(nums, k);
		print(answer);
	}

	private static int[] topKFrequent2(int[] nums, int k) {
		Map<Integer, Integer> freq = new HashMap<>();
		// first we will calculate all the frequency
		for (int item : nums)
			freq.put(item, freq.getOrDefault(item, 0) + 1);
		// we will use a min heap, so that element with lower frequency will be popped out
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparing(pair -> pair.f));
		// now we will store the num and its count to the minHeap
		// to get the k highest frequent numbers
		for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
			minHeap.offer(new Pair(entry.getKey(), entry.getValue()));
			if (minHeap.size() > k) minHeap.poll();
		}
		int[] answer = new int[k];
		// adding to the ans array from the back
		while (!minHeap.isEmpty()) answer[--k] = minHeap.poll().num;
		return answer;
	}

	// Brute force approach
	// first we will create a frequency map with [num, freq[num]]
	// and store this pair into an array and
	private static void type1() {
		int[] nums = {1, 1, 1, 2, 2, 3};
		int k = 2;
		int[] answer = topKFrequent1(nums, k);
		print(answer);
	}

	private static int[] topKFrequent1(int[] nums, int k) {
		Map<Integer, Integer> freq = new HashMap<>();
		// at first, we will calculate all the frequency
		for (int num : nums)
			freq.put(num, freq.getOrDefault(num, 0) + 1);

		// creating a pair list of num and frequency of the num
		Pair[] pairs = new Pair[freq.size()];
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : freq.entrySet())
			pairs[i++] = new Pair(entry.getKey(), entry.getValue());
		// sorting the pair array based on the frequency in decreasing order
		Arrays.sort(pairs, (p1, p2) -> p2.f - p1.f);
		int[] answer = new int[k];
		// now taking only the first k items from the pairs array
		for (i = 0; i < k; i++) answer[i] = pairs[i].num;
		return answer;
	}

	static class Pair {
		public int num, f;

		public Pair(int num, int f) {
			this.num = num;
			this.f = f;
		}

		public String toString() {
			return "[" + num + "," + f + "]";
		}
	}

}
