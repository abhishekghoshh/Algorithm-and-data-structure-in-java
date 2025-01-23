package com.problems.queue;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/implement-queue-using-linked-list_8161235
 *
 * Solution link :
 *
 *
 * https://takeuforward.org/data-structure/implement-queue-using-linked-list/
 * */
public class QueueUsingLinkedlist {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {

    }

    static class Node {
        public int data;
        public Node next;

        Node() {
            this.data = 0;
            this.next = null;
        }

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static class Queue {
        Node front;
        Node rear;
        private int size = 0;

        Queue() {
            front = rear = null;
        }

        public void push(int item) {
            size++;
            Node node = new Node(item);
            if (front == null) front = rear = node;
            else {
                rear.next = node;
                rear = node;
            }
        }

        public int pop() {
            if (size == 0) return -1;
            size--;
            Node node = front;
            if (front == rear) front = rear = null;
            else front = node.next;
            return node.data;
        }
    }

    private static void type1() {
        QueueUsingLinkedList<Integer> queue = new QueueUsingLinkedList<>();
        queue.offer(7);
        queue.offer(14);
        queue.offer(24);
        queue.offer(34);
        System.out.println("The peek of the queue before deleting any element " + queue.peek());
        System.out.println("The size of the queue before deletion " + queue.size());
        System.out.println("The first element to be deleted " + queue.poll());
        System.out.println("The peek of the queue after deleting an element " + queue.peek());
        System.out.println("The size of the queue after deleting an element " + queue.size());
    }

    public static class QueueUsingLinkedList<T> {
        private Node<T> front;
        private Node<T> rear;
        private int size = 0;

        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        QueueUsingLinkedList() {
            front = rear = null;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(T item) {
            size++;
            Node<T> node = new Node<>(item);
            if (front == null) front = rear = node;
            else {
                rear.next = node;
                rear = node;
            }
        }

        public T poll() {
            if (size == 0) throw new UnsupportedOperationException("No element present for popping");
            size--;
            Node<T> node = front;
            if (front == rear) front = rear = null;
            else front = node.next;
            return node.data;
        }

        public T peek() {
            if (size == 0) throw new UnsupportedOperationException("No element present for peeking");
            return front.data;
        }
    }
}
