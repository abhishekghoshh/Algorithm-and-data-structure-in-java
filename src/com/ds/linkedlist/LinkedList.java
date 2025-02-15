package com.ds.linkedlist;

/*
 * Problem link :
 *
 * Solution link :
 *
 *
 */
public class LinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // Internal node class to represent data
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Empty this linked list, O(n)
    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    // Return the size of this linked list
    public int size() {
        return size;
    }

    // Is this linked list empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // Add an element to the tail of the linked list, O(1)
    public void add(T elem) {
        addLast(elem);
    }

    // Add a node to the tail of the linked list, O(1)
    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null);
        } else {
            tail.next = new Node<T>(elem, null);
            tail = tail.next;
        }
        size++;
    }

    // Add an element to the beginning of this linked list, O(1)
    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null);
        } else {
            head = new Node<T>(elem, head);
        }
        size++;
    }

    // Add an element at a specified index
    public void addAt(int index, T data) throws IllegalArgumentException {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal Index");
        } else if (index == 0) {
            addFirst(data);
            return;
        } else if (index == size) {
            addLast(data);
            return;
        }

        Node<T> temp = head;

        for (int i = 0; i < index - 1; i++) temp = temp.next;
        temp.next = new Node<>(data, temp.next);
        // increasing the size
        size++;
    }

    // Check the value of the first node if it exists, O(1)
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    // Check the value of the last node if it exists, O(1)
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }

    // Remove the first value at the head of the linked list, O(1)
    public T removeFirst() {
        // Can't remove data from an empty list
        if (isEmpty()) throw new RuntimeException("Empty list");
        // Extract the data at the head and move
        // the head pointer forwards one node
        T data = head.data;
        head = head.next;
        --size;
        // If the list is empty, set the tail to null
        if (isEmpty()) tail = null;
        // Return the data that was at the first node we just removed
        return data;
    }

    // Remove the last value at the tail of the linked list, O(n)
    public T removeLast() {
        // Can't remove data from an empty list
        if (isEmpty()) throw new RuntimeException("Empty list");
        if (size == 1) return removeFirst();
        // Extract the data at the tail and move, the tail pointer backwards one node
        T data = tail.data;
        Node<T> node = head;
        // at this point, there are at least 2 nodes in the linkedlist,
        // so node.next is not null, the loop will stop at before the last node
        // which will give us the parent node of the tail
        while (node.next.next != null) node = node.next;
        --size;
        tail = node;
        // Do a memory clean of the node that was just removed
        tail.next = null;
        // Return the data that was in the last node we just removed
        return data;
    }

    // Remove a node at a particular index, O(n)
    public T removeAt(int index) {
        // Make sure the index provided is valid
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        int i;
        Node<T> trav = head, parent = head;
        // Search from the front of the list
        for (i = 0; i != index; i++) {
            parent = trav;
            trav = trav.next;
        }
        if (i == 1) return removeFirst();
        if (i == size - 1) return removeLast();
        T data = trav.data;
        parent.next = trav.next;
        return data;
    }

    // Remove a particular value in the linked list, O(n)
    public boolean remove(Object obj) {
        Node<T> trav = head;

        // Support searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
            // Search for a non-null object
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // Find the index of a particular value in the linked list, O(n)
    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;

        // Support searching for null
        if (obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null) {
                    return index;
                }
            }
            // Search for a non-null object
        } else {
            for (; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    // Check is a value is contained within the linked list
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data);
            if (trav.next != null) sb.append(", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        list.addFirst(0);
        System.out.println(list);
        list.addLast(4);
        System.out.println(list);
        list.addAt(0, -1);
        System.out.println(list);
        list.addAt(list.size(), 5);
        System.out.println(list);

        System.out.println(list.indexOf(-100));
    }

}
