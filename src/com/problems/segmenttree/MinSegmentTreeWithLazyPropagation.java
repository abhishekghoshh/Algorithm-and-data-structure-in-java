package com.problems.segmenttree;

/*
 * Problem link :
 *
 * Solution link :
 *
 */
public class MinSegmentTreeWithLazyPropagation {

	public static void main(String[] args) {
		type1();

	}

	// todo check the lazy sum propagation for better understanding
	private static void type1() {
		// for range minimum query
		// instead of tree[index] += (high - low + 1) * val;
		// the tree[index] will be updated by val only
		// tree[index] += val;
		int[] arr = { 1, 3, 1, 2, 5 };
		SegmentTree segmentTree = new SegmentTree(arr);
		System.out.println(segmentTree.min(0, arr.length - 1));
		System.out.println(segmentTree.min(0, 2));
	}

	public static class SegmentTree {
		int n;
		int[] tree;
		int[] lazy;
		int arrSize;

		public SegmentTree(int[] arr) {
			arrSize = arr.length;
			n = 4 * arrSize;
			tree = new int[n];
			lazy = new int[n];
			build(arr, tree, 0, arr.length - 1, 0);
		}

		private void build(int[] arr, int[] tree, int left, int right, int index) {
			if (left == right) {
				tree[index] = arr[left];
			} else {
				int mid = (left + right) / 2;
				build(arr, tree, left, mid, 2 * index + 1);
				build(arr, tree, mid + 1, right, 2 * index + 2);
				tree[index] = Math.min(tree[2 * index + 1], tree[2 * index + 2]);
			}
		}

		public int min(int left, int right) {
			return min(tree, 0, arrSize - 1, left, right, 0);
		}

		private int min(int[] tree, int low, int high, int left, int right, int index) {
			// update the previous remaining update and propagate that
			// later we can do the current operation
			if (lazy[index] != 0) {
				// if the both child is updated by some value then the root node also updated by
				// the same value
				tree[index] += lazy[index];
				// if low!=high then it has children so we can propagate the addition down
				if (low != high) {
					lazy[2 * index + 1] += lazy[index];
					lazy[2 * index + 2] += lazy[index];
				}
				lazy[index] = 0;
			}

			// our main query operation
			if (low > right || high < left) {
				return 0;
			} else if (low >= left && high <= right) {
				return tree[index];
			} else {
				int mid = low + (high - low) / 2;
				int leftMin = min(tree, low, mid, left, right, 2 * index + 1);
				int rightMin = min(tree, mid + 1, high, left, right, 2 * index + 2);
				return Math.min(leftMin, rightMin);
			}
		}

		public void update(int i, int num) {
			update(tree, i, num, 0, n - 1, 0);
		}

		private void update(int[] tree, int index, int num, int treeRangeStart, int treeRangeEnd, int treeIndex) {
			if (treeRangeStart == treeRangeEnd) {
				tree[treeIndex] = num;
			} else {
				int mid = treeRangeStart + (treeRangeEnd - treeRangeStart) / 2;
				int leftIndex = 2 * treeIndex + 1, rightIndex = 2 * treeIndex + 2;
				if (index <= mid)
					update(tree, index, num, treeRangeStart, mid, leftIndex);
				else
					update(tree, index, num, mid + 1, treeRangeEnd, rightIndex);
				tree[treeIndex] = Math.min(tree[leftIndex], tree[rightIndex]);
			}
		}

		// update the node when you visit it
		// propagate the update to downwards
		// only for the complete overlap we will change the node and propagate
		public void update(int left, int right, int addition) {
			updateAndPropagate(tree, 0, arrSize - 1, left, right, 0, addition);
		}

		public void updateAndPropagate(int[] tree, int treeRangeStart, int treeRangeEnd, int start, int end, int treeIndex, int addition) {
			int leftIndex = 2 * treeIndex + 1, rightIndex = 2 * treeIndex + 2;
			// update the previous remaining update and propagate that later we can update the current update
			if (lazy[treeIndex] != 0) {
				// if the both children are updated by some value, then the root node also updated by
				// the same value
				tree[treeIndex] += lazy[treeIndex];
				// if low!=high then it has children, so we can propagate the addition down
				if (treeRangeStart != treeRangeEnd) {
					lazy[leftIndex] += lazy[treeIndex];
					lazy[rightIndex] += lazy[treeIndex];
				}
				lazy[treeIndex] = 0;
			}
			// actual addition for current addition
			if (treeRangeStart > end || treeRangeEnd < start) {
				// no overlap
				return;
			} else if (treeRangeStart >= start && treeRangeEnd <= end) {
				// complete overlap
				// update the current node by the addition
				tree[treeIndex] += addition;
				// if the node is responsible for more than one node
				// so can propagate the addition
				if (treeRangeStart != treeRangeEnd) {
					lazy[leftIndex] += addition;
					lazy[rightIndex] += addition;
				}
			} else {
				int mid = treeRangeStart + (treeRangeEnd - treeRangeStart) / 2;
				updateAndPropagate(tree, treeRangeStart, mid, start, end, leftIndex, addition);
				updateAndPropagate(tree, mid + 1, treeRangeEnd, start, end, rightIndex, addition);
				tree[treeIndex] = Math.min(tree[leftIndex], tree[rightIndex]);
			}
		}
	}
}
