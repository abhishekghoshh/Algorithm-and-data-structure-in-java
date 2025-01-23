package com.problems.binarytree;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/number-of-nodes_8162204
 *
 * Solution link :
 * https://www.youtube.com/watch?v=_ANrF3FJm7I&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=2
 * https://www.youtube.com/watch?v=ctCpP0RFDFc&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=3
 * https://www.youtube.com/watch?v=hyLyW7rP24I&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=4
 */
public class MaximumNumberOfNodesAtNthLabelInBinaryTree {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        // Given an integer i, the maximum number of nodes on level i of a binary tree.
        int n = 5;
        int maxNodes = 1 << (n - 1);
        System.out.println(maxNodes);
    }
}
