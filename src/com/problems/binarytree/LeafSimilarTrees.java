package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.List;

/*
 * problem links :
 * https://leetcode.com/problems/leaf-similar-trees/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Nr8dbnL0_cM
 *
 * https://neetcode.io/solutions/leaf-similar-trees
 * */
public class LeafSimilarTrees {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        TNode root1 = TNode.withObjectNodes(3, 5, 1, 6, 2, 9, 8, null, null, 7, 4);
        TNode root2 = TNode.withObjectNodes(3, 5, 1, 6, 7, 4, 2, null, null, null, null, null, null, 9, 8);

        boolean ans = leafSimilar(root1, root2);
        System.out.println(ans);
    }


    public static boolean leafSimilar(TNode root1, TNode root2) {
        List<Integer> list1 = traverse(root1, new ArrayList<>());
        List<Integer> list2 = traverse(root2, new ArrayList<>());
        return list1.equals(list2);
    }


    private static List<Integer> traverse(TNode root, List<Integer> list) {
        if (null == root) return list;
        if (root.left == null && root.right == null) {
            list.add(root.val);
        }
        traverse(root.left, list);
        traverse(root.right, list);
        return list;
    }
}
