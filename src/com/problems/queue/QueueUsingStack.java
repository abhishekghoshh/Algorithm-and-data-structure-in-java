package com.problems.queue;

import java.util.Stack;
/*
 * Problem link :
 * https://leetcode.com/problems/implement-queue-using-stacks/description/
 * https://www.naukri.com/code360/problems/day-25-:-queue-using-stack_799482
 *
 * Solution link :
 * https://www.youtube.com/watch?v=tqQ5fTamIN4
 * https://www.youtube.com/watch?v=3Et9MrMc02A
 * https://www.youtube.com/watch?v=eanwa3ht3YQ
 *
 * https://takeuforward.org/data-structure/implement-queue-using-stack/
 * https://neetcode.io/solutions/implement-queue-using-stacks
 * */
public class QueueUsingStack {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		MyQueue2 queue = new MyQueue2();
		System.out.println("adding 1,2,3,4 to queue");
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		System.out.println("top element " + queue.peek());
		System.out.println("popped element " + queue.pop());
		System.out.println("popped element " + queue.pop());
		System.out.println("popped element " + queue.pop());
		System.out.println("stack is empty " + queue.empty());
		System.out.println("popped element " + queue.pop());
		System.out.println("stack is empty " + queue.empty());
	}

	public static class MyQueue2 {
		Stack<Integer> primary;
		Stack<Integer> secondary;

		public MyQueue2() {
			primary = new Stack<>();
			secondary = new Stack<>();
		}

		// push operation complexity is O(1)
		// we are only pushing
		public void push(int x) {
			primary.push(x);
		}

		// we will only pop from secondary stack
		// if stack is not empty, then we will directly pop from the secondary, else we
		// will add items from the primary to secondary, the overall time complexity is
		// O(2n) for n items, for max of the time pop will take O(1) time but when
		// the stack is empty then only for that time items are being pushed to
		// secondary, and for n items that can be pushed only n times
		public int pop() {
			if (secondary.isEmpty())
				while (!primary.isEmpty())
					secondary.push(primary.pop());
			return secondary.pop();
		}

		// same ad pop method
		public int peek() {
			if (secondary.isEmpty())
				while (!primary.isEmpty())
					secondary.push(primary.pop());
			return secondary.peek();
		}

		public boolean empty() {
			return primary.isEmpty() && secondary.isEmpty();
		}
	}

	private static void type1() {
		MyQueue1 queue = new MyQueue1();
		System.out.println("adding 1,2,3,4 to queue");
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		System.out.println("top element " + queue.peek());
		System.out.println("popped element " + queue.pop());
		System.out.println("popped element " + queue.pop());
		System.out.println("popped element " + queue.pop());
		System.out.println("stack is empty " + queue.empty());
		System.out.println("popped element " + queue.pop());
		System.out.println("stack is empty " + queue.empty());

	}

	public static class MyQueue1 {
		Stack<Integer> primary;
		Stack<Integer> secondary;

		public MyQueue1() {
			primary = new Stack<>();
			secondary = new Stack<>();
		}

		// we will perform 3 steps
		// first we will be pushing all the items to secondary
		// now secondary has all the items but in reverse
		// now push the current item to primary
		// again push all the items from secondary to primary
		// so the current will be sitting at last and previous items order will also be
		// time complexity O(2n)
		// space complexity O(n)
		public void push(int x) {
			while (!primary.isEmpty()) secondary.push(primary.pop());
			secondary.push(x);
			while (!secondary.isEmpty()) primary.push(secondary.pop());
		}

		public int pop() {
			return primary.pop();
		}

		public int peek() {
			return primary.peek();
		}

		public boolean empty() {
			return primary.isEmpty();
		}
	}

}


