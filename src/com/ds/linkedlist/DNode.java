package com.ds.linkedlist;

public class DNode {
    public int data;
    public DNode next;
    public DNode prev;

    public DNode() {

    }
    public DNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public DNode(int data, DNode next) {
        this.data = data;
        this.next = next;
        this.prev = next;
    }

    public DNode(int data, int... others) {
        DNode prev = this;
        this.data = data;
        for (int num : others) {
            DNode current = new DNode(num);
            current.prev = prev;
            prev.next = current;
            prev = current;
        }
    }

    public DNode next(DNode node) {
        this.next = node;
        node.prev = this;
        return this;
    }
}
