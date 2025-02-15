package com.ds.queue;

import com.ds.linkedlist.LinkedList;

public class LinkedListQueue<T> implements Iterable<T>, Queue<T> {

    private final LinkedList<T> list = new LinkedList<T>();

    public LinkedListQueue() {
    }

    public LinkedListQueue(T firstElem) {
        offer(firstElem);
    }

    // Return the size of the queue
    public int size() {
        return list.size();
    }

    // Returns whether the queue is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Peek the element at the front of the queue
    // The method throws an error is the queue is empty
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return list.peekFirst();
    }

    // Poll an element from the front of the queue
    // The method throws an error is the queue is empty
    public T poll() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return list.removeFirst();
    }

    // Add an element to the back of the queue
    public void offer(T elem) {
        list.addLast(elem);
    }

    // Return an iterator to allow the user to traverse
    // through the elements found inside the queue
    @Override
    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }
}
