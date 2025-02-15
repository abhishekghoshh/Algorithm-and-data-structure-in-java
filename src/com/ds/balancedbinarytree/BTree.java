package com.ds.balancedbinarytree;

/*
 * Problem link :
 *
 *
 * Solution link :
 * https://www.programiz.com/dsa/b-tree
 */
public class BTree {
    private Node root;
    private final int t;

    // Node creation
    public class Node {
        int n = 0;
        int[] key = new int[2 * t - 1];
        Node[] child = new Node[2 * t];
        boolean leaf = true;

        public int Find(int k) {
            for (int i = 0; i < this.n; i++) {
                if (this.key[i] == k) {
                    return i;
                }
            }
            return -1;
        }
    }

    public BTree(int t) {
        this.t = t;
        root = new Node();
        root.n = 0;
        root.leaf = true;
    }

    // Search key
    private Node Search(Node x, int key) {
        int i = 0;
        if (x == null)
            return x;
        for (i = 0; i < x.n; i++) {
            if (key < x.key[i]) {
                break;
            }
            if (key == x.key[i]) {
                return x;
            }
        }
        if (x.leaf) {
            return null;
        } else {
            return Search(x.child[i], key);
        }
    }

}