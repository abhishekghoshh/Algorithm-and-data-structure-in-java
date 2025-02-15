package com.problems.binarytree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * https://www.geeksforgeeks.org/problems/inorder-traversal/1
 * https://www.naukri.com/code360/problems/inorder-traversal_3839605
 *
 * Solution link :
 * https://www.youtube.com/watch?v=80Zug6D1_r4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=38
 *
 * https://takeuforward.org/data-structure/morris-inorder-traversal-of-a-binary-tree/
 */

public class MorrisInorderTraversal {

    // TODO normal inorder traversal using recursion uses a recursion stack
    //  and using iteration uses a normal stack to traverse
    //  in both cases it will take O(n) memory
    //  we can do better, for a inoder traversal the node sequence is left <- root <- right
    //  the recursion stack or the normal stack was used like a pointer that after left the it will come back to the
    //  the root and after root it will go to the right
    //  if some how we could attach the last of of left subtree to root, then our work is done
    //  we can do this with a simple trick, the rightest node of the left subtree is the last node of the left subtree
    //  if we could set that-node.right=root, then our work is done
    public static void main(String[] args) {
        type1();
    }

    // inorder traversal without using any extra space
    // in order we know that after all the left node is completed then root node
    // will be traversed, the last node of the left side will be the rightest node of
    // the left side, so we will create a link between the rightest node and the root
    // and if there is no left, then we will directly add to list and go to right
    private static void type1() {
        TNode root = TNode.withCount(15);
        PrintUtl.inOrder(root);

        List<Integer> answer = inorderTraversal(root);
        System.out.println(answer);
    }

    private static List<Integer> inorderTraversal(TNode root) {
        List<Integer> list = new ArrayList<>();
        while (null != root) {
            // if it has the left node, then we will manipulate the pointers
            if (null != root.left) {
                TNode last = root.left;
                // we will go to the rightest node
                // we have added another extra condition here,
                // let's say we have already established left-last to root.
                // and now we are visiting the root, but again by the code logic, it will again to
                // establish the connection to root-left-last to root
                // so we have to check that if the connection is already there or not
                while (null != last.right && last.right != root)
                    last = last.right;
                // setting the link then, we will go to the left, we know that root will be visited at some point
                if (last.right == null) {
                    // this is the first time we are visiting the node
                    last.right = root;
                    root = root.left;
                } else {
                    // this is the second time we are visiting the node
                    // there was already a link,
                    // that means this is the second time we are in the root.
                    // so the left part is traversed completely,
                    // and we will now delete the link and add the root to list
                    last.right = null;
                    // as this is the inorder traversal, we will add the node when we second time visit the node
                    // that means we have visited the entire left subtree
                    list.add(root.data);
                    root = root.right;
                }
            } else {
                // if there is no left subtree, then it will go directly to the right
                // TODO for the last node of left subtree, there was a link attached from the last-node.right=root
                //  this is the time when we will finally visit the root node
                list.add(root.data);
                root = root.right;
            }
        }
        return list;
    }
}
