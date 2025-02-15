package com.problems.binarytree;

import com.ds.binarytree.TNode;

/*
 * Problem link :
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * https://www.codingninjas.com/codestudio/problems/920541
 * https://www.codingninjas.com/studio/problems/lca-of-binary-tree_920541
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=_-QHfMDde90&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=28
 * 
 * https://takeuforward.org/data-structure/lowest-common-ancestor-for-two-given-nodes/
 */
public class LowestCommonAncestorOfBinaryTree {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// unlike the type1 it is little hard to visualize
	// but, it will cater all the cases
	private static void type2() {
		TNode root = TNode.withCount(100);
		TNode p = root.search(20);
		TNode q = root.search(40);

		TNode node = lca2(root, p, q);
		System.out.println(node.data);
	}

	// it is straightforward to visualize if both nodes are in child nodes of some node.
	// like p <- left <- left <- [node] -> right -> q,
	// but it is hard to visualize for the arrangement like
	// p -> right -> q
	// in that time p will be the answer,
	// so the recursion will stop once it finds the p node.
	// which eventually will be our answer
	// TODO visualize it another time
	public static TNode lca2(TNode root, TNode p, TNode q) {
		// unlike another traversal,
		// it will go to the bottom till one of the nodes is found
		if (root == null || root == p || root == q) return root;
		// we will go left and right to find if there is any node or not
		TNode left = lca2(root.left, p, q);
		TNode right = lca2(root.right, p, q);
		// both left and right are not null, we found our result node,
		// so we will return the root
		if (left != null && right != null) return root;
		// otherwise, we will send the non-null node
		// as it contains the answer
		return (left != null) ? left : right;
	}


	// little naive approach
	private static void type1() {
		TNode root = TNode.withCount(100);
		TNode p = root.search(20);
		TNode q = root.search(40);
		lca1(root, p, q);
		System.out.println(node.data);

	}

	private static TNode node = null;

	// we are using one flag for finding the lowest common ancestor
	// flag value 0 means no node found
	// flag value 1 means one node found
	// flag value 2 means both nodes found
	private static int lca1(TNode root, TNode p, TNode q) {
		// root is null means no nodes are found so returning 0
		if (null == root) return 0;
		// we will start with 0 flagValue, means no node found
		int flag = 0;
		// if one of the nodes is found, then we will increment the flag value
		flag += (root == p || root == q) ? 1 : 0;
		// now we will go to the left and right subtree
		// if that have any node or not
		flag += lca1(root.left, p, q) + lca1(root.right, p, q);
		// if the flag value is 2 and node is still null
		// then it is the lowest common ancestor
		// null check is important otherwise for all the upper level also it will set the node
		if (flag == 2 && null == node) node = root;
		// lastly, we will return the flag value
		return flag;
	}

}
