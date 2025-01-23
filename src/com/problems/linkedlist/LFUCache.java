package com.problems.linkedlist;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link :
 * https://leetcode.com/problems/lfu-cache/description/
 * https://www.naukri.com/code360/problems/lfucache_3114758
 *
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=0PSB9y8ehbk&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=79
 * https://www.youtube.com/watch?v=bLEIHn-DgoA
 *
 * https://neetcode.io/solutions/lfu-cache
 */

// Tags : Linked List. Hashing
public class LFUCache {


	// todo check this later one more time
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// accepted in leetcode
	// TODO check the solution later
	// here we are using node and doubly linked list
	private static void type4() {

	}

	static class LFUCache4 {
		static class Node {
			int key, val;
			int cnt = 1;
			Node prev, next;

			Node(int key, int val) {
				this.key = key;
				this.val = val;
			}
		}

		Node[] keys = new Node[100001];
		Node[] counts = new Node[200001];
		Node tail;
		int size = 0;
		int capacity;

		public LFUCache4(int capacity) {
			this.capacity = capacity;
		}

		void remove(Node node) {
			Node prev = node.prev;
			Node next = node.next;
			if (prev != null) prev.next = next;
			if (next != null) next.prev = prev;
			keys[node.key] = null;
			if (counts[node.cnt] == node) {
				if (next != null && next.cnt == node.cnt)
					counts[node.cnt] = next;
				else counts[node.cnt] = null;
			}
			if (tail == node) tail = prev;
			node.prev = null;
			node.next = null;
		}

		void insert(Node node, Node next) {
			counts[node.cnt] = node;
			keys[node.key] = node;
			if (next == null) {
				if (tail != null) tail.next = node;
				node.prev = tail;
				tail = node;
			} else {
				if (next.prev != null) next.prev.next = node;
				node.prev = next.prev;
				node.next = next;
				next.prev = node;
			}
		}

		void moveUp(Node node) {
			Node next = counts[node.cnt + 1] != null ? counts[node.cnt + 1] : counts[node.cnt];
			if (next == node) next = node.next;
			remove(node);
			node.cnt++;
			insert(node, next);
		}

		public int get(int key) {
			Node node = keys[key];
			if (node == null) return -1;
			moveUp(node);
			return node.val;
		}

		public void put(int key, int value) {
			Node node = keys[key];
			if (node == null) {
				node = new Node(key, value);
				if (size >= capacity) remove(tail);
				else size++;
				insert(node, counts[1]);
			} else {
				moveUp(node);
				node.val = value;
			}
		}
	}


	// accepted in leetcode
	// TODO check the solution later
	// here we are using node and doubly linked list
	private static void type3() {

	}

	static class LFUCache3 {
		static class Node {
			int key, val;
			Node next, prev;
			int freq = 1;

			Node(int key, int value) {
				this.key = key;
				this.val = value;
			}
		}

		static class DoublyLinkedList {
			Node head, tail;

			DoublyLinkedList() {
				head = new Node(-1, -1);
				tail = new Node(-1, -1);
				head.next = tail;
				tail.prev = head;
			}


			void addNode(Node node) {
				Node next = head.next;
				head.next = node;
				node.prev = head;
				head.next = node;
				node.next = next;
				next.prev = node;
			}

			Node removeNode() {
				Node node = tail.prev;
				node.prev.next = tail;
				tail.prev = node.prev;
				return node;
			}


			void removeNode(Node v) {
				Node prev = v.prev;
				Node next = v.next;
				prev.next = next;
				next.prev = prev;
			}

			boolean isEmpty() {
				return head.next == tail;
			}
		}

		HashMap<Integer, DoublyLinkedList> freqList = new HashMap<>();
		HashMap<Integer, Node> lfuCache = new HashMap<>();
		int capacity;
		int minFreq;

		public LFUCache3(int capacity) {
			this.capacity = capacity;
			minFreq = 1;
		}

		public int get(int key) {
			if (lfuCache.get(key) == null) return -1;
			Node node = lfuCache.get(key);
			freqList.get(node.freq).removeNode(node);
			if (freqList.get(node.freq).isEmpty()) {
				if (minFreq == node.freq)
					minFreq = node.freq + 1;
			}
			node.freq += 1;
			if (freqList.get(node.freq) == null) {
				DoublyLinkedList d = new DoublyLinkedList();
				d.addNode(node);
				freqList.put(node.freq, d);
			} else {
				freqList.get(node.freq).addNode(node);
			}
			return node.val;
		}

