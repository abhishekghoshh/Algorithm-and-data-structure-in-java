package com.problems.queue;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/implement-queue-using-arrays_8390825
 *
 * Solution link :
 * https://www.youtube.com/watch?v=M6GnoUDpqEE&t=1s
 *
 * https://takeuforward.org/data-structure/implement-queue-using-array/
 * */
public class Queue {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		QueueUsingArray<Integer> queue = new QueueUsingArray<>(6);
		queue.offer(4);
		queue.offer(14);
		queue.offer(24);
		queue.offer(34);
		System.out.println("The peek of the queue before deleting any element " + queue.peek());
		System.out.println("The size of the queue before deletion " + queue.size());
		System.out.println("The first element to be deleted " + queue.poll());
		System.out.println("The peek of the queue after deleting an element " + queue.peek());
		System.out.println("The size of the queue after deleting an element " + queue.size());
	}

	public static class QueueUsingArray<T> {
		private final T[] array;
		private int front;
		private int rear;
		private int size;
		private final int capacity;

		@SuppressWarnings("unchecked")
		QueueUsingArray(int capacity) {
			this.capacity = capacity;
			array = (T[]) new Object[capacity];
			front = -1;
			rear = -1;
			size = 0;
		}

		public void offer(T item) {
			if (size == capacity) {
				System.out.println("Queue is full");
				return;
			}
			if (rear == -1) front = rear = 0;
			else rear = (rear + 1) % capacity;
			array[rear] = item;
			System.out.println("The element pushed is " + item);
			size++;
		}

		public T poll() {
			if (front == -1) {
				System.out.println("Queue Empty");
				return null;
			}
			T popped = array[front];
			if (size == 1) front = rear = -1;
			else front = (front + 1) % capacity;
			size--;
			return popped;
		}

		public T peek() {
			if (front == -1) {
				System.out.println("Queue is Empty");
				System.exit(1);
			}
			return array[front];
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}
}
