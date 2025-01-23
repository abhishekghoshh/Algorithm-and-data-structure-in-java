package com.problems.binarytree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * https://www.geeksforgeeks.org/problems/preorder-traversal/1
 * https://www.naukri.com/code360/problems/preorder-binary-tree_5948
 * https://www.naukri.com/code360/problems/preorder-traversal_3838888
 *
 * Solution link :
 * https://www.youtube.com/watch?v=80Zug6D1_r4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=38
 *
 * https://takeuforward.org/data-structure/morris-preorder-traversal-of-a-binary-tree/
 */
public class MorrisPreorderTraversal {

    // TODO check the explanation from Morris inorder traversal
    public static void main(String[] args) {
        type1();
    }

    // preorder traversal without using any extra space
    // same as inorder, but here we are adding the root at the first time when we are seeing this
    // that means no link is set yet
    private static void type1() {
        TNode root = TNode.withCount(15);
        PrintUtl.preOrder(root);
        List<Integer> answer = preorderTraversal(root);
        System.out.println(answer);
    }

    private static List<Integer> preorderTraversal(TNode root) {
        List<Integer> answer = new ArrayList<>();
        while (null != root) {
            if (null != root.left) {
                TNode last = root.left;
                // like the inorder we are going as a left as possible
                while (null != last.right && last.right != root)
                    last = last.right;
                // we will establish the link from the left subtree to the root
                if (last.right == null) {
                    // this is the first time we are visiting the node
                    last.right = root;
                    // as this is the preorder traversal, we will add the node when we first visit the node
                    answer.add(root.data);
                    root = root.left;
                } else {
                    // this is the second time we are visiting the node
                    last.right = null;
                    root = root.right;
                }
            } else {
                answer.add(root.data);
                root = root.right;
            }
        }
        return answer;
    }

}
