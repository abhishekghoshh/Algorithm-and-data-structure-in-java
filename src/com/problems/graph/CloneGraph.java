package com.problems.graph;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/clone-graph/description/
 *
 * Solution link :
 */
public class CloneGraph {
    // todo this problem is very hard to test in local
    //  but both the problem accepted in the leetcode
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // instead of map we will use an array
    // exactly like the previous type
    private static void type4() {
    }

    public static Node cloneGraph4(Node node) {
        if (node == null) return null;
        Node[] map = new Node[101];
        return dfs(node, map);
    }

    private static Node dfs(Node node, Node[] map) {
        // if we have already computed the node then we will return it
        if (map[node.val] != null) return map[node.val];
        Node newNode = new Node(node.val);
        map[newNode.val] = newNode;
        // iterating over all the neighbor nodes of the actual node
        // and find the new neighbor nodes
        for (Node neighbor : node.neighbors) {
            Node newNeighbor = dfs(neighbor, map);
            newNode.neighbors.add(newNeighbor);
        }
        return newNode;
    }


    // we have used DFS here
    // this is simplest of all the previous approaches
    // we are also using a map to store the new nodes with the corresponding values
    private static void type3() {
    }

    public static Node cloneGraph3(Node node) {
        if (node == null) return null;
        Map<Integer, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private static Node dfs(Node node, Map<Integer, Node> map) {
        // if we have already computed the node then we will return it
        if (map.containsKey(node.val)) return map.get(node.val);
        Node newNode = new Node(node.val);
        map.put(newNode.val, newNode);
        // iterating over all the neighbor nodes of the actual node
        // and find the new neighbor nodes
        for (Node neighbor : node.neighbors) {
            Node newNeighbor = dfs(neighbor, map);
            newNode.neighbors.add(newNeighbor);
        }
        return newNode;
    }

    // same as the previous but here we have use integer array as map
    private static void type2() {
    }

    public static Node cloneGraph2(Node root) {
        if (null == root) return null;
        Queue<Node> queue = new LinkedList<>();
        // we will use a map to mark if the new nodes are already created and added to queue or not
        Node[] newNodes = new Node[101];
        // we will start with the root
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // if already computed then we will skip
            if (newNodes[node.val] != null) continue;
            List<Node> neighbors = node.neighbors;
            // we will add the nodes and add to the queue
            Node newNode = new Node(node.val, (ArrayList<Node>) neighbors);
            newNodes[node.val] = newNode;
            // adding neighbors to the queue
            if (null == neighbors || neighbors.isEmpty()) continue;
            for (Node neighbor : neighbors)
                if (newNodes[neighbor.val] == null)
                    queue.offer(neighbor);
        }
        // iterating over the map and not we will compute all the neighbors from the map
        for (Node node : newNodes) {
            if (null == node || null == node.neighbors || node.neighbors.isEmpty()) continue;
            ArrayList<Node> neighbors = new ArrayList<>();
            for (Node neighbor : node.neighbors)
                neighbors.add(newNodes[neighbor.val]);
            node.neighbors = neighbors;
        }
        return newNodes[root.val];
    }

    // we will use BFS
    // we have used a queue to traverse all the nodes
    // we have used a map to store all the new nodes
    // after the nodes are stored then we have again iterated on the new nodes
    // and set the new adjacent nodes from the newNodes map
    private static void type1() {
    }

    public static Node cloneGraph1(Node root) {
        if (null == root) return null;
        Queue<Node> queue = new LinkedList<>();
        // we will use a map to mark if the new nodes are already created and added to queue or not
        Map<Integer, Node> newNodes = new HashMap<>();
        // we will start with the root
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // if already computed then we will skip
            if (newNodes.containsKey(node.val)) continue;
            List<Node> neighbors = node.neighbors;
            // we will add the nodes and add to the queue
            Node newNode = new Node(node.val, (ArrayList<Node>) neighbors);
            newNodes.put(node.val, newNode);
            // adding neighbors to the queue
            if (null == neighbors || neighbors.isEmpty()) continue;
            for (Node neighbor : neighbors)
                if (!newNodes.containsKey(neighbor.val))
                    queue.offer(neighbor);
        }
        // iterating over the map and not we will compute all the neighbors from the map
        for (Map.Entry<Integer, Node> entry : newNodes.entrySet()) {
            Node node = entry.getValue();
            ArrayList<Node> neighbors = new ArrayList<>();
            if (null == node.neighbors || node.neighbors.isEmpty()) continue;
            for (Node neighbor : node.neighbors)
                neighbors.add(newNodes.get(neighbor.val));
            node.neighbors = neighbors;
        }
        return newNodes.get(root.val);
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