		public void put(int key, int value) {
			if (capacity == 0) return;
			if (lfuCache.get(key) != null) {
				Node node = lfuCache.get(key);
				freqList.get(node.freq).removeNode(node);
				if (freqList.get(node.freq).isEmpty()) {
					if (minFreq == node.freq)
						minFreq = node.freq + 1;
				}
				node.freq += 1;
				if (freqList.get(node.freq) == null) {
					DoublyLinkedList d = new DoublyLinkedList();
					d.addNode(node);
					freqList.put(node.freq, d);
				} else {
					freqList.get(node.freq).addNode(node);
				}
				node.val = value;
			} else {
				if (lfuCache.size() == capacity) {
					Node v = freqList.get(minFreq).removeNode();
					lfuCache.remove(v.key);
				}
				Node newNode = new Node(key, value);
				lfuCache.put(key, newNode);
				if (freqList.get(1) != null) {
					freqList.get(1).addNode(newNode);
				} else {
					DoublyLinkedList d = new DoublyLinkedList();
					d.addNode(newNode);
					freqList.put(1, d);
				}
				minFreq = 1;
			}
		}
	}

	// TODO solve it in leetcode this solution has some problem same as type 1
	//  this is specifically for leetcode, here we are specifying the types
	private static void type2() {

	}

	public static class LFUCache2 {
		private int size = 0;
		private final int capacity;
		private int leastFrequency = 1;
		Map<Integer, LRUCache> frequencyMap;
		Map<Integer, Node> cache;

		public LFUCache2(int capacity) {
			this.capacity = capacity;
			frequencyMap = new HashMap<>();
			cache = new HashMap<>();
		}

		public int get(int key) {
			if (cache.containsKey(key)) {
				Node node = cache.get(key);
				updateFrequencyAndAdd(node);
				return node.value;
			}
			return -1;
		}

		private void updateFrequencyAndAdd(Node node) {
			frequencyMap.get(node.f).remove(node);
			if (frequencyMap.get(node.f).isEmpty()) {
				frequencyMap.remove(node.f);
				leastFrequency = node.f + 1;
			}
			node.f++;
			if (!frequencyMap.containsKey(node.f)) {
				frequencyMap.put(node.f, new LRUCache());
			}
			frequencyMap.get(node.f).put(node);
		}

		public void put(int key, int value) {
			if (cache.containsKey(key)) {
				Node node = cache.get(key);
				node.value = value;
				updateFrequencyAndAdd(node);
			} else {
				if (size == capacity) {
					Node last = frequencyMap.get(leastFrequency).removeTail();
					cache.remove(last.key);
					if (frequencyMap.get(leastFrequency).isEmpty()) {
						frequencyMap.remove(leastFrequency);
					}
					size--;
				}
				size++;
				leastFrequency = 1;
				Node node = new Node(key, value, 1);
				if (!frequencyMap.containsKey(node.f)) {
					frequencyMap.put(node.f, new LRUCache());
				}
				cache.put(key, node);
				frequencyMap.get(node.f).put(node);
			}
		}

		public static class LRUCache {
			public int size = 0;
			public Node head, tail;

			public LRUCache() {
				head = new Node(-1, -1, 0);
				tail = new Node(-1, -1, 0);
				head.next = tail;
				tail.prev = head;
			}

			public void put(Node node) {
				size++;
				Node next = head.next;
				node.next = next;
				node.prev = head;
				head.next = node;
				next.prev = node;
			}

			public void remove(Node node) {
				size--;
				Node prev = node.prev;
				prev.next = node.next;
				node.next.prev = prev;
			}

			public Node removeTail() {
				Node node = tail.prev;
				remove(node);
				return node;
			}

			public boolean isEmpty() {
				return size == 0;
			}
		}

		public static class Node {
			public int key, value, f;
			public Node prev, next;

			public Node(int key, int value, int f) {
				this.f = f;
				this.key = key;
				this.value = value;
				this.next = null;
				this.prev = null;
			}
		}
	}

