package com.problems.heap;

import com.ds.heap.MaxHeap;
import com.ds.heap.MinHeap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapExample {

	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		// Using a normal array
		int[] array = {10, 5, 7, 30, 9};
		MaxHeap maxHeap = new MaxHeap(array);
		maxHeap.print();
		System.out.println("Head value using peek function: " + maxHeap.extractMax());
		System.out.println("Head value using peek function: " + maxHeap.extractMax());
		System.out.println("Head value using peek function: " + maxHeap.extractMax());
		maxHeap.insert(9);
		maxHeap.insert(10);
		maxHeap.insert(11);
		maxHeap.print();
		System.out.println("Head value using peek function: " + maxHeap.extractMax());
		System.out.println("Head value using peek function: " + maxHeap.extractMax());

		// Using priority Queue

		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
		priorityQueue.add(10);
		priorityQueue.add(5);
		priorityQueue.add(7);
		priorityQueue.add(30);
		System.out.println("Head value using peek function: " + priorityQueue.peek());
		for (Integer integer : priorityQueue)
			System.out.println(integer);
		System.out.println("poll value using poll function: " + priorityQueue.poll());
		priorityQueue.offer(15);
		System.out.println("Head value using peek function: " + priorityQueue.peek());
		System.out.println("remove value using remove function: " + priorityQueue.remove());
	}

	private static void type1() {

		// Using normal array

		System.out.println("using custom heap");
		int[] array = { 10, 5, 7, 30, 9 };
		MinHeap minHeap = new MinHeap(array);
		minHeap.print();
		System.out.println("Head value using peek function: " + minHeap.extractMin());
		System.out.println("Head value using peek function: " + minHeap.extractMin());
		System.out.println("Head value using peek function: " + minHeap.extractMin());
		minHeap.insert(9);
		minHeap.insert(10);
		minHeap.insert(11);
		minHeap.print();
		System.out.println("Head value using peek function: " + minHeap.extractMin());
		System.out.println("Head value using peek function: " + minHeap.extractMin());

		// Using priority Queue

		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());
		priorityQueue.add(10);
		priorityQueue.add(5);
		priorityQueue.add(7);
		priorityQueue.add(30);
		System.out.println("Head value using peek function: " + priorityQueue.peek());
		for (Integer integer : priorityQueue)
			System.out.println(integer);
		System.out.println("poll value using poll function: " + priorityQueue.poll());
		priorityQueue.offer(15);
		System.out.println("Head value using peek function: " + priorityQueue.peek());
		System.out.println("remove value using remove function: " + priorityQueue.remove());
	}
}
