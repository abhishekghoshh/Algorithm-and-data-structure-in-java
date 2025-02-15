package com.problems.binarytree;

import com.ds.binarytree.TNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 * Solution link :
 */
public class BinaryTreePaths {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        TNode root = TNode.withCount(5);
        List<String> ans = binaryTreePaths(root);
        System.out.println(ans);
    }

    public static List<String> binaryTreePaths(TNode root) {
        List<String> ans = new ArrayList<>();
        binaryTreePaths(root, new ArrayList<>(), ans);
        return ans;
    }

    private static void binaryTreePaths(TNode root, List<Integer> list, List<String> ans) {
        if (null == root) return;
        // adding the node
        list.add(root.data);
        if (null == root.left && null == root.right) {
            // preparing the string
            StringBuilder sb = new StringBuilder();
            for (int item : list) sb.append(item).append("->");
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            // add it to the ans
            ans.add(sb.toString());
        }
        // go to the left and right
        if (null != root.left) binaryTreePaths(root.left, list, ans);
        if (null != root.right) binaryTreePaths(root.right, list, ans);
        // removing the item
        list.remove(list.size() - 1);
    }
}
