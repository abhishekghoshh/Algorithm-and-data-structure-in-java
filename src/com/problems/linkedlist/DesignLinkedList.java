package com.problems.linkedlist;

/*
 *
 * problem links :
 * https://leetcode.com/problems/design-linked-list/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Wf4QhpdVFQo
 *
 * https://github.com/neetcode-gh/leetcode/blob/main/java/0707-design-linked-list.java
 * https://neetcode.io/solutions/design-linked-list
 * */

// TODO Explore later and code walkthrough
public class DesignLinkedList {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		MyLinkedList myLinkedList = new MyLinkedList();
		myLinkedList.addAtHead(1);
		myLinkedList.addAtTail(3);
		myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
		myLinkedList.get(1);              // return 2
		myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
		myLinkedList.get(1);
	}


	static class Node {
		final int val;
		Node prev;
		Node next;

		public Node(int val) {
			this.val = val;
			this.prev = null;
			this.next = null;
		}
	}

	static class MyLinkedList {
		final Node start;
		final Node end;
		int n = 0;

		public MyLinkedList() {
			start = new Node(0);
			end = new Node(0);
			start.next = end;
			end.prev = start;
		}

		public int get(int index) {
			if (index >= n) return -1;
			Node node = start;
			for (int i = 0; i <= index; i++) {
				node = node.next;
			}
			return node.val;
		}

		public void addAtHead(int val) {
			addInBetween(start, new Node(val), start.next);
		}

		public void addAtTail(int val) {
			addInBetween(end.prev, new Node(val), end);
		}

		public void addAtIndex(int index, int val) {
			if (index == 0) {
				addAtHead(val);
				return;
			}
			if (index == n) {
				addAtTail(val);
				return;
			}
			Node curr = start.next;
			while (index > 0) {
				curr = curr.next;
				index--;
			}
			addInBetween(curr.prev, new Node(val), curr);
		}

		private void addInBetween(Node prev, Node curr, Node next) {
			n++;
			prev.next = curr;
			curr.prev = prev;
			curr.next = next;
			next.prev = curr;
		}


		public void deleteAtIndex(int index) {
			if (index == 0) {
				n--;
				Node next = start.next;
				start.next = next.next;
				next.next.prev = start;
				return;
			}
			if (index == n - 1) {
				n--;
				Node prev = end.prev;
				end.prev = prev.prev;
				prev.prev.next = end;
				return;
			}
			if (index >= 0 && index < n) {
				Node cur = start.next;
				n--;
				while (index > 0) {
					cur = cur.next;
					index--;
				}
				Node next = cur.next;
				Node prev = cur.prev;
				next.prev = prev;
				prev.next = next;
			}
		}
	}
}