	// TODO this solution has some problem
	//  here we will use 2 map
	//  one for
	private static void type1() {
		Cache<Integer, String> cache = new Cache<>(3);
		cache.put(1, "Abhishek");
		cache.put(2, "Nasim Molla");
		System.out.println(cache);
		cache.put(3, "Bishal Mukherjee");
		System.out.println(cache);
		cache.put(1, "Abhishek Ghosh");
		System.out.println(cache);
		System.out.println(cache.get(2));
		System.out.println(cache);
		cache.put(4, "Abhishek Pal");
		cache.put(4, "Abhishek Pal");
		cache.put(1, "Abhishek Ghosh");
		System.out.println(cache);
	}

	public static class Cache<T, R> {
		private int size = 0;
		private final int capacity;
		private int minF = 1;
		Map<Integer, LFUCache1<T, R>> freqMap;
		Map<T, Node<T, R>> cache;

		public Cache() {
			this(3);
		}

		public Cache(int capacity) {
			this.capacity = capacity;
			freqMap = new HashMap<>();
			cache = new HashMap<>();
		}

		public R get(T key) {
			if (cache.containsKey(key)) {
				Node<T, R> node = cache.get(key);
				updateFrequencyAndAdd(node);
				return node.value();
			}
			return null;
		}

		private void updateFrequencyAndAdd(Node<T, R> node) {
			freqMap.get(node.f).remove(node);
			if (freqMap.get(node.f).isEmpty()) {
				freqMap.remove(node.f);
				minF = node.f + 1;
			}
			node.f++;
			freqMap.getOrDefault(node.f, new LFUCache1<>()).put(node);
		}

		public void put(T key, R value) {
			if (cache.containsKey(key)) {
				Node<T, R> node = cache.get(key);
				node.value(value);
				updateFrequencyAndAdd(node);
			} else {
				if (size == capacity) {
					Node<T, R> last = freqMap.get(minF).removeTail();
					cache.remove(last.key());
					if (freqMap.get(minF).isEmpty()) {
						freqMap.remove(minF);
					}
					size--;
				}
				size++;
				minF = 1;
				Node<T, R> node = new Node<>(key, value, 1);
				if (!freqMap.containsKey(node.f)) {
					freqMap.put(node.f, new LFUCache1<>());
				}
				cache.put(key, node);
				freqMap.get(node.f).put(node);
			}
		}

		@Override
		public String toString() {
			return "LFUCache {size=" + size + ", capacity=" + capacity + ", frequencyMap=" + freqMap + "}";
		}

		public static class LFUCache1<T, R> {
			private int size = 0;
			private final Node<T, R> head;
			private final Node<T, R> tail;

			public LFUCache1() {
				head = new Node<>();
				tail = new Node<>();
				head.next(tail);
				tail.prev(head);
			}

			public void put(Node<T, R> node) {
				size++;
				Node<T, R> next = head.next;
				node.next = next;
				node.prev = head;
				head.next = node;
				next.prev = node;
			}

			public void remove(Node<T, R> node) {
				size--;
				Node<T, R> prev = node.prev;
				prev.next = node.next;
				node.next.prev = prev;
			}

			public Node<T, R> removeTail() {
				Node<T, R> node = tail.prev;
				remove(node);
				return node;
			}

			public boolean isEmpty() {
				return size == 0;
			}

			public String toString() {
				Node<T, R> node = head.next;
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				while (tail != node) {
					sb.append(node.value());
					node = node.next;
					if (tail != node) {
						sb.append(",");
					}
				}
				sb.append("]");
				return sb.toString();
			}
		}

		public static class Node<T, R> {
			private final T key;
			R value;
			int f;
			Node<T, R> prev;
			Node<T, R> next;

			public Node(T key, R value, int f) {
				this.f = f;
				this.key = key;
				this.value = value;
				this.next = null;
				this.prev = null;
			}

			public Node() {
				this(null, null, 0);
			}

			public T key() {
				return key;
			}

			public R value() {
				return value;
			}

			public Node<T, R> value(R value) {
				this.value = value;
				return this;
			}

			public Node<T, R> prev() {
				return prev;
			}

			public Node<T, R> prev(Node<T, R> prev) {
				this.prev = prev;
				return this;
			}

			public Node<T, R> next() {
				return next;
			}

			public Node<T, R> next(Node<T, R> next) {
				this.next = next;
				return this;
			}
		}
	}
}
