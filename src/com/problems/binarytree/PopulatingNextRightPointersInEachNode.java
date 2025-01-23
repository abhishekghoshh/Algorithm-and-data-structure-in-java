package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.ds.binarytree.TNode.withCount;
import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 *
 * Solution link :
 */
public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // this is based on the assumption is the tree is a perfect binary tree
    // left height is always bigger or equal to the right height.
    // here we will be doing it in place
    // the intuition is
    // we will go only to the left of the tree
    // and every time we will connect nodes of the next level, so the
    // first connection will be parent.left.next = parent.right
    // but for how to check it for parent.right.next
    // so for that we need parent's sibling nodes left
    // parent's sibling node will be parent.next
    // so parent.right.next will be paren.next.left
    // so we have connected parent's left and right children
    // now we will go to parent's sibling node and will connect all its child nodes
    private static void type2() {
        TNode root = withCount(6);
        root = connect2(root);
        print(root);
    }

    public static TNode connect2(TNode root) {
        if (root == null) return null;
        TNode node = root;
        while (node.left != null) {
            TNode parent = node;
            while (parent != null) {
                parent.left.next = parent.right;
                parent.right.next = (parent.next != null) ? parent.next.left : null;
                parent = parent.next;
            }
            node = node.left;
        }
        return root;
    }

    // we will use a map to store all the nodes in level wise
    // we are using DFS, but we could also use BFS here for level wise traversal
    // and storing the nodes 
    private static void type1() {
        TNode root = withCount(6);
        root = connect1(root);
        print(root);
    }

    public static TNode connect1(TNode root) {
        Map<Integer, List<TNode>> floors = new HashMap<>();
        traverse(root, floors, 0);
        for (Entry<Integer, List<TNode>> levelWiseEntry : floors.entrySet()) {
            List<TNode> rightToLeftNodes = levelWiseEntry.getValue();
            TNode rightMostNode = null;
            for (TNode currentRightNode : rightToLeftNodes) {
                currentRightNode.next = rightMostNode;
                rightMostNode = currentRightNode;
            }
        }
        return root;
    }

    private static void traverse(TNode root, Map<Integer, List<TNode>> floors, int rank) {
        if (null != root) {
            if (!floors.containsKey(rank)) {
                floors.put(rank, new ArrayList<>());
            }
            floors.get(rank).add(root);
            traverse(root.right, floors, rank + 1);
            traverse(root.left, floors, rank + 1);
        }
    }
}
