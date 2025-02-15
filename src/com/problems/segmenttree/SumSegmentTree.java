package com.problems.segmenttree;

/*
 * Problem link :
 * 
 * 
 * Solution link :
 * 
 * 
 */
public class SumSegmentTree {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		SegmentTree segmentTree = new SegmentTree(arr);
		System.out.println(segmentTree.sum(0, 9));
		System.out.println(segmentTree.sum(2, 5));
		segmentTree.update(3, 7);
		System.out.println(segmentTree.sum(2, 5));
	}

	public static class SegmentTree {

		int n;
		int arrSize;
		int[] tree;

		public SegmentTree(int[] arr) {
			arrSize = arr.length;
			n = 4 * arrSize + 1;
			tree = new int[n];
			build(arr, tree, 0, arrSize - 1, 0);
		}

		private void build(int[] arr, int[] tree, int treeRangeStart, int treeRangeEnd, int treeIndex) {
			if (treeRangeStart == treeRangeEnd) {
				tree[treeIndex] = arr[treeRangeEnd];
			} else {
				int mid = (treeRangeStart + treeRangeEnd) / 2;
				int leftIndex = 2 * treeIndex + 1, rightIndex = 2 * treeIndex + 2;
				build(arr, tree, treeRangeStart, mid, leftIndex);
				build(arr, tree, mid + 1, treeRangeEnd, rightIndex);
				tree[treeIndex] = tree[leftIndex] + tree[rightIndex];
			}
		}

		public int sum(int start, int end) {
			return sum(start, end, 0, arrSize - 1, 0, tree);
		}

		private int sum(int start, int end, int treeRangeStart, int treeRangeEnd, int treeIndex, int[] tree) {
			if (end < treeRangeStart || treeRangeEnd < start) {
				return 0;
			} else if (start <= treeRangeStart && treeRangeEnd <= end) {
				return tree[treeIndex];
			} else {
				int mid = (treeRangeStart + treeRangeEnd) / 2;
				int leftIndex = 2 * treeIndex + 1, rightIndex = 2 * treeIndex + 2;
				int leftSum = sum(start, end, treeRangeStart, mid, leftIndex, tree);
				int rightSum = sum(start, end, mid + 1, treeRangeEnd, rightIndex, tree);
				return leftSum + rightSum;
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
				if (index <= mid)
					update(index, num, treeRangeStart, mid, leftIndex, tree);
				else
					update(index, num, mid + 1, treeRangeEnd, rightIndex, tree);
				tree[treeIndex] = tree[leftIndex] + tree[rightIndex];
			}
		}
	}
}
