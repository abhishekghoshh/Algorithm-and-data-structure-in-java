package com.problems.segmenttree;

/*
 * Problem link :
 * 
 * 
 * Solution link :
 * 
 * 
 */
public class MaxSegmentTree {

	// if you understand min segment tree then it is exactly same
	// just in place of min we have to do max
	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		SegmentTree segmentTree = new SegmentTree(arr);
		System.out.println(segmentTree.max(0, 9));
		System.out.println(segmentTree.max(2, 5));
		segmentTree.update(3, 7);
		System.out.println(segmentTree.max(2, 5));
	}

	public static class SegmentTree {
		int n;
		int[] tree;
		int arrSize;

		public SegmentTree(int[] arr) {
			arrSize = arr.length;
			n = 4 * arrSize;
			tree = new int[n];
			build(arr, tree, 0, arrSize - 1, 0);
		}

		private void build(int[] arr, int[] tree, int left, int right, int index) {
			if (left == right) {
				tree[index] = arr[left];
			} else {
				int mid = (left + right) / 2;
				int leftIndex = 2 * index + 1;
				build(arr, tree, left, mid, leftIndex);
				build(arr, tree, mid + 1, right, 2 * index + 2);
				tree[index] = Math.max(tree[leftIndex], tree[2 * index + 2]);
			}
		}

		public int get(int start, int end) {
			return 0;
		}

		public int max(int start, int end) {
			return max(start, end, 0, arrSize - 1, 0, tree);
		}

		private int max(int start, int end, int treeRangeStart, int treeRangeEnd, int treeIndex, int[] tree) {
			if (end < treeRangeStart || treeRangeEnd < start) {
				return Integer.MIN_VALUE;
			} else if (start <= treeRangeStart && treeRangeEnd <= end) {
				return tree[treeIndex];
			} else {
				int leftIndex = 2 * treeIndex + 1, rightIndex = 2 * treeIndex + 2;
				int mid = (treeRangeStart + treeRangeEnd) / 2;
				int leftmax = max(start, end, treeRangeStart, mid, leftIndex, tree);
				int rightMax = max(start, end, mid + 1, treeRangeEnd, rightIndex, tree);
				return Math.max(leftmax, rightMax);
			}
		}

		public void update(int index, int num) {
			update(index, num, 0, arrSize - 1, 0, tree);
		}

		private void update(int index, int num, int treeRangeStart, int treeRangeEnd, int treeIndex, int[] tree) {
			if (treeRangeStart == treeRangeEnd) {
				tree[treeIndex] = num;
			} else {
				int mid = (treeRangeStart + treeRangeEnd) / 2;
				int leftIndex = 2 * treeIndex + 1, rightIndex = 2 * treeIndex + 2;
				if (index < mid)
					update(index, num, treeRangeStart, mid, leftIndex, tree);
				else
					update(index, num, mid + 1, treeRangeEnd, rightIndex, tree);
				tree[treeIndex] = Math.max(tree[leftIndex], tree[rightIndex]);
			}
		}
	}

}
