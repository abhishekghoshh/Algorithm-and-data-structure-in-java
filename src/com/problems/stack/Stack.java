package com.problems.stack;

import java.util.ArrayList;
import java.util.List;

import static com.util.PrintUtl.printException;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/stack-implementation-using-array_3210209
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=GYptUgnIM_I
 * https://www.youtube.com/watch?v=P1bAPZg5uaE&list=PL_z_8CaSLPWdeOezg68SKkeLN4-T_jNHd
 *
 *
 * https://takeuforward.org/data-structure/implement-stack-using-array/
 * */
public class Stack {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		StackUsingDynamicArray<Integer> stack = new StackUsingDynamicArray<>(3);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		printException(() -> stack.push(4));
		System.out.println(stack.peek() + " " + stack.pop());
		System.out.println(stack.peek() + " " + stack.pop());
		System.out.println(stack.peek() + " " + stack.pop());
		System.out.println(stack.peek() + " " + stack.pop());
		printException(() -> System.out.println(stack.peek()));
		printException(() -> System.out.println(stack.pop()));
	}

	public static class StackUsingDynamicArray<T> {
		private static final int DEFAULT_CAPACITY = 100;
		private final List<T> array;
		private int size = 0;

		StackUsingDynamicArray() {
			this(DEFAULT_CAPACITY);
		}

		StackUsingDynamicArray(int initialCapacity) {
			this.array = new ArrayList<>(initialCapacity);
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public int size() {
			return size;
		}

		public void push(T item) {
			size++;
			array.add(item);
		}

		public T pop() {
			if (size == 0) throw new UnsupportedOperationException("No element present for popping");
			return array.remove(--size);
		}

		public T peek() {
			if (size == 0) throw new UnsupportedOperationException("No element present for peeking");
			return array.get(size - 1);
		}
	}

	private static void type1() {
		StackUsingArray<Integer> stack = new StackUsingArray<>(3);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		printException(() -> stack.push(4));
		System.out.println(stack.peek() + " " + stack.pop());
		System.out.println(stack.peek() + " " + stack.pop());
		System.out.println(stack.peek() + " " + stack.pop());
		printException(() -> System.out.println(stack.peek()));
		printException(() -> System.out.println(stack.pop()));
	}

	public static class StackUsingArray<T> {
		private static final int DEFAULT_CAPACITY = 100;
		private final T[] array;
		private final int capacity;
		private int size = 0;

		StackUsingArray() {
			this(DEFAULT_CAPACITY);
		}

		@SuppressWarnings("unchecked")
		StackUsingArray(int capacity) {
			this.capacity = capacity;
			this.array = (T[]) new Object[capacity];
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public int size() {
			return size;
		}

		public void push(T item) {
			if (size == capacity) throw new UnsupportedOperationException("Capacity is full");
			array[size++] = item;
		}

		public T pop() {
			if (size == 0) throw new UnsupportedOperationException("No element present for popping");
			return array[--size];
		}

		public T peek() {
			if (size == 0) throw new UnsupportedOperationException("No element present for peeking");
			return array[size - 1];
		}

	}


}
