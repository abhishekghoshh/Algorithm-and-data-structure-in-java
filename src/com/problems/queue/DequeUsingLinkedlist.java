package com.problems.queue;

/*
 * Problem link :
 *
 * Solution link :
 *
 *
 * */
public class DequeUsingLinkedlist {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        DeQueue<Integer> queue = new DeQueue<>();
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

    public static class DeQueue<T> {
        private final Node<T> start;
        private final Node<T> last;
        private int size = 0;

        private static class Node<T> {
            T data;
            Node<T> next;
            Node<T> prev;

            Node(T data) {
                this.data = data;
                this.next = null;
                this.prev = null;
            }
        }

        DeQueue() {
            start = new Node<>(null);
            last = new Node<>(null);
            start.next = last;
            last.prev = start;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(T item) {
            offerLast(item);
        }

        public void offerFirst(T item) {
            size++;
            Node<T> node = new Node<>(item);
            node.prev = start;
            node.next = start.next;
            start.next.prev = node;
            start.next = node;
        }

        public void offerLast(T item) {
            size++;
            Node<T> node = new Node<>(item);
            node.next = last;
            node.prev = last.prev;
            last.prev.next = node;
        }

        public T poll() {
            return pollFirst();
        }

        public T pollFirst() {
            if (isEmpty()) throw new UnsupportedOperationException("No element present for popping");
            size--;
            Node<T> node = start.next;
            start.next = node.next;
            node.next.prev = start;
            return node.data;
        }

        public T pollLast() {
            if (isEmpty()) throw new UnsupportedOperationException("No element present for popping");
            size--;
            Node<T> node = last.prev;
            node.prev.next = last;
            last.prev = node.prev;
            return node.data;
        }

        public T peek() {
            return peekFirst();
        }

        public T peekFirst() {
            if (isEmpty()) throw new UnsupportedOperationException("No element present for peeking");
            return start.next.data;
        }

        public T peekLast() {
            if (isEmpty()) throw new UnsupportedOperationException("No element present for peeking");
            return last.prev.data;
        }
    }
}
