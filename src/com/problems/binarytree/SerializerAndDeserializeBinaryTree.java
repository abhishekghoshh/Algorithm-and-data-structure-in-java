package com.problems.binarytree;

import com.ds.binarytree.TNode;
import com.util.PrintUtl;

import java.util.LinkedList;
import java.util.Queue;


/*
 * Problem link :
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 * https://neetcode.io/problems/serialize-and-deserialize-binary-tree
 * https://www.naukri.com/code360/problems/920328
 *
 * Solution link :
 * https://www.youtube.com/watch?v=-YbXySKJsX8&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=37
 * https://www.youtube.com/watch?v=u4JAi2JJhI8
 * 
 * https://takeuforward.org/data-structure/serialize-and-deserialize-a-binary-tree/
 * https://neetcode.io/solutions/serialize-and-deserialize-binary-tree
 */
public class SerializerAndDeserializeBinaryTree {

	// TODO this is a very good problem
	public static void main(String[] args) {
		type1();
		type2();
	}

	// TODO we are using preorder as we can get the root at the starting index everytime
	//  for deserializing we are not slitting the string into nodes rather we are working on string directly using one pointer
	//  and everytime we see a space we treat the next number as new node
	private static void type2() {
		TNode root = TNode.withObjectNodes(1, 2, 3, null, null, 4, 5);
		Codec2 codec = new Codec2();
		String data = codec.serialize(root);
		TNode outputNode = codec.deserialize(data);
		System.out.println(data);
		PrintUtl.inOrder(root);
		PrintUtl.inOrder(outputNode);
	}

	static class Codec2 {

		int i = 0;

		public String serialize(TNode root) {
			StringBuilder sb = new StringBuilder();
			preOrder(root, sb);
			return sb.toString();
		}

		void preOrder(TNode root, StringBuilder curr) {
			if (root == null) {
				curr.append('n');
				return;
			}
			curr.append(root.val).append(" ");
			preOrder(root.left, curr);
			curr.append(" ");
			preOrder(root.right, curr);
		}

		public TNode deserialize(String data) {
			i = 0;
			return deserialize(data.toCharArray());
		}

		public TNode deserialize(char[] data) {
			if (i == data.length || data[i] == 'n') return null;
			int start = i;
			// it will go till we see a space which denoted end of the current node
			while (data[i] != ' ') i++;
			int end = i - 1;
			TNode root = new TNode(valueOf(data, start, end));
			i++;// 'i' is currently on space so incrementing i to go to the next node
			root.left = deserialize(data);
			i += 2; // 'i' might be on 'N' that's why the left subtree ended, that's why we are doing i+2 so that it can go after 'N '
			root.right = deserialize(data);
			return root;
		}

		private int valueOf(char[] data, int start, int end) {
			int num = 0;
			int sign = 1;
			if (data[start] == '-') {
				sign = -1;
				start++;
			}
			for (int i = start; i <= end; i++) {
				num = num * 10 + (data[i] - '0');
			}
			return sign * num;
		}
	}


	// todo it is a very good approach Using BFS and not very complex
	// this is done using level order
	private static void type1() {
		TNode root = TNode.withObjectNodes(1, 2, 3, null, null, 4, 5);
		Codec1 codec = new Codec1();
		String data = codec.serialize(root);
		TNode outputNode = codec.deserialize(data);
		System.out.println(data);
		PrintUtl.inOrder(root);
		PrintUtl.inOrder(outputNode);
	}

	static class Codec1 {
		public static final String NULL = "N";

		// we are traversing the nodes in BFS
		// and store the node left and right, but we will also save even if they are null.
		// and in the next iteration when we get the null node from the BFS queue, then
		// we will save N char to the answer
		// because is will be used while deserializing
		// we are saving an almost complete binary tree
		public String serialize(TNode root) {
			if (null == root) return "";
			StringBuilder sb = new StringBuilder();
			Queue<TNode> queue = new LinkedList<>();
			queue.offer(root);
			while (!queue.isEmpty()) {
				TNode node = queue.poll();
				// if the node is null, then we will add N into the answer
				if (null == node) {
					sb.append(NULL).append(" ");
					continue;
				}
				// we will add the data into the answer and the subtrees into the queue
				sb.append(node.val).append(" ");
				queue.offer(node.left);
				queue.offer(node.right);
			}
			return sb.toString();
		}

		// Decodes your encoded data to tree.
		// TODO check it one more time, only the explanation of the video
		public TNode deserialize(String data) {
			if (null == data || data.isEmpty()) return null;
			// now we have all the nodes as a string array
			// we can construct a tree from the node array in many ways.
			// but here we will use a queue
			// we could also create an array to calculate the index of the nodes and then build a tree
			String[] nodes = data.split(" ");
			int n = nodes.length;
			// we will use this queue to get root node, and if it has left and right then we will add them into the queue as well
			Queue<TNode> queue = new LinkedList<>();
			TNode root = new TNode(Integer.parseInt(nodes[0]));
			queue.offer(root);
			int i = 0;
			// 'i' will specify which node we are currently specifying
			// the front of the queue will be root node, everytime we are incrementing by 2, one for left node another for right
			while (i < n && !queue.isEmpty()) {
				TNode node = queue.poll();
				// if left index is present and not equal to N
				int leftI = i + 1;
				if (leftI < n && !NULL.equals(nodes[leftI])) {
					node.left = new TNode(Integer.parseInt(nodes[leftI]));
					queue.offer(node.left);
				}
				// if right index is present and not equal to N
				int rightI = i + 2;
				if (rightI < n && !NULL.equals(nodes[rightI])) {
					node.right = new TNode(Integer.parseInt(nodes[rightI]));
					queue.offer(node.right);
				}
				i += 2; // everytime we will add +2 as everytime we are considering 2 nodes
			}
			return root;
		}
	}

}
