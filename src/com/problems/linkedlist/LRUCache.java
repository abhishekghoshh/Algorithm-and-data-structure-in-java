package com.problems.linkedlist;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/*
 * Problem link :
 * https://leetcode.com/problems/lru-cache/description/
 * https://neetcode.io/problems/lru-cache
 * https://www.naukri.com/code360/problems/670276
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=xDEuM5qa0zg&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=77
 * https://www.youtube.com/watch?v=7ABFKPK2hD4
 * 
 * https://takeuforward.org/data-structure/implement-lru-cache/
 * https://neetcode.io/solutions/lru-cache
 */


// Tags : Linked List. Hashing
public class LRUCache {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// This is purely for leetcode
	// same as type2
	// here we will use an array instead of hashmap
	// as the keys will be starting from the 0 to n
	private static void type3() {

	}

	static class LRUCache3 {
		static class Node {
			int key, value;
			Node next, prev;

			Node(int key, int value) {
				this.key = key;
				this.value = value;
				this.next = null;
				this.prev = null;
			}
		}

		Node head, tail;
		Node[] map;
		int capacity, count;

		public LRUCache3(int capacity) {
			this.capacity = capacity;
			count = 0;
			map = new Node[10000 + 1];
			head = new Node(-1, -1);
			tail = new Node(-1, -1);
			head.next = tail;
			tail.prev = head;
		}

		private void deleteNode(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}

		private void addToTail(Node node) {
			node.prev = tail.prev;
			tail.prev.next = node;
			node.next = tail;
			tail.prev = node;
		}

		public int get(int key) {
			if (map[key] == null) return -1;
			Node node = map[key];
			deleteNode(node);
			addToTail(node);
			return node.value;
		}

		public void put(int key, int value) {
			if (map[key] != null) {
				Node updatedNode = map[key];
				updatedNode.value = value;
				deleteNode(updatedNode);
				addToTail(updatedNode);
			} else {
				Node newNode = new Node(key, value);
				map[key] = newNode;
				if (count < capacity) {
					count++;
					addToTail(newNode);
				} else {
					map[head.next.key] = null;
					deleteNode(head.next);
					addToTail(newNode);
				}
			}
		}
	}

	// This is purely for leetcode same as type1 here will not use any generic
	private static void type2() {

	}

	static class LRUCache2 {
		private final Node head;
		private final Node tail;
		private final int capacity;
		private int size = 0;
		Map<Integer, Node> cache;

		public LRUCache2(int capacity) {
			this.capacity = capacity;
			cache = new HashMap<>();
			head = new Node();
			tail = new Node();
			head.next = tail;
			tail.prev = head;
		}

		public int get(int key) {
			if (cache.containsKey(key)) {
				removeAndAddAfterHead(cache.get(key));
				return cache.get(key).value;
			}
			return -1;
		}

		public void put(int key, int value) {
			if (cache.containsKey(key)) {
				removeAndAddAfterHead(cache.get(key));
				cache.get(key).value = value;
			} else {
				if (size == capacity) {
					Node last = tail.prev;
					cache.remove(last.key);
					remove(last);
					size--;
				}
				size++;
				Node node = new Node(key, value);
				add(this.head, node);
				cache.put(key, node);
			}
		}

		private void removeAndAddAfterHead(Node node) {
			remove(node);
			add(this.head, node);
		}

		private void add(Node head, Node node) {
			Node next = head.next;
			node.next = next;
			node.prev = head;
			head.next = node;
			next.prev = node;
		}

		private void remove(Node node) {
			Node prev = node.prev;
			prev.next = node.next;
			node.next.prev = prev;
		}

		static class Node {
			public int key;
			public int value;
			public Node prev;
			public Node next;

			public Node() {
				this(-1, -1);
			}

			public Node(int key, int value) {
				this.key = key;
				this.value = value;
				this.next = null;
				this.prev = null;
			}
		}
	}

	// check leetcode submission for a better solution
	private static void type1() {
		LRUCache1<Integer, Integer> cache = new LRUCache1<>(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache);
		System.out.println(cache.get(1));
		System.out.println(cache);
		cache.put(3, 3);
		System.out.println(cache);
		System.out.println(cache.get(2));
		cache.put(4, 4);
		System.out.println(cache);
		System.out.println(cache.get(1));
	}

	public static class LRUCache1<T, R> {
		private final Node<T, R> head;
		private final Node<T, R> tail;
		private final int capacity;
		private int size = 0;
		// We can also create an array in place of map if the key is integer,
		// and we know the key range which is very good for leetcode
		// but in real world scenario it will not help and using generics may look over engineering,
		// but it will work better if we have a dynamic key value pair based on situation
		Map<T, Node<T, R>> cache;

		public LRUCache1(int capacity) {
			this.capacity = capacity;
			cache = new HashMap<>();
			head = new Node<>();
			tail = new Node<>();
			head.next(tail);
			tail.prev(head);
		}

		public R get(T key) {
			if (cache.containsKey(key)) {
				removeAndAddAfterHead(cache.get(key));
				return cache.get(key).value();
			}
			return null;
		}

		private void removeAndAddAfterHead(Node<T, R> node) {
			remove(node);
			add(this.head, node);
		}

		private void add(Node<T, R> head, Node<T, R> node) {
			Node<T, R> next = head.next;
			node.next = next;
			node.prev = head;
			head.next = node;
			next.prev = node;
		}

		private void remove(Node<T, R> node) {
			Node<T, R> prev = node.prev;
			prev.next = node.next;
			node.next.prev = prev;
		}

		public void put(T key, R value) {
			if (cache.containsKey(key)) {
				removeAndAddAfterHead(cache.get(key));
				cache.get(key).value(value);
			} else {
				if (size == capacity) {
					cache.remove(tail.prev.key());
					remove(tail.prev);
					size--;
				}
				size++;
				Node<T, R> node = new Node<>(key, value);
				add(this.head, node);
				cache.put(key, node);
			}
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

		public int capacity() {
			return capacity;
		}

		public int size() {
			return size;
		}

		public Set<T> keys() {
			return cache.keySet();
		}

		public static class Node<T, R> {
			private final T key;
			R value;
			Node<T, R> prev;
			Node<T, R> next;

			public Node(T key, R value) {
				this.key = key;
				this.value = value;
				this.next = null;
				this.prev = null;
			}

			public Node() {
				this(null, null);
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
