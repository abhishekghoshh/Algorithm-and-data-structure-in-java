package com.ds.linkedlist;

public class Node {
    public int data;
    public int val;
    public Node next = null;
    public Node bottom = null;
    public Node random = null;


    public Node() {
        this.val = this.data = 0;
        this.next = null;
    }

    public Node(int data) {
        this.val = this.data = data;
        this.next = null;
    }

    public Node(int data, Node next) {
        this.val = this.data = data;
        this.next = next;
    }


    public Node(int val, int... others) {
        Node node = this;
        this.val = this.data = val;
        for (int data : others) {
            Node newNode = new Node(data);
            node.next = newNode;
            node = newNode;
        }
    }

    public Node next(Node node) {
        this.next = node;
        return this;
    }

    public Node chain(Node node) {
        Node curr = this;
        while (curr.next != null) curr = curr.next;
        curr.next = node;
        return node;
    }

    public Node last(Node node) {
        Node curr = this;
        while (curr.next != null) curr = curr.next;
        curr.next = node;
        return this;
    }

    public Node bottom(int... datas) {
        Node node = this;
        for (int data : datas) {
            node.bottom = new Node(data);
            node = node.bottom;
        }
        return this;
    }


    public static Node attach(Node node, Node... nodes) {
        Node head = node;
        Node last = node;
        while (last.next != null) last = last.next;
        for (Node start : nodes) {
            last.next = start;
            while (last.next != null) last = last.next;
        }
        return head;
    }

    @Override
    public String toString() {
        return "" + data;
    }
}
